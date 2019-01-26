package com.company.logic;

public class CommandParser {

    static String[] parseCommand(String plainText) {
        String tokens[] = plainText.replaceAll("(^\\s+|\\s+$)", "").split("\\s+");

        for (String t : tokens) {
            t = t.toLowerCase();
            System.out.println(t);
        }
        return tokens;
    }


}