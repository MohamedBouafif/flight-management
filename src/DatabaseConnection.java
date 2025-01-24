import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/airport_db"; // Database URL
    private static final String USER = "postgres"; // Database user
    private static final String PASSWORD = "nfcyy662"; // Database password
    private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());
    static com.sun.jdi.connect.spi.Connection getConnection;

    /**
     * Establishes a connection to the PostgreSQL database.
     *
     * @return Connection object if successful, null otherwise.
     */
   
    public static Connection getConnection() throws SQLException {
        // JDBC URL, username, password
        String url = "jdbc:postgresql://localhost:5432/airport_db";
        String user = "postgres";
        String password = "nfcyy662";

        // Return a connection to the database
        return DriverManager.getConnection(url, user, password);
    }



    public static void main(String[] args) {
        // Test the database connection
        try (Connection conn = getConnection()) {
            if (conn != null) {
                LOGGER.info("Connection test succeeded.");
            } else {
                LOGGER.warning("Connection test failed.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error during connection test: ", e);
        }
    }
}
