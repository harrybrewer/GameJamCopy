package com.company.ui;

import com.company.logic.Game;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gui extends JFrame {
    // variable setup

    public JTextArea output;
    public JTextArea userInput;
    public JTextArea response;
    Game game;

    public Gui() {
        JPanel mainDisplay = new JPanel();
        JPanel textDisplay = new JPanel();
        JPanel responseDisplay = new JPanel();
        add(mainDisplay, BorderLayout.NORTH);
        add(responseDisplay, BorderLayout.CENTER);
        add(textDisplay, BorderLayout.SOUTH);

        responseDisplay.add(response = new JTextArea());
        response.setLineWrap(true);
        response.setPreferredSize(new Dimension(780, 100));
        response.setEditable(false);
        response.setBackground(Color.black);
        response.setForeground(Color.green);
        response.setFont(new Font("Arial", Font.PLAIN, 20));

        mainDisplay.add(output = new JTextArea());
        output.setLineWrap(true);
        output.setPreferredSize(new Dimension(780, 360));
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

        userInput.addKeyListener(new InputListener(this));
        game = new Game(this.output, this.userInput, this.response);
    }

    public static void main(String[] args) {
        Gui frame = new Gui();
        frame.setBackground(Color.black);
        frame.setSize(800, 600);
        frame.setTitle("Puzzle bitches");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();

        // Create the game object and give it the GUI's input/output components
        frame.game.run();
    }
}

class InputListener implements KeyListener{
    private Gui Gui;
    InputListener(Gui Gui){
        this.Gui = Gui;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            String command = Gui.userInput.getText();
            Gui.game.readUserInput(command);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}