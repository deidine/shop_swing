package com.inventory.tables;

import java.sql.ResultSet; 

import javax.swing.table.DefaultTableModel;

import com.inventory.DAO.CustomerDAO;
import com.inventory.DAO.ProductDAO;

public class ProductTable extends DefaultTableModel {
    public ProductTable() {

    }
    public int countRecords (){

        return TableData.countRecords(getTableName());
     }
    public DefaultTableModel generateTable(int Limit, int Offset) {
        return TableData.generateTable(this.getRows(Limit, Offset), this.getColums());
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
                 "Code_Produit","Nom_Produit",  "Prix_Achat","quentite","Prix_Vende","Marque"
        };
        return COLUMNS;

    }

    ResultSet getData(int Limit, int Offset) {
                ProductDAO productDAO = new ProductDAO();

        
        ResultSet res = productDAO.getQueryResult(Limit,Offset);
        // ResultSetMetaData metaData = res.getMetaData();
        // int colCount = metaData.getColumnCount();
        return res;
    }

    

    public Object[][] getRows(int Limit, int Offset) {
        ResultSet res=this.getData(Limit, Offset);     
        ResultSet res2=this.getData(Limit, Offset);

        Object[][] ROWS = TableData.fetchDataQuery(this.getTableName(),res , this.getColumnCount(),
                 new ProductDAO().fetchDataCount3(res2));
        return ROWS;
    }
}
