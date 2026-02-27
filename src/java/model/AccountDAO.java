package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import utils.DBUtils;

public class AccountDAO implements Workable<AccountDTO> {

    //--- Provide a reason why Khac Nhan declares accounts outside the function.
    List<AccountDTO> accounts = null;

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public AccountDTO checkLogin(String username, String password)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //--- Step 1: Make connection to Database.
            con = DBUtils.makeConnection();
            if (con != null) {
                //--- Step 2: Write SQL String.
                String sqlString = "SELECT "
                        + "account, "
                        + "pass, "
                        + "lastName, "
                        + "firstName, "
                        + "birthday, "
                        + "gender, "
                        + "phone, "
                        + "isUse, "
                        + "roleInSystem "
                        + "FROM accounts "
                        + "WHERE account = ? AND pass = ?";
                //--- Step 3: Create PreparedStatement and set SQL.
                stm = con.prepareStatement(sqlString);
                stm.setString(1, username);
                stm.setString(2, password);
                //--- Step 4: Execute Query.
                rs = stm.executeQuery();
                //--- Step 5: Process.
                AccountDTO currentAccount = null;
                if (rs.next()) {
                    String account = rs.getString("account");
                    String pass = rs.getString("pass");
                    String lastName = rs.getString("lastName");
                    String firstName = rs.getString("firstName");
                    Date birthday = rs.getDate("birthday");
                    boolean gender = rs.getBoolean("gender");
                    String phone = rs.getString("phone");
                    boolean isUse = rs.getBoolean("isUse");
                    int roleInSystem = rs.getInt("roleInSystem");
                    currentAccount = new AccountDTO(account, pass, lastName,
                            firstName, birthday, gender, phone, isUse, roleInSystem);
                }
                return currentAccount;
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
        return null;
    }

