package backend.queries;

import database.SqlConnector;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorClasses {
    public static final String SQL = "SELECT * FROM Classes WHERE instructor_id = ";

    private String instructorId;
    private String classId;
    private String className;
    private String price;

    public InstructorClasses(String studentId, String classId, String className, String price) {
        this.instructorId = studentId;
        this.classId = classId;
        this.className = className;
        this.price = price;
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
        info.add(new ColumnInfoTriple("class_price", "price", "Price"));
        return info;
    }

    public static void fillList(ObservableList<InstructorClasses> list, int instructor_id) {
        ResultSet rs = SqlConnector.runQuery(SQL + instructor_id);
        try {
            while (rs.next()) {
                list.add(new InstructorClasses(
                        rs.getString("instructor_id"),
                        rs.getString("id"),
                        rs.getString("name"),
                        String.format("$%.2f", rs.getDouble("price"))
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public static void removeClass(int class_id) {
        SqlConnector.runUpdate("DELETE FROM Classes WHERE id = " + class_id);
    }

    public String getStudentId() {
        return instructorId;
    }

    public String getEventId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public String getPrice() {
        return price;
    }
}
