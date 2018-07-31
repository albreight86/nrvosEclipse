/*
 * COPYRIGHT 2017. Al Breight
 * File:     Provider.java
 */
package com.arb.db;

import com.arb.model.ProviderBid;

import java.util.Comparator;

public class Provider {
   private Integer providerId;
   private String providerName;
   private String url;

   public Integer getProviderId()
   { return providerId; }

   public void setProviderId(Integer providerId)
   { this.providerId = providerId; }

   public String getProviderName()
   { return providerName; }

   public void setProviderName(String providerName)
   { this.providerName = providerName; }

   public String getUrl()
   { return url; }

   public void setUrl(String url)
   { this.url = url; }


}
