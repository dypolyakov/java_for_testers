package manager.hbm;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "addressbook")
public class ContactRecord {
    @Id
    public int id;
    @Column(name = "firstname")
    public String firstName;
    @Column(name = "lastname")
    public String lastName;
    public String address;
    public String address2;
    public String email;
    public String email2;
    public String email3;
    public String home;
    public String mobile;
    public String work;
    @Column(name = "phone2")
    public String secondary;

    @ManyToMany
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    public List<GroupRecord> groups;

    public String middlename = "";
    public String nickname = "";
    public String company = "";
    public String title = "";
//    public String mobile = "";
//    public String work = "";
    public String fax = "";
//    public String email2 = "";
//    public String email3 = "";
    public String im = "";
    public String im2 = "";
    public String im3 = "";
    public String homepage = "";
    public int bday = 0;
    public String bmonth = "";
    public String byear = "";
    public int aday = 0;
    public String amonth = "";
    public String ayear = "";
//    public String address2 = "";
//    @Column(name = "phone2")
//    public String secondary = "";
    public String notes = "";
    public ContactRecord() {
    }

    public ContactRecord(int id, String firstName, String lastName, String address, String email, String home) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.home = home;
    }
}
