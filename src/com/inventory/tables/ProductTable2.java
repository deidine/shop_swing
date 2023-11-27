package com.inventory.tables;

import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

import com.inventory.DAO.CustomerDAO;
import com.inventory.DAO.ProductDAO;
import com.inventory.Database.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductTable2 extends DefaultTableModel {

    Connection conn = null;
    PreparedStatement prepStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    private static PreparedStatement preparedStatement;

    public ProductTable2() {

    }

    public int countRecords() {

        return TableData.countRecords(getTableName());
    }

    public DefaultTableModel generateTable2(String txt) {
        return TableData.generateTable(this.getRows2(txt), this.getColums());
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3; // To change body of generated methods, choose Tools | Templates.
    }

    String getTableName() {
        return "products";
    }
    

    public String[] getColums() {
        String[] COLUMNS = {
            "Code_Produit", "Nom_Produit","Prx_Vente", "quentite"
        };
        return COLUMNS;

    }
 
    ResultSet getData2(String txt) {
        ProductDAO productDAO = new ProductDAO();

        ResultSet res = productDAO.getProductSearch2(txt);
        // ResultSetMetaData metaData = res.getMetaData();
        // int colCount = metaData.getColumnCount();
        return res;
    }

    int getRowsCount(String txt) { 
        ProductDAO productDAO = new ProductDAO();
 
        int rowSize =productDAO.getProdSearch2Count(txt);   
        return rowSize;
    }

    public Object[][] getRows2(String txt) {
        Object[][] ROWS = TableData.fetchDataQuery(this.getTableName(), this.getData2(txt), this.getColumnCount(),
                this.getRowsCount(txt));
        return ROWS;
    }
}
