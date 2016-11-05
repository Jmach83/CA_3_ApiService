/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import security.IUser;
import security.PasswordStorage;

/**
 *
 * @author Bruger
 */
public class UserFacadeTest {
    EntityManagerFactory emf;
    UserFacade instance;
    
    public UserFacadeTest() {
        instance = new UserFacade();
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("@BeforeClass");
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
        System.out.println("@Before setUp");
        HashMap<String, Object> puproperties = new HashMap();
        //puproperties.put("javax.persistence.sql-load-script-source", "scripts/ClearDB.sql");
        Persistence.generateSchema("pu_development", puproperties);
        //Persistence.generateSchema("pu_development", null);
        emf = Persistence.createEntityManagerFactory("pu_development");
        instance.setEmf(emf);
        System.out.println("@Before Users in DataBase: " + instance.getUsers().size());
    }
    
    @After
    public void tearDown() {
        System.out.println("@After tearDown");
        emf.close();
    }

    /**
     * Test of getUserByUserId method, of class UserFacade.
     */
    @Test
    public void testGetUserByUserId() {
        System.out.println("getUserByUserId");
        String id = "admin";
        String expResult = "";
        try {
        IUser user = new User("admin", "test");
        
            expResult = user.getUserName();
        } catch (PasswordStorage.CannotPerformOperationException ex) {
            Logger.getLogger(UserFacadeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        String result = instance.getUserByUserId(id).getUserName();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of addUser method, of class UserFacade.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        List<User> users;
        int expResult = instance.getUsers().size()+1;
        try {
            User user = new User("testuser","test");
            instance.addUser(user);
         } catch (PasswordStorage.CannotPerformOperationException ex) {
            Logger.getLogger(UserFacadeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        users = instance.getUsers();
        int index = users.size()-1;
        System.out.println("index " + index);
        int result = instance.getUsers().size();
        instance.deleteUser("testuser");
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsers method, of class UserFacade.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        List<User> expResult = instance.getUsers();
        assertNotNull(expResult);
    }

    /**
     * Test of deleteUser method, of class UserFacade.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        int expResultListSize = instance.getUsers().size();
        try {
            User user = new User("deleteUser","delete");
            instance.addUser(user);
            System.out.println("Users in DataBase after deleteUser is added: " + instance.getUsers().size());
         } catch (PasswordStorage.CannotPerformOperationException ex) {
            Logger.getLogger(UserFacadeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        String username = "deleteUser";
        User expResult = null;
        User result = instance.deleteUser(username);
        String resultName = result.getUserName();
        String expResultName = username;
        int resultListSize = instance.getUsers().size();
        System.out.println("Users in DataBase after deleteUser is deleted: " + instance.getUsers().size());
        result = (User)instance.getUserByUserId(result.getUserName());
        assertEquals(expResult, result);
        assertEquals(expResultListSize, resultListSize);
        assertEquals(expResultName, resultName);
        
        
    }

    /**
     * Test of authenticateUser method, of class UserFacade.
     */
    @Test
    public void testAuthenticateUser() {
        System.out.println("authenticateUser");
        String userName = "admin";
        String password = "test";
        String expResult = "Admin";
        List<String> roleList = instance.authenticateUser(userName, password);
        String result = roleList.get(0);
        assertEquals(expResult, result);
        
    }
    
}
