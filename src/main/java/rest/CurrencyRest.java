/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.Currency;
import facades.CurrencyFacade;
import java.util.List;
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

        return new Gson().toJson(c);
    //return "swag";
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("dailyrates")
  public String getRates()
  {
      
      List<Currency> currencies = cf.getCurrency();
      return new Gson().toJson(currencies); 
    
  }
  
  @GET 
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("calculator/{amount}/{fromcurrency}/{tocurrency}")
  public String exchangeRateCal(@PathParam("amount") double amount, @PathParam("fromcurrency") 
                                String fcurrency, @PathParam("tocurrency") String tcurrency) {
      
      double fcur = cf.getCurrencyBySymbol(fcurrency).getRate();
      double tcur = cf.getCurrencyBySymbol(tcurrency).getRate();
      
      double cur = fcur/tcur;
      
      String formel = String.format("%.2f", amount * cur);
      
      JsonObject jsonObj = new JsonObject();
      jsonObj.addProperty("result", formel);
      // double res = fcur * tcur;
      
      return new Gson().toJson(jsonObj);
}
}
