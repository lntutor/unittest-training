package com.unittest.demo;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unittest.demo.controller.StudentController;
import com.unittest.demo.repository.StudentRepository;

public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentController studentController;

    @Before
    public void init () {
    }

    /*
     * converts a Java object into JSON representation
     * use for post/put Restful testing
     */
    public static String asJsonString (final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

