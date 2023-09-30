package manager;

import model.ContactData;
import org.openqa.selenium.By;

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
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    public boolean isContactPresent() {
        openContactsPage();
        return manager.isElementPresent(By.name("entry"));
    }

    private void openContactsPage() {
        if (!manager.isElementPresent(By.id("maintable"))) {
            click(By.linkText("home"));
        }
    }

    public void removeContact() {
        openContactsPage();
        selectContact();
        removeSelectedContact();
        confirmRemoval();
        openContactsPage();
    }

    private void confirmRemoval() {
        manager.driver.switchTo().alert().accept();
    }

    private void removeSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }
}
