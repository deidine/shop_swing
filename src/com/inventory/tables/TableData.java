/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.inventory.Database.ConnectionFactory;

/**
 * TableData es la clase encargada de recepcionar los datos y nombres de
 * columnas de la tabla, para aplicarlos a la tabla.
 */
public class TableData extends DefaultTableModel {

    // ATTRIBUTES
    private static Connection conn = null;

    PreparedStatement prepStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    private static PreparedStatement preparedStatement;

    Date tanggal = new Date();

    private Object[][] rows;
    private Object[] column;

    // CONSTRUCTOR
    /**
     * @param data, datos de la tabla
     * @param headers, titulos de las celdas
     */
    public TableData(Object[][] rows, Object[] column) {
        super();
        this.rows = rows;
        this.column = column;
        // setDataVector(this.data, this.headers);

    }

    /**
     * Establece que ninguna celda es editable de manera directa.
     */
    @Override
    public boolean isCellEditable(int row, int colum) {
        return false;
    }

    public static Integer countRecords(String table) {

        ResultSet result = null;
        try {
            try {
                conn = new ConnectionFactory().getConn();
                // statement = conn.createStatement();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            preparedStatement = conn.prepareStatement("SELECT COUNT(*) FROM " + table);
            result = preparedStatement.executeQuery();

            if (result.next()) {
                return result.getInt(1);
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // close(con);
            // close(preparedStatement);
        }
    }

    /**
     * Este método construye un array de strings con los encabezados que
     * contendra la tabla.
     */
    public static Object[][] getPagination(int Limit, int Offset, String table, int rowSize, int columSize) {
        try {
            conn = new ConnectionFactory().getConn();
            // statement = conn.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ResultSet result = null;
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM " + table + " LIMIT ? OFFSET ?");
            preparedStatement.setInt(1, Limit);
            preparedStatement.setInt(2, Offset);

            result = preparedStatement.executeQuery();
            Object[][] data = new Object[rowSize][columSize];

            int index = 0;
            while (result.next()) {
                Object[] row = new Object[columSize];

                for (int index2 = 0; index2 < row.length; index2++) {
                    try {
                        row[index2] = result.getObject(index2 + 1);
                    } catch (SQLException e) {
                        break;
                    }
                }

                data[index] = row;
                index++;
            }

            return data;

        } catch (SQLException sq) {
            throw new RuntimeException(sq);
        } finally {
            // BarangDaoImpl.close(con);
            // close(preparedStatement);
        }
    }

    private void close(PreparedStatement preparedStatement2) {
    }

    static public Object[][] fetchDataQuery(String table, ResultSet resultSet, int columSize, int rowSize) {
        try {

            int size = resultSet.getRow();
        ResultSetMetaData metaData = resultSet.getMetaData();

            int colCount = metaData.getColumnCount();

//            Object[][] data = null;
//int rowcount = 0;
//if (resultSet.last()) {
//  rowcount = resultSet.getRow();  
//  resultSet.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
//}
//            
//            while (resultSet.next()) {
//                size += 1;
//            }

            Object[][] data = new Object[rowSize][columSize];
            int index = 0;
            while (resultSet.next()) {
                Object[] row = new Object[columSize];
                
                for (int index2 = 0; index2 < row.length; index2++) {
                    try {
                        row[index2] = resultSet.getObject(index2 + 1);
                    } catch (SQLException e) {
                        break;
                    }
                }

                data[index] = row;
                index++;
            }
         
            return data;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public DefaultTableModel generateTable(Object[][] rows, Object[] column) {

        return new DefaultTableModel(
                rows, column) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        Vector<String> columnNames = new Vector<String>();
        int colCount = metaData.getColumnCount();

        for (int col = 1; col <= colCount; col++) {
            columnNames.add(metaData.getColumnName(col).toUpperCase(Locale.ROOT));
        }

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (resultSet.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int col = 1; col <= colCount; col++) {
                vector.add(resultSet.getObject(col));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }

}
