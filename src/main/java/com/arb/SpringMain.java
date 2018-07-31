package com.arb;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain
{

   public static void main (String[] args)
   {
      new SpringMain();
   }

   public SpringMain()
   {
      // open/read the application context file
      ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

   }

}
