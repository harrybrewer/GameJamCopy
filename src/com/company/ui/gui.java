package com.company.ui;

import com.company.Application;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.*;

public class gui extends JFrame{
    // variable setup

    JPanel mainDisplay;
    JPanel textDisplay;
    public JTextArea output;
    public JTextArea userInput;

    public gui(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainDisplay = new JPanel();
        textDisplay = new JPanel();
        add(mainDisplay, BorderLayout.NORTH);
        add(textDisplay, BorderLayout.SOUTH);
        mainDisplay.setLayout(new GridBagLayout());
        textDisplay.setLayout(new GridBagLayout());

        //constraints.weightx = 1;
        mainDisplay.add(output = new JTextArea());
        output.setLineWrap(true);
        output.setPreferredSize(new Dimension(Application.width, Application.height/2));
        output.setMinimumSize(new Dimension(Application.width, Application.height/2));
        output.setEditable(false);

        textDisplay.add( userInput = new JTextArea());
        userInput.setLineWrap(true);
        userInput.setPreferredSize(new Dimension(Application.width, Application.height/2));
        userInput.setMinimumSize(new Dimension(Application.width, Application.height/2));
        userInput.setEditable(true);
    }
}
