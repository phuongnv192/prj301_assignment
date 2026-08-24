package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDAO extends DBContext {

    public User findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT userID, username, [password], fullName, email, [role], [status] "
                + "FROM Users WHERE username = ? AND [password] = ? AND [status] = 1";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
            return null;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot login user", ex);
        }
    }

    public User findByUsername(String username) {
        String sql = "SELECT userID, username, [password], fullName, email, [role], [status] "
                + "FROM Users WHERE username = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
            return null;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot find user by username", ex);
        }
    }

    public boolean insert(User user) {
        String sql = "INSERT INTO Users(username, [password], fullName, email, [role], [status]) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getRole());
            ps.setBoolean(6, user.isStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot insert user", ex);
        }
    }

    public List<User> findAll() {
        String sql = "SELECT userID, username, [password], fullName, email, [role], [status] "
                + "FROM Users ORDER BY userID ASC";
        List<User> users = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                users.add(mapRow(rs));
            }
            return users;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot list users", ex);
        }
    }

    public User findById(int userId) {
        String sql = "SELECT userID, username, [password], fullName, email, [role], [status] "
                + "FROM Users WHERE userID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
            return null;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot find user by id", ex);
        }
    }

    public boolean updateStatus(int userId, boolean status) {
        String sql = "UPDATE Users SET [status] = ? WHERE userID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setBoolean(1, status);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot update user status", ex);
        }
    }

    public int countAll() {
        String sql = "SELECT COUNT(*) FROM Users";
        try ( PreparedStatement ps = connection.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot count users", ex);
        }
    }

    private User mapRow(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserID(rs.getInt("userID"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setFullName(rs.getString("fullName"));
        user.setEmail(rs.getString("email"));
        user.setRole(rs.getString("role"));
        user.setStatus(rs.getBoolean("status"));
        return user;
    }
}
