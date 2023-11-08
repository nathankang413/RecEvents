package backend.queries;

import backend.SqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentValidation {
    public static String hashPassword(String password) {
        // TODO
        return "abcde";
    }

    public static boolean validateLoginCreds(String username, String password) {
        ResultSet rs = SqlConnector.runSql(String.format("SELECT pw_hash FROM Students WHERE username = '%s'", username));
        if (rs == null) return false;

        String pwHash;
        try {
            if (!rs.next()) return false;
            pwHash = rs.getString("pw_hash");
        } catch (SQLException e) {
            System.out.println("Couldn't validate password: " + e);
            return false;
        }

        return pwHash.equals(hashPassword(password));
    }


}
