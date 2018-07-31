/*
 * COPYRIGHT 2017.
 * File:     SovrnBusiness.java
 */
package com.arb.business;

import com.arb.db.AdSize;
import com.arb.db.AdSizeMapper;
import com.arb.db.Provider;
import com.arb.db.ProviderMapper;
import com.arb.db.ProviderSizeAssoc;
import com.arb.db.ProviderSizeAssocMapper;
import com.arb.db.UserProviderAssoc;
import com.arb.db.UserProviderAssocMapper;
import com.arb.model.AdBid;
import com.arb.model.ProviderBid;
import com.arb.model.Transaction;
import com.arb.model.Transaction.Click;
import com.arb.model.Transactions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class SovrnBusiness
{
   private final static String SQL_USER_PROVIDER_ASSOC = "select * from USER_PROVIDER_ASSOC where user_id = ?";
   private final static String SQL_PROVIDER_SIZE_ASSOC = "select * from PROVIDER_SIZE_ASSOC where provider_id = ?";
   private final static String SQL_AD_SIZE = "select * from AD_SIZE where ad_size_id = ?";

   Queue<Transaction> transactionQueue;
   Map<Integer, Provider> providerMap;
   Set<ProviderBid> providerBidSet;

   //used to simulate the providers' servers
   Random random;

   int staleSeconds;
   @Value("${clickTimer:6600}")
   public void setStaleAfterSeconds(int seconds)
   { System.out.println("clicktimer: " + seconds); staleSeconds = seconds; }

   JdbcTemplate jdbc;
   @Resource
   public void setJdbcTemplate(JdbcTemplate jdbc)
   { this.jdbc = jdbc; }

   RestClient restClient;
   @Resource
   public void setRestClient(RestClient client)
   { this.restClient = client; }

   @PostConstruct
   public void init()
   {
      transactionQueue = new ConcurrentLinkedQueue<Transaction>();

      //this may be re-addressed to handle the situation where the providers can change after this class is instantiated.
      providerMap = getCurrentProviders();

      providerBidSet = new TreeSet<ProviderBid>(new ProviderBid.ProviderBidCompare());

      random = new Random();
   }


   public AdBid retrieveBestBid(int userId, int width, int height)
   {
      providerBidSet.clear();

      List<UserProviderAssoc> userProviders = jdbc.query(SQL_USER_PROVIDER_ASSOC, new Object[]{userId}, new UserProviderAssocMapper());

      for (UserProviderAssoc userProvider : userProviders)
      {
         List<ProviderSizeAssoc> providerSizes = jdbc.query(SQL_PROVIDER_SIZE_ASSOC,
                                                                 new Object[]{userProvider.getProviderId()},
                                                                 new ProviderSizeAssocMapper());
         AdSize adSize;
         int providerId;
         ProviderBid pBid;

         for(ProviderSizeAssoc providerSize : providerSizes)
         {
            adSize = jdbc.queryForObject(SQL_AD_SIZE, new Object[]{providerSize.getAdSizeId()}, new AdSizeMapper());

            if ( !(adSize.getHeight() == height && adSize.getWidth() == width) )
            {
               continue;
            }

            providerId = providerSize.getProviderId();
            pBid = retrieveBidPrice(providerMap.get(providerId).getUrl());
            pBid.setProviderId(providerId);
            providerBidSet.add(pBid);
         }
      }

      if(providerBidSet.size() == 0)
      {
         return AdBid.NULL_ADBID;
      }

      Transaction tran = new Transaction();
      tran.setBids(new ArrayList<ProviderBid>(providerBidSet));
      tran.setUserid(userId);

      ProviderBid winningBid = providerBidSet.iterator().next();
      tran.setWinningPrice(winningBid.getBidprice());
      tran.setWinningProvider(winningBid.getProviderId());
      tran.setTransactionId(UUID.randomUUID().toString());
      tran.setClickResult(Click.REQUEST);

      transactionQueue.add(tran);

      AdBid adBid = new AdBid();
      adBid.setHtml(winningBid.getAdhtml());
      adBid.setTid(tran.getTransactionId());
      return adBid;
   }

   private Map<Integer, Provider> getCurrentProviders()
   {
      String SQL = "select * from PROVIDER";
      //first, get all of the providers.
      List<Provider> allProviders = jdbc.query(SQL, new ProviderMapper());

      // TODO: may replace with stream semantics to make more performant
      Map<Integer, Provider> providerMap = new HashMap<Integer, Provider>();
      for (Provider p : allProviders)
      {
         providerMap.put(p.getProviderId(), p);
      }
      return providerMap;
   }

   public void click(String tid, Integer userId)
   {
      for(Transaction trans : transactionQueue)
      {
         if (! trans.getTransactionId().equals(tid))
         {
            continue;
         }
         if (System.currentTimeMillis() - trans.getCreationTimeStamp() >= staleSeconds)
         {
            System.out.println("setting as stale tid: " + tid);
            trans.setClickResult(Click.STALE);
            return;
         }
         System.out.println("setting as click tid: " + tid);
         trans.setClickResult(Click.CLICK);
      }
   }

   private ProviderBid retrieveBidPrice(String URL)
   {
      //Return random providerBid since we dont have a provider server
      ProviderBid bid = new ProviderBid(random.nextDouble(), "some html" );
      return bid;

        //this would be the real code.
//      ResponseEntity entity = restClient.get("", ProviderBid.class);
//      if(entity == null)
//      {
//         return null;
//      }
//      return (ProviderBid)entity.getBody();
   }

   public Transactions getHistory()
   {
      Transactions actions = new Transactions();
      actions.transactions = new ArrayList<Transaction>(transactionQueue);
      return actions;
   }


}
