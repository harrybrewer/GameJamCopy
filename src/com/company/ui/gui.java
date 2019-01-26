package com.company.ui;

import javax.swing.*;
import java.awt.*;

public class gui extends JFrame{
    // variable setup
     JPanel mainDisplay;
     JPanel textDisplay;

    public JTextArea output;
    public JTextField userInput;

    public gui(){
        mainDisplay = new JPanel();
        textDisplay = new JPanel();
        add(mainDisplay, BorderLayout.NORTH);
        add(textDisplay, BorderLayout.SOUTH);

        mainDisplay.add(output = new JTextArea());
        output.setEditable(false);
        output.setRows(10);
        output.setColumns(45);

        textDisplay.add( userInput = new JTextField());
        userInput.setColumns(10);
    }
}
