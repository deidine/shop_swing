package com.inventory.tables;

import com.inventory.DAO.CaisseDAO;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

import com.inventory.DAO.ProductDAO;

public class DevisTable extends DefaultTableModel {

    public DefaultTableModel generateTable(String isloan) {
        return TableData.generateTable(this.getRows(isloan), this.getColums());
    }

    public DefaultTableModel generateSearchTable(String txt, String isLoan) {
        return TableData.generateTable(getRowsSearch(txt, isLoan), this.getColums());
    }

    public DefaultTableModel generateSearchDateTable(String start, String end, String isLoan) {
        return TableData.generateTable(this.getRowsSearchDate(start, end, isLoan), this.getColums());
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3; // To change body of generated methods, choose Tools | Templates.
    }

    String getTableName() {
        return "devisdata";
    }

    public String[] getColums() {
        String[] COLUMNS = {
            "idVente", "Date", "Code_Client", "Total_payee", "recu", "retourner", "Vender_par"
        };
        return COLUMNS;

    }

    ResultSet getData(String isloan) {
        CaisseDAO productDAO = new CaisseDAO();

        ResultSet res = productDAO.getSalesDevisInfo(isloan);
        // ResultSetMetaData metaData = res.getMetaData();
        // int colCount = metaData.getColumnCount();
        return res;
    }

    ResultSet getSearchData(String txt, String isLoan) {
        CaisseDAO productDAO = new CaisseDAO();

        ResultSet res = productDAO.getSalesDevisSearchInfo(txt, isLoan);
        // ResultSetMetaData metaData = res.getMetaData();
        // int colCount = metaData.getColumnCount();
        return res;
    }

    ResultSet getSearchByDateData(String start, String end, String isLoan) {
        CaisseDAO productDAO = new CaisseDAO();

        ResultSet res = productDAO.getDateOfSalesDevisSearchInfo(start, end, isLoan);
        // ResultSetMetaData metaData = res.getMetaData();
        // int colCount = metaData.getColumnCount();
        return res;
    }

    public Object[][] getRows(String isloan) {
        ResultSet res = this.getData(isloan);
        ResultSet res2 = this.getData(isloan);

        Object[][] ROWS = TableData.fetchDataQuery(this.getTableName(), res, this.getColumnCount(), new ProductDAO().fetchDataCount3(res2));
        return ROWS;
    }

    public Object[][] getRowsSearch(String txt, String isLoan) {
        ResultSet res = this.getSearchData(txt, isLoan);
        ResultSet res2 = this.getSearchData(txt, isLoan);
        Object[][] ROWS = TableData.fetchDataQuery(this.getTableName(), res, this.getColumnCount(), new ProductDAO().fetchDataCount3(res2));
        return ROWS;
    }

    public Object[][] getRowsSearchDate(String start, String end, String isLoan) {
        ResultSet res = this.getSearchByDateData(start, end, isLoan);
        ResultSet res2 = this.getSearchByDateData(start, end, isLoan);
        Object[][] ROWS = TableData.fetchDataQuery(this.getTableName(), res, this.getColumnCount(), new ProductDAO().fetchDataCount3(res2));
        return ROWS;
    }
}
