package com.springboot.decode.controller;

import com.springboot.decode.service.DecodeService;
import com.springboot.decode.util.ApiJsonResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
    DecodeService decodeService;

    @PostMapping(value = "/decodeBits2Morse/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiJsonResponse<String> decodeBits2Morse(@RequestBody String request) {

        ApiJsonResponse<String> response = new ApiJsonResponse<>();

        String morseStr = "";
	    try {
            JSONObject obj = new JSONObject(request);
            String bits = obj.getString("bits");
            morseStr = this.decodeService.decodeBits2Morse(bits);
        } catch (Exception ex){
            return response.error(HttpStatus.BAD_REQUEST.value(), "Request Mal formado");
        }

		return response.success(HttpStatus.OK.value(), morseStr);
	}

    @PostMapping(value = "/translate2Human/")
    @ResponseBody
	public ApiJsonResponse<String> getUser(@RequestBody String morseCode) {

        ApiJsonResponse<String> response = new ApiJsonResponse<>();

        String humanStr = "";
        try {
            humanStr = this.decodeService.translate2Human(morseCode);
        } catch (Exception ex){
            return response.error(HttpStatus.BAD_REQUEST.value(), humanStr);
        }

		return response.success(HttpStatus.OK.value(), humanStr);
	}
}