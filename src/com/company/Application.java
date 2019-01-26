package com.company;


import com.company.logic.Game;
import com.company.ui.gui;

import javax.swing.*;

public class Application {
    public static int width = 800;
    public static int height = 600;
    public static void main(String[] args) {
        // init the GUI class and fields

        gui frame = new gui();
        frame.setSize(width, height);
        frame.setTitle("Draughts");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        // Create the game object and give it the GUI's input/output components
        Game game = new Game(frame.output, frame.userInput);

        game.run();

    }
}
