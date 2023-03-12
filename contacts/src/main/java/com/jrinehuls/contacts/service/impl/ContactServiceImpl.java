package com.jrinehuls.contacts.service.impl;

import java.util.ArrayList;
import java.util.stream.IntStream;

import com.jrinehuls.contacts.exception.ResourceNotFoundException;
import com.jrinehuls.contacts.model.Contact;
import com.jrinehuls.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrinehuls.contacts.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public ArrayList<Contact> getAllContacts() {
        return contactRepository.getContacts();
    }

    @Override
    public Contact getContactById(String id) {
        return contactRepository.getContact(getIndexById(id));
    }

    @Override
    public void saveContact(Contact contact) {
        contactRepository.saveContact(contact);
    }

    @Override
    public void updateContact(String id, Contact contact) {
        int index = getIndexById(id);
        contactRepository.updateContact(index, contact);
    }

    @Override
    public void deleteContact(String id) {
        int index = getIndexById(id);
        contactRepository.deleteContact(index);
    }

    public int getIndexById(String id) {
        for (int i = 0; i < contactRepository.getContacts().size(); i++) {
            if (contactRepository.getContacts().get(i).getId().equals(id)) {
                return i;
            }
        }
        throw new ResourceNotFoundException(id);
    }

/*
    private int findIndexById(String id) {
        return IntStream.range(0, contactRepository.getContacts().size())
            .filter(index -> contactRepository.getContacts().get(index).getId().equals(id))
            .findFirst()
            .orElseThrow();
    }
*/

}
