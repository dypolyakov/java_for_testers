package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContact() {
        app.contacts().createContact(new ContactData(
                "Patrick",
                "Bateman",
                "55 West 81st Street, Upper West Side",
                "patrick@bateman.com",
                "917-941-0426"
                ));
    }
}
