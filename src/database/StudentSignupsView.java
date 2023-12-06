package database;

import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentSignupsView {
    public static final String SQL = "SELECT * FROM StudentSignups WHERE student_id = ";

    private String signupId;
    private String studentId;
    private String eventId;
    private String className;
    private String room;
    private String startTime;
    private String endTime;
    private String instructorName;

    public StudentSignupsView(String signupId, String studentId, String eventId, String className, String room, String startTime, String endTime, String instructorName) {
        this.signupId = signupId;
        this.studentId = studentId;
        this.eventId = eventId;
        this.className = className;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.instructorName = instructorName;
    }

    /**
     * Get columns info to create a display based on this view
     * @return the dbName, varName, and display name of each column
     */
    public static List<ColumnInfoTriple> getColumnInfo() {
        ArrayList<ColumnInfoTriple> info = new ArrayList<>();
//        info.add(new ColumnInfoTriple("signup_id", "signupId", "Signup ID"));
//        info.add(new ColumnInfoTriple("student_id", "studentId", "Student ID"));
//        info.add(new ColumnInfoTriple("event_id", "eventId", "Event ID"));
        info.add(new ColumnInfoTriple("class_name", "className", "Class Name"));
        info.add(new ColumnInfoTriple("room", "room", "Room"));
        info.add(new ColumnInfoTriple("start_time", "startTime", "Start Time"));
        info.add(new ColumnInfoTriple("end_time", "endTime", "End Time"));
        info.add(new ColumnInfoTriple("instructor_name", "instructorName", "Instructor"));
        return info;
    }

    public static void fillList(ObservableList<StudentSignupsView> list, int student_id) {
        ResultSet rs = SqlConnector.runQuery(SQL + student_id);
        try {
            while (rs.next()) {
                list.add(new StudentSignupsView(
                        rs.getString("signup_id"),
                        rs.getString("student_id"),
                        rs.getString("event_id"),
                        rs.getString("class_name"),
                        rs.getString("room"),
                        rs.getString("start_time"),
                        rs.getString("end_time"),
                        rs.getString("instructor_name")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public static void removeSignup(int signup_id) {
        SqlConnector.runUpdate("DELETE FROM Signups WHERE id = " + signup_id);
    }

    public String getSignupId() {
        return signupId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getClassName() {
        return className;
    }

    public String getRoom() {
        return room;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getInstructorName() {
        return instructorName;
    }


}
