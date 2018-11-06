package com.springboot.decode;

import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class SpringBootRestTestClient {
 
    public static final String REST_SERVICE_URI = "http://localhost:8080/SpringBootRestApi/api";
     
    /* POST */
    private static void sendBits() {
        System.out.println("Testing Send bits to API----------");
        RestTemplate restTemplate = new RestTemplate();
        String bits = "101010100011011011000101101010001011000011011000101011000110100011010100011011011";
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/translateBits2Morse/", bits);
        System.out.println("Result : "+ uri.toASCIIString());
    }

    public static void main(String args[]){
        sendBits();
    }
}