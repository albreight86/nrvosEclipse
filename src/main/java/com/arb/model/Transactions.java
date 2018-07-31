/*
 * COPYRIGHT 2017.  Al Breight
 * File:     histories.java
 */
package com.arb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement( name = "history" )
@XmlAccessorType(XmlAccessType.FIELD)
public class Transactions
{
   public List<Transaction> transactions;
}
