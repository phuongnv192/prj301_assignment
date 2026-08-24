package dal;

import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.CareLog;
import model.Plant;

public class CareLogDAO extends DBContext {

    public List<CareLog> findByPlantId(int plantId) {
        String sql = "SELECT cl.logID, cl.plantID, cl.actionDate, cl.actionType, cl.[description], p.plantName "
                + "FROM CareLogs cl "
                + "INNER JOIN Plants p ON cl.plantID = p.plantID "
                + "WHERE cl.plantID = ? "
                + "ORDER BY cl.actionDate DESC, cl.logID DESC";
        List<CareLog> logs = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, plantId);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    logs.add(mapRow(rs));
                }
            }
            return logs;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot list care logs by plant", ex);
        }
    }

    public boolean insert(CareLog careLog) {
        String sql = "INSERT INTO CareLogs(plantID, actionDate, actionType, [description]) VALUES (?, ?, ?, ?)";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, careLog.getPlant().getPlantID());
            Date actionDate = careLog.getActionDate();
            if (actionDate == null) {
                ps.setNull(2, Types.DATE);
            } else {
                ps.setDate(2, actionDate);
            }
            ps.setString(3, careLog.getActionType());
            ps.setString(4, careLog.getDescription());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot insert care log", ex);
        }
    }

    public boolean update(CareLog careLog) {
        String sql = "UPDATE CareLogs SET actionDate = ?, actionType = ?, [description] = ? "
                + "WHERE logID = ? AND plantID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            Date actionDate = careLog.getActionDate();
            if (actionDate == null) {
                ps.setNull(1, Types.DATE);
            } else {
                ps.setDate(1, actionDate);
            }
            ps.setString(2, careLog.getActionType());
            ps.setString(3, careLog.getDescription());
            ps.setInt(4, careLog.getLogID());
            ps.setInt(5, careLog.getPlant().getPlantID());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot update care log", ex);
        }
    }

    public boolean delete(int logId) {
        String sql = "DELETE FROM CareLogs WHERE logID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, logId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot delete care log", ex);
        }
    }

    public List<CareLog> findByUserId(int userId) {
        String sql = "SELECT cl.logID, cl.plantID, cl.actionDate, cl.actionType, cl.[description], p.plantName "
                + "FROM CareLogs cl "
                + "INNER JOIN Plants p ON cl.plantID = p.plantID "
                + "WHERE p.userID = ? "
                + "ORDER BY cl.actionDate DESC, cl.logID DESC";
        List<CareLog> logs = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    logs.add(mapRow(rs));
                }
            }
            return logs;
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot list care logs by user", ex);
        }
    }

    private CareLog mapRow(ResultSet rs) throws SQLException {
        CareLog careLog = new CareLog();
        careLog.setLogID(rs.getInt("logID"));
        careLog.setActionDate(rs.getDate("actionDate"));
        careLog.setActionType(rs.getString("actionType"));
        careLog.setDescription(rs.getString("description"));
        Plant plant = new Plant();
        plant.setPlantID(rs.getInt("plantID"));
        plant.setPlantName(rs.getString("plantName"));
        careLog.setPlant(plant);
        return careLog;
    }
}
