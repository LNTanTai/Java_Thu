/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import database.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author cutay
 */
public class LoginDAO{
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public LoginDAO() {
    }
    
    private void closeConnection() throws Exception{
        
        if (rs != null) {
            rs.close();
        }
        
        if (ps != null) {
            ps.close();
        }
        
        if (con != null) {
            con.close();
        }
    }
    
    public String getFullname (String userName) throws Exception {
        String fullname = null;
        String sql = "Select fullName From TblUsers Where userID = ?";
        try{
            con = MyConnection.getMyConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            if (rs.next()) {
                fullname = rs.getString("fullName");
            }
        }
        finally{
            closeConnection();
        }
        return fullname;
    }
    
    public boolean checkLogin (String userName , String passWord) throws Exception {
        String sql = "Select userID, password From TblUsers Where userID = ? and password = ? And status != 'False'";
        try{
            con = MyConnection.getMyConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, passWord);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        finally{
            closeConnection();
        }
        return false;
    }
}
