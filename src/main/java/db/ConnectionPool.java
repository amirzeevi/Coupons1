package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPool {
    private static final int NUMBER_OF_CONNECTIONS=10;
    private static ConnectionPool instance=null;
    private final Stack<Connection> connections = new Stack<>();

    private ConnectionPool() throws SQLException {
        openAllConnections();
    }

    private void openAllConnections() throws SQLException {
        for (int counter = 0; counter < NUMBER_OF_CONNECTIONS; counter++) {
            Connection connection = DriverManager.getConnection(DBmanager.URL,DBmanager.USER,DBmanager.PASS);
            connections.push(connection);
        }
    }

    public void closeAllConnection() throws InterruptedException{
        synchronized (connections){
            while(connections.size()<NUMBER_OF_CONNECTIONS){
                connections.wait();
            }
            connections.removeAllElements();
        }
    }

    public static ConnectionPool getInstance()  {
        if (instance==null){
            synchronized (ConnectionPool.class){
                if (instance==null){
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

    public Connection getConnection() throws InterruptedException{
        synchronized (connections){
            if (connections.isEmpty()){
                connections.wait();
            }
            return connections.pop();
        }
    }

    public void returnConnection(Connection connection){
        synchronized (connections){
            connections.push(connection);
            connections.notify();
        }
    }
}