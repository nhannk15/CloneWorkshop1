package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import utils.DBUtils;

public class CategoryDAO implements Workable<CategoryDTO> {
    
    List<CategoryDTO> categories;

    public List<CategoryDTO> getCategories() {
        return categories;
    }
    
    public void loadCategories() 
            throws ClassNotFoundException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            //--- Step 1: Make Connection to Database.
            con = DBUtils.makeConnection();
            if (con != null) {
                //--- Step 2: Create SQL String.
                String sqlString = "SELECT "
                        + "typeId, "
                        + "categoryName, "
                        + "memo "
                        + "FROM categories";
                //--- Step 3: Create PrepareStatment and set SQL.
                stm = con.prepareStatement(sqlString);
                //--- Step 4: Execute Query.
                rs = stm.executeQuery();
                //--- Step 5: Process.
                while(rs.next()){
                    int typeId = rs.getInt("typeId");
                    String categoryName = rs.getString("categoryName");
                    String memo = rs.getString("memo");
                    CategoryDTO dto = new CategoryDTO(typeId, categoryName, memo);
                    if(categories == null){
                        categories = new ArrayList<>();
                    }
                    categories.add(dto);
                }
            }
        } finally {
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
    }
    
    public void searchCategory(String searchValue) 
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            //--- Step 1: Make Connection to Database.
            con = DBUtils.makeConnection();
            if (con != null) {
                //--- Step 2: write SQL String.
                String sqlString = "SELECT "
                        + "typeId, "
                        + "categoryName, "
                        + "memo "
                        + "FROM categories "
                        + "WHERE categoryName LIKE ?";
                //--- Step 3: Create PrepareStatement and set SQL.
                stm = con.prepareStatement(sqlString);
                stm.setString(1, "%" + searchValue + "%");
                //--- Step 4: Execute Query.
                rs = stm.executeQuery();
                //--- Step 5. Process.
                while(rs.next()){
                    int typeId = rs.getInt("typeId");
                    String categoryName = rs.getString("categoryName");
                    String memo = rs.getString("memo");
                    CategoryDTO dto = new CategoryDTO(typeId, categoryName, memo);
                    if(categories == null){
                        categories = new ArrayList<>();
                    }
                    categories.add(dto);
                }
            }
        } finally {
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
    }
    @Override
    public int insert(CategoryDTO dto) 
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            //--- Step 1: Make Connection to Database.
            con = DBUtils.makeConnection();
            if (con != null) {
                //--- Step 2: Write SQL String.
                String sqlString = "INSERT INTO categories VALUES"
                        + "(?, ?)";
                //--- Step 3: Create PrepareStatement and set SQL.
                stm = con.prepareStatement(sqlString);
                stm.setString(1, dto.getCategoryName());
                stm.setString(2, dto.getMemo());
                //--- Step 4: Execute Update.
                int numOfRow = stm.executeUpdate();
                return numOfRow;
            }
        } finally {
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return 0;
    }

    @Override
    public void listAll() throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(CategoryDTO dto) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //--- Step 1. Make Connection to database.
            con = DBUtils.makeConnection();
            if (con != null) {
                //--- Step 2. Write SQL String.
                String sqlString = "UPDATE categories SET "
                        + "categoryName = ?, "
                        + "memo = ? "
                        + "WHERE typeId = ?";
                //--- Step 3. Create PreparedStatement and set SQL.
                stm = con.prepareStatement(sqlString);
                stm.setString(1, dto.getCategoryName());
                stm.setString(2, dto.getMemo());
                stm.setInt(3, dto.getTypeId());
                //--- Step 4. Execute Update.
                int numOfRow = stm.executeUpdate();
                return numOfRow;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    @Override
    public int delete(String id) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm1 = null;
        PreparedStatement stm2 = null;
        try {
            int typeId = Integer.parseInt(id);
            //--- Step 1. Make Connection to database.
            con = DBUtils.makeConnection();
            if (con != null) {
                //--- Step 2. Write SQL String.
                String sqlString1 = "DELETE FROM categories "
                        + "WHERE typeId = ?";
                String sqlString2 = "DELETE FROM products "
                        + "WHERE typeId = ?";
                //--- Step 3. Create PreparedStatement and set SQL.
                stm1 = con.prepareStatement(sqlString1);
                stm1.setInt(1, typeId);
                
                stm2 = con.prepareStatement(sqlString2);
                stm2.setInt(1, typeId);
                //--- Step 4. Execute Update.
                int numOfRow2 = stm2.executeUpdate();
                int numOfRow1 = stm1.executeUpdate();
                return numOfRow1;
            }
        } finally {
            if (stm1 != null) {
                stm1.close();
            }
            if (stm2 != null) {
                stm2.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    @Override
    public CategoryDTO getObjectById(String id) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //--- Step 1. Make Connection to database.
            con = DBUtils.makeConnection();
            if (con != null) {
                //--- Step 2. Write SQL String.
                String sqlString = "SELECT "
                        + "typeId, "
                        + "categoryName, "
                        + "memo "
                        + "FROM categories "
                        + "WHERE typeId = ?";
                //--- Step 3. Create PreparedStatement and set SQL.
                stm = con.prepareStatement(sqlString);
                stm.setInt(1, Integer.parseInt(id));
                //--- Step 4. Execute Update.
                rs = stm.executeQuery();
                //--- Step 5. Process
                if (rs.next()) {
                    int typeId = rs.getInt("typeId");
                    String categoryName = rs.getString("categoryName");
                    String memo = rs.getString("memo");
                    CategoryDTO dto = new CategoryDTO(typeId, categoryName, memo);
                    return dto;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }    
}
