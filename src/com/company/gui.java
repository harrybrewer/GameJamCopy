package com.company;

import javax.swing.*;
import java.awt.*;

public class gui extends JFrame{

    public static void main(String[] args) {
        gui frame = new gui();
        frame.setSize(700, 500);
        frame.setTitle("Draughts");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private gui(){
        JPanel mainDisplay = new JPanel();
        JPanel textDisplay = new JPanel();
        add(mainDisplay, BorderLayout.CENTER);
        add(textDisplay, BorderLayout.SOUTH);

        mainDisplay.add(new JTextArea("Blank"));
        textDisplay.add(new JTextField("user poop"));
    }
}
