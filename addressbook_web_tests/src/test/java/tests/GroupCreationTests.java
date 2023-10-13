package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    public static List<GroupData> GroupProvider() throws IOException {
        List<GroupData> result = new ArrayList<>();
//        for (String name : List.of("", "group name")) {
//            for (String header : List.of("", "group header")) {
//                for (String footer : List.of("", "group footer")) {
//                    result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
//                }
//            }
//        }
        ObjectMapper mapper = new ObjectMapper();
        List<GroupData> value = mapper.readValue(new File("groups.json"), new TypeReference<List<GroupData>>() {
        });
        result.addAll(value);
        return result;
    }

    public static List<GroupData> negativeGroupProvider() {
        return new ArrayList<>(List.of(
                new GroupData().withName("group name'")
        ));
    }

    @ParameterizedTest
    @MethodSource("GroupProvider")
    public void canCreateMultipleGroups(GroupData group) {
        List<GroupData> oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        List<GroupData> newGroups = app.groups().getList();
        Comparator<GroupData> compareById = Comparator.comparingInt(o -> Integer.parseInt(o.id()));
        newGroups.sort(compareById);
        List<GroupData> expectedGroups = new ArrayList<>(oldGroups);
        expectedGroups.add(group.withId(newGroups.get((newGroups.size()) - 1).id()).withHeader("").withFooter(""));
        expectedGroups.sort(compareById);
        Assertions.assertEquals(expectedGroups, newGroups);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void cannotCreateGroup(GroupData group) {
        List<GroupData> oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        List<GroupData> newGroups = app.groups().getList();
        Assertions.assertEquals(newGroups, oldGroups);
    }

}
