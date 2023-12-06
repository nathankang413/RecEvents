package database;

import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ClassView {
    public static final String SQL = "SELECT id, name FROM Classes where instructor_id = ";
    private int classId;
    private String className;
    public ClassView(int classId, String className) {
        this.classId = classId;
        this.className = className;
    }

    public static void fillList(ObservableList<ClassView> list, int id) {
        ResultSet rs = SqlConnector.runQuery(SQL + id);
        try {
            while (rs.next()) {
                list.add(new ClassView(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

    }

    public int getClassId() {
        return classId;
    }

    @Override
    public String toString() {
        return className;
    }
}
