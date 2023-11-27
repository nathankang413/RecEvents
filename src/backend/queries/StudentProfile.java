package backend.queries;

import backend.SqlConnector;

import java.sql.ResultSet;

public class StudentProfile {

    private int student_id;
    private String full_name;
    private String username;
    private String password;
    private String confirm_password;

    public StudentProfile(int student_id) {
        ResultSet rs = SqlConnector.runQuery(String.format("SELECT * FROM Students WHERE id = %d;", student_id));
        if (rs == null) {
            System.out.println("Couldn't retrieve student with id " + student_id);
            return;
        }

        try {
            if (!rs.next()) {
                System.out.println("Couldn't retrieve student with id " + student_id);
                return;
            }
            this.student_id = rs.getInt("id");
            this.full_name = rs.getString("full_name");
            this.username = rs.getString("username");
            this.password = "";
            this.confirm_password = "";

        } catch (Exception e) {
            System.out.println("Error retrieving student with id " + student_id);
        }
    }

    public boolean changeName(String full_name) {
        if (SqlConnector.runUpdate(String.format("UPDATE Students SET full_name = '%s' WHERE id = %d;", full_name, student_id))) {
            this.full_name = full_name;
            return true;
        }
        return false;
    }

    public boolean changeUsername(String username) {
        // TODO: Check if username already exists
        ResultSet rs = SqlConnector.runQuery(String.format("SELECT COUNT(*) AS n FROM Students WHERE username = '%s';", username));
        if (rs == null) {
            System.out.println("Error counting Students" + username);
            return false;
        }
        try {
            if (!rs.next()) {
                System.out.println("Error counting Students" + username);
                return false;
            }
            if (rs.getInt("n") > 0) {
                System.out.println("Username already exists");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error counting Students" + username);
            return false;
        }

        if (SqlConnector.runUpdate(String.format("UPDATE Students SET username = '%s' WHERE id = %d;", username, student_id))) {
            this.username = username;
            return true;
        }
        return false;
    }

    public boolean changePassword(String password, String confirm_password) {
        if (!password.equals(confirm_password) || password.length() == 0) {
            return false;
        }
        if (SqlConnector.runUpdate(String.format("UPDATE Students SET pw_hash = '%d' WHERE id = %d;", password.hashCode(), student_id))) {
            this.password = password;
            this.confirm_password = confirm_password;
            return true;
        }
        return false;
    }

    public int getStudent_id() {
        return student_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

}
