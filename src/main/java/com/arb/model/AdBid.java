/*
 * COPYRIGHT 2017.  Al Breight
 * File:     AdBid.java
 */
package com.arb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AdBid")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdBid
{
   private String tid;
   private String html;
   public static final AdBid NULL_ADBID = new AdBid();

   public String getTid()
   {
      return tid;
   }

   public void setTid(String tid)
   {
      this.tid = tid;
   }

   public String getHtml()
   {
      return html;
   }

   public void setHtml(String html)
   {
      this.html = html;
   }
}
