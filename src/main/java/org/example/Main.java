package org.example;
import java.io.IOException;
import java.util.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        Services<ContactDetails> service = new Services();
        ContactDetails contact1 = new ContactDetails("Aashi", "9149826871", "abc@gmail.com", "Agra");
        ContactDetails contact2 = new ContactDetails("Ayush", "9653478089", "xyz@gmail.comn", "Mathura");
        ContactDetails contact3 = new ContactDetails("Akrati", "9084211976", "pqr@gmail.comn", "Aligarh");
        service.insert(contact1);
        service.insert(contact2);
        service.insert(contact3);
//        service.display();
//        try{
//            service.remove(contact3);
//        }catch(ContactNotFoundException e){
//            System.out.println(e.getMessage());
//        }
//        service.display();
//        try {
//            System.out.println( service.searchContactByName("Aashi"));
//        } catch (ContactNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            System.out.println( service.searchContactByPhoneNumber("9653478089"));
//        } catch (ContactNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
        String filePath = "C:\\Users\\ASUS\\Desktop\\AddressBookManagementSystem\\src\\main\\contacts.csv";
        List<ContactDetails> contacts = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            reader.readNext();
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                try {
                    String name = nextLine[0].trim();
                    String phoneNumber = nextLine[1].trim();
                    String email = nextLine[2].trim();
                    String address = nextLine[3].trim();
                    contacts.add(new ContactDetails(name, phoneNumber, email, address));
                } catch (Exception e) {
                    System.err.println("Skipping invalid data: " + String.join(", ", nextLine));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        for (ContactDetails contact : contacts) {
            service.insert(contact);
        }
        service.display();
        try{
            service.removeByName("Akrati");
            System.out.println("Contact 'Akrati' removed successfully.");
        }catch(ContactNotFoundException e){
           System.out.println(e.getMessage());
        }
        service.display();

    }
}
