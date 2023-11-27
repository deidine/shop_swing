package com.inventory.tables;

import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;
 
import com.inventory.DAO.ProductDAO;
import com.inventory.DAO.SupplierDAO;

public class FournisseurTable extends DefaultTableModel {
    public FournisseurTable() {

    }

    public int countRecords (){

        return TableData.countRecords(getTableName());
     }
    public DefaultTableModel generateTable(int Limit, int Offset) {
        return TableData.generateTable(this.getRows(Limit, Offset), this.getColums());
    }
    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3; // To change body of generated methods, choose Tools | Templates.
    }

    String getTableName() {
        return "suppliers";
    }

    public String[] getColums() {
        String[] COLUMNS = {
            "Code_Fournisseur", "Nom_Complet", "Lieu", "Telephone"        };
        return COLUMNS;

    }

    ResultSet getData(int Limit, int Offset) {
        SupplierDAO supplierDAO = new SupplierDAO();

        ResultSet res = supplierDAO.getQueryResult(Limit, Offset);
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
