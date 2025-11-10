package repository;

import util.DBUtil;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of InvoiceItemRepository.
 */
public class InvoiceItemRepositoryImpl implements InvoiceItemRepository {

    private static final Logger logger = Logger.getLogger(InvoiceItemRepositoryImpl.class.getName());

    @Override
    public void createInvoiceItem(String stockId, String qty, String invoiceId) throws Exception {
        String sql = "INSERT INTO invoice_item (stock_id, qty, invoice_id) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, stockId);
            ps.setString(2, qty);
            ps.setString(3, invoiceId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creating invoice item", e);
            throw new Exception("Failed to create invoice item", e);
        } finally {
            DBUtil.closeQuietly(null, ps, conn);
        }
    }
}
