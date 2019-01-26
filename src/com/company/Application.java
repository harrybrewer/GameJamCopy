package com.company;


import com.company.logic.Game;
import com.company.ui.gui;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        // init the GUI class and fields
        gui frame = new gui();
        frame.setSize(700, 500);
        frame.setTitle("Draughts");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        // Create the game object and give it the GUI's input/output components
        Game game = new Game(frame.output, frame.userInput);

        game.run();

    }
}
