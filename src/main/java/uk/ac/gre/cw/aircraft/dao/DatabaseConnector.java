package uk.ac.gre.cw.aircraft.dao;

import uk.ac.gre.cw.aircraft.Common;
import uk.ac.gre.cw.aircraft.SystemProperties;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnector {

    private static final Logger logger = Logger.getLogger(DatabaseConnector.class.getName());

    static class Configuration {
        private String jdbcDriver;
        private String databaseUrl;
        private String username;
        private String password;

        public String getJdbcDriver() {
            return jdbcDriver;
        }

        public void setJdbcDriver(String jdbcDriver) {
            this.jdbcDriver = jdbcDriver;
        }

        public String getDatabaseUrl() {
            return databaseUrl;
        }

        public void setDatabaseUrl(String databaseUrl) {
            this.databaseUrl = databaseUrl;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    private static Configuration defaultConfiguration;

    static {
        defaultConfiguration = new Configuration();
        defaultConfiguration.setDatabaseUrl(SystemProperties.getValue(Common.DATABASE_URL));
        defaultConfiguration.setJdbcDriver(SystemProperties.getValue(Common.DATABASE_JDBC_DRIVER));
        defaultConfiguration.setPassword(SystemProperties.getValue(Common.DATABASE_PASSWORD));
        defaultConfiguration.setUsername(SystemProperties.getValue(Common.DATABASE_USER));
    }

    public static Connection open() {
        return open(null);
    }

    public static Connection open(Configuration configuration) {
        if (configuration == null) {
            configuration = defaultConfiguration;
        }
        try {
            Class.forName(configuration.getJdbcDriver());
            return DriverManager.getConnection(configuration.getDatabaseUrl(), configuration.getUsername(), configuration.getPassword());
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Could not open connect. Database driver not found", e);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Could not open connect", e);
        }
        return null;
    }

    public static void close(Connection connection) {
        close(connection, null);
    }

    public static void close(Connection connection, Statement statement) {
        close(connection, statement, null);
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Could not close result set", e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Could not close statement", e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Could not close database", e);
            }
        }

    }
}
