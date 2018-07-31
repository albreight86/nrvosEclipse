/*
 * COPYRIGHT 2017.  Al Breight
 * File:     ProviderMapper.java
 */
package com.arb.db;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProviderMapper  implements RowMapper<Provider>
{
   public Provider mapRow(ResultSet rs, int rowNum) throws SQLException
   {
      Provider provider = new Provider();
      provider.setProviderId(rs.getInt("provider_id"));
      provider.setProviderName(rs.getString("provider_name"));
      provider.setUrl(rs.getString("url"));
      return provider;
   }
}
