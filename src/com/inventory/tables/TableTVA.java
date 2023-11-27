package com.inventory.tables;

import com.inventory.DAO.CaisseDAO;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

import com.inventory.DAO.ProductDAO;

public class TableTVA extends DefaultTableModel {

    public TableTVA() {

    }

    public DefaultTableModel generateTable(String isTva) {
        return TableData.generateTable(this.getRows(isTva), this.getColums());
    }

    public DefaultTableModel generateSearchTable(String txt, String isTva) {
        return TableData.generateTable(getRowsSearch(txt, isTva), this.getColums());
    }

    public DefaultTableModel generateSearchDateTable(String start, String end, String isTva) {
        return TableData.generateTable(this.getRowsSearchDate(start, end, isTva), this.getColums());
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3; // To change body of generated methods, choose Tools | Templates.
    }

    String getTableName() {
        return "salledata";
    }

    public String[] getColums() {
        String[] COLUMNS = {
            "idVente", "Date", "Code_Client", "Total_payee", "recu", "retourner", "Vender_par" 
        };
        return COLUMNS;

    }

    ResultSet getData(String isTva) {
        CaisseDAO productDAO = new CaisseDAO();

        ResultSet res = productDAO.getSalesTVAInfo(isTva);
        // ResultSetMetaData metaData = res.getMetaData();
        // int colCount = metaData.getColumnCount();
        return res;
    }

    ResultSet getSearchData(String txt, String isTva) {
        CaisseDAO productDAO = new CaisseDAO();

        ResultSet res = productDAO.getSalesTVASearchInfo(txt, isTva);
        // ResultSetMetaData metaData = res.getMetaData();
        // int colCount = metaData.getColumnCount();
        return res;
    }

    ResultSet getSearchByDateData(String start, String end, String isTva) {
        CaisseDAO productDAO = new CaisseDAO();

        ResultSet res = productDAO.getDateOfSalesTvaSearchInfo(start, end, isTva);
        // ResultSetMetaData metaData = res.getMetaData();
        // int colCount = metaData.getColumnCount();
        return res;
    }

    public Object[][] getRows(String isTva) {
        ResultSet res = this.getData(isTva);
        ResultSet res2 = this.getData(isTva);

        Object[][] ROWS = TableData.fetchDataQuery(this.getTableName(), res, this.getColumnCount(), new ProductDAO().fetchDataCount3(res2));
        return ROWS;
    }

    public Object[][] getRowsSearch(String txt, String isTva) {
        ResultSet res = this.getSearchData(txt, isTva);
        ResultSet res2 = this.getSearchData(txt, isTva);
        Object[][] ROWS = TableData.fetchDataQuery(this.getTableName(), res, this.getColumnCount(), new ProductDAO().fetchDataCount3(res2));
        return ROWS;
    }

    public Object[][] getRowsSearchDate(String start, String end, String isTva) {
        ResultSet res = this.getSearchByDateData(start, end, isTva);
        ResultSet res2 = this.getSearchByDateData(start, end, isTva);
        Object[][] ROWS = TableData.fetchDataQuery(this.getTableName(), res, this.getColumnCount(), new ProductDAO().fetchDataCount3(res2));
        return ROWS;
    }
}
