package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import facades.UserFacade;
import entity.User;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import jsonmappers.UserMapper;

@Path("admin")
@RolesAllowed("Admin")
public class Admin {
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("users")
  public String getUsers() {
    UserFacade uf = new UserFacade();
    List<User> users = uf.getUsers();
    List<UserMapper> uMapped = new ArrayList();
    for(User u : users) {
        uMapped.add(new UserMapper(u));
    }
    return new Gson().toJson(uMapped);
  }
  
  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("delete/{username}")
  public void deleteUser(@PathParam("username") String username) {
    UserFacade uf = new UserFacade();
    uf.deleteUser(username);
  }
 
}
