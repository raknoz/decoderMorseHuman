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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
    DecodeService decodeService;

    @PostMapping(value = "/translateBits2Morse/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiJsonResponse<String> decodeBits2Morse(@RequestBody String request) {

        ApiJsonResponse<String> response = new ApiJsonResponse<>();
        String morseStr;
	    try {
            JSONObject obj = new JSONObject(request);
            String bits = obj.getString("text");
            Pattern pattern = Pattern.compile("^[0-1]*$");
            Matcher matcher = pattern.matcher(bits);

            //Valido que no ponga caracteres diferentes
            if(!matcher.matches()){
                return response.error(HttpStatus.BAD_REQUEST.value(), "Request con caracteres no incluidos");
            }

            //Quito los espacios al principio y al final de la cadena de caracteres
            int inicio = bits.indexOf("1");
            int fin = bits.lastIndexOf("1");
            if(inicio > 0 || fin < bits.length()){
                bits = bits.substring(inicio, fin + 1);
            }

            morseStr = this.decodeService.decodeBits2Morse(bits);
        } catch (Exception ex){
            return response.error(HttpStatus.BAD_REQUEST.value(), "Ocurrio un error al procesar el pedido");
        }

		return response.success(HttpStatus.OK.value(), morseStr);
	}

    @PostMapping(value = "/translateMorse2Human/")
	public ApiJsonResponse<String> translate2Human(@RequestBody String request) {

        ApiJsonResponse<String> response = new ApiJsonResponse<>();
        String humanStr;
        try {
            JSONObject obj = new JSONObject(request);
            String text = obj.getString("text");
            humanStr = this.decodeService.translate2Human(text);
        } catch (Exception ex){
            return response.error(HttpStatus.BAD_REQUEST.value(), "Ocurrio un error al procesar el pedido");
        }

		return response.success(HttpStatus.OK.value(), humanStr);
	}

    @PostMapping(value = "/translateHuman2Morse/")
    public ApiJsonResponse<String> translate2Morse(@RequestBody String request) {

        ApiJsonResponse<String> response = new ApiJsonResponse<>();
        String morseCode;
        try {
            JSONObject obj = new JSONObject(request);
            String text = obj.getString("text");
            morseCode = this.decodeService.decodeText2Morse(text);
        } catch (Exception ex){
            return response.error(HttpStatus.BAD_REQUEST.value(), "Ocurrio un error al procesar el pedido");
        }

        return response.success(HttpStatus.OK.value(), morseCode);
    }
}