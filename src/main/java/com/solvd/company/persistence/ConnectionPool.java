package com.solvd.company.persistence;

import com.solvd.company.domain.exception.CreateConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ConnectionPool {

    private static volatile ConnectionPool instance;
    private static volatile List<Connection> connections;
    private static final Integer maxConnectionNumber = Config.POOL_SIZE;

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    try {
                        Class.forName(Config.DRIVER);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException("Can't find a class", e);
                    }
                    instance = new ConnectionPool();
                }
                connections = new ArrayList<>();
                IntStream.range(0, maxConnectionNumber)
                        .boxed()
                        .forEach(i -> connections.add(createConnection()));
                return instance;
            }
        }
        return instance;
    }


    private static Connection createConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
        } catch (SQLException e) {
            throw new CreateConnectionException("Can't create a connection", e);
        }
        return connection;
    }

    public Connection getConnection() {
        synchronized (this) {
            Connection connection = connections
                    .remove(connections.size() - 1);
            return connection;
        }
    }

    public synchronized void releaseConnection(Connection connection) {
        connections.add(connection);
    }
}