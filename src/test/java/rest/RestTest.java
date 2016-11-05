package rest;

import facades.UserFacade;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.parsing.Parser;
import java.net.MalformedURLException;
import javax.servlet.ServletException;
import org.apache.catalina.LifecycleException;
import static org.hamcrest.Matchers.*;
import test.utils.EmbeddedTomcat;

/**
 *
 * @author miaryvard
 */
public class RestTest
{
    private static EmbeddedTomcat tomcat;
    private static String securityToken;
    
    public RestTest()
    {
    }
    
    private static void login(String role, String password)
    {
        String json = String.format("{username: \"%s\", password: \"%s\"}", role, password);
        System.out.println(json);
        securityToken = given()
                .contentType("application/json")
                .body(json)
                .when().post("/api/login")
                .then()
                .extract().path("token");
        System.out.println("Token: " + securityToken);

    }

    private void logOut()
    {
        securityToken = null;
    }
    
    @BeforeClass
    public static void setUpClass() throws ServletException, MalformedURLException, LifecycleException
    {
        tomcat = new EmbeddedTomcat();
        tomcat.start(9999, "/seedMaven");

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9999;
        RestAssured.basePath = "/seedMaven";
        RestAssured.defaultParser = Parser.JSON;
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        tomcat.stop();
    }
    
    //Virker kun hvis man kører projektet
    @Test
    public void serverIsRunningPort8080()
    {
        given().
                when().get("http://localhost:8080/seedMaven").
                then().
                statusCode(200);
    }
    
    @Test
    public void serverIsRunningPort9999()
    {
        given().
                when().get().
                then().
                statusCode(200);
    }
    
    @Test
    public void testCreateUser()
    {
        System.out.println("Rest Assured - testCreateUser");
        String username = "testCreate";
        String password = "create";
        String json = "{\"username\":\""+username+"\",\"password\":\""+password+"\"}";
        
        given().
                contentType("application/json").
                body(json).
                when().
                post("/api/login/createuser").
                as(User.class);

        UserFacade instance = new UserFacade();
        assertNotNull(instance.authenticateUser(username, password));
        instance.deleteUser(username);
    }

    @Test
    public void testDeleteUser()
    {
        System.out.println("Rest Assured - testCreateUser");

        String username = "testDelete";
        String password = "delete";
        String json = "{\"username\":\"" + username + "\",\"password\":\""+password+"\"}";
        given().
                contentType("application/json").
                body(json).
                when().
                post("/api/login/createuser").
                as(User.class);

        //Skal indeholde noget med authorization
        given().
                contentType("application/json").
                when().
                delete("api/admin/delete/" + username);

        UserFacade instance = new UserFacade();
        assertNull(instance.authenticateUser(username, password));

    }

    

    @Test
    public void testAdminGetUsers()
    {
        login("admin", "test");

        given()
                .contentType("application/json")
                .header("Authorization", "Bearer" + securityToken)
                .when().get("/api/admin/users")
                .then().statusCode(200);
    }
    
    @Test
    public void testCurrencyRates()
    {
        login("user","test");
        double amount = 5;
        String fromcurrency = "AUD", tocurrency = "CNY";
        given()
                .contentType("application/json")
                .header("Authorization", "Bearer" + securityToken)
                .when().get("/api/currency/calculator/"+amount+"/"+fromcurrency+"/"+tocurrency)
                .then().statusCode(200)
                .body("result", equalTo("25,96"));

    }
    
}
