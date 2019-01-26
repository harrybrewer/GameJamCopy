package com.company;

import com.company.logic.Game;
import com.company.ui.gui;

import javax.swing.*;
import java.awt.*;

public class Application {
    public static void main(String[] args) {
        // init the GUI class and fields

        gui frame = new gui();
        frame.setBackground(Color.black);
        frame.setSize(800, 600);
        frame.setTitle("Puzzle bitches");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();

        // Create the game object and give it the GUI's input/output components
        Game game = new Game(frame.output, frame.userInput);
        game.run();

    }
}
