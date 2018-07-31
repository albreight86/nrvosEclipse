/*
 * COPYRIGHT 2017.  Al Breight
 * File:     UserProviderAssocMapper.java
 */
package com.arb.db;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProviderAssocMapper implements RowMapper<UserProviderAssoc>
{
      public UserProviderAssoc mapRow(ResultSet rs, int rowNum) throws SQLException
      {
         UserProviderAssoc assoc = new UserProviderAssoc();
         assoc.setProviderId(rs.getInt("provider_id"));
         assoc.setUserId(rs.getInt("user_id"));
         assoc.setUserProviderAssocId(rs.getInt("user_provider_assoc_id"));
         return assoc;
      }
}
