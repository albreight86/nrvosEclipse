/*
 * COPYRIGHT 2017.  Alan R. Breight
 * File:     UserProviderAssocMapper.java
 */
package com.arb.db;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProviderSizeAssocMapper implements RowMapper<ProviderSizeAssoc>
{
      public ProviderSizeAssoc mapRow(ResultSet rs, int rowNum) throws SQLException
      {
         ProviderSizeAssoc assoc = new ProviderSizeAssoc();
         assoc.setProviderId(rs.getInt("provider_id"));
         assoc.setAdSizeId(rs.getInt("ad_size_id"));
         assoc.setProviderSizeAssocId(rs.getInt("provider_size_assoc_id"));
         return assoc;
      }
}
