package com.company.logic;

public class CommandParser {

    static String[] parseCommand(String plainText) {
        String tokens[] = plainText.split(" ");

        for (String t : tokens) {
            System.out.println(t);
        }
        return tokens;
    }


}