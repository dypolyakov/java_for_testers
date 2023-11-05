package tests;

import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class GroupModificationTests extends TestBase {

    @Test
    public void canModifyGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(CommonFunctions.randomGroup());
        }
        List<GroupData> oldGroups = app.hbm().getGroupList();
        int index = new Random().nextInt(oldGroups.size());
        GroupData modifiedGroup = CommonFunctions.randomGroup();
        app.groups().modifyGroup(oldGroups.get(index), modifiedGroup);
        List<GroupData> newGroups = app.hbm().getGroupList();
        List<GroupData> expectedGroups = new ArrayList<>(oldGroups);
        expectedGroups.set(index, modifiedGroup.withId(oldGroups.get(index).id()));
        Assertions.assertEquals(Set.copyOf(expectedGroups), Set.copyOf(newGroups));
    }
}
