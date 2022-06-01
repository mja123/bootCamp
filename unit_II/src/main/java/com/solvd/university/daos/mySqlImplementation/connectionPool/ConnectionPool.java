package com.solvd.university.daos.mySqlImplementation.connectionPool;

import com.solvd.university.daos.mySqlImplementation.StudentDAO;
import com.solvd.university.daos.mySqlImplementation.exceptions.FullConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectionPool {
  private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
  private static ConnectionPool poolInstance = null;
  private final Integer MAX_CONNECTIONS = 5;
  private static Integer connectionsInUse;
  private final BlockingQueue<Connection> connections = new ArrayBlockingQueue<>(MAX_CONNECTIONS);
  private ConcurrentLinkedQueue<IListener> listeners = new ConcurrentLinkedQueue<>();

  private ConnectionPool() {
    connectionsInUse = 0;
  }

  public Connection getConnection() throws FullConnectionPoolException, IOException, SQLException {
    LOGGER.info("********* Connections in use " + connectionsInUse + " *********");
    // Look if I can create connections and if there aren't connections free in the collection.
    if ((connections.size() < this.MAX_CONNECTIONS)
        && ((connections.size() - connectionsInUse) == 0)) {
      Properties properties = new Properties();
      FileReader reader;
      reader = new FileReader(System.getenv("PROPERTIES"));
      properties.load(reader);

      connections.add(
          DriverManager.getConnection(
              properties.getProperty("URL"),
              properties.getProperty("USER"),
              properties.getProperty("PASSWORD")));

      connectionsInUse++;
      return connections.poll();

    } else if ((connections.size() - connectionsInUse) > 0) {
      connectionsInUse++;
      return connections.poll();
    } else {
      throw new FullConnectionPoolException("There aren't available connections to use.");
    }
  }

  public void goBackConnection(Connection connection) {
    connections.add(connection);
    //notify about the available connection.
    listeners.forEach(IListener::notification);
    connectionsInUse--;
  }

  public static ConnectionPool getInstance() {
    if (poolInstance == null) {
      poolInstance = new ConnectionPool();
    }
    return poolInstance;
  }

  public void addListener(IListener listener) {
    listeners.add(listener);
  }

  public void removeListener(IListener listener) {
    listeners.remove(listener);
  }

  public ConcurrentLinkedQueue<IListener> getListeners() {
    return listeners;
  }
}
