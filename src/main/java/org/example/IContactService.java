package org.example;
import java.util.Map;

interface IContactService<T> {
    void insert(T contact);
    void remove( T contact) throws ContactNotFoundException;
    void update( T contact);
    T searchContactByPhoneNumber( String phoneNumber) throws ContactNotFoundException;
    T searchContactByName(String name) throws ContactNotFoundException;
    void display();
}