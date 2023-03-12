package com.jrinehuls.contacts;

import com.jrinehuls.contacts.model.Contact;
import com.jrinehuls.contacts.repository.ContactRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ContactsApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ContactRepository contactRepository;

	private Contact[] contacts = {
			new Contact("123", "Justin", "Rinehuls", 38),
			new Contact("456", "Jennifer", "Getz", 39),
			new Contact("789", "Yuichi", "Kun", 7)
	};

	@BeforeEach
	void initialize() {
		for (Contact contact: contacts) {
			contactRepository.saveContact(contact);
		}
	}

	@AfterEach
	void clear() {
		contactRepository.getContacts().clear();
	}

	@Test
	void getAllContactsTest(){

	}

	@Test
	void getContactTest() throws Exception {
		mockMvc.perform(get("/contact/123"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.firstName").value("Justin"))
				.andExpect(jsonPath("$.age").value(38));
	}

	@Test
	void createContactTest(){}

	@Test
	void updateContactTest(){}

	@Test
	void deleteContactTest(){}

	@Test
	void contextLoads() {
		Assertions.assertNotNull(mockMvc);
	}

}
