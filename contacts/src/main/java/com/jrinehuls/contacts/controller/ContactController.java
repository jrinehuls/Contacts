package com.jrinehuls.contacts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jrinehuls.contacts.service.ContactService;

@Controller
public class ContactController {
    
    @Autowired
    private ContactService contactService;

}
