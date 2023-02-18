package com.jrinehuls.contacts.repository;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jrinehuls.contacts.model.Contact;

@Repository
public class ContactRepository {
    
    private List<Contact> contacts = Arrays.asList(
            new Contact("1", "Justin", "330-831-5391")
    );

    public List<Contact> getContacts() {
        return contacts;
    }

    public Contact getContact(int index) {
        return contacts.get(index);
    }

    public void saveContact(Contact contact) {
        contacts.add(contact);
    }

    public void updateContact(int index, Contact contact) { 
        contacts.set(index, contact); 
    }
    
    public void deleteContact(int index) {
        contacts.remove(index);
    }

}
