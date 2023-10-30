package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        String json = Files.readString(Paths.get("contacts.json"));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<>() {});
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContact(ContactData contact) {
        List<ContactData> oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        List<ContactData> newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = Comparator.comparingInt(o -> Integer.parseInt(o.id()));
        newContacts.sort(compareById);
        List<ContactData> expectedContacts = new ArrayList<>(oldContacts);
        String id = newContacts.get(newContacts.size() - 1).id();
        expectedContacts.add(contact.withId(id).withPhoto(""));
        expectedContacts.sort(compareById);
        Assertions.assertEquals(expectedContacts, newContacts);
    }

    @Test
    public void canCreateContactInGroup() {
        ContactData contact = new ContactData().withFirstName(CommonFunctions.randomString(10));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(CommonFunctions.randomGroup());
        }
        GroupData group = app.hbm().getGroupList().get(0);
        List<ContactData> oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        List<ContactData> newRelated = app.hbm().getContactsInGroup(group);
        Comparator<ContactData> compareById = Comparator.comparingInt(o -> Integer.parseInt(o.id()));
        newRelated.sort(compareById);
        List<ContactData> expectedRelated = new ArrayList<>(oldRelated);
        String id = newRelated.get(newRelated.size() - 1).id();
        expectedRelated.add(contact.withId(id).withPhoto(""));
        expectedRelated.sort(compareById);
        Assertions.assertEquals(expectedRelated, newRelated);
    }

    @Test
    public void canAddContactInGroup() {
        // Если групп нет, то создать группу
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(CommonFunctions.randomGroup());
        }
        // Если контактов нет, то создать контакт
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData().withFirstName(CommonFunctions.randomString(10)));
        }
        // Получить список групп и контактов
        List<GroupData> groups = app.hbm().getGroupList();
        List<ContactData> contacts = app.hbm().getContactList();

        GroupData groupToAdd = groups.get(0);
        ContactData contactToAdd = null;

        // Поиск группы, в которую можно добавить контакт и контакта, который можно добавить в эту группу
        for (GroupData group : groups) {
            List<ContactData> contactsInGroup = app.hbm().getContactsInGroup(group);
            if (contactsInGroup.size() < contacts.size()) {
                contacts.removeAll(contactsInGroup);
                contactToAdd = contacts.get(0);
                groupToAdd = group;
                break;
            }
        }

        // Если нет контакта, который можно добавить в существующие группы, то создаем такой контакт
        if (contactToAdd == null) {
            app.hbm().createContact(new ContactData().withFirstName(CommonFunctions.randomString(10)));
            List<ContactData> newContacts = app.hbm().getContactList();
            newContacts.removeAll(contacts);
            contactToAdd = newContacts.get(0);
        }

        List<ContactData> oldRelated = app.hbm().getContactsInGroup(groupToAdd);
        app.contacts().addToGroup(contactToAdd, groupToAdd);
        List<ContactData> newRelated = app.hbm().getContactsInGroup(groupToAdd);
        Comparator<ContactData> compareById = Comparator.comparingInt(o -> Integer.parseInt(o.id()));
        newRelated.sort(compareById);
        List<ContactData> expectedRelated = new ArrayList<>(oldRelated);
        String id = newRelated.get(newRelated.size() - 1).id();
        expectedRelated.add(contactToAdd.withId(id).withPhoto(""));
        expectedRelated.sort(compareById);
        Assertions.assertEquals(expectedRelated, newRelated);
    }
}
