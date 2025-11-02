package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for database connections and resource management.
 */
public class DBUtil {

    private static final Logger logger = Logger.getLogger(DBUtil.class.getName());
    private static Properties properties;

    static {
        loadProperties();
    }

    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = DBUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                logger.warning("config.properties not found, using environment variables");
                properties.setProperty("db.url", System.getenv().getOrDefault("DB_URL", "jdbc:mysql://localhost:3306/pos_db"));
                properties.setProperty("db.user", System.getenv().getOrDefault("DB_USER", "root"));
                properties.setProperty("db.password", System.getenv().getOrDefault("DB_PASS", ""));
                return;
            }
            properties.load(input);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load config.properties", e);
        }
    }

    /**
     * Gets a database connection.
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        logger.info("Connecting to database: " + url);
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Closes database resources quietly.
     * @param resources AutoCloseable resources to close
     */
    public static void closeQuietly(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Failed to close resource", e);
                }
            }
        }
    }
}