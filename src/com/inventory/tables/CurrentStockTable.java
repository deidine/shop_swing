package com.inventory.tables;

import java.sql.ResultSet; 

import javax.swing.table.DefaultTableModel;
 
import com.inventory.DAO.ProductDAO;

public class CurrentStockTable extends DefaultTableModel {
    public CurrentStockTable() {

    }

    public DefaultTableModel generateTable() {
        return TableData.generateTable(this.getRows(), this.getColums());
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
        return "currentstock";
    }

    public String[] getColums() {
        String[] COLUMNS = {
               "Code_Produit","Nom_Produit", "Quentite", "Prix_Achat","Prix_Vende" 
        };
        return COLUMNS;

    }

    ResultSet getData() {
                ProductDAO productDAO = new ProductDAO();

        
        ResultSet res = productDAO.getCurrentStockInfo();
        // ResultSetMetaData metaData = res.getMetaData();
        // int colCount = metaData.getColumnCount();
        return res;
    }

    int getRowsCount() {
        ProductDAO productDAO = new ProductDAO();
        int rowSize = productDAO.fetchDataCount(this.getTableName());
        return rowSize;
    }

    public Object[][] getRows() {
        Object[][] ROWS = TableData.fetchDataQuery(this.getTableName(), this.getData(), this.getColumnCount(), this.getRowsCount());
        return ROWS;
    }
}
