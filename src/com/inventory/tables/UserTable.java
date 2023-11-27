package com.inventory.tables;

import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

import com.inventory.DAO.CustomerDAO;
import com.inventory.DAO.ProductDAO;
import com.inventory.DAO.UserDAO;
import com.inventory.DAO.SupplierDAO;

public class UserTable extends DefaultTableModel {
    public UserTable() {

    }

    public int countRecords (){

        return TableData.countRecords(getTableName());
     }
    public DefaultTableModel generateTable( ) {
        return TableData.generateTable(this.getRows( ), this.getColums());
    }
    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3; // To change body of generated methods, choose Tools | Templates.
    }

    String getTableName() {
        return "users";
    }

    public String[] getColums() {
        String[] COLUMNS = {
            "Code_Vendeur", "Nom_Complet", "Lieu", "Telephone","username","mot de pass","type" ,"peuxVender"       };
        return COLUMNS;

    }

    ResultSet getData( ) {
        UserDAO userDAO = new UserDAO();

        ResultSet res = userDAO.getQueryResult( );
        // ResultSetMetaData metaData = res.getMetaData();
        // int colCount = metaData.getColumnCount();
        return res;
    }

    int getRowsCount() {
        ProductDAO productDAO = new ProductDAO();
        int rowSize = productDAO.fetchDataCount(this.getTableName());
        return rowSize;
    }

 

    public Object[][] getRows( ) {
        ResultSet res=this.getData( );     
        ResultSet res2=this.getData( );

        Object[][] ROWS = TableData.fetchDataQuery(this.getTableName(),res , this.getColumnCount(),
                 new ProductDAO().fetchDataCount3(res2));
        return ROWS;
    }
}
