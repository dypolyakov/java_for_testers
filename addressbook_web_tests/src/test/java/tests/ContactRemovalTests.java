package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if(app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData().withFirstName("For removal"));
        }
        int contactsCount = app.contacts().getCount();
        app.contacts().removeContact();
        int newContactsCount = app.contacts().getCount();
        Assertions.assertEquals(contactsCount - 1, newContactsCount);
    }
}
