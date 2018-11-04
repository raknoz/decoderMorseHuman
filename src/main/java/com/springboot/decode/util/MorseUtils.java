package com.springboot.decode.util;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author David 1/11/2018
 */

@Component
public class MorseUtils {

    private static Map<Character, String> letterToMorse;
    private static Map<String, Character> morseToLetter;


    @PostConstruct
    private void initData(){
        //Cargo los mapas con la informacion para traducir
        letterToMorse = new HashMap<>();
        letterToMorse.put('a', ".-");
        letterToMorse.put('b', "-...");
        letterToMorse.put('c', "-.-");
        letterToMorse.put('d', "-..");
        letterToMorse.put('e', ".");
        letterToMorse.put('f', "..-.");
        letterToMorse.put('g', "--.");
        letterToMorse.put('h', "....");
        letterToMorse.put('i', "..");
        letterToMorse.put('j', ".---");
        letterToMorse.put('k', "-.");
        letterToMorse.put('l', ".-..");
        letterToMorse.put('m', "--");
        letterToMorse.put('n', "-.");
        letterToMorse.put('o', "---");
        letterToMorse.put('p', ".--.");
        letterToMorse.put('q', "--.-");
        letterToMorse.put('r', ".-.");
        letterToMorse.put('s', "...");
        letterToMorse.put('t', "-");
        letterToMorse.put('u', "..-");
        letterToMorse.put('v', "...-");
        letterToMorse.put('w', ".--");
        letterToMorse.put('x', "-..-");
        letterToMorse.put('y', "-.--");
        letterToMorse.put('z', "--..");
        letterToMorse.put('1', ".----");
        letterToMorse.put('2', "..---");
        letterToMorse.put('3', "...--");
        letterToMorse.put('4', "....-");
        letterToMorse.put('5', ".....");
        letterToMorse.put('6', "-....");
        letterToMorse.put('7', "--...");
        letterToMorse.put('8', "---..");
        letterToMorse.put('9', "----.");
        letterToMorse.put('0', "-----");
        letterToMorse.put(',', "--..--");
        letterToMorse.put('.', ".-.-.-");
        letterToMorse.put('?', "..--..");

        morseToLetter = new HashMap<>();
        for(Map.Entry<Character, String> entry : letterToMorse.entrySet()){
            morseToLetter.put(entry.getValue(), entry.getKey());
        }
    }

    public static Character getLetterFromMorse(String morseCode){
        return morseToLetter.get(morseCode);
    }

    public static String getMorseFromLetter(Character letter){
        return letterToMorse.get(letter);
    }

    public static Map<Character, String> getLetterToMorse() {
        return letterToMorse;
    }

    public static void setLetterToMorse(Map<Character, String> letterToMorse) {
        MorseUtils.letterToMorse = letterToMorse;
    }

    public static Map<String, Character> getMorseToLetter() {
        return morseToLetter;
    }

    public static void setMorseToLetter(Map<String, Character> morseToLetter) {
        MorseUtils.morseToLetter = morseToLetter;
    }
}
