package db;

import beans.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Map;

public class DBrunQuery {


    public static void runQuery(String sql) {
        Connection connection = null;
        PreparedStatement stmt;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.execute();
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    public static void runQuery(String sql, Map<Integer, Object> map) {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            stmt = connection.prepareStatement(sql);
        } catch (InterruptedException | SQLException e) {
            System.out.println(e.getMessage());
        }
        PreparedStatement finalStmt = stmt;
        map.forEach((key, value) -> {
            try {
                if (value instanceof Integer) {
                    finalStmt.setInt(key, (Integer) value);
                } else if (value instanceof String) {
                    finalStmt.setString(key, String.valueOf(value));
                } else if (value instanceof Date) {
                    finalStmt.setDate(key, (Date) value);
                } else if (value instanceof Double) {
                    finalStmt.setDouble(key, (Double) value);
                } else if (value instanceof Boolean) {
                    finalStmt.setBoolean(key, (Boolean) value);
                } else if (value instanceof Float) {
                    finalStmt.setFloat(key, (Float) value);
                } else if (value instanceof Category) {
                    finalStmt.setString(key, String.valueOf(value));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
        try {
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    public static ResultSet getResultSet(String sql, Map<Integer, Object> map) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);

            map.forEach((key, value) -> {
                try {
                    if (value instanceof Integer) {
                        stmt.setInt(key, (Integer) value);
                    } else if (value instanceof String) {
                        stmt.setString(key, String.valueOf(value));
                    } else if (value instanceof Date) {
                        stmt.setDate(key, (Date) value);
                    } else if (value instanceof Double) {
                        stmt.setDouble(key, (Double) value);
                    } else if (value instanceof Boolean) {
                        stmt.setBoolean(key, (Boolean) value);
                    } else if (value instanceof Float) {
                        stmt.setFloat(key, (Float) value);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });
            return stmt.executeQuery();
        } catch (SQLException | InterruptedException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    public static ResultSet getResultSet(String sql) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        PreparedStatement stmt;
        try {
            assert connection != null;
            stmt = connection.prepareStatement(sql);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return null;
    }
}
