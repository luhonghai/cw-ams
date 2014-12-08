package uk.ac.gre.cw.aircraft.dao;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDAO<T,V> implements IDAO<T,V> {

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    protected Connection conn = null;
    protected PreparedStatement statement = null;
    protected ResultSet rs = null;

    protected String getQuery(String name) {
        try {
            return FileUtils.readFileToString(new File(this.getClass().getClassLoader().getResource("queries/" + name + ".sql").getFile()),"UTF-8");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Could not load query " + name, e);
            return "";
        }
    }

    protected java.sql.Date toSqlDate(java.util.Date date) {
        return date == null ? null : new java.sql.Date(date.getTime());
    }

    protected java.util.Date getDate(final ResultSet rs, String column) throws SQLException {
        java.sql.Date date = rs.getDate(column);
        return date == null ? null : new java.util.Date(date.getTime());
    }

    protected java.util.Date getDate(final ResultSet rs, int column) throws SQLException {
        java.sql.Date date = rs.getDate(column);
        return date == null ? null : new java.util.Date(date.getTime());
    }

    protected void open() throws SQLException {
        if (conn == null || conn.isClosed())
            conn = DatabaseConnector.open();
    }

    protected void close() {
        DatabaseConnector.close(conn,statement,rs);
    }
}
