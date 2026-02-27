package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class ProductDAO implements Workable<ProductDTO> {

    private List<ProductDTO> products;

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void filterProductByCategory(int id) 
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //--- Step 1: Make Connection to database.
            con = DBUtils.makeConnection();
            if (con != null) {
                //--- Step 2: Write SQL String.
                String sqlString = "SELECT "
                        + "productId, "
                        + "productName, "
                        + "productImage, "
                        + "brief, "
                        + "postedDate, "
                        + "typeId, "
                        + "account, "
                        + "unit, "
                        + "price, "
                        + "discount "
                        + "FROM products "
                        + "WHERE typeId = ?";
                //--- Step 3: Create PrepareStatement and set SQL.
                stm = con.prepareStatement(sqlString);
                stm.setInt(1, id);
                //--- Step 4: Execute Query.
                rs = stm.executeQuery();
                //--- Step 5: Process.
                while (rs.next()) {
                    String productId = rs.getString("productId");
                    String productName = rs.getString("productName");
                    String productImage = rs.getString("productImage");
                    String brief = rs.getString("brief");
                    Timestamp postedDate = rs.getTimestamp("postedDate");
                    int typeId = rs.getInt("typeId");
                    String account = rs.getString("account");
                    String unit = rs.getString("unit");
                    int price = rs.getInt("price");
                    int discount = rs.getInt("discount");
                    ProductDTO dto = new ProductDTO(productId, productName,
                            productImage, brief, postedDate, typeId, account,
                            unit, price, discount);
                    if (products == null) {
                        products = new ArrayList<>();
                    }
                    products.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public int insert(ProductDTO g) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void listAll() throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //--- Step 1: Make Connection to database.
            con = DBUtils.makeConnection();
            if (con != null) {
                //--- Step 2: Write SQL String.
                String sqlString = "SELECT "
                        + "productId, "
                        + "productName, "
                        + "productImage, "
                        + "brief, "
                        + "postedDate, "
                        + "typeId, "
                        + "account, "
                        + "unit, "
                        + "price, "
                        + "discount "
                        + "FROM products";
                //--- Step 3: Create PrepareStatement.
                stm = con.prepareStatement(sqlString);
                //--- Step 4: Execute Query.
                rs = stm.executeQuery();
                //--- Step 5: Process.
                while (rs.next()) {
                    String productId = rs.getString("productId");
                    String productName = rs.getString("productName");
                    String productImage = rs.getString("productImage");
                    String brief = rs.getString("brief");
                    Timestamp postedDate = rs.getTimestamp("postedDate");
                    int typeId = rs.getInt("typeId");
                    String account = rs.getString("account");
                    String unit = rs.getString("unit");
                    int price = rs.getInt("price");
                    int discount = rs.getInt("discount");
                    ProductDTO dto = new ProductDTO(productId, productName,
                            productImage, brief, postedDate, typeId, account,
                            unit, price, discount);
                    if (products == null) {
                        products = new ArrayList<>();
                    }
                    products.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public int update(ProductDTO g) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String id) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ProductDTO getObjectById(String id) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    public static void main(String[] args) 
//            throws ClassNotFoundException, SQLException{
//        ProductDAO dao = new ProductDAO();
//        dao.listAll();
//        List<ProductDTO> result = dao.getProducts();
//        for(ProductDTO dto: result){
//            System.out.println(dto.getProductName());
//        }
//    }
}
