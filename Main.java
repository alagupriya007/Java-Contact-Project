
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static ContactService service = new ContactService();

    public static void main(String[] args) {
        String choice;

        do {
            System.out.println("\n=== Contact Management App ===");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Edit Contact");
            System.out.println("5. Delete Contact");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scan.nextLine().trim();
            System.out.println("You entered: [" + choice + "]");
            switch (choice) {
                case "1":
                    addContact();
                    break;

                case "2":
                    System.out.println("View option selected");
                    viewContacts();
                     break;

                case "3":
                    searchContact();
                    break;

                case "4":
                    editContact();
                    break;

                case "5":
                    deleteContact();
                    break;

                case "6":
                    System.out.println("Exiting app...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (!choice.equals("6"));

        scan.close();
    }

    static String readName() {
        while (true) {
            System.out.print("Enter name: ");
            String name = scan.nextLine().trim();

            if (!name.isEmpty()) {
                return name;
            }

            System.out.println("Name cannot be empty.");
        }
    }

    static String readPhone() {
        while (true) {
            System.out.print("Enter 10-digit phone: ");
            String phone = scan.nextLine().trim();

            if (phone.matches("[0-9]{10}")) {
                return phone;
            }

            System.out.println(
                    "Invalid phone! Enter exactly 10 digits.");
        }
    }

    static String readEmail() {
        while (true) {
            System.out.print("Enter email: ");
            String email = scan.nextLine().trim();

            if (email.matches(
                    "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                return email;
            }

            System.out.println("Invalid email! Try again.");
        }
    }

    static void addContact() {
        String name = readName();
        String phone = readPhone();
        String email = readEmail();

        Contact contact = new Contact(name, phone, email);

        if (service.findContact(contact.getPhone()) != null) {
    System.out.println("Phone number already exists!");
    return;
}

if (service.addContact(contact)) {
    System.out.println("Contact added and saved!");
} else {
    System.out.println("File save failed.");
}
    }

    static void viewContacts() {
        ArrayList<Contact> contacts = service.getAllContacts();

        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }

        for (Contact c : contacts) {
            c.displayContact();
        }
    }

    static void searchContact() {
        System.out.print("Enter name or phone to search: ");
        String query = scan.nextLine().trim();

        Contact contact = service.findContact(query);

        if (contact != null) {
            contact.displayContact();
        } else {
            System.out.println("Contact not found.");
        }
    }

    static void editContact() {
        System.out.print("Enter current name or phone: ");
        String query = scan.nextLine().trim();

        Contact contact = service.findContact(query);

        if (contact == null) {
            System.out.println("Contact not found.");
            return;
        }

        System.out.println("Enter updated details:");
        String name = readName();
        String phone = readPhone();
        String email = readEmail();

        if (service.updateContact(contact, name, phone, email)) {
            System.out.println("Contact updated and saved!");
        } else {
            System.out.println(
                    "Updated in memory, but file save failed.");
        }
    }

    static void deleteContact() {
        System.out.print("Enter name or phone to delete: ");
        String query = scan.nextLine().trim();

        Contact contact = service.findContact(query);

        if (contact == null) {
            System.out.println("Contact not found.");
            return;
        }

        if (service.deleteContact(contact)) {
            System.out.println("Contact deleted and saved!");
        } else {
            System.out.println(
                    "Deleted in memory, but file save failed.");
        }
    }
}
