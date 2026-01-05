package com.akshay.testing.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PeopleFlowIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    void fullFlowTest() throws Exception {
        mockMvc.perform(post("/people")
                        .contentType("application/json")
                        .content("{\"name\":\"Raj\"}"))
                .andExpect(status().isOk());
    }
}



