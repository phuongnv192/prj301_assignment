package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Plant;
import model.User;

public class PlantDAO extends DBContext {

    public List<Plant> findByUserId(int userId) {
        String sql = "SELECT p.plantID, p.plantName, p.categoryID, p.userID, p.imageUrl, p.healthStatus, p.note, p.createdAt, "
                + "c.categoryName, u.username, u.fullName, u.email, u.role, u.[status] "
                + "FROM Plants p "
                + "INNER JOIN Categories c ON p.categoryID = c.categoryID "
                + "INNER JOIN Users u ON p.userID = u.userID "
                + "WHERE p.userID = ? "
                + "ORDER BY p.createdAt DESC, p.plantID DESC";
        List<Plant> plants = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    plants.add(mapRow(rs));
                }
            }
            return plants;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot list plants by user", ex);
        }
    }

    public List<Plant> findAll() {
        String sql = "SELECT p.plantID, p.plantName, p.categoryID, p.userID, p.imageUrl, p.healthStatus, p.note, p.createdAt, "
                + "c.categoryName, u.username, u.fullName, u.email, u.role, u.[status] "
                + "FROM Plants p "
                + "INNER JOIN Categories c ON p.categoryID = c.categoryID "
                + "INNER JOIN Users u ON p.userID = u.userID "
                + "ORDER BY p.plantID ASC";
        List<Plant> plants = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                plants.add(mapRow(rs));
            }
            return plants;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot list all plants", ex);
        }
    }

    public Plant findById(int plantId) {
        String sql = "SELECT p.plantID, p.plantName, p.categoryID, p.userID, p.imageUrl, p.healthStatus, p.note, p.createdAt, "
                + "c.categoryName, u.username, u.fullName, u.email, u.role, u.[status] "
                + "FROM Plants p "
                + "INNER JOIN Categories c ON p.categoryID = c.categoryID "
                + "INNER JOIN Users u ON p.userID = u.userID "
                + "WHERE p.plantID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, plantId);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot find plant by id", ex);
        }
        return null;
    }

    public Plant findByIdAndUserId(int plantId, int userId) {
        String sql = "SELECT p.plantID, p.plantName, p.categoryID, p.userID, p.imageUrl, p.healthStatus, p.note, p.createdAt, "
                + "c.categoryName, u.username, u.fullName, u.email, u.role, u.[status] "
                + "FROM Plants p "
                + "INNER JOIN Categories c ON p.categoryID = c.categoryID "
                + "INNER JOIN Users u ON p.userID = u.userID "
                + "WHERE p.plantID = ? AND p.userID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, plantId);
            ps.setInt(2, userId);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
            return null;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot find plant by id and user", ex);
        }

    }

    public boolean insert(Plant plant) {
        String sql = "INSERT INTO Plants(plantName, categoryID, userID, imageUrl, healthStatus, note) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, plant.getPlantName());
            ps.setInt(2, plant.getCategory().getCategoryID());
            ps.setInt(3, plant.getOwner().getUserID());
            ps.setString(4, plant.getImageUrl());
            ps.setString(5, plant.getHealthStatus());
            ps.setString(6, plant.getNote());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot insert plant", ex);
        }
    }

    public boolean update(Plant plant) {
        String sql = "UPDATE Plants SET plantName = ?, categoryID = ?, imageUrl = ?, healthStatus = ?, note = ? "
                + "WHERE plantID = ? AND userID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, plant.getPlantName());
            ps.setInt(2, plant.getCategory().getCategoryID());
            ps.setString(3, plant.getImageUrl());
            ps.setString(4, plant.getHealthStatus());
            ps.setString(5, plant.getNote());
            ps.setInt(6, plant.getPlantID());
            ps.setInt(7, plant.getOwner().getUserID());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot update plant", ex);
        }
    }

    public boolean delete(int plantId, int userId) {
        String sql = "DELETE FROM Plants WHERE plantID = ? AND userID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, plantId);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot delete plant", ex);
        }
    }

    public int countByUserId(int userId) {
        String sql = "SELECT COUNT(*) FROM Plants WHERE userID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            return 0;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot count plants by user", ex);
        }
    }

    public int countAll() {
        String sql = "SELECT COUNT(*) FROM Plants";
        try ( PreparedStatement ps = connection.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot count plants", ex);
        }
    }

    private Plant mapRow(ResultSet rs) throws SQLException {
        Plant plant = new Plant();
        plant.setPlantID(rs.getInt("plantID"));
        plant.setPlantName(rs.getString("plantName"));
        plant.setImageUrl(rs.getString("imageUrl"));
        plant.setHealthStatus(rs.getString("healthStatus"));
        plant.setNote(rs.getString("note"));
        plant.setCreatedAt(rs.getDate("createdAt"));
        Category category = new Category();
        category.setCategoryID(rs.getInt("categoryID"));
        category.setCategoryName(rs.getString("categoryName"));
        plant.setCategory(category);

        User owner = new User();
        owner.setUserID(rs.getInt("userID"));
        owner.setUsername(rs.getString("username"));
        owner.setFullName(rs.getString("fullName"));
        owner.setEmail(rs.getString("email"));
        owner.setRole(rs.getString("role"));
        owner.setStatus(rs.getBoolean("status"));
        plant.setOwner(owner);
        return plant;
    }
}
