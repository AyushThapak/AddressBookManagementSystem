package org.example;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.*;
public class Services<T extends ContactDetails> implements IContactService<T> {
    Map<String , T> addressBook = new HashMap<>();
    @Override
    public void insert(T contact) {
        if(contact == null){
            throw new IllegalArgumentException("Contact cannot be null");
        }
            addressBook.put(contact.getName(), contact);
    }
    @Override
    public void remove(T contact) throws ContactNotFoundException {
        if(!addressBook.containsKey(contact.getName())){
            throw new ContactNotFoundException("Contact with name '" + contact.getName() + "' not found.");
        }
        addressBook.remove(contact.getName());
    }
    @Override
    public void update(T contact) {
        if (contact == null || !addressBook.containsKey(contact.getName())) {
            try {
                throw new ContactNotFoundException("Contact with name '" + contact.getName() + "' not found.");
            } catch (ContactNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        addressBook.put(contact.getName(), contact);
    }
    @Override
    public void display() {
        addressBook.values().forEach(System.out::println);
    }
    @Override
    public T searchContactByName(String name) throws ContactNotFoundException {
        String regex = "[A-Z]+[A-Za-z]*";
        Pattern pattern= Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid Name format: " + name);
        }
        if(!addressBook.containsKey(name)) {
            throw new ContactNotFoundException("Contact with name '" + name + "' not found.");
        }
        throw new ContactNotFoundException("Contact with name '" + name + "' not found.");
    }
    @Override
    public T searchContactByPhoneNumber(String phoneNumber) throws ContactNotFoundException {
        String regex = "[6-9][0-9]{9}";
        Pattern pattern= Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid phone number format: " + phoneNumber);
        }else {
            for (T contact : addressBook.values()) {
                if (contact.getPhoneNumber().equals(phoneNumber)) {
                    return contact;
                }
            }
        }
        throw new ContactNotFoundException("Contact with phone number '" + phoneNumber + "' not found.");
    }
    public void removeByName(String name) throws ContactNotFoundException{
        if (addressBook.containsKey(name)) {
            addressBook.remove(name);
            System.out.println("Contact '" + name + "' removed successfully.");
        } else {
            throw new ContactNotFoundException("Contact Not Found");
        }
    }

}




