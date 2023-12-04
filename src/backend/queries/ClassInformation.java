package backend.queries;

import backend.SqlConnector;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClassInformation {
    public static final String SQL = "SELECT id, name FROM Classes where instructor_id = ";
    private int id;
    private String name;
    public ClassInformation(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public static List<ColumnInfoTriple> getColumnInfo() {
        ArrayList<ColumnInfoTriple> info = new ArrayList<>();
//        info.add(new ColumnInfoTriple("event_id", "eventId", "Event ID"));
        info.add(new ColumnInfoTriple("id", "classID", "Class ID"));
        info.add(new ColumnInfoTriple("name", "className", "Class Name"));
        return info;
    }

    public static void fillList(ObservableList<ClassInformation> list, int id) {
        ResultSet rs = SqlConnector.runQuery(SQL + id);
        try {
            while (rs.next()) {
                list.add(new ClassInformation(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

    }

    public int get_id() {return id;}
    public String getName() {return name;}

    @Override
    public String toString() {
        return name;
    }
}
