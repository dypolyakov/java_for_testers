package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    public void testPhones() {
        List<ContactData> contacts = app.contacts().getList();
        ContactData contact = contacts.get(0);
        String phones = app.contacts().getPhones(contact);
        String expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary()).filter(s -> s != null && !s.isEmpty()).collect(Collectors.joining("/n"));
        Assertions.assertEquals(expected, phones);
    }
}
