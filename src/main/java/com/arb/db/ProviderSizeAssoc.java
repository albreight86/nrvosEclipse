/*
 * COPYRIGHT 2017.  Al Breight
 * File:     UserProviderAssoc.java
 */
package com.arb.db;

public class ProviderSizeAssoc
{
   private int providerId;
   private int adSizeId;
   private int providerSizeAssocId;

   public int getProviderId()
   { return providerId; }

   public void setProviderId(int providerId)
   { this.providerId = providerId; }

   public int getAdSizeId()
   { return adSizeId; }

   public void setAdSizeId(int adSizeId)
   { this.adSizeId = adSizeId; }

   public int getProviderSizeAssocId()
   { return providerSizeAssocId; }

   public void setProviderSizeAssocId(int providerSizeAssocId)
   { this.providerSizeAssocId = providerSizeAssocId; }
}
