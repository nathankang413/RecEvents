package database;

import backend.SqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentValidation {

    /**
     *
     * @param username
     * @param password
     * @return The id of the user or -1 if not valid
     */
    public static int validateLoginCreds(String username, String password) {
        String query = "SELECT id, pw_hash FROM Students WHERE username = '%s'";
        ResultSet rs = SqlConnector.runQuery(String.format(query, username));

        if (rs == null) return -1;

        int student_id, pwHash;
        try {
            if (!rs.next()) return -1;
            student_id = rs.getInt("id");
            pwHash = rs.getInt("pw_hash");
        } catch (SQLException e) {
            System.out.println("Couldn't validate password: " + e);
            return -1;
        }

        if (pwHash == password.hashCode()) {
            return student_id;
        }
        return -1;
    }

    public static boolean usernameExists(String username) {
        String query = "SELECT * FROM Students WHERE username = '%s'";
        ResultSet rs = SqlConnector.runQuery(String.format(query, username));

        if (rs == null) return false;

        try {
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Couldn't validate username: " + e);
            return false;
        }
    }

    public static int insertNewCreds(String fullname, String username, String password) {
        String insert_query = "INSERT INTO Students (full_name, username, pw_hash) VALUES ('%s', '%s', '%d')";
        String get_student_id = "SELECT id FROM Students WHERE username = '%s'";

        if (!SqlConnector.runUpdate(String.format(insert_query, fullname, username, password.hashCode()))) {
            return -1;
        }

        ResultSet rs = SqlConnector.runQuery(String.format(get_student_id, username));
        if (rs == null) return -1;
        try {
            if (!rs.next()) return -1;
            return rs.getInt("id");
        } catch (SQLException e) {
            System.out.println("Couldn't retrieve signup: " + e);
            return -1;
        }
    }
}
