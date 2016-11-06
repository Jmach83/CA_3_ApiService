/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Currency;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bruger
 */
public class CurrencyFacadeTest {

    EntityManagerFactory emf;
    CurrencyFacade instance;

    public CurrencyFacadeTest() {
        instance = new CurrencyFacade();
    }

    @BeforeClass
    public static void setUpClass() {
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
    }

    @After
    public void tearDown() {
        System.out.println("@After tearDown");
        emf.close();
    }

    /**
     * Test of getCuList method, of class CurrencyFacade.
     */
    @Test
    public void testGetCuList() {
        System.out.println("getCuList");
        //String expResult = "AUD";
        ArrayList<Currency> cuList = instance.getCuList();
        boolean result = instance.getCuList().isEmpty();
        System.out.println("cuList is empty: " + cuList.isEmpty());
        //String result = cuList.get(0).getSymbol();
        //assertEquals(expResult, result);
        assertTrue(result);
    }

    /**
     * Test of addCurrency method, of class CurrencyFacade.
     */
    @Test
    public void testAddCurrency() {
        System.out.println("addCurrency");
//        Currency c = null;
//        instance.addCurrency(c);
        boolean result = instance.getCurrency().isEmpty();
        assertFalse(result);
    }

    /**
     * Test of getCurrencyBySymbol method, of class CurrencyFacade.
     */
    @Test
    public void testGetCurrencyBySymbol() {
        System.out.println("getCurrencyBySymbol");
        String expResult = "USD";
        Currency result = instance.getCurrencyBySymbol(expResult);
        assertEquals(expResult, result.getSymbol());
    }

    /**
     * Test of getCurrency method, of class CurrencyFacade.
     */
    @Test
    public void testGetCurrency() {
        System.out.println("getCurrency");
        String expResult = "AUD";
        List<Currency> currencyList = instance.getCurrency();
        Currency currency = currencyList.get(0);
        String result = currency.getSymbol();
        assertEquals(expResult, result);
    }

    /**
     * Test of dropCurrencyTable method, of class CurrencyFacade.
     */
    //@Test
    public void testDropCurrencyTable() {
        System.out.println("dropCurrencyTable");
        instance.dropCurrencyTable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of populateCurrencyDB method, of class CurrencyFacade.
     */
    //@Test
    public void testPopulateCurrencyDB() {
        System.out.println("populateCurrencyDB");
        instance.populateCurrencyDB();
    }

}
