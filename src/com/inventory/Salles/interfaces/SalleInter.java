package com.inventory.Salles.interfaces;

import java.net.URISyntaxException;
import java.util.Date;

public interface SalleInter {

    boolean isCustomerInconu(String code);

    String getCustomerInfo(String code);

    void saveFile() throws URISyntaxException, InterruptedException;

    void devisProduct(boolean isLoan, boolean isTva) throws URISyntaxException, InterruptedException;

    void selleOrloanProduct(boolean isLoan) throws URISyntaxException, InterruptedException;

    void putMoneyInCaisse();

    void setProductTable();

    void validQuentite();

    void reduceQuentite();

    void duplicateProduct();

    void duplicateClient();

    boolean canLoan();

    void totalValue();

    void clear3();

    void clear();

    void clear2();

    String getCureentTime();

    String stringToDaTe(Date s);

    String getCurrentDate();

    void loadSearchDataProduct(String text);

    void isUNKnowedCLient();

    void isKnowedCLient();

    void pushDataSalle();

    void doTheSale();
}
