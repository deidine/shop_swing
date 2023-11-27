
package com.inventory.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.Date;  
import com.inventory.Database.ConnectionFactory;

/**
 *
 * @author deidine
 */
public class Pagination {
    Connection conn = null;

    PreparedStatement prepStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    private PreparedStatement preparedStatement;

    Date tanggal = new Date();

    public Pagination() {
        try {
            conn = new ConnectionFactory().getConn();
            statement = conn.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public ResultSet getPagination(int Limit, int Offset,String table) {
        ResultSet result = null; 
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM "+ table + " LIMIT ? OFFSET ?" );
            preparedStatement.setInt(1, Limit);
            preparedStatement.setInt(2, Offset);

            result = preparedStatement.executeQuery();

            return result;

        } catch (SQLException sq) {
            throw new RuntimeException(sq);
        } finally {
            // BarangDaoImpl.close(con);
            // close(preparedStatement);
        }
    }

}
