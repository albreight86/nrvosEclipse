/*
 * COPYRIGHT 2017.  Al Breight
 * File:     ProviderAdBid.java
 */
package com.arb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Comparator;

@XmlRootElement(name = "ProviderBid")
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(XmlAccessType.FIELD)
public class ProviderBid
{
   @XmlElement( name = "bidprice")
   private double bidprice;
   @XmlElement( name = "adhtml")
   private String adhtml;

   private int providerId;

   public ProviderBid()
   {}

   public ProviderBid(double bidprice, String adhtml)
   {
      this.bidprice = bidprice;
      this.adhtml = adhtml;
   }

   public double getBidprice()
   { return bidprice; }

   public String getAdhtml()
   { return adhtml; }

   public int getProviderId()
   { return providerId; }

   public void setProviderId(int providerId)
   { this.providerId = providerId; }

   public static class ProviderBidCompare implements Comparator<ProviderBid>
   {
      @Override
      public int compare(ProviderBid pbid1, ProviderBid pbid2)
      {
         if (pbid1.getBidprice() > pbid2.getBidprice())
         {
            return 1;
         }
         if (pbid1.getBidprice() < pbid2.getBidprice())
         {
            return -1;
         }
         return 0;
      }
   }
}
