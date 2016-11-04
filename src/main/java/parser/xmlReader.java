package parser;


import entity.Currency;
import facades.CurrencyFacade;
import java.io.IOException;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.URL;
import java.util.ArrayList;

public class xmlReader extends DefaultHandler
{
    
    CurrencyFacade cf = new CurrencyFacade();
    
    @Override
    public void startDocument() throws SAXException
    {
        System.out.println("Start Document (Sax-event)");
    }

    @Override
    public void endDocument() throws SAXException
    {
        System.out.println("End Document (Sax-event)");
    }

    @Override // læser for hver linje
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        
            
            if (localName.equals("dailyrates"))
            {
                System.out.println("dailyrates");
                System.out.println(attributes.getValue(0));
                
                
            }
            //System.out.print("[Atribute: NAME: " + attributes.getLocalName(i) + " VALUE: " + attributes.getValue(i) + "] ");
            if (localName.equals("currency") && !attributes.getValue(2).equals("-"))
            {
                
                String symbol = attributes.getValue(0);
                
                String description = attributes.getValue(1);
                Double rate = Double.parseDouble(attributes.getValue(2));
                
                Currency co = new Currency(symbol, description, rate);
                cf.getCuList().add(co);
                System.out.println("size " + cf.getCuList().size());
                System.out.println(symbol);
                System.out.println(description);
                System.out.println(rate);
                

            }

        
        
    }

    public void printObjects()
    {
        System.out.println("Object list");
        for (int i = 0; i < cf.getCuList().size(); i++)
        {   
            System.out.println(cf.getCuList().size());
            System.out.print(cf.getCuList().get(i).getSymbol() + ":");
            System.out.print(cf.getCuList().get(i).getDescription() + ":");
            System.out.print(cf.getCuList().get(i).getRate());
            System.out.println("");
        }
    }
    public static void main(String[] argv)
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
        
    }
}
