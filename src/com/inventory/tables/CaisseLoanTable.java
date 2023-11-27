package com.inventory.tables;

import com.inventory.DAO.CaisseDAO;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

import com.inventory.DAO.ProductDAO;

public class CaisseLoanTable extends DefaultTableModel {

    public CaisseLoanTable() {

    }

    public DefaultTableModel generateTable( ) {
        return TableData.generateTable(this.getRows(), this.getColums());
    }

    public DefaultTableModel generateSearchTable(String txt ) {
        return TableData.generateTable(getRowsSearch(txt ), this.getColums());
    }
      public DefaultTableModel generateSearchDateTable(String start ,String end ) {
        return TableData.generateTable(this.getRowsSearchDate(start,end ), this.getColums());
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3; // To change body of generated methods, choose Tools | Templates.
    }

    String getTableName() {
        return "payloan";
    }

    public String[] getColums() {
        String[] COLUMNS = {
            "id",  "Code_Client", "Total_payee",   "Vender_par",  "Date" 
        };
        return COLUMNS;

    }

    ResultSet getData() {
        CaisseDAO caisseDAO = new CaisseDAO();

        ResultSet res = caisseDAO.getCaisseLoanInfo();
        // ResultSetMetaData metaData = res.getMetaData();
        // int colCount = metaData.getColumnCount();
        return res;
    }

    ResultSet getSearchData(String txt ) {
        CaisseDAO productDAO = new CaisseDAO();

        ResultSet res = productDAO.getCaisseLoanSearchInfo(txt);
        // ResultSetMetaData metaData = res.getMetaData();
        // int colCount = metaData.getColumnCount();
        return res;
    }

    ResultSet getSearchByDateData(String start,String end ) {
        CaisseDAO productDAO = new CaisseDAO();

        ResultSet res = productDAO.getDateOfCaisseLoanSearchInfo(start,end );
        // ResultSetMetaData metaData = res.getMetaData();
        // int colCount = metaData.getColumnCount();
        return res;
    }

   

    public Object[][] getRows( ) {
             ResultSet res=this.getData( );     
             ResultSet res2=this.getData( );

        Object[][] ROWS = TableData.fetchDataQuery(this.getTableName(),res , this.getColumnCount(), new ProductDAO().fetchDataCount3(res2));
        return ROWS;
    }

    public Object[][] getRowsSearch(String txt) {
           ResultSet res=this.getSearchData(txt );     
           ResultSet res2=this.getSearchData(txt );

        Object[][] ROWS = TableData.fetchDataQuery(this.getTableName(), res, this.getColumnCount(), new ProductDAO().fetchDataCount3(res2));
        return ROWS;
    }  
    public Object[][] getRowsSearchDate(String start,String end ) {
        ResultSet res=this.getSearchByDateData(start,end );
        ResultSet res2=this.getSearchByDateData(start,end );

        Object[][] ROWS = TableData.fetchDataQuery(this.getTableName(),res , this.getColumnCount(), new ProductDAO().fetchDataCount3(res2));
        return ROWS;
    }
}
