package com.company;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CypherTools cypherTools = new CypherTools();
        //cypherTools.breakCaesarCipher(CypherTexts.CEASER_CYPHER_TEXT);
        //cypherTools.breakJuliasCypher(CypherTexts.JULIAS_CYPHER_TEXT);

        //System.out.println(cypherTools.findHammingDistance("this is a test","wokka wokka!!!"));

        //cypherTools.calculateHammingDistInCypher(CypherTexts.ALAN_CYPHER_TEXT);
        int keyLenght = 24;
        byte[] keyBytes = new byte[12];
        keyBytes[0] =70;
        keyBytes[1] =95;
        keyBytes[2] = 121;
        keyBytes[3] = 95;
        keyBytes[4] = 79;
        keyBytes[5] = 95;
        keyBytes[6] = 95;
        String key = new String (keyBytes);

        int[] mostLetters = new int[keyLenght];
        byte[] keyBits = new byte[keyLenght];

        for(int i=0; i< keyLenght; i++){
            mostLetters[i] = 0;
        }

        byte shift = (byte) 0;
        for(int i =0; i<256; i++){
            shift++;
            cypherTools.splitCypher(keyLenght,CypherTexts.ALAN_CYPHER_TEXT, shift, mostLetters, keyBits);
        }

        //System.out.println("NumberOfLetters: " +mostLetters.toString());
        System.out.println("Key: "+ new String(keyBits));



          key = new String (keyBits);
       String cypherText = new String((cypherTools.encrypt(cypherTools.decode(CypherTexts.ALAN_CYPHER_TEXT), key)));
//
        System.out.println("KEY: "+ key);
        System.out.println(key.getBytes().length);
        System.out.println(cypherText);

        //cypherTools.findLongestMatches(cypherTools.decode(CypherTexts.ALAN_CYPHER_TEXT).toString());
//        System.out.println(cypherTools.findLengthBetweenMatches("HGR6Ig0QJFArAxE4HgsQFzkDUkI",CypherTexts.ALAN_CYPHER_TEXT));
//        System.out.println(cypherTools.findLengthBetweenMatches("gyJRgGQjsrGRoMGzgfGxFCai",CypherTexts.ALAN_CYPHER_TEXT));
//        System.out.println(cypherTools.findLengthBetweenMatches("OgcKFmozCQsKJBMaDQs5Skg",CypherTexts.ALAN_CYPHER_TEXT));
//        System.out.println(cypherTools.findLengthBetweenMatches("eikOGSRQPBcKIx4PWFge",CypherTexts.ALAN_CYPHER_TEXT));
//        System.out.println(cypherTools.findLengthBetweenMatches("oOSYRBkIsPwIBDB9wUC",CypherTexts.ALAN_CYPHER_TEXT));
//        System.out.println(cypherTools.findLengthBetweenMatches("ILHAkMWB4FGgsWLUpI",CypherTexts.ALAN_CYPHER_TEXT));
//        System.out.println(cypherTools.findLengthBetweenMatches("MQQDFmokHRARJBdSQ",CypherTexts.ALAN_CYPHER_TEXT));
//        System.out.println(cypherTools.findLengthBetweenMatches("KHWolRQAXKwQb",CypherTexts.ALAN_CYPHER_TEXT));
//        cypherTools.splitCypher(4,CypherTexts.ALAN_CYPHER_TEXT);
//        String cypherText = cypherTools.tryVisonerrCypher("OSVKEV",CypherTexts.BETA_CYPHER_TEXT);
//        System.out.println(cypherText);
//       cypherTools.splitCypher(6,cypherText);
       //System.out.println();
    }

}
