/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.DAO;

import com.inventory.Database.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author deidine
 */
public class CaisseDAO {

    Connection conn = null;
    PreparedStatement prepStatement = null;
    PreparedStatement prepStatement2 = null;
    Statement statement = null;
    Statement statement2 = null;
    ResultSet resultSet = null;

    public CaisseDAO() {
        try {
            conn = new ConnectionFactory().getConn();
            statement = conn.createStatement();
            statement2 = conn.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet getSalesInfo(String isloan) {
        try {

            String query = "SELECT `salesid`, `date`, `customercode`, `total_paye`, `recu`,"
                    + "`changeMony`, `soldby`  FROM   salesinfo where isLoan='" + isloan + "' ORDER BY `salesid` DESC";

            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getSalesOnly() {
        try {

            String query = "Select `salesid`, `date`, `customercode`, `total_paye`, `recu`,"
                    + " `changeMony`, `soldby` FROM  salesinfo order by salesid DESC";

            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public boolean cancelSalle(int id) {
        try {

            String query = "Select * FROM  sale_detail where salesid  ='" + id + "' ORDER BY `salesid` DESC";

            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                updateQuent(resultSet.getInt("quantity"), resultSet.getString("productcode"));
            
            }
            String query2 = "DELETE FROM salesinfo WHERE salesid  ='" + id + "'";

            try {
                statement.executeUpdate(query2);
                        return true;

            } catch (SQLException ex) {
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }
public boolean cancelDevis(int id) {
        try {

            String query = "Select * FROM  sale_devis_detail where salesid  ='" + id + "' ORDER BY `salesid` DESC";

            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                updateQuent(resultSet.getInt("quantity"), resultSet.getString("productcode"));
            
            }
            String query2 = "DELETE FROM salesdevis WHERE salesid  ='" + id + "' ORDER BY `salesid` DESC";

            try {
                statement.executeUpdate(query2);
                        return true;

            } catch (SQLException ex) {
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    void updateQuent(int quente, String prodcode) {
        try {
            String query = "UPDATE products SET quantity=quantity+? WHERE productcode=?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, quente);
            prepStatement.setString(2, prodcode);

            prepStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet getSalesTVAInfo(String isTva) {
        try {

            String query = "SELECT * FROM   salesinfo where isTva='" + isTva + "' ORDER BY `salesid` DESC";

            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getSalesDevisInfo(String isloan) {
        try {

            String query = "SELECT `salesid`, `date`, `customercode`, `total_paye`, `recu`,"
                    + "`changeMony`, `soldby`   FROM   salesdevis where isLoan='" + isloan + "' ORDER BY `salesid` DESC";

            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getCaisseInfo() {
        try {

            String query = "SELECT * FROM   caisse   ORDER BY `caisseid` DESC";

            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getCaisseLoanInfo() {
        try {

            String query = "SELECT * FROM   payloan ORDER BY `id` DESC";

            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getLoansSearchInfo(String text) {
        try {

            String query = "SELECT `salesid`, `date`, `customercode`, `total_paye`, `recu`, "
                    + "`changeMony`, `soldby`, `productcode`, `quantity`,"
                    + "   `detaild` FROM loandata where "
                    + "productcode LIKE '%" + text + "%'   "
                    + " OR soldby  LIKE '%" + text + "%' OR customercode LIKE '%" + text
                    + "%' ORDER BY loansid DESC";

            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getSalesSearchInfo(String text, String isLoan) {
        try {

            String query = "SELECT `salesid`, `date`, `customercode`, `total_paye`, `recu`,"
                    + "`changeMony`, `soldby` FROM "
                    + "salesinfo where "
                    + " customercode LIKE '%" + text + "%' and isLoan= '" + isLoan + "' ORDER BY salesid DESC";

            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getSalesOnlySearchInfo(String text) {
        try {

            String query = "SELECT `salesid`, `date`, `customercode`, `total_paye`, `recu`, "
                    + "`changeMony`, `soldby`"
                    + "FROM "
                    + "salesinfo where "
                    + " customercode LIKE '%" + text + "%'  ORDER BY salesid DESC";

            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getSalesTVASearchInfo(String text, String isTva) {
        try {

            String query = "SELECT * FROM "
                    + "salesinfo where "
                    + " customercode LIKE '%" + text + "%' "
                    + "and isTva= '" + isTva + "' ORDER BY salesid DESC";

            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getSalesDevisSearchInfo(String text, String isLoan) {
        try {

            String query = "SELECT `salesid`, `date`, `customercode`, `total_paye`, `recu`, "
                    + "`changeMony`, `soldby`"
                    + "      FROM "
                    + "salesdevis where "
                    + " customercode LIKE '%" + text + "%' and isLoan= '" + isLoan + "' ORDER BY salesid DESC";

            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getCaisseSearchInfo(String text) {
        try {

            String query = "SELECT * FROM "
                    + "caisse where "
                    + " soldby LIKE '%" + text + "%'   ORDER BY salesid DESC";

            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getCaisseLoanSearchInfo(String text) {
        try {

            String query = "SELECT * FROM "
                    + "payloan where "
                    + " soldby LIKE '%" + text + "%' or customercode  LIKE '%" + text + "%'   ORDER BY  id DESC";

            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getDateOfSalesSearchInfo(String start, String end, String isLoan) {
        try {

            String query = "SELECT * FROM salesinfo where date BETWEEN '" + start + "' and '" + end + "' and isLoan= '" + isLoan + "' ORDER BY `salesid` DESC";

            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getDateOfSalesOnlySearchInfo(String start, String end) {
        try {

            String query = "SELECT `salesid`, `date`, `customercode`, `total_paye`, "
                    + "`recu`, `changeMony`, `soldby` FROM salesinfo where"
                    + " date BETWEEN '" + start + "' and '" + end + "' ORDER BY `salesid` DESC";

            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getDateOfSalesTvaSearchInfo(String start, String end, String isTva) {
        try {

            String query = "SELECT * FROM salesinfo where date BETWEEN '" + start + "' and '" + end + "' and isTva= '" + isTva + "' ORDER BY `salesid` DESC";

            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getDateOfSalesDevisSearchInfo(String start, String end, String isLoan) {
        try {

            String query = "SELECT * FROM salesdevis where date BETWEEN '" + start + "' and '" + end + "' and isLoan= '" + isLoan + "' ORDER BY `salesid` DESC";

            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getDateOfCaisseSearchInfo(String start, String end) {
        try {

            String query = "SELECT * FROM caisse where date BETWEEN '" + start + "' and '" + end + "' ORDER BY `caisseid` DESC";

            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getDateOfCaisseLoanSearchInfo(String start, String end) {
        try {

            String query = "SELECT * FROM payloan where date BETWEEN '" + start + "' and '" + end + "' ORDER BY `id` DESC";

            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

}
