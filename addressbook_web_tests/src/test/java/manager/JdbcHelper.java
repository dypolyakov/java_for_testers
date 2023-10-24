package manager;

import model.GroupData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase {
    public JdbcHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list")
        ) {
            while (result.next()) {
                groups.add(new GroupData()
                        .withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer"))
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }
}
