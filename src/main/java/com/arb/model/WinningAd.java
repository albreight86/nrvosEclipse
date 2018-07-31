/*
 * COPYRIGHT 2017.  Al Breight
 * File:     WinningAd.java
 */
package com.arb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "WinningAd")
@XmlAccessorType(XmlAccessType.FIELD)
public class WinningAd
{
   String tid;
   String html;
}
