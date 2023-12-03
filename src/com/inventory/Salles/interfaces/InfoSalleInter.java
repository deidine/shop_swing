package com.inventory.Salles.interfaces;

import java.util.Date;

/**
 *
 * @author deidine
 */
public interface InfoSalleInter {
     String stringToDaTe(Date s) ;
     void loadSearchDataSale(String text);
     String getCureentTime();
     abstract void loadSearchDataSaleByDate(String start, String end);
}
