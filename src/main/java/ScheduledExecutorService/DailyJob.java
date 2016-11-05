/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScheduledExecutorService;

import facades.CurrencyFacade;

/**
 *
 * @author Jmach
 */
public class DailyJob implements Runnable {
    CurrencyFacade cf = new CurrencyFacade();
    
    @Override
    public void run() {
        cf.dropCurrencyTable();
        cf.populateCurrencyDB();
    }

}
