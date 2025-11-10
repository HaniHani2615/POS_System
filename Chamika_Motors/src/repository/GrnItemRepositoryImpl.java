package repository;

import util.DBUtil;
import java.math.BigDecimal;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of GrnItemRepository.
 */
public class GrnItemRepositoryImpl implements GrnItemRepository {

    private static final Logger logger = Logger.getLogger(GrnItemRepositoryImpl.class.getName());

    @Override
    public void createGrnItem(String stockId, double qty, BigDecimal buyingPrice, String grnId) throws Exception {
        String sql = "INSERT INTO grn_item (stock_id, qty, buying_price, grn_id) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, stockId);
            ps.setDouble(2, qty);
            ps.setBigDecimal(3, buyingPrice);
            ps.setString(4, grnId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creating GRN item", e);
            throw new Exception("Failed to create GRN item", e);
        } finally {
            DBUtil.closeQuietly(null, ps, conn);
        }
    }
}
