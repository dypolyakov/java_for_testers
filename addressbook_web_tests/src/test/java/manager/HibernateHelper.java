package manager;

import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {

    private final SessionFactory sessionFactory;
    public HibernateHelper(ApplicationManager manager) {
        super(manager);

        sessionFactory =
            new Configuration()
                .addAnnotatedClass(GroupRecord.class)
                .addAnnotatedClass(ContactRecord.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    public List<GroupData> getGroupList() {
        return convertGroupList(sessionFactory.fromSession(session -> session.createQuery("from GroupRecord", GroupRecord.class).list()));
    }

    public List<ContactData> getContactList() {
        return convertContactList(sessionFactory.fromSession(session -> session.createQuery("from ContactRecord", ContactRecord.class).list()));
    }

    static List<GroupData> convertGroupList(List<GroupRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    static List<ContactData> convertContactList(List<ContactRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }



    private static ContactData convert(ContactRecord record) {
        return new ContactData()
                .withId("" + record.id)
                .withFirstName(record.firstName)
                .withLastName(record.lastName)
                .withAddress(record.address)
                .withAddress2(record.address2)
                .withEmail(record.email)
                .withEmail2(record.email2)
                .withEmail3(record.email3)
                .withHomePhone(record.home)
                .withMobilePhone(record.mobile)
                .withWorkPhone(record.work)
                .withSecondaryPhone(record.secondary);
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        String id = data.id();
        if (id.isEmpty()) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    private static ContactRecord convert(ContactData data) {
        String id = data.id();
        if (id.isEmpty()) {
            id = "0";
        }
        return new ContactRecord(
                Integer.parseInt(id),
                data.firstName(),
                data.lastName(),
                data.address(),
                data.email(),
                data.home()
        );
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult());
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }


    public long getContactCount() {
        return sessionFactory.fromSession(session -> session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult());
    }

    public void createContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(contactData));
            session.getTransaction().commit();
        });
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> convertContactList(session.get(GroupRecord.class, group.id()).contacts));
    }
}
