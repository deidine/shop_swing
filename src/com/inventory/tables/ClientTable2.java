package com.inventory.tables;

import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

import com.inventory.DAO.CustomerDAO;
import com.inventory.DAO.ProductDAO;

public class ClientTable2 extends DefaultTableModel {

    public ClientTable2() {

    }

    public DefaultTableModel generateTable(String txt) {
        return TableData.generateTable(this.getRows(txt), this.getColums());
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

    ResultSet getData(String txt) {

        CustomerDAO customerDAO = new CustomerDAO();

        ResultSet res = customerDAO.getCustomerSearch2(txt);
        // ResultSetMetaData metaData = res.getMetaData();
        // int colCount = metaData.getColumnCount();
        return res;
    }

    int getRowsCount(String txt) {
      CustomerDAO customerDAO = new CustomerDAO();
int rowSize = customerDAO.getCustomerCountSearch2(txt);
        return rowSize;
    }

    public Object[][] getRows(String txt) {
        Object[][] ROWS = TableData.fetchDataQuery(this.getTableName(), this.getData(txt), this.getColumnCount(),
                this.getRowsCount(txt));
        return ROWS;
    }
}
