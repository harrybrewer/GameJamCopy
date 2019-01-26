package com.company.ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.*;

public class gui extends JFrame {
    // variable setup

    public JTextArea output;
    public JTextArea userInput;

    public gui() {
        JPanel mainDisplay = new JPanel();
        JPanel textDisplay = new JPanel();
        add(mainDisplay, BorderLayout.NORTH);
        add(textDisplay, BorderLayout.SOUTH);
        mainDisplay.setLayout(new GridBagLayout());
        textDisplay.setLayout(new GridBagLayout());

        mainDisplay.add(output = new JTextArea());
        output.setLineWrap(true);
        output.setPreferredSize(new Dimension(780, 460));
        output.setEditable(false);
        output.setBackground(Color.black);
        output.setForeground(Color.green);
        output.setFont(new Font("Arial", Font.PLAIN, 20));

        userInput = new JTextArea(" ");
        userInput.setLineWrap(true);
        userInput.setPreferredSize(new Dimension(780, 100));
        userInput.setEditable(true);
        userInput.setBackground(Color.black);
        userInput.setForeground(Color.green);
        userInput.setFont(new Font("Arial", Font.PLAIN, 20));

        Border border = BorderFactory.createLineBorder(Color.WHITE);
        userInput.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        textDisplay.add(userInput);
    }
}
