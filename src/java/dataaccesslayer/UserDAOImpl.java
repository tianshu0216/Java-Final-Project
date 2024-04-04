/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

/**
 *
 * @author Jiaying Qiu
 */
public class UserDAOImpl extends UserDAO {
    
    private Connection connection;
    
    public UserDAOImpl() 
            
    {
        
    	this.connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public User getUserByEmail(String email) {
       // Connection connection = DBConnection.getConnection();
      //  System.out.println(connection);
        try {
            //prepare query
            PreparedStatement pstmt = this.connection.prepareStatement("Select * from user where email = ? ");
            pstmt.setString(1, email);
            //execute and get resultset
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Email: " + rs.getString("email") + "Password " + rs.getString("password"));

                //return new User(rs.getString("name"), rs.getString("email"), rs.getString("password"),rs.getString("type"),rs.getBoolean("subscribed"));
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getBoolean("isSubscribe"),
                        rs.getString("userType"),
                        rs.getString("location")
                        
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public int createUser(User user) {
        
        try {
            //prepare query
            PreparedStatement pstmt = this.connection.prepareStatement("INSERT INTO user (name, email, password, isSubscribe, userType, location) "
                    + "VALUES (?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setBoolean(4, user.getIsSubscribe());
            pstmt.setString(5, user.getUserType());
            pstmt.setString(6, user.getLocation());
            //execute
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
