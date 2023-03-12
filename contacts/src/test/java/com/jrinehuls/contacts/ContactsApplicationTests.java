package com.jrinehuls.contacts;

import com.fasterxml.jackson.databind.ObjectMapper;
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

	@Autowired
	private ObjectMapper objectMapper; // Converts POJO into JSON

	private final Contact[] CONTACTS = {
			new Contact("123", "Justin", "Rinehuls", 38),
			new Contact("456", "Jennifer", "Getz", 39),
			new Contact("789", "Yuichi", "Kun", 7)
	};

	@BeforeEach
	void initialize() {
		for (Contact contact: CONTACTS) {
			contactRepository.saveContact(contact);
		}
	}

	@AfterEach
	void clear() {
		contactRepository.getContacts().clear();
	}

	@Test
	void getAllContactsTest() throws Exception {
		mockMvc.perform(get("/contact"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.size()").value(CONTACTS.length))
				.andExpect(jsonPath("$.[?(@.firstName==\"Yuichi\" && @.lastName==\"Kun\" && @.age==7)]").exists());

	}

	@Test
	void getValidContactTest() throws Exception {
		mockMvc.perform(get("/contact/123"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.firstName").value(CONTACTS[0].getFirstName()))
				.andExpect(jsonPath("$.age").value(CONTACTS[0].getAge()));
	}

	@Test
	void getInvalidContactTest() throws Exception {
		mockMvc.perform(get("/contact/1345234"))
				.andExpect(status().isNotFound());
	}

	@Test
	void createValidContactTest() throws Exception {
		mockMvc.perform(post("/contact")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new Contact("Patty", "Chickie", 36)))
		)
				.andExpect(status().isCreated());
	}

	@Test
	void createInvalidContactTest() throws Exception {
		mockMvc.perform(post("/contact")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new Contact("Patty", "Chickie", 3)))
		)
				.andExpect(status().isBadRequest());
	}

	@Test
	void contextLoads() {
		Assertions.assertNotNull(mockMvc);
	}

}
