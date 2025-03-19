package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServicesTest {
    private Services<ContactDetails> service;
    private ContactDetails contact1;
    private ContactDetails contact2;

    @BeforeEach
    void setUp() {
        service = new Services<>();
        contact1 = new ContactDetails("Aashi", "9149826871", "abc@gmail.com", "Agra");
        contact2 = new ContactDetails("Ayush", "9653478089", "xyz@gmail.com", "Mathura");
    }
    @Test
    void testInsert() throws ContactNotFoundException {
        service.insert(contact1);
        service.insert(contact2);
        assertEquals(contact1, service.searchContactByName(".Aashi"));
        assertEquals(contact2, service.searchContactByName("Ayush"));
    }
    @Test
    void testRemove() throws ContactNotFoundException {
        service.insert(contact1);
        service.insert(contact2);
        service.remove(contact1);
        assertThrows(ContactNotFoundException.class, () -> service.searchContactByName("Aashi"));
        assertEquals(contact2, service.searchContactByName("Ayush"));
    }
}