    public void searchAccount(String searchValue)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //--- Step 1: Make connection to Database.
            con = DBUtils.makeConnection();
            if (con != null) {
                //--- Step 2. Write SQL String.
                String sqlString = "SELECT "
                        + "account, "
                        + "pass, "
                        + "lastName, "
                        + "firstName, "
                        + "birthday, "
                        + "gender, "
                        + "phone, "
                        + "isUse, "
                        + "roleInSystem "
                        + "FROM accounts "
                        + "WHERE CONCAT(lastName, ' ', firstName) LIKE ?";
                //--- Step 3. Create PreparedStatement and set SQL.
                stm = con.prepareStatement(sqlString);
                stm.setString(1, "%" + searchValue + "%");
                //--- Step 4. Execute Query.
                rs = stm.executeQuery();
                //--- Step 5. Process.
                while (rs.next()) {
                    String account = rs.getString("account");
                    String pass = rs.getString("pass");
                    String lastName = rs.getString("lastName");
                    String firstName = rs.getString("firstName");
                    Date birthday = rs.getDate("birthday");
                    boolean gender = rs.getBoolean("gender");
                    String phone = rs.getString("phone");
                    boolean isUse = rs.getBoolean("isUse");
                    int roleInSystem = rs.getInt("roleInSystem");
                    AccountDTO dto = new AccountDTO(account, pass, lastName,
                            firstName, birthday, gender, phone, isUse, roleInSystem);
                    if (accounts == null) {
                        accounts = new ArrayList();
                    }
                    accounts.add(dto);
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
    public int insert(AccountDTO dto) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            //--- Step 1: Make Connection to Database.
            con = DBUtils.makeConnection();
            if (con != null) {
                //--- Step 2: Write SQL String.
                String sqlString = "INSERT INTO accounts VALUES"
                        + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
                //--- Step 3: Create PrepareStatement and set SQL.
                stm = con.prepareStatement(sqlString);
                stm.setString(1, dto.getAccount());
                stm.setString(2, dto.getPass());
                stm.setString(3, dto.getLastName());
                stm.setString(4, dto.getFirstName());
                stm.setDate(5, dto.getBirthday());
                stm.setInt(6, dto.isGender() ? 1 : 0) ;
                stm.setString(7, dto.getPhone());
                stm.setInt(8, dto.isUse() ? 1 : 0);
                stm.setInt(9, dto.getRoleInSystem());
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
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //--- Step 1: Make Connection to Database.
            con = DBUtils.makeConnection();
            if (con != null) {
                //--- Step 2: Write SQL String.
                String sqlString = "SELECT "
                        + "account, "
                        + "pass, "
                        + "lastName, "
                        + "firstName, "
                        + "birthday, "
                        + "gender, "
                        + "phone, "
                        + "isUse, "
                        + "roleInSystem "
                        + "FROM accounts";
                //--- Step 3: Create PreparedStatement and set SQL.
                stm = con.prepareStatement(sqlString);
                //--- Step 4. Execute Query.
                rs = stm.executeQuery();
                while (rs.next()) {
                    String account = rs.getString("account");
                    String pass = rs.getString("pass");
                    String lastName = rs.getString("lastName");
                    String firstName = rs.getString("firstName");
                    Date birthday = rs.getDate("birthday");
                    boolean gender = rs.getBoolean("gender");
                    String phone = rs.getString("phone");
                    boolean isUse = rs.getBoolean("isUse");
                    int roleInSystem = rs.getInt("roleInSystem");
                    AccountDTO dto = new AccountDTO(account, pass, lastName,
                            firstName, birthday, gender, phone, isUse, roleInSystem);
                    if (accounts == null) {
                        accounts = new ArrayList();
                    }
                    accounts.add(dto);
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
    public int update(AccountDTO dto)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //--- Step 1. Make Connection to database.
            con = DBUtils.makeConnection();
            if (con != null) {
                //--- Step 2. Write SQL String.
                String sqlString = "UPDATE accounts SET "
                        + "pass = ?, "
                        + "lastName = ?, "
                        + "firstName = ?, "
                        + "birthday = ?, "
                        + "gender = ?, "
                        + "phone = ?, "
                        + "isUse = ?, "
                        + "roleInSystem = ? "
                        + "WHERE account = ?";
                //--- Step 3. Create PreparedStatement and set SQL.
                stm = con.prepareStatement(sqlString);
                stm.setString(1, dto.getPass());
                stm.setString(2, dto.getLastName());
                stm.setString(3, dto.getFirstName());
                stm.setDate(4, dto.getBirthday());
                stm.setInt(5, dto.isGender() ? 1 : 0);
                stm.setString(6, dto.getPhone());
                stm.setInt(7, dto.isUse() ? 1 : 0);
                stm.setInt(8, dto.getRoleInSystem());
                stm.setString(9, dto.getAccount());
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
            //--- Step 1. Make Connection to database.
            con = DBUtils.makeConnection();
            if (con != null) {
                //--- Step 2. Write SQL String.
                String sqlString1 = "DELETE FROM accounts "
                        + "WHERE account = ?";
                String sqlString2 = "DELETE FROM products "
                        + "WHERE account = ?";
                //--- Step 3. Create PreparedStatement and set SQL.
                stm1 = con.prepareStatement(sqlString1);
                stm1.setString(1, id);
                
                stm2 = con.prepareStatement(sqlString2);
                stm2.setString(1, id);
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
    public AccountDTO getObjectById(String id) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //--- Step 1. Make Connection to database.
            con = DBUtils.makeConnection();
            if (con != null) {
                //--- Step 2. Write SQL String.
                String sqlString = "SELECT "
                        + "account, "
                        + "pass, "
                        + "lastName, "
                        + "firstName, "
                        + "birthday, "
                        + "gender, "
                        + "phone, "
                        + "isUse, "
                        + "roleInSystem "
                        + "FROM accounts "
                        + "WHERE account = ?";
                //--- Step 3. Create PreparedStatement and set SQL.
                stm = con.prepareStatement(sqlString);
                stm.setString(1, id);
                //--- Step 4. Execute Update.
                rs = stm.executeQuery();
                //--- Step 5. Process
                if (rs.next()) {
                    String account = rs.getString("account");
                    String pass = rs.getString("pass");
                    String lastName = rs.getString("lastName");
                    String firstName = rs.getString("firstName");
                    Date birthday = rs.getDate("birthday");
                    boolean gender = rs.getBoolean("gender");
                    String phone = rs.getString("phone");
                    boolean isUse = rs.getBoolean("isUse");
                    int roleInSystem = rs.getInt("roleInSystem");
                    AccountDTO dto = new AccountDTO(account, pass, lastName,
                            firstName, birthday, gender, phone, isUse, roleInSystem);
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
