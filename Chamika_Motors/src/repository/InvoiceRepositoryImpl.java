package repository;

import model.Invoice;
import util.DBUtil;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of InvoiceRepository.
 */
public class InvoiceRepositoryImpl implements InvoiceRepository {

    private static final Logger logger = Logger.getLogger(InvoiceRepositoryImpl.class.getName());

    @Override
    public int countByMonth(String yyyyMM) throws Exception {
        String sql = "SELECT COUNT(*) AS cnt FROM invoice WHERE date_time LIKE ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, yyyyMM + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("cnt");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error counting invoices by month: " + yyyyMM, e);
            throw new Exception("Failed to count invoices", e);
        } finally {
            DBUtil.closeQuietly(rs, ps, conn);
        }
        return 0;
    }

    @Override
    public double sumPaidByMonth(String yyyyMM) throws Exception {
        String sql = "SELECT SUM(paid_amount) AS total FROM invoice WHERE date_time LIKE ?";
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
    public List<Invoice> findByMonth(String yyyyMM) throws Exception {
        String sql = "SELECT id, date_time, paid_amount FROM invoice WHERE date_time LIKE ?";
        List<Invoice> invoices = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, yyyyMM + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
                invoice.setDateTime(rs.getTimestamp("date_time").toLocalDateTime());
                invoice.setPaidAmount(rs.getBigDecimal("paid_amount"));
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding invoices by month: " + yyyyMM, e);
            throw new Exception("Failed to find invoices", e);
        } finally {
            DBUtil.closeQuietly(rs, ps, conn);
        }
        return invoices;
    }
}