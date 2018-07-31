/*
 * COPYRIGHT 2017.  Alan R. Breight
 * File:     UserProviderAssocMapper.java
 */
package com.arb.db;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdSizeMapper implements RowMapper<AdSize>
{
      public AdSize mapRow(ResultSet rs, int rowNum) throws SQLException
      {
         AdSize assoc = new AdSize();
         assoc.setAdSizeId(rs.getInt("ad_size_id"));
         assoc.setHeight(rs.getInt("height"));
         assoc.setWidth(rs.getInt("width"));
         return assoc;
      }
}
