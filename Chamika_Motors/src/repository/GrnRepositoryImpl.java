package repository;

import model.Grn;
import util.DBUtil;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of GrnRepository.
 */
public class GrnRepositoryImpl implements GrnRepository {

    private static final Logger logger = Logger.getLogger(GrnRepositoryImpl.class.getName());

    @Override
    public void createGrn(String id, String supplierId, String employeeMobile, 
                         LocalDateTime dateTime, BigDecimal paidAmount) throws Exception {
        String sql = "INSERT INTO grn (id, supplier_id, employee_mobile, date_time, paid_amount) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, supplierId);
            ps.setString(3, employeeMobile);
            ps.setTimestamp(4, Timestamp.valueOf(dateTime));
            ps.setBigDecimal(5, paidAmount);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creating GRN: " + id, e);
            throw new Exception("Failed to create GRN", e);
        } finally {
            DBUtil.closeQuietly(null, ps, conn);
        }
    }

    @Override
    public double sumPaidByMonth(String yyyyMM) throws Exception {
        String sql = "SELECT SUM(paid_amount) AS total FROM grn WHERE date_time LIKE ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, yyyyMM + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                BigDecimal total = rs.getBigDecimal("total");
                return total != null ? total.doubleValue() : 0.0;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error summing paid amounts by month: " + yyyyMM, e);
            throw new Exception("Failed to sum paid amounts", e);
        } finally {
            DBUtil.closeQuietly(rs, ps, conn);
        }
        return 0.0;
    }

    @Override
    public List<Grn> findByMonth(String yyyyMM) throws Exception {
        String sql = "SELECT id, date_time, paid_amount FROM grn WHERE date_time LIKE ?";
        List<Grn> grns = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, yyyyMM + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Grn grn = new Grn();
                grn.setId(rs.getInt("id"));
                grn.setDateTime(rs.getTimestamp("date_time").toLocalDateTime());
                grn.setPaidAmount(rs.getBigDecimal("paid_amount"));
                grns.add(grn);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding GRNs by month: " + yyyyMM, e);
            throw new Exception("Failed to find GRNs", e);
        } finally {
            DBUtil.closeQuietly(rs, ps, conn);
        }
        return grns;
    }
}