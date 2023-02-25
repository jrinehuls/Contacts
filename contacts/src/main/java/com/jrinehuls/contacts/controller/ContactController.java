package com.jrinehuls.contacts.controller;

import com.jrinehuls.contacts.exception.ResourceNotFoundException;
import com.jrinehuls.contacts.model.Contact;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import com.jrinehuls.contacts.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.Arrays;

@RestController // ResponseBody: Java -> JSON
public class ContactController {
    
    @Autowired
    private ContactService contactService;

    @GetMapping("/contact")
    public ResponseEntity<ArrayList<Contact>> getAllContacts() {
        return new ResponseEntity<>(contactService.getAllContacts(), HttpStatus.OK);
    }

    @GetMapping("/contact/{id}")
    public ResponseEntity<Object> getContact(@PathVariable("id") String id) {
        return new ResponseEntity<>(contactService.getContactById(id), HttpStatus.OK);
    }

    @PostMapping("/contact")
    public ResponseEntity<String> createContact(@Valid @RequestBody Contact contact) {
        contactService.saveContact(contact);
        return new ResponseEntity<>(contact.getId(), HttpStatus.CREATED);
    }

    @PutMapping("/contact/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable("id") String id, @Valid @RequestBody Contact contact) {
        contactService.updateContact(id, contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);

    }

    @DeleteMapping("contact/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable("id") String id) {
        contactService.deleteContact(id);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }


}
