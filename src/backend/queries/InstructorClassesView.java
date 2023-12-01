package backend.queries;

import backend.SqlConnector;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorClassesView {
    public static final String SQL = "SELECT instructor_id, E.id as event_id, C.name as class_name, room, start_time, end_time, seats FROM Classes C, Events E WHERE E.class_id = C.id\n" +
            "and instructor_id = ";

    private String instructorId;
    private String eventId;
    private String className;
    private String room;
    private String startTime;
    private String endTime;
    private String enrollment;

    public InstructorClassesView(String studentId, String eventId, String className, String room, String startTime, String endTime, String enrollment) {
        this.instructorId = studentId;
        this.eventId = eventId;
        this.className = className;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.enrollment = enrollment;
    }

    /**
     * Get columns info to create a display based on this view
     * @return the dbName, varName, and display name of each column
     */
    public static List<ColumnInfoTriple> getColumnInfo() {
        ArrayList<ColumnInfoTriple> info = new ArrayList<>();
//        info.add(new ColumnInfoTriple("student_id", "studentId", "Student ID"));
//        info.add(new ColumnInfoTriple("event_id", "eventId", "Event ID"));
        info.add(new ColumnInfoTriple("class_name", "className", "Class Name"));
        info.add(new ColumnInfoTriple("room", "room", "Room"));
        info.add(new ColumnInfoTriple("start_time", "startTime", "Start Time"));
        info.add(new ColumnInfoTriple("end_time", "endTime", "End Time"));
        info.add(new ColumnInfoTriple("seats", "enrollment", "Enrollment"));
        return info;
    }

    public static void fillList(ObservableList<InstructorClassesView> list, int instructor_id) {
        ResultSet rs = SqlConnector.runQuery(SQL + instructor_id);
        try {
            while (rs.next()) {
                list.add(new InstructorClassesView(
                        rs.getString("instructor_id"),
                        rs.getString("event_id"),
                        rs.getString("class_name"),
                        rs.getString("room"),
                        rs.getString("start_time"),
                        rs.getString("end_time"),
                        rs.getString("seats")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public static void removeClass(int event_id) {
        SqlConnector.runUpdate("DELETE FROM Events WHERE id = " + event_id);
        //TODO: CASCADE ON DELETE
    }

    public String getStudentId() {
        return instructorId;
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

    public String getEnrollment() {
        return enrollment;
    }


}
