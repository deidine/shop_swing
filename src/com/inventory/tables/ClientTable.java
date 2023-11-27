package com.inventory.tables;

import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

import com.inventory.DAO.CustomerDAO;
import com.inventory.DAO.ProductDAO;

public class ClientTable extends DefaultTableModel {

    public ClientTable() {

    }

    public DefaultTableModel generateTable(int Limit, int Offset) {
        return TableData.generateTable(this.getRows(Limit, Offset), this.getColums());
    }

    public int countRecords() {

        return TableData.countRecords(getTableName());
    }

    public DefaultTableModel paginationTable(int Limit, int Offset) {
        return TableData.generateTable(this.paginationRows(Limit, Offset), this.getColums());
    }

    public Object[][] paginationRows(int Limit, int Offset) {
        return TableData.getPagination(Limit, Offset, this.getTableName(), this.getColumnCount(), this.getRowCount());
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3; // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Code_Client";
            case 1:
                return "Nom_Complet";
            case 2:
                return "Lieu";
            case 3:
                return "Telephone";
            // case 4:
            // return "Date";
            default:
                return null;
        }
    }

    String getTableName() {
        return "customers";
    }

    public String[] getColums() {
        String[] COLUMNS = {
            "Code_Client", "Nom_Complet", "Lieu", "Telephone"
        };
        return COLUMNS;

    }

    ResultSet getData(int Limit, int Offset) {

        CustomerDAO customerDAO = new CustomerDAO();

        ResultSet res = customerDAO.getQueryResult(Limit, Offset);
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
