package com.springboot.decode.service;


public interface DecodeService {
	
	String decodeBits2Morse(String bits);

	String translate2Human(String morseCode);

	String decodeText2Morse(String texto);
	
}
