/*
 * COPYRIGHT 2017.  Al Breight
 * File:     UserProviderAssoc.java
 */
package com.arb.db;

public class UserProviderAssoc
{
   private int userProviderAssocId;
   private int userId;
   private int providerId;

   public int getUserProviderAssocId()
   { return userProviderAssocId; }

   public void setUserProviderAssocId(int userProviderAssocId)
   { userProviderAssocId = userProviderAssocId; }

   public int getUserId()
   { return userId; }

   public void setUserId(int userId)
   { userId = userId; }

   public int getProviderId()
   { return providerId; }

   public void setProviderId(int providerId)
   { this.providerId = providerId; }

}
