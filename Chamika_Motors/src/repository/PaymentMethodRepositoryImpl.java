package repository;

import util.DBUtil;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of PaymentMethodRepository.
 */
public class PaymentMethodRepositoryImpl implements PaymentMethodRepository {

    private static final Logger logger = Logger.getLogger(PaymentMethodRepositoryImpl.class.getName());

    @Override
    public Map<String, String> findAllPaymentMethods() throws Exception {
        String sql = "SELECT id, name FROM payment_method";
        Map<String, String> paymentMethods = new HashMap<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                paymentMethods.put(rs.getString("name"), rs.getString("id"));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding payment methods", e);
            throw new Exception("Failed to find payment methods", e);
        } finally {
            DBUtil.closeQuietly(rs, ps, conn);
        }
        return paymentMethods;
    }

    @Override
    public String findIdByName(String name) throws Exception {
        String sql = "SELECT id FROM payment_method WHERE name = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("id");
            }
            return null;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding payment method by name: " + name, e);
            throw new Exception("Failed to find payment method", e);
        } finally {
            DBUtil.closeQuietly(rs, ps, conn);
        }
    }
}
