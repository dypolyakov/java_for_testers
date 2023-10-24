package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName("For removal")
                    .withLastName("For removal")
                    .withAddress("For removal")
                    .withEmail("For removal")
                    .withPhone("For removal")
            );
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
}
