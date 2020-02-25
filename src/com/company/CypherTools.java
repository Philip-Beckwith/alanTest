package com.company;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CypherTools {
    CypherTools(){}

    void breakCaesarCipher(String cypherText){
        for(char key =0; key <=28; key++){
            String keyVal = String.valueOf((char)(key +('A')));
            System.out.println("Key:  "+keyVal+"\n"+tryKey(cypherText,key) + "\n\n\n");
        }
    }

    String tryKey(String cypherText, char key){
        StringBuilder outText = new StringBuilder();
        for(int i =0; i<cypherText.length(); i++){
            outText.append(shiftChar(cypherText.charAt(i),key));
        }
        return outText.toString();
    }

    String shiftChar(char cypher, char key){
        char shiftedCrypto =(char)(cypher+key);
        shiftedCrypto = shiftedCrypto>'Z'? (char) (shiftedCrypto+'a'-'z'-1):shiftedCrypto;
        return String.valueOf(shiftedCrypto);
    }

    public String encode (byte[] text)
    {
        return Base64.getEncoder().encodeToString(text);
    }

    public byte[] decode (String text)
    {
        return Base64.getDecoder().decode(text);
    }

    public byte[] encrypt(byte[] cleartext, byte key)
    {
        byte[] toReturn = new byte[cleartext.length];
        for (int i = 0; i < cleartext.length; i++)
        {
            toReturn[i] = (byte) (cleartext[i] ^ key);
        }
        return toReturn;
    }

    public void breakJuliasCypher(String cypherText){
        for(int key = 0; key<256; key++ ){
            tryJuliasCypher(cypherText, key);
        }
    }

    void tryJuliasCypher(String cypherText, int key){
        System.out.println("Key: "+ Integer.toHexString(key) +"  Int Value:"+ key);
        System.out.println(new String((encrypt(decode(cypherText), (byte)key))));
        System.out.println("\n\n\n\n\n");
    }

    void findLongestMatches(String cypherText){
        ArrayList<String> matches = new ArrayList<String>();
        Matcher m = Pattern.compile("(\\S{2,})(?=.*?\\1)").matcher(cypherText);
        while (m.find()) {
            matches.add(m.group());
        }
        matches.sort((s1, s2) -> s2.length() - s1.length());
        System.out.println(matches);
        int shortestLength = 10000;
        int length = 1000000;
        for(String match:matches){
            if(match.length() > 5){
                length = findLengthBetweenMatches(match,cypherText);}
            if(length<shortestLength){
                System.out.println(length);
                shortestLength = length;
            }
        }
    }

    int findLengthBetweenMatches(String match, String cypherText){
        int index = cypherText.indexOf(match);
        cypherText = cypherText.substring(index+match.length());
        return cypherText.indexOf(match)+match.length();
    }

    void splitCypher(int keyLenght, String cypherText, Byte shift, int[] mostLetters, byte[] key){
        ArrayList<ArrayList<Byte>> groups;
        byte[] byteArray = cypherText.getBytes();
        groups = initGroups(keyLenght);

        for(int index=0; index<byteArray.length; index++){
            groups.get(index%keyLenght).add(byteArray[index]);
        }
        int index =0;
        int count =0;
        for(ArrayList<Byte> group:groups){
            System.out.println("\n\n\n\n\n\n");
            count = printHistogram(group, shift);
            if(count> mostLetters[index]){
                mostLetters[index]=count;
                key[index]=shift;
            }
            count=0;
            index++;
        }
    }

    int printHistogram(ArrayList<Byte> group, byte shift){
        int[] letterCount = new int[500];
        for(int i = 0; i < 260; i++){
            //Set every single number the array to 0.
            letterCount[i] = 0;
        }
        int numberOfletters=0;
        for(byte s : group){
            s = (byte) (s^shift);
            int converted = (int) s;
            //converted -= 65;
           if(converted <400 && converted>=0){
                letterCount[converted] += 1;
                if (converted<70 && converted>=0){
                    numberOfletters++;
                }
            }
           if(converted<0){
               numberOfletters =0;
           }

        }

////Print out the letter with the frequencies.
//        for(int i = 0; i < 260; i++){
//            char convertback = (char) (i+97);
//            String stars = "";
//            for(int j = 0; j < letterCount[i]; j++){
//                stars += "*";
//            }
//            System.out.println(i + " " + stars + " - " + letterCount[i]);
//        }
        return numberOfletters;
    }

    ArrayList<ArrayList<Byte>> initGroups(int keyLenght){
        ArrayList<ArrayList<Byte>> groups = new ArrayList<>();
        for(int i =0; i<keyLenght; i++){
            groups.add(new ArrayList<Byte>());
        }
        return groups;
    }

    public String tryVisonerrCypher(String key, String cypherText){
        StringBuilder outText = new StringBuilder();
        for(int i =0; i<cypherText.length(); i++){
            outText.append(backShiftChar(cypherText.charAt(i),(char)(key.charAt(i%key.length())-65)));
        }
        return outText.toString();
    }

    String backShiftChar(char cypher, char key){
        char shiftedCrypto =(char)(cypher-key);
        shiftedCrypto = shiftedCrypto<'A'? (char) (shiftedCrypto+'Z'-'A'+1):shiftedCrypto;
        return String.valueOf(shiftedCrypto);
    }




//    public static void main(String[] args)
//    {
//        System.out.println(encode(encrypt("test".getBytes(), (byte)0x80)));
//        System.out.println(new String((encrypt(decode("9OXz9A=="), (byte)0x80))));
//    }

    public static byte[] encrypt(byte[] cleartext, String key)
    {
        byte[] toReturn = new byte[cleartext.length];
        byte[] keyBytes = key.getBytes();
        for (int i = 0; i < cleartext.length; i++)
        {
            toReturn[i] = (byte) (cleartext[i] ^ keyBytes[i % keyBytes.length]);
        }
        return toReturn;
    }

//    public static void main(String[] args)
//    {
//        System.out.println(encode(encrypt("this is a test".getBytes(), "12")));
//        System.out.println(new String((encrypt(decode("RVpYQRFbQhJQEkVXQkY="), "12"))));
//    }

    int findHammingDistance(String block1, String block2){
        int count = 0;

        for(int i=0; i<block1.length(); i++) {
            count += findCharDistance(block1.charAt(i),block2.charAt(i));
        }
        return count;
    }
    int findCharDistance(char char1, char char2){
        return solve((int)char1,(int) char2);
    }

    int solve(int A, int B)
    {
        int count = 0;
        // since, the numbers are less than 2^31
        // run the loop from '0' to '31' only
        for (int i = 0; i < 32; i++) {

            // right shift both the numbers by 'i' and
            // check if the bit at the 0th position is different
            if (((A >> i) & 1) != ((B >> i) & 1)) {
                count++;
            }
        }
        return count;
    }

    int findByteHamm(byte b1, byte b2){
        return solve(b1,b2);
    }

    void calculateHammingDistInCypher(String cypherText){
        byte [] byteArray = cypherText.getBytes();
        ArrayList<Double> hamming = new ArrayList<>();
        for(int i=2; i<15; i++){
            Double ham = 0.0;
            for(int j=0; j<i; j++){
                ham += findByteHamm(byteArray[j], byteArray[i+j]);
            }
            ham = ham/i;
            System.out.println("Lenght: "+ i + " Hamm: "+ ham);
            hamming.add(ham);
        }
        Collections.sort(hamming);
        System.out.println(hamming);
    }



}
