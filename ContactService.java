
import java.io.*;
import java.util.ArrayList;

public class ContactService {
    private ArrayList<Contact> contacts = new ArrayList<>();
    private final String FILE_NAME = "contacts.dat";

    public ContactService() {
        loadContacts();
    }

    public ArrayList<Contact> getAllContacts() {
        return contacts;
    }

  public boolean addContact(Contact contact) {

    for (Contact c : contacts) {
        if (c.getPhone().equals(contact.getPhone())) {
            System.out.println("Phone number already exists!");
            return false;
        }
    }

    contacts.add(contact);
    return saveContacts();
}

    public Contact findContact(String query) {
        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(query)
                    || c.getPhone().equals(query)) {
                return c;
            }
        }
        return null;
    }

    public boolean updateContact(
            Contact contact, String name,
            String phone, String email) {
        contact.setName(name);
        contact.setPhone(phone);
        contact.setEmail(email);
        return saveContacts();
    }

    public boolean deleteContact(Contact contact) {
        contacts.remove(contact);
        return saveContacts();
    }

    private boolean saveContacts() {
        try (ObjectOutputStream output =
                new ObjectOutputStream(
                        new FileOutputStream(FILE_NAME))) {
            output.writeObject(contacts);
            return true;
        } catch (IOException e) {
            System.out.println("File save error: " + e.getMessage());
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    private void loadContacts() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream input =
                new ObjectInputStream(
                        new FileInputStream(file))) {
            Object data = input.readObject();

            if (data instanceof ArrayList<?>) {
                ArrayList<?> savedList = (ArrayList<?>) data;

                for (Object item : savedList) {
                    if (item instanceof Contact) {
                        contacts.add((Contact) item);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(
                    "File loading error: " + e.getMessage());
        }
    }
}
