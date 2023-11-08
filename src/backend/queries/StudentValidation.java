package backend.queries;

import backend.SqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentValidation {

    public static boolean validateLoginCreds(String username, String password) {
        ResultSet rs = SqlConnector.runQuery(String.format("SELECT pw_hash FROM Students WHERE username = '%s'", username));
        if (rs == null) return false;

        int pwHash;
        try {
            if (!rs.next()) return false;
            pwHash = rs.getInt("pw_hash");
        } catch (SQLException e) {
            System.out.println("Couldn't validate password: " + e);
            return false;
        }

        return pwHash == password.hashCode();
    }

    public static boolean usernameExists(String username) {
        ResultSet rs = SqlConnector.runQuery(String.format("SELECT * FROM Students WHERE username = '%s'", username));
        if (rs == null) return false;
        try {
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Couldn't validate username: " + e);
            return false;
        }
    }

    public static boolean insertNewCreds(String fullname, String username, String password) {
        return SqlConnector.runUpdate(String.format("INSERT INTO Students (full_name, username, pw_hash) VALUES ('%s', '%s', '%d')", fullname, username, password.hashCode()));
    }


}
