/*
 * COPYRIGHT 2017.  Al Breight
 * File:     history.java
 */
package com.arb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlRootElement( name = "history" )
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction
{
   private String transactionId;
   private int userid;
   private List<ProviderBid> bids;
   private double winningPrice;
   private int winningProvider;
   private Click clickResult;

   @XmlTransient
   private long creationTimeStamp;

   public Transaction()
   {
      creationTimeStamp = System.currentTimeMillis();
   }

   public enum Click
   {
      REQUEST,
      CLICK,
      STALE
   }

   public String getTransactionId()
   {
      return transactionId;
   }

   public void setTransactionId(String transactionId)
   {
      this.transactionId = transactionId;
   }

   public int getUserid()
   {
      return userid;
   }

   public void setUserid(int userid)
   {
      this.userid = userid;
   }

   public List<ProviderBid> getBids()
   {
      return bids;
   }

   public void setBids(List<ProviderBid> bids)
   {
      this.bids = bids;
   }

   public double getWinningPrice()
   {
      return winningPrice;
   }

   public void setWinningPrice(double winningPrice)
   {
      this.winningPrice = winningPrice;
   }

   public int getWinningProvider()
   {
      return winningProvider;
   }

   public void setWinningProvider(int winningProvider)
   {
      this.winningProvider = winningProvider;
   }

   public Click getClickResult()
   {
      return clickResult;
   }

   public void setClickResult(Click clickResult)
   {
      this.clickResult = clickResult;
   }

   public long getCreationTimeStamp()
   {
      return creationTimeStamp;
   }
}
