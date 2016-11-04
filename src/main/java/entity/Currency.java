/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.Entity;
import java.util.ArrayList;
import javax.persistence.Id;

@Entity
public class Currency
{
    
    @Id
    private String symbol;
    private String description;
    private Double rate; 
    
    
    public Currency()
    {
        
    }
    public Currency( String symbol, String description , Double rate)
            {
                this.symbol = symbol;
                this.description = description;
                this.rate = rate;
            }

    public String getSymbol()
    {
        return symbol;
    }

    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public double getRate()
    {
        return rate;
    }

    public void setRate(Double rate)
    {
        this.rate = rate;
    }

    
}

