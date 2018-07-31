/*
 * COPYRIGHT 2017.  Al Breight
 * File:     Rest.java
 */

package com.arb;

import com.arb.business.SovrnBusiness;
import com.arb.model.AdBid;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.cxf.annotations.GZIP;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service("sovrnRestService")
@Path("")
@GZIP
public class RestService
{

   private static Response RESPONSE_OK = Response.ok().build();
   private static Response RESPONSE_NO_CONTENT = Response.noContent().build();
   private static Response RESPONSE_BAD_REQUEST = Response.status(Status.BAD_REQUEST).build();

   SovrnBusiness business;

   @Resource
   public void setSovrnBusiness(SovrnBusiness business)
   {
      this.business = business;
   }

   public RestService()
   { }

   @PostConstruct
   public void init()
   { }

   @Path("/ad")
   @GET
   @Produces({MediaType.APPLICATION_JSON})
   public Response getAd(@QueryParam("userId") int userId,
                         @QueryParam("width") int width,
                         @QueryParam("height") int height,
                         @QueryParam("URL") String pageUrl
   )
   {

      System.out.println("userId: " + userId);
      System.out.println("width: " + width);
      System.out.println("height: " + height);
      System.out.println("URL: " + pageUrl);

      if(userId == 0 || width == 0 || height == 0)
      {
        return RESPONSE_BAD_REQUEST;
      }

      AdBid bid = business.retrieveBestBid(userId, width, height);

      return Response.ok(bid).build();
   }

   @Path("/click")
   @POST
   @Produces({MediaType.APPLICATION_JSON})
   public Response click(@QueryParam("tid") String tid, @QueryParam("userId") Integer userId)
   {
      business.click(tid, userId);

      return Response.ok().build();
   }

   @Path("/history")
   @GET
   @Produces({MediaType.APPLICATION_JSON})
   public Response getHistory()
   {
      return Response.ok(business.getHistory()).build();
   }
}


