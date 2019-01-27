package com.company.logic;

public class CommandParser {

    static String[] parseCommand(String plainText) {
        plainText = plainText.toLowerCase();
        return plainText.replaceAll("(^\\s+|\\s+$)", "").split("\\s+");
    }


}