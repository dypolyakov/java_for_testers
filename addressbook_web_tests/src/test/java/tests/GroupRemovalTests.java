package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData().withName("Name").withHeader("Header").withFooter("Footer"));
        }
        List<GroupData> oldGroups = app.groups().getList();
        int index = new Random().nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        List<GroupData> newGroups = app.groups().getList();
        List<GroupData> expectedGroups = new ArrayList<>(oldGroups);
        expectedGroups.remove(index);
        Assertions.assertEquals(expectedGroups, newGroups);
    }

    @Test
    public void CanRemoveAllGroupsAtOnce() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData().withName("Name").withHeader("Header").withFooter("Footer"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }

}
