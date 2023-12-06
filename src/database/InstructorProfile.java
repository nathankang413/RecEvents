package database;

import database.SqlConnector;
import database.StudentValidation;

import java.sql.ResultSet;

public class InstructorProfile {

    private int instructor_id;
    private String full_name;
    private String username;

    public InstructorProfile(int instructor_id) {
        ResultSet rs = SqlConnector.runQuery(String.format("SELECT * FROM Instructors WHERE id = %d;", instructor_id));
        if (rs == null) {
            System.out.println("Couldn't retrieve student with id " + instructor_id);
            return;
        }

        try {
            if (!rs.next()) {
                System.out.println("Couldn't retrieve student with id " + instructor_id);
                return;
            }
            this.instructor_id = rs.getInt("id");
            this.full_name = rs.getString("full_name");
            this.username = rs.getString("username");

        } catch (Exception e) {
            System.out.println("Error retrieving student with id " + instructor_id);
        }
    }

    public boolean changeName(String full_name) {
        if (SqlConnector.runUpdate(String.format("UPDATE Students SET full_name = '%s' WHERE id = %d;", full_name, instructor_id))) {
            this.full_name = full_name;
            return true;
        }
        return false;
    }

    public boolean changeUsername(String username) {
        if (StudentValidation.usernameExists(username)) {
            return false;
        }
        if (SqlConnector.runUpdate(String.format("UPDATE Students SET username = '%s' WHERE id = %d;", username, instructor_id))) {
            this.username = username;
            return true;
        }
        return false;
    }

    public boolean changePassword(String password, String confirm_password) {
        if (!password.equals(confirm_password) || password.length() == 0) {
            return false;
        }
        if (SqlConnector.runUpdate(String.format("UPDATE Students SET pw_hash = '%d' WHERE id = %d;", password.hashCode(), instructor_id))) {
            return true;
        }
        return false;
    }

    public int getStudent_id() {
        return instructor_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getUsername() {
        return username;
    }


}
