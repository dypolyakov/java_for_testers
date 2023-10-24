package tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ContactModificationTests extends TestBase {

    @Test
    public void canModifyContact() {
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(CommonFunctions.randomContact());
        }

        List<ContactData> oldContacts = app.hbm().getContactList();
        int index = new Random().nextInt(oldContacts.size());
        ContactData modifiedContact = CommonFunctions.randomContact();

        app.contacts().modifyContact(oldContacts.get(index), modifiedContact);
        List<ContactData> newContacts = app.hbm().getContactList();
        List<ContactData> expectedContacts = new ArrayList<>(oldContacts);
        expectedContacts.set(index, modifiedContact.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = Comparator.comparingInt(o -> Integer.parseInt(o.id()));
        expectedContacts.sort(compareById);
        newContacts.sort(compareById);
        Assertions.assertEquals(expectedContacts, newContacts);
    }
}
