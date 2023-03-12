package com.jrinehuls.contacts.service;

import com.jrinehuls.contacts.exception.ResourceNotFoundException;
import com.jrinehuls.contacts.model.Contact;

import java.util.ArrayList;

public interface ContactService {

    ArrayList<Contact> getAllContacts();
    Contact getContactById(String id);

    void saveContact(Contact contact);

    void updateContact(String id, Contact contact);

    void deleteContact(String id);

}
