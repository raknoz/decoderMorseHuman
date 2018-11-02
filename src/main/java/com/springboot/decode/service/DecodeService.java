package com.springboot.decode.service;


public interface DecodeService {
	
	String decodeBits2Morse(String bits);

	String translate2Human(String morse);

	String decodeText2Mose(String texto);
	
}
