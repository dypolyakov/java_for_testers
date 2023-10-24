package tests;

import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(CommonFunctions.randomGroup());
        }
        List<GroupData> oldGroups = app.hbm().getGroupList();
        int index = new Random().nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        List<GroupData> newGroups = app.hbm().getGroupList();
        List<GroupData> expectedGroups = new ArrayList<>(oldGroups);
        expectedGroups.remove(index);
        Assertions.assertEquals(expectedGroups, newGroups);
    }

    @Test
    public void CanRemoveAllGroupsAtOnce() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(CommonFunctions.randomGroup());
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.hbm().getGroupCount());
    }

}
