package tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        List<ContactData> result = new ArrayList<>();
//        for (String firstName : List.of("", "Patrick")) {
//            for (String lastName : List.of("", "Bateman")) {
//                for (String address : List.of("", "55 West 81st Street, Upper West Side")) {
//                    for (String email : List.of("", "patrick@bateman.com")) {
//                        for (String phone : List.of("", "917-941-0426")) {
//                            result.add(new ContactData()
//                                    .withFirstName(firstName)
//                                    .withLastName(lastName)
//                                    .withAddress(address)
//                                    .withEmail(email)
//                                    .withPhone(phone)
//                            );
//                        }
//                    }
//                }
//            }
//        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData()
                    .withFirstName(CommonFunctions.randomString(i * 5))
                    .withLastName(CommonFunctions.randomString(i * 5))
                    .withAddress(CommonFunctions.randomString(i * 5))
                    .withEmail(CommonFunctions.randomString(i * 5))
                    .withPhone(CommonFunctions.randomString(i * 5))
                    .withPhoto(randomFile("src/test/resources/images"))
            );
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContact(ContactData contact) {
        List<ContactData> oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        List<ContactData> newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = Comparator.comparingInt(o -> Integer.parseInt(o.id()));
        newContacts.sort(compareById);
        List<ContactData> expectedContacts = new ArrayList<>(oldContacts);
        String id = newContacts.get(newContacts.size() - 1).id();
        expectedContacts.add(contact.withId(id));
        expectedContacts.sort(compareById);
        Assertions.assertEquals(expectedContacts, newContacts);
    }
}
