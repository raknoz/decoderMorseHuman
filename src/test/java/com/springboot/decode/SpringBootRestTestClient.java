package com.springboot.decode;

import com.springboot.decode.controller.RestApiController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestApiController.class)
public class SpringBootRestTestClient {
 
    public static final String REST_SERVICE_URI = "http://localhost:8080/SpringBootRestApi/api";

    @Autowired
    private MockMvc mvc;
     
//    @Test
//    private static void sendBits() {
//        System.out.println("Testing Send bits to API----------");
//        RestTemplate restTemplate = new RestTemplate();
//        String bits = "101010100011011011000101101010001011000011011000101011000110100011010100011011011";
//        URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/translateBits2Morse/", bits);
//        System.out.println("Result : "+ uri.toASCIIString());
//
//
//        mvc.perform(get("/translateBits2Morse/")).
//    }

    @Test
    public void addEmployeeTest() throws Exception {

        mvc.perform(post("/api/translateBits2Morse/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isOk());
    }

}