package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Plant;
import model.Report;
import model.User;

public class ReportDAO extends DBContext {

    public boolean insert(Report report) {
        String sql = "INSERT INTO Reports(userID, plantID, title, [description]) VALUES (?, ?, ?, ?)";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, report.getUser().getUserID());
            ps.setInt(2, report.getPlant().getPlantID());
            ps.setString(3, report.getTitle());
            ps.setString(4, report.getDescription());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot insert report", ex);
        }
    }

    public List<Report> findByUserId(int userId) {
        String sql = "SELECT r.reportID, r.userID, r.plantID, r.title, r.[description], r.reportStatus, r.adminReply, r.createdAt, "
                + "u.username, u.fullName, u.email, u.role, u.[status], p.plantName "
                + "FROM Reports r "
                + "INNER JOIN Users u ON r.userID = u.userID "
                + "INNER JOIN Plants p ON r.plantID = p.plantID "
                + "WHERE r.userID = ? "
                + "ORDER BY r.createdAt DESC, r.reportID DESC";
        List<Report> reports = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    reports.add(mapRow(rs));
                }
            }
            return reports;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot list reports by user", ex);
        }
    }

    public List<Report> findAll() {
        String sql = "SELECT r.reportID, r.userID, r.plantID, r.title, r.[description], r.reportStatus, r.adminReply, r.createdAt, "
                + "u.username, u.fullName, u.email, u.role, u.[status], p.plantName "
                + "FROM Reports r "
                + "INNER JOIN Users u ON r.userID = u.userID "
                + "INNER JOIN Plants p ON r.plantID = p.plantID "
                + "ORDER BY r.createdAt DESC, r.reportID DESC";
        List<Report> reports = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                reports.add(mapRow(rs));
            }
            return reports;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot list reports", ex);
        }
    }

    public List<Report> findByStatus(String status) {
        String sql = "SELECT r.reportID, r.userID, r.plantID, r.title, r.[description], r.reportStatus, r.adminReply, r.createdAt, "
                + "u.username, u.fullName, u.email, u.role, u.[status], p.plantName "
                + "FROM Reports r "
                + "INNER JOIN Users u ON r.userID = u.userID "
                + "INNER JOIN Plants p ON r.plantID = p.plantID "
                + "WHERE r.reportStatus = ? "
                + "ORDER BY r.createdAt DESC, r.reportID DESC";
        List<Report> reports = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, status);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    reports.add(mapRow(rs));
                }
            }
            return reports;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot list reports by status", ex);
        }
    }

    public Report findById(int reportId) {
        String sql = "SELECT r.reportID, r.userID, r.plantID, r.title, r.[description], r.reportStatus, r.adminReply, r.createdAt, "
                + "u.username, u.fullName, u.email, u.role, u.[status], p.plantName "
                + "FROM Reports r "
                + "INNER JOIN Users u ON r.userID = u.userID "
                + "INNER JOIN Plants p ON r.plantID = p.plantID "
                + "WHERE r.reportID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, reportId);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
            return null;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot find report by id", ex);
        }
    }

    public boolean replyReport(int reportId, String adminReply, String reportStatus) {
        String sql = "UPDATE Reports SET adminReply = ?, reportStatus = ? WHERE reportID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, adminReply);
            ps.setString(2, reportStatus);
            ps.setInt(3, reportId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot reply report", ex);
        }
    }

    public int countPendingByUserId(int userId) {
        String sql = "SELECT COUNT(*) FROM Reports WHERE userID = ? AND reportStatus = 'PENDING'";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            return 0;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot count pending reports by user", ex);
        }
    }

    public int countPendingAll() {
        String sql = "SELECT COUNT(*) FROM Reports WHERE reportStatus = 'PENDING'";
        try ( PreparedStatement ps = connection.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot count pending reports", ex);
        }
    }

    private Report mapRow(ResultSet rs) throws SQLException {
        Report report = new Report();
        report.setReportID(rs.getInt("reportID"));
        report.setTitle(rs.getString("title"));
        report.setDescription(rs.getString("description"));
        report.setReportStatus(rs.getString("reportStatus"));
        report.setAdminReply(rs.getString("adminReply"));
        report.setCreatedAt(rs.getDate("createdAt"));
        User user = new User();
        user.setUserID(rs.getInt("userID"));
        user.setUsername(rs.getString("username"));
        user.setFullName(rs.getString("fullName"));
        user.setEmail(rs.getString("email"));
        user.setRole(rs.getString("role"));
        user.setStatus(rs.getBoolean("status"));
        report.setUser(user);

        Plant plant = new Plant();
        plant.setPlantID(rs.getInt("plantID"));
        plant.setPlantName(rs.getString("plantName"));
        report.setPlant(plant);
        return report;
    }
}
