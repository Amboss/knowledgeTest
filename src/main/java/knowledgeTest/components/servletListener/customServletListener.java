package knowledgeTest.components.servletListener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Application custom servlet listener
 */
public class CustomServletListener implements ServletContextListener {

    protected static Logger logger = Logger.getLogger(CustomServletListener.class);

    /**
     * Notification that the web application initialization process is starting.
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        initiateDbCatalog("KNOWLEDGE_DB");
    }

    /**
     * Notification that the servlet context is about to be shut down.
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }

    /**
     * Initialisation of Data Base catalog;
     *
     * @param nameOfDataBase - name of DB catalog to be created;
     * @throws RuntimeException
     */
    public void initiateDbCatalog(String nameOfDataBase) {

        assert !nameOfDataBase.equals(null) : "ERROR: initiateDbCatalog - nameOfDataBase is empty!";
        logger.debug("initiating initiateDbCatalog");

        String url = "jdbc:mysql://localhost/?user=root&password=";
        String query = "CREATE DATABASE IF NOT EXISTS " + nameOfDataBase + " CHARACTER SET utf8 COLLATE utf8_bin;\n" +
                "GRANT ALL PRIVILEGES ON " + nameOfDataBase + ".* TO 'root'@localhost IDENTIFIED BY '' ;";
        String dbDialect = "MySQL";

        try {
            Connection connection = DriverManager.getConnection(url);
            if (connection != null && testDataBaseDialect(connection, dbDialect)) {
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                statement.close();
            } else {
                throw new RuntimeException("WARNING: You are using not supported data base, you must " +
                        "initiate database catalog manually!");
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException("ERROR: initiateDbCatalog - can't initialise the Data Base catalog!");
        }
    }

    /**
     * Testing Dialect of Data Base;
     *
     * @param connection - connection made by driver to data base;
     * @param dbDialect - target Data base dialect;
     * @throws RuntimeException
     */
    public boolean testDataBaseDialect(Connection connection, String dbDialect) {

        assert connection != null : "ERROR: testDataBaseDialect - connection is null!";

        logger.debug("initiating testDataBaseDialect()");
        Boolean type = false;
        try {
            if (connection.getMetaData().getDatabaseProductName().contains(dbDialect)) {
                type = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("ERROR: testDataBaseDialect - can't determine type of Database");
        }
        return type;
    }
}
