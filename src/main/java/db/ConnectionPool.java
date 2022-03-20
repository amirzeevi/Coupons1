package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

/**
 * A singleton class to manage all connections to the database.
 */
public class ConnectionPool {
    private static final int NUMBER_OF_CONNECTIONS = 10;
    private static ConnectionPool instance = null;
    private final Stack<Connection> connections = new Stack<>();

    /**
     * private constructor so that the class can not be instantiated.
     * Opens all connections.
     */
    private ConnectionPool() throws SQLException {
        openAllConnections();
    }

    /**
     * Gets connections from the jdbc for mySQL database and pushes them into the connections Stack.
     * limited to the specified number of connections.
     */
    private void openAllConnections() throws SQLException {
        for (int counter = 0; counter < NUMBER_OF_CONNECTIONS; counter++) {
            Connection connection = DriverManager.getConnection(DBmanager.URL, DBmanager.USER, DBmanager.PASS);
            connections.push(connection);
        }
    }

    /**
     * Checks in a synchronized way if the connections Stack if full,
     * and if so, meaning no connection is being used, will remove all connections from the Stack.
     */
    public void closeAllConnection() throws InterruptedException {
        synchronized (connections) {
            while (connections.size() < NUMBER_OF_CONNECTIONS) {
                connections.wait();
            }
            connections.removeAllElements();
        }
    }

    /**
     * Returns this class's instance. If it is null, will return a new instance. Else will return the same reference.
     */
    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    try {
                        instance = new ConnectionPool();
                    } catch (SQLException err) {
                        System.out.println(err.getMessage());
                    }
                }
            }
        }
        return instance;
    }

    /**
     * Checks in a synchronized way, so no two entities can access at the same time,
     * if there is a connection in the connections Stack and
     * Returns it. Else, will wait until it is notified a connection is present in the Stack.
     */
    public Connection getConnection() throws InterruptedException {
        synchronized (connections) {
            if (connections.isEmpty()) {
                connections.wait();
            }
            return connections.pop();
        }
    }

    /**
     * When a connection returns after travel from database, it is pushed back into the connections Stack and notifies it.
     */
    public void returnConnection(Connection connection) {
        synchronized (connections) {
            connections.push(connection);
            connections.notify();
        }
    }
}