package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        List<ContactData> result = new ArrayList<>();
        for (String firstName : List.of("", "Patrick")) {
            for (String lastName : List.of("", "Bateman")) {
                for (String address : List.of("", "55 West 81st Street, Upper West Side")) {
                    for (String email : List.of("", "patrick@bateman.com")) {
                        for (String phone : List.of("", "917-941-0426")) {
                            result.add(new ContactData(firstName, lastName, address, email, phone));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData()
                    .withFirstName(randomString(i * 10))
                    .withLastName(randomString(i * 10))
                    .withAddress(randomString(i * 10))
                    .withEmail(randomString(i * 10))
                    .withPhone(randomString(i * 10))
            );
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContact(ContactData contact) {
        int contactsCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactsCount = app.contacts().getCount();
        Assertions.assertEquals(contactsCount + 1, newContactsCount);
    }
}
