package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;

public class CategoryDAO extends DBContext {

    public List<Category> findAll() {
        String sql = "SELECT categoryID, categoryName FROM Categories ORDER BY categoryID ASC";
        List<Category> categories = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                categories.add(mapRow(rs));
            }
            return categories;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot list categories", ex);
        }
    }

    public Category findById(int categoryId) {
        String sql = "SELECT categoryID, categoryName FROM Categories WHERE categoryID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
            return null;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot find category by id", ex);
        }
    }

    public boolean insert(Category category) {
        String sql = "INSERT INTO Categories(categoryName) VALUES (?)";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, category.getCategoryName());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot insert category", ex);
        }
    }

    public boolean update(Category category) {
        String sql = "UPDATE Categories SET categoryName = ? WHERE categoryID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, category.getCategoryName());
            ps.setInt(2, category.getCategoryID());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot update category", ex);
        }
    }

    public boolean delete(int categoryId) {
        String sql = "DELETE FROM Categories WHERE categoryID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot delete category", ex);
        }
    }

    private Category mapRow(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setCategoryID(rs.getInt("categoryID"));
        category.setCategoryName(rs.getString("categoryName"));
        return category;
    }
}
