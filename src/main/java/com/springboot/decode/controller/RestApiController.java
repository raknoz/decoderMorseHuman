package com.springboot.decode.controller;

import com.springboot.decode.service.DecodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
    DecodeService decodeService;

	@RequestMapping(value = "/decodeBits2Morse/{bits}", method = RequestMethod.GET)
	public ResponseEntity<String> decodeBits2Morse(@PathVariable("bits") String bits) {

        String morseStr = "";

	    try {
            morseStr = this.decodeService.decodeBits2Morse(bits);
        } catch (Exception ex){
            return new ResponseEntity<>(morseStr, HttpStatus.BAD_REQUEST);
        }

		return new ResponseEntity<>(morseStr, HttpStatus.OK);
	}

	@RequestMapping(value = "/translate2Human/{morseCode}", method = RequestMethod.GET)
	public ResponseEntity<String> getUser(@PathVariable("morseCode") String morseCode) {

        String humanStr = "";
        try {
            humanStr = this.decodeService.translate2Human(morseCode);
        } catch (Exception ex){
            return new ResponseEntity<>(humanStr, HttpStatus.BAD_REQUEST);
        }

		return new ResponseEntity<>(humanStr, HttpStatus.OK);
	}
}