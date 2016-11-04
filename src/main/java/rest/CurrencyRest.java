/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import facades.CurrencyFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("currency")
public class CurrencyRest
{
    
    
  EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_development");
  CurrencyFacade cf = new CurrencyFacade(emf);
    
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("dailyrates/{symbol}")
  public String getCurrency(@PathParam("symbol") String symbol)
  {

        entity.Currency c = cf.getCurrencyBySymbol(symbol);

    //return new Gson().toJson(c);
    return "swag";
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("hello")
  public String bob()
  {

        

    return "helloworld";
  }
}
