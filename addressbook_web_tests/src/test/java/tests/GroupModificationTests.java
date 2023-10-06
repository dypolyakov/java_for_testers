package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTests extends TestBase {

    @Test
    public void canModifyGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("Name", "Header", "Footer"));
        }
        app.groups().modifyGroup(new GroupData().withName("Modified Group Name"));
    }
}
