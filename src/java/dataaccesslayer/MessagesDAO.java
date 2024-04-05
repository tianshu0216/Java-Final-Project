/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import model.Message;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessagesDAO {

    private Connection connection;

    public MessagesDAO() {
        // Initialize your database connection
        this.connection = DBConnection.getInstance().getConnection();
    }

    public boolean postMessage(int userId, String message) {
        String sql = "INSERT INTO messages (userId, message, status) VALUES (?, ?, 'OPEN')";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setString(2, message);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages";
        
        try (Connection conn = DBConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getInt("id"));
                message.setUserId(rs.getInt("userId"));
                message.setMessage(rs.getString("message"));
                message.setPostedAt(rs.getTimestamp("postedAt"));
                message.setStatus(rs.getString("status"));
                // Handling NULL values for claimedBy
                int claimedById = rs.getInt("claimedBy");
                if (rs.wasNull()) {
                    message.setClaimedBy(null);
                } else {
                    message.setClaimedBy(claimedById);
                }
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }
    public boolean claimMessage(int messageId, int claimedBy) {
        String sql = "UPDATE messages SET status = 'CLAIMED', claimedBy = ? WHERE id = ? AND status = 'OPEN'";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, claimedBy);
            statement.setInt(2, messageId);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Message> getOpenMessages() {
    List<Message> messages = new ArrayList<>();
    String sql = "SELECT * FROM messages WHERE status = 'OPEN'";
    try (PreparedStatement stmt = connection.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            Message message = extractMessageFromResultSet(rs);
            messages.add(message);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return messages;
}

public List<Message> getMessagesClaimedByUser(int userId) {
    List<Message> messages = new ArrayList<>();
    String sql = "SELECT * FROM messages WHERE claimedBy = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        
        stmt.setInt(1, userId);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Message message = extractMessageFromResultSet(rs);
                messages.add(message);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return messages;
}

private Message extractMessageFromResultSet(ResultSet rs) throws SQLException {
    Message message = new Message();
    message.setId(rs.getInt("id"));
    message.setUserId(rs.getInt("userId"));
    message.setMessage(rs.getString("message"));
    message.setPostedAt(rs.getTimestamp("postedAt"));
    message.setStatus(rs.getString("status"));
    int claimedBy = rs.getInt("claimedBy");
    if (!rs.wasNull()) {
        message.setClaimedBy(claimedBy);
    }
    return message;
}
public List<Message> getClaimedMessagesPostedByUser(int userId) {
    List<Message> messages = new ArrayList<>();
    String sql = "SELECT m.*, u.name AS claimedByName FROM messages m " +
                 "LEFT JOIN user u ON m.claimedBy = u.id " +
                 "WHERE m.userId = ? AND m.status = 'CLAIMED'";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, userId);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Message message = new Message();
            message.setId(rs.getInt("id"));
            message.setUserId(rs.getInt("userId"));
            message.setMessage(rs.getString("message"));
            message.setPostedAt(rs.getTimestamp("postedAt"));
            message.setStatus(rs.getString("status"));
            message.setClaimedBy(rs.getInt("claimedBy"));
            messages.add(message);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return messages;
}
public List<Message> getMessagesPostedByUser(int userId) {
    List<Message> messages = new ArrayList<>();
    
    String sql = "SELECT * FROM messages WHERE userId = ? AND status = 'OPEN'";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, userId);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Message message = new Message();
            message.setId(rs.getInt("id"));
            message.setUserId(rs.getInt("userId"));
            message.setMessage(rs.getString("message"));
            message.setPostedAt(rs.getTimestamp("postedAt"));
            message.setStatus(rs.getString("status"));
            int claimedById = rs.getInt("claimedBy");
            if (!rs.wasNull()) {
                message.setClaimedBy(claimedById);
            }
            // Populate message object
            messages.add(message);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return messages;
}

}
