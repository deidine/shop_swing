package com.inventory.tables;

import java.sql.ResultSet; 

import javax.swing.table.DefaultTableModel;

import com.inventory.DAO.ProductDAO;

public class PurcahseTable extends DefaultTableModel {
    public PurcahseTable() {

    }
    public int countRecords (){

        return TableData.countRecords(getTableName());
     }
    public DefaultTableModel generateTable(int Limit, int Offset) {
        return TableData.generateTable(this.getRows(Limit, Offset), this.getColums());
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
        return "purchaseinfo";
    }

    public String[] getColums() {
        String[] COLUMNS = {
                "IdAchat", "Code_Produit","Nom_Produit",   "Quentite","Total_payee"
        };
        return COLUMNS;

    }

    ResultSet getData(int Limit, int Offset) {
                ProductDAO productDAO = new ProductDAO();

        
        ResultSet res =  productDAO.getPurchaseInfo(Limit, Offset);
        // ResultSetMetaData metaData = res.getMetaData();
        // int colCount = metaData.getColumnCount();
        return res;
    }

    int getRowsCount() {
        ProductDAO productDAO = new ProductDAO();
        int rowSize = productDAO.fetchDataCount(this.getTableName());
        return rowSize;
    }


    public Object[][] getRows(int Limit, int Offset) {
        ResultSet res=this.getData(Limit, Offset);     
        ResultSet res2=this.getData(Limit, Offset);

        Object[][] ROWS = TableData.fetchDataQuery(this.getTableName(),res , this.getColumnCount(),
                 new ProductDAO().fetchDataCount3(res2));
        return ROWS;
    }
}
