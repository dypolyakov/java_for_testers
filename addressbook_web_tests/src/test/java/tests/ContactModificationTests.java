package tests;

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
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData()
                    .withFirstName(randomString(5))
                    .withLastName(randomString(5))
                    .withAddress(randomString(5))
                    .withEmail(randomString(5))
                    .withPhone(randomString(5))
            );
        }

        List<ContactData> oldContacts = app.contacts().getList();
        int index = new Random().nextInt(oldContacts.size());
        ContactData modifiedContact = new ContactData()
                .withFirstName(randomString(5))
                .withLastName(randomString(5))
                .withAddress(randomString(5))
                .withEmail(randomString(5))
                .withPhone(randomString(5));

        app.contacts().modifyContact(oldContacts.get(index), modifiedContact);
        List<ContactData> newContacts = app.contacts().getList();
        List<ContactData> expectedContacts = new ArrayList<>(oldContacts);
        expectedContacts.set(index, modifiedContact.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = Comparator.comparingInt(o -> Integer.parseInt(o.id()));
        expectedContacts.sort(compareById);
        newContacts.sort(compareById);
        Assertions.assertEquals(expectedContacts, newContacts);
    }
}
