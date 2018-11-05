package com.springboot.decode.service;

import com.springboot.decode.util.CustomErrorType;
import com.springboot.decode.util.MorseUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("decodeService")
public class DecodeServiceImpl implements DecodeService {

    final private String SEPARATE_WORD = "    ";
    final private String CHAR_DOT = ".";
    final private String CHAR_LINE = "-";
    final private String CHAR_EMPTY = "";

    private String[] listBits;
    private String[] listPause;

    @Override
    public String decodeBits2Morse(String bits) {

        String textInMorse;
        try {
            this.listBits = bits.replaceAll("0", " ").trim().split(" ");
            this.listPause = bits.replaceAll("1", " ").trim().split(" ");
            textInMorse = processBits(bits, this.listBits, this.listPause);
        } catch (Exception ex){
            throw new CustomErrorType("Ocurrio un error al procesar el pedido");
        }

        return textInMorse;
    }

    @Override
    public String translate2Human(String morseCode) {
        return null;
    }

    @Override
    public String decodeText2Morse(String texto) {

        String[] words = texto.toLowerCase().trim().split(" ");
        String result = "";

        for (String word : words) {
            for (int w = 0; w < word.length(); w++) {
                Character caracter = word.substring(w, w + 1).charAt(0);
                result += MorseUtils.getMorseFromLetter(caracter) + " ";
            }
            result += "   ";
        }

        return result;
    }

    private String processBits(String bits, String[] listBits, String[] listPause) {

        Integer dotLimit = null;
        Integer lineLimit = null;
        String dot = "";
        String line = "";
        Boolean first = true;

        //Obtengo el valor en bits del punto y la linea
        for (int x = 0; x < listBits.length; x++) {

            if (!first) {
                if (!listBits[x].isEmpty() && listBits[x].length() < dotLimit) {
                    dotLimit = listBits[x].length();
                    dot = listBits[x];
                } else if (!listBits[x].isEmpty() && listBits[x].length() > lineLimit) {
                    lineLimit = listBits[x].length();
                    line = listBits[x];
                }
            } else {
                dotLimit = listBits[x].length();
                dot = listBits[x];
                lineLimit = listBits[x].length();
                line = listBits[x];
                first = false;
            }
        }

        String wordSpace = "";
        Integer wordLimit = null;
        String letterSpace = "";

        //Obtengo el valor del espacio entre palabras y letras
        first = true;
        for (int x = 0; x < listPause.length; x++) {
            if (!first) {
                if (!listPause[x].isEmpty() && listPause[x].length() > wordLimit) {
                    letterSpace = wordSpace;
                    wordLimit = listPause[x].length();
                    wordSpace = listPause[x];
                }

            } else {
                wordLimit = listPause[x].length();
                wordSpace = listPause[x];
                letterSpace = listPause[x];
                first = false;
            }
        }

        Map<String, String> params = new HashMap<>();
        params.put("CHAR_LINE" , line);
        params.put("CHAR_DOT" , dot);
        params.put("SEPARATE_LATTER" , letterSpace);
        params.put("SEPARATE_WORD" , wordSpace);

        return this.generateMorseFromBits(bits, params);


    }

    private String generateMorseFromBits(String texto, Map<String, String> params){

        String tempMorse = texto.replaceAll(params.get("SEPARATE_WORD"), SEPARATE_WORD);
        tempMorse = tempMorse.replaceAll(params.get("CHAR_LINE"), CHAR_LINE);
        tempMorse = tempMorse.replaceAll(params.get("CHAR_DOT"), CHAR_DOT);
        tempMorse = tempMorse.replaceAll(params.get("SEPARATE_LATTER"), " ");
        tempMorse = tempMorse.replaceAll("0", CHAR_EMPTY);

        return tempMorse;
    }

    private String translateMorse2Human(String morseCode){

        String[] splitWords = morseCode.split(SEPARATE_WORD);
        String translate = "";
        for(String words : splitWords){
            String[] letters = words.split(" ");
            for(String letter : letters){
                translate += MorseUtils.getLetterFromMorse(letter);
            }
            translate +=" ";
        }

        return translate;
    }
}
