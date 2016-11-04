/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Currency;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import parser.xmlReader;

/**
 *
 * @author hamzalaroussi
 */
public class CurrencyFacade
{
    EntityManagerFactory emf;
    private static ArrayList<Currency> cuList = new ArrayList<>();
    Currency cu;
    
    public ArrayList<Currency> getCuList()
    {
        return cuList;
    }
    public CurrencyFacade() {
        emf = Persistence.createEntityManagerFactory("pu_development");

    }
    
    public CurrencyFacade(EntityManagerFactory emf)
  {
    this.emf = emf;
  }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void addCurrency(Currency c) {
      
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    
    public Currency getCurrencyBySymbol(String symbol) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Currency.class, symbol);
        } finally {
            em.close();
        }
    }
    
    public void populateCurrencyDB()
    {
        try
        {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(new xmlReader());
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));
            
        } catch (SAXException | IOException e)
        {
            e.printStackTrace();
        }
        
        System.out.println(getCuList().size());
        for (int i = 0; i < getCuList().size(); i++)
        {
            Currency cu = getCuList().get(i);
            System.out.println(cu.getSymbol());
            addCurrency(cu);
        }
    }
    
    
    
    
    
}
