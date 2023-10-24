package tests;

import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(CommonFunctions.randomContact());
            app.contacts().refreshPage();
        }
        List<ContactData> oldContacts = app.hbm().getContactList();
        int index = new Random().nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        List<ContactData> newContacts = app.hbm().getContactList();
        List<ContactData> expectedContacts = new ArrayList<>(oldContacts);
        expectedContacts.remove(index);
        Assertions.assertEquals(expectedContacts, newContacts);
    }

    @Test
    public void canRemoveContactFromGroup() {
        if (app.jdbc().getGroupListWithContacts().isEmpty()) {
            if (app.hbm().getGroupCount() == 0) {
                app.groups().createGroup(CommonFunctions.randomGroup());
            }
            if (app.hbm().getContactCount() == 0) {
                ContactData contact = CommonFunctions.randomContact();
                GroupData group = app.hbm().getGroupList().get(0);
                app.contacts().createContact(contact, group);
            } else {
                ContactData contact = app.hbm().getContactList().get(0);
                GroupData group = app.hbm().getGroupList().get(0);
                app.contacts().addToGroup(contact,group);
            }
        }

        GroupData group = app.jdbc().getGroupListWithContacts().get(0);
        List<ContactData> oldRelated = app.hbm().getContactsInGroup(group);
        int index = new Random().nextInt(oldRelated.size());
        ContactData contact = oldRelated.get(index);
        app.contacts().removeContactFromGroup(contact, group);
        List<ContactData> newRelated = app.hbm().getContactsInGroup(group);
        List<ContactData> expectedRelated = new ArrayList<>(oldRelated);
        expectedRelated.remove(index);
        Assertions.assertEquals(expectedRelated, newRelated);
    }

}
