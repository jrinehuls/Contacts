package com.jrinehuls.contacts;


import com.jrinehuls.contacts.model.Contact;
import com.jrinehuls.contacts.repository.ContactRepository;
import com.jrinehuls.contacts.service.impl.ContactServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContactServiceImplTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactServiceImpl contactService;

    @Test
    void getIndexByIdTest() {
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("123", "Justin", "Rinehuls", 38));
        contacts.add(new Contact("456", "Jennifer", "Getz", 38));

        when(contactRepository.getContacts()).thenReturn(contacts);

        Assertions.assertEquals(0, contactService.getIndexById("123"));

    }
}
