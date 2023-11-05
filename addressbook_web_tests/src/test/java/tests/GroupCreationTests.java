package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GroupCreationTests extends TestBase {

    public static List<GroupData> GroupProvider() throws IOException {
        List<GroupData> result = new ArrayList<>();
        for (String name : List.of("", "group name")) {
            for (String header : List.of("", "group header")) {
                for (String footer : List.of("", "group footer")) {
                    result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }
        String json = Files.readString(Paths.get("groups.json"));

        ObjectMapper mapper = new ObjectMapper();
        List<GroupData> value = mapper.readValue(json, new TypeReference<>() {
        });
        result.addAll(value);
        return result;
    }

    public static List<GroupData> negativeGroupProvider() {
        return new ArrayList<>(List.of(
                new GroupData().withName("group name'")
        ));
    }

    public static Stream<GroupData> randomGroupsProvider() {
        Supplier<GroupData> randomGroup = CommonFunctions::randomGroup;
        return Stream.generate(randomGroup).limit(3);
    }

    @ParameterizedTest
    @MethodSource("randomGroupsProvider")
    public void canCreateGroup(GroupData group) {
        List<GroupData> oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        List<GroupData> newGroups = app.hbm().getGroupList();
        List<GroupData> expectedGroups = new ArrayList<>(oldGroups);
        List<GroupData> extraGroups = newGroups.stream().filter(g -> !oldGroups.contains(g)).toList();
        String newId = extraGroups.get(0).id();
        expectedGroups.add(group.withId(newId));
        Assertions.assertEquals(Set.copyOf(expectedGroups), Set.copyOf(newGroups));
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void cannotCreateGroup(GroupData group) {
        List<GroupData> oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        List<GroupData> newGroups = app.hbm().getGroupList();
        Assertions.assertEquals(newGroups, oldGroups);
    }

}
