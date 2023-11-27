/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.DAO;

import com.inventory.DTO.ProductDTO;
import com.inventory.Database.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

/**
 *
 * @author asjad
 */
// Data Access Object for Products, Purchase, Stock and Sales
public class ProductDAO {

    Connection conn = null;
    PreparedStatement prepStatement = null;
    PreparedStatement prepStatement2 = null;
    Statement statement = null;
    Statement statement2 = null;
    ResultSet resultSet = null;

    public ProductDAO() {
        try {
            conn = new ConnectionFactory().getConn();
            statement = conn.createStatement();
            statement2 = conn.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int fetchDataCount(String table) {
        try {
            ResultSet fetchCount = statement.executeQuery(
                    "SELECT COUNT(*) "
                    + "FROM " + table);
            fetchCount.next();
            int count = fetchCount.getInt(1);
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int fetchDataCount2(String query) {
        try {
            ResultSet fetchCount = statement.executeQuery(query);
            fetchCount.next();
            int count = fetchCount.getInt(1);
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int fetchDataCount3(ResultSet res) {
        int rowcount = 0;
        try {
//            if (res.last()) {
//                rowcount = res.getRow();
//                res.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
//            }

            while (res.next()) {
                rowcount += 1;
            }

            return rowcount;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ResultSet getSuppInfo() {
        try {
            String query = "SELECT * FROM suppliers ORDER BY `suppliercode` DESC";
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getCustInfo() {
        try {
            String query = "SELECT * FROM customers ORDER BY `customercode` DESC";
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getProdStock() {
        try {
            String query = "SELECT * FROM currentstock";
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getProdInfo() {
        try {
            String query = "SELECT * FROM products ORDER BY `productcode` DESC";
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public Double getProdCost(String prodCode) {
        Double costPrice = null;
        try {
            String query = "SELECT costprice FROM products WHERE productcode='" + prodCode + "' ORDER BY `productcode` DESC";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                costPrice = resultSet.getDouble("costprice");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return costPrice;
    }

    public Double getProdSell(String prodCode) {
        Double sellPrice = null;
        try {
            String query = "SELECT sellprice FROM products WHERE productcode='" + prodCode + "' ORDER BY `productcode` DESC";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                sellPrice = resultSet.getDouble("sellprice");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sellPrice;
    }

    String suppCode;

    public String getSuppCode(String suppName) {
        try {
            String query = "SELECT suppliercode FROM suppliers WHERE fullname='" + suppName + "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                suppCode = resultSet.getString("suppliercode");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppCode;
    }

    String prodCode;

    public String getProdCode(String prodName) {
        try {
            String query = "SELECT productcode FROM products WHERE productname='" + prodName + "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                suppCode = resultSet.getString("productcode");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prodCode;
    }

    String custCode;

    public String getCustCode(String custName) {
        try {
            String query = "SELECT customercode FROM suppliers WHERE fullname='" + custName + "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                suppCode = resultSet.getString("customercode");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return custCode;
    }

    // Methods to add a new product
    public void addProductDAO(ProductDTO productDTO) {
        try {
            String query = "SELECT * FROM products WHERE productname='"
                    + productDTO.getProdName()
                    + "' AND costprice='"
                    + productDTO.getCostPrice()
                    + "' AND sellprice='"
                    + productDTO.getSellPrice()
                    + "' AND brand='"
                    + productDTO.getBrand()
                    + "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "le produit existe d'ejat.");
            } else {
                addFunction(productDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFunction(ProductDTO productDTO) {
        try {
            String query = "INSERT INTO products(`productcode`, `productname`, `costprice`, `sellprice`, `brand`,`quantity` ) VALUES(?,?,?,?,?,?)";
            prepStatement = (PreparedStatement) conn.prepareStatement(query);
            prepStatement.setString(1, productDTO.getProdCode());
            prepStatement.setString(2, productDTO.getProdName());
            prepStatement.setDouble(3, productDTO.getCostPrice());
            prepStatement.setDouble(4, productDTO.getSellPrice());
            prepStatement.setString(5, productDTO.getBrand());
            prepStatement.setInt(6, productDTO.getQuantity());

            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "le produit est enregister avec succes.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // Method to add a new purchase transaction
    public void addPurchaseDAO(ProductDTO productDTO) {
        try {
            String query = "INSERT INTO purchaseinfo VALUES(null,?,?,?,?,?)";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, productDTO.getSuppCode());
            prepStatement.setString(2, productDTO.getProdCode());
            prepStatement.setString(3, productDTO.getDate());
            prepStatement.setInt(4, productDTO.getQuantity());
            prepStatement.setDouble(5, productDTO.getTotalCost());

            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "le commande est terminer avec succes.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String prodCode = productDTO.getProdCode();

        try {
            String query = "UPDATE products SET quantity=quantity+? WHERE productcode=?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, productDTO.getQuantity());
            prepStatement.setString(2, prodCode);

            prepStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // deleteStock();
    }

    // Method to update existing product details
    public void editProdDAO(ProductDTO productDTO) {
        try {
            String query = "UPDATE products SET productname=?,costprice=?,sellprice=?,brand=?,quantity=? WHERE productcode=?";
            prepStatement = (PreparedStatement) conn.prepareStatement(query);
            prepStatement.setString(1, productDTO.getProdName());
            prepStatement.setDouble(2, productDTO.getCostPrice());
            prepStatement.setDouble(3, productDTO.getSellPrice());
            prepStatement.setString(4, productDTO.getBrand());

            prepStatement.setInt(5, productDTO.getQuantity());

            prepStatement.setString(6, productDTO.getProdCode());

            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "le produit est modifier.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // Methods to handle updating of stocks in Inventory upon any transaction made
    public void editPurchaseStock(String code, int quantity) {
        try {
            String query = "SELECT * FROM currentstock WHERE productcode='" + code + "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String query2 = "UPDATE currentstock SET quantity=quantity-? WHERE productcode=?";
                prepStatement = conn.prepareStatement(query2);
                prepStatement.setInt(1, quantity);
                prepStatement.setString(2, code);
                prepStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editSoldStock(String code, int quantity) {
        try {
            String query = "SELECT * FROM currentstock WHERE productcode='" + code + "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String query2 = "UPDATE currentstock SET quantity=quantity+? WHERE productcode=?";
                prepStatement = conn.prepareStatement(query2);
                prepStatement.setInt(1, quantity);
                prepStatement.setString(2, code);
                prepStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteStock() {
        try {
            String query = "DELETE FROM currentstock WHERE productcode NOT IN(SELECT productcode FROM purchaseinfo)";
            String query2 = "DELETE FROM salesinfo WHERE productcode NOT IN(SELECT productcode FROM products)";
            statement.executeUpdate(query);
            statement.executeUpdate(query2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // Method to permanently delete a product from inventory
    public void deleteProductDAO(String code) {
        try {
            String query = "DELETE FROM products WHERE productcode=?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, code);

            prepStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "le produit est suprimer.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // deleteStock();
    }

    public void deletePurchaseDAO(int ID) {
        try {
            String query = "DELETE FROM purchaseinfo WHERE purchaseID=?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            prepStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "le commende et suprimer.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // deleteStock();
    }

    public void deleteSaleDAO(int ID) {
        try {
            String query = "DELETE FROM salesinfo WHERE salesID=?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            prepStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "le vende est suprimer.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // deleteStock();
    }

    // look to the quqntite if corret
    public boolean validQuantity(int qunty, String code) {
        int quantity = 0;
        String prodCode = null;
        String query = "SELECT quantity,productcode FROM products WHERE productcode='" + code + "'";
        try {

            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                prodCode = resultSet.getString("productcode");
                quantity = resultSet.getInt("quantity");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (qunty > quantity) {
            return false;
        } else {
            return true;
        }

    }
    // Current Stock

    public int currentStock(String code) {
        int quentite = 0;
        try {
            String query = "SELECT * FROM products WHERE productcode='" + code + "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {

                quentite = resultSet.getInt("quantity");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return quentite;
    }

    public int idDevisSalle() {
        int idJual = 0;
        try {
            String idSale = "SELECT MAX(salesdevis.salesid) FROM salesdevis;";
            ResultSet rs = statement.executeQuery(idSale);

            if (rs.next()) {
                idJual = rs.getInt(1);

                // idJual += 1;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "di cek Kembali " + e + "");
        }
        return idJual;
    }

    public int idSalle() {
        int idJual = 0;
        try {
            String idSale = "SELECT MAX(salesinfo.salesid) FROM salesinfo;";
            ResultSet rs = statement.executeQuery(idSale);

            if (rs.next()) {
                idJual = rs.getInt(1);

                // idJual += 1;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "di cek Kembali " + e + "");
        }
        return idJual;
    }

    // Sales transaction handling
    public void sellProductDAO(ProductDTO productDTO, String username, boolean isLoan, boolean isTva) {
        int quantity = 0;
        String prodCode = null;
        try {

            String salesQuery = "INSERT INTO salesinfo(`salesid`, `date`, `customercode`, `total_paye`, `recu`, `changeMony`, `soldby`,`isLoan`,`isTva`)"
                    + "VALUES(null,'" + productDTO.getDate() + "','"
                    + productDTO.getCustCode()
                    + "','" + productDTO.getTotalPayee() + "','"
                    + productDTO.getTotalRcue() + "','" + productDTO.getTotalChange() + "','" + username + "','" + isLoan + "','" + isTva + "')";

            statement.executeUpdate(salesQuery);
//            JOptionPane.showMessageDialog(null, "le vende est terminer  pour le client." + productDTO.getCustCode() + " par le vendeur " + username + " bonjournee");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
// Sales transaction handling
    public void sellDevisProductDAO(ProductDTO productDTO, String username, boolean isLoan, boolean isTva) {
        int quantity = 0;
        String prodCode = null;
        try {

            String salesQuery = "INSERT INTO salesdevis(`salesid`, `date`, `customercode`, `total_paye`, `recu`, `changeMony`, `soldby`,`isLoan`,`isTva`)"
                    + "VALUES(null,'" + productDTO.getDate() + "','"
                    + productDTO.getCustCode()
                    + "','" + productDTO.getTotalPayee() + "','"
                    + productDTO.getTotalRcue() + "','" + productDTO.getTotalChange() + "','" + username + "','" + isLoan+ "','" + isTva + "')";

            statement.executeUpdate(salesQuery);
//            JOptionPane.showMessageDialog(null, "le vende est terminer  pour le client." + productDTO.getCustCode() + " par le vendeur " + username + " bonjournee");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
      
    // Sales detaill transaction handling
    public void sellDevisDetailProductDAO(ProductDTO productDTO, int id) {

        try {
//            String stockQuery = "UPDATE products SET quantity=quantity-'"
//                    + productDTO.getQuantity()
//                    + "' WHERE productcode='"
//                    + productDTO.getProdCode()
//                    + "'";
//            statement.executeUpdate(stockQuery);
            String salesQuery = "INSERT INTO sale_devis_detail(salesid ,productcode,quantity ,sellprice)"
                    + "VALUES('" + id + "','" + productDTO.getProdCode()
                    + "','" + productDTO.getQuantity() + "','" + productDTO.getSellPrice()+ "'  )";

            statement.executeUpdate(salesQuery);
//            JOptionPane.showMessageDialog(null, productDTO.getProdCode() + "produit est vender.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
  
// Sales transaction handling

    public void putInCaisse(String date, Double total, String username) {
        int quantity = 0;
        String prodCode = null;
        try {

            String salesQuery = "INSERT INTO caisse(`caisseid`,  `date`,  `total_paye`,  `soldby` )"
                    + "VALUES(null,'" + date + "','" + total + "','"
                    + username + "' )";
            // statement.executeUpdate(stockQuery);
            statement.executeUpdate(salesQuery);
            JOptionPane.showMessageDialog(null, "Les vente est entrer dans la caisse  par le vendeur " + username + " bonjournee");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // Sales detaill transaction handling
    public void sellDetailProductDAO(ProductDTO productDTO, int id) {

        try {
            String stockQuery = "UPDATE products SET quantity=quantity-'"
                    + productDTO.getQuantity()
                    + "' WHERE productcode='"
                    + productDTO.getProdCode()
                    + "'";
            statement.executeUpdate(stockQuery);
            String salesQuery = "INSERT INTO sale_detail(salesid ,productcode,quantity,sellPrice )"
                    + "VALUES('" + id + "','" + productDTO.getProdCode()
                    + "','" + productDTO.getQuantity() +  "','" + productDTO.getSellPrice()+ "'  )";

            statement.executeUpdate(salesQuery);
//            JOptionPane.showMessageDialog(null, productDTO.getProdCode() + "produit est vender.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void report(int id) {
//        try {
//            String report = ("src/com/raport/nota_penjualan.jrxml");
//            HashMap hash = new HashMap();
//            System.out.println("Tidak d  " + report);
//
//            hash.put("kode", id);
//            JasperReport JRpt = JasperCompileManager.compileReport(report);
//            JasperPrint JPrint = JasperFillManager.fillReport(JRpt, hash, conn);
//            JasperViewer.viewReport(JPrint, false);
//        } catch (JRException e) {
//            System.out.println("Tidak dapat menampilkan struk karena " + e);
//        }

    }

    // Products data set retrieval for display
    public ResultSet getQueryResult(int Limit, int Offset) {
        try {
            String query = "SELECT * FROM `products`  LIMIT " + Limit + " OFFSET " + Offset;

            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    // Purchase table data set retrieval
    public ResultSet getPurchaseInfo(int Limit, int Offset) {
        try {
            String query = "SELECT PurchaseID,purchaseinfo.ProductCode,ProductName,purchaseinfo.Quantity,Totalcost "
                    + "FROM purchaseinfo INNER JOIN products "
                    + "ON products.productcode=purchaseinfo.productcode   ORDER BY purchaseid LIMIT  " + Limit + " OFFSET  " + Offset + ";";
            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    // Stock table data set retrieval
    public ResultSet getCurrentStockInfo() {
        try {
            String query = """
                    SELECT currentstock.ProductCode,products.ProductName,
                    currentstock.Quantity,products.CostPrice,products.SellPrice
                    FROM currentstock INNER JOIN products
                    ON currentstock.productcode=products.productcode;
                    """;
            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    // Sales table data set retrieval
    public ResultSet getSalesKachir() {
        try {
            String query = """
                    SELECT  productcode,productname

                    FROM products
                    ;
                    """;
            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    // Search method for products
    public ResultSet getProductSearch(String text) {
        try {
            String query = "SELECT * FROM products "
                    + "WHERE productcode LIKE '%" + text + "%' OR productname LIKE '%" + text + "%' OR brand LIKE '%"
                    + text + "%'";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Search method for products
    public ResultSet getProductSearch2(String text) {
        try {
            String query = "SELECT  `productcode`, `productname`, `sellprice`,"
                    + "  `quantity` FROM products "
                    + "WHERE productcode LIKE '%" + text + "%' OR productname LIKE '%" + text + "%' OR brand LIKE '%"
                    + text + "%'";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public Integer getProdSearch2Count(String text) {

        ResultSet result = null;
        try {
            String query = "SELECT  COUNT(*) FROM products "
                    + "WHERE productcode LIKE '%" + text + "%' OR productname LIKE '%" + text + "%' OR brand LIKE '%"
                    + text + "%'";
            result = statement.executeQuery(query);
            if (result.next()) {
                return result.getInt(1);
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Search method for cuurentstok
    public ResultSet getCurrentStockSearch(String text) {
        try {
            String query = " SELECT * "
                    + " FROM currentstock where  productcode LIKE '%" + text + "%' ";

            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getProdFromCode(String text) {
        try {
            String query = "SELECT * FROM products "
                    + "WHERE productcode='" + text + "' LIMIT 1";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Search method for sales
    public ResultSet getSalesSearch(String text) {
        try {
            String query = "SELECT salesid,salesinfo.productcode,productname,\n"
                    + "                    salesinfo.quantity,revenue,users.name AS Sold_by\n"
                    + "                    FROM salesinfo INNER JOIN products\n"
                    + "                    ON salesinfo.productcode=products.productcode\n"
                    + "                    INNER JOIN users\n"
                    + "                    ON salesinfo.soldby=users.username\n"
                    + "                    INNER JOIN customers\n"
                    + "                    ON customers.customercode=salesinfo.customercode\n"
                    + "WHERE salesinfo.productcode LIKE '%" + text + "%' OR productname LIKE '%" + text + "%' "
                    + "OR users.name LIKE '%" + text + "%' OR customers.fullname LIKE '%" + text
                    + "%' ORDER BY salesid;";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Search method for sales
    public ResultSet getLoansSearch(String text) {
        try {
            String query = "SELECT loansid,loansinfo.productcode,productname,\n"
                    + "                    loansinfo.quantity,revenue,users.name AS Sold_by\n"
                    + "                    FROM loansinfo INNER JOIN products\n"
                    + "                    ON loansinfo.productcode=products.productcode\n"
                    + "                    INNER JOIN users\n"
                    + "                    ON loansinfo.soldby=users.username\n"
                    + "                    INNER JOIN customers\n"
                    + "                    ON customers.customercode=loansinfo.customercode\n"
                    + "WHERE loansinfo.productcode LIKE '%" + text + "%' OR productname LIKE '%" + text + "%' "
                    + "OR users.name LIKE '%" + text + "%' OR customers.fullname LIKE '%" + text
                    + "%' ORDER BY loansid";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Search method for purchase logs
    public ResultSet getPurchaseSearch(String text) {
        try {
            String query = "SELECT PurchaseID,purchaseinfo.productcode,products.productname,purchaseinfo.quantity,totalcost "
                    + "FROM purchaseinfo INNER JOIN products ON purchaseinfo.productcode=products.productcode "
                    + "INNER JOIN suppliers ON purchaseinfo.suppliercode=suppliers.suppliercode"
                    + "WHERE PurchaseID LIKE '%" + text + "%' OR purchaseinfo.productcode LIKE '%" + text
                    + "%' OR productname LIKE '%"
                    + text + "%' "
                    + "OR suppliers.fullname LIKE '%" + text + "%' OR purchaseinfo.suppliercode LIKE '%" + text + "%' "
                    + "OR date LIKE '%" + text + "%' ORDER BY purchaseid";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getProdName(String code) {
        try {
            String query = "SELECT productname FROM products WHERE productcode='" + code + "'";
            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public String getSuppName(int ID) {
        String name = null;
        try {
            String query = "SELECT fullname FROM suppliers "
                    + "INNER JOIN purchaseinfo ON suppliers.suppliercode=purchaseinfo.suppliercode "
                    + "WHERE purchaseid='" + ID + "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                name = resultSet.getString("fullname");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return name;
    }

    public String getCustName(int ID) {
        String name = null;
        try {
            String query = "SELECT fullname FROM customers "
                    + "INNER JOIN salesinfo ON customers.customercode=salesinfo.customercode "
                    + "WHERE salesid='" + ID + "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                name = resultSet.getString("fullname");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return name;
    }

    public String getPurchaseDate(int ID) {
        String date = null;
        try {
            String query = "SELECT date FROM purchaseinfo WHERE purchaseid='" + ID + "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                date = resultSet.getString("date");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return date;
    }

    public String getSaleDate(int ID) {
        String date = null;
        try {
            String query = "SELECT date FROM salesinfo WHERE salesid='" + ID + "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                date = resultSet.getString("date");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return date;
    }

    // Method to display product-related data set in tabular form
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
