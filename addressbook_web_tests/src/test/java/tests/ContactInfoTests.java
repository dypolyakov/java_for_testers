package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {
    @Test
    public void testContact() {
        ContactData contact = app.hbm().getContactList().get(0);

        String phones = app.contacts().getPhones(contact);
        String expectedPhones = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                .filter(s -> s != null && !s.isEmpty()).collect(Collectors.joining("\n"));
        Assertions.assertEquals(expectedPhones, phones);

        String emails = app.contacts().getEmails(contact);
        String expectedEmails = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !s.isEmpty()).collect(Collectors.joining("\n"));
        Assertions.assertEquals(expectedEmails, emails);

        String address = app.contacts().getAddress(contact);
        String expectedAddress = Stream.of(contact.address(), contact.address2())
                .filter(s -> s != null && !s.isEmpty()).collect(Collectors.joining("\n"));
        Assertions.assertEquals(expectedAddress, address);
    }
}
