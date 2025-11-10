package repository;

import dto.CustomerDto;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of CustomerRepository.
 */
public class CustomerRepositoryImpl implements CustomerRepository {

    private static final Logger logger = Logger.getLogger(CustomerRepositoryImpl.class.getName());

    @Override
    public void createCustomer(String mobile, String name) throws Exception {
        String sql = "INSERT INTO customer (mobile, name, points) VALUES (?, ?, 0)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, mobile);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creating customer: " + mobile, e);
            throw new Exception("Failed to create customer", e);
        } finally {
            DBUtil.closeQuietly(null, ps, conn);
        }
    }

    @Override
    public void updateCustomer(String mobile, String name) throws Exception {
        String sql = "UPDATE customer SET name = ? WHERE mobile = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, mobile);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating customer: " + mobile, e);
            throw new Exception("Failed to update customer", e);
        } finally {
            DBUtil.closeQuietly(null, ps, conn);
        }
    }

    @Override
    public Optional<CustomerDto> findByMobile(String mobile) throws Exception {
        String sql = "SELECT mobile, name, points FROM customer WHERE mobile = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, mobile);
            rs = ps.executeQuery();
            if (rs.next()) {
                CustomerDto dto = new CustomerDto();
                dto.setMobile(rs.getString("mobile"));
                dto.setName(rs.getString("name"));
                dto.setPoints(rs.getDouble("points"));
                return Optional.of(dto);
            }
            return Optional.empty();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding customer: " + mobile, e);
            throw new Exception("Failed to find customer", e);
        } finally {
            DBUtil.closeQuietly(rs, ps, conn);
        }
    }

    @Override
    public List<CustomerDto> searchByName(String namePattern) throws Exception {
        String sql = "SELECT mobile, name, points FROM customer WHERE name LIKE ? ORDER BY name ASC";
        List<CustomerDto> customers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, namePattern + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                CustomerDto dto = new CustomerDto();
                dto.setMobile(rs.getString("mobile"));
                dto.setName(rs.getString("name"));
                dto.setPoints(rs.getDouble("points"));
                customers.add(dto);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error searching customers: " + namePattern, e);
            throw new Exception("Failed to search customers", e);
        } finally {
            DBUtil.closeQuietly(rs, ps, conn);
        }
        return customers;
    }

    @Override
    public double getCustomerPoints(String mobile) throws Exception {
        String sql = "SELECT points FROM customer WHERE mobile = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, mobile);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("points");
            }
            return 0.0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting customer points: " + mobile, e);
            throw new Exception("Failed to get customer points", e);
        } finally {
            DBUtil.closeQuietly(rs, ps, conn);
        }
    }

    @Override
    public void updateCustomerPoints(String mobile, double newPoints) throws Exception {
        String sql = "UPDATE customer SET points = ? WHERE mobile = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, newPoints);
            ps.setString(2, mobile);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating customer points: " + mobile, e);
            throw new Exception("Failed to update customer points", e);
        } finally {
            DBUtil.closeQuietly(null, ps, conn);
        }
    }

    @Override
    public void addCustomerPoints(String mobile, double points) throws Exception {
        String sql = "UPDATE customer SET points = points + ? WHERE mobile = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, points);
            ps.setString(2, mobile);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding customer points: " + mobile, e);
            throw new Exception("Failed to add customer points", e);
        } finally {
            DBUtil.closeQuietly(null, ps, conn);
        }
    }
}
