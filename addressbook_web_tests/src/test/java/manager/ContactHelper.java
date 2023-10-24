package manager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.phone());
        type(By.name("email"), contact.email());
        attach(By.name("photo"), contact.photo());
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

private void openContactsPage() {
    if (!manager.isElementPresent(By.id("maintable"))) {
        click(By.linkText("home"));
    }
}

    public void removeContact(ContactData contact) {
        openContactsPage();
        selectContact(contact);
        removeSelectedContact();
        confirmRemoval();
        openContactsPage();
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openContactsPage();
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    private void confirmRemoval() {
        manager.driver.switchTo().alert().accept();
    }

    private void removeSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    private void selectContact(ContactData contact) {
        click(By.id(contact.id()));
    }

    public int getCount() {
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = manager.driver.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String id = element.findElement(By.name("selected[]")).getAttribute("id");
            String firstName = element.findElement(By.xpath("td[3]")).getText();
            String lastName = element.findElement(By.xpath("td[2]")).getText();
            String address = element.findElement(By.xpath("td[4]")).getText();
            String email = element.findElement(By.xpath("td[5]")).getText();
            String phone = element.findElement(By.xpath("td[6]")).getText();
            contacts.add(new ContactData()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withAddress(address)
                    .withEmail(email)
                    .withPhone(phone)
            );
        }
        return  contacts;
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void initContactModification(ContactData contact) {
        click(By.xpath(String.format("//a[@href='edit.php?id=%s']", contact.id())));
    }

    public void refreshPage() {
        click(By.linkText("home"));
    }
}
