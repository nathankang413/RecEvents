package backend.queries;

import backend.SqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstructorValidation {

    /**
     *
     * @param username
     * @param password
     * @return The id of the user or -1 if not valid
     */
    public static int validateLoginCreds(String username, String password) {
        ResultSet rs = SqlConnector.runQuery(String.format("SELECT id, pw_hash FROM Instructors WHERE username = '%s'", username));
        if (rs == null) return -1;

        int instructor_id;
        int pwHash;
        try {
            if (!rs.next()) return -1;
            instructor_id = rs.getInt("id");
            pwHash = rs.getInt("pw_hash");
        } catch (SQLException e) {
            System.out.println("Couldn't validate password: " + e);
            return -1;
        }

        if (pwHash == password.hashCode()) {
            return instructor_id;
        }
        return -1;
    }

    public static boolean usernameExists(String username) {
        ResultSet rs = SqlConnector.runQuery(String.format("SELECT * FROM Instructors WHERE username = '%s'", username));
        if (rs == null) return false;
        try {
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Couldn't validate username: " + e);
            return false;
        }
    }
/*
    public static int insertNewCreds(String fullname, String username, String password) {
        if (! SqlConnector.runUpdate(String.format("INSERT INTO Instructors (full_name, username, pw_hash) VALUES ('%s', '%s', '%d')", fullname, username, password.hashCode()))) {
            return -1;
        }

        ResultSet rs = SqlConnector.runQuery(String.format("SELECT id FROM Students WHERE username = '%s'", username));
        if (rs == null) return -1;
        try {
            if (!rs.next()) return -1;
            return rs.getInt("id");
        } catch (SQLException e) {
            System.out.println("Couldn't retrieve signup: " + e);
            return -1;
        }
    }
*/

}
