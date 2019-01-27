package com.company.logic;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gui extends JFrame {
    // variable setup

    JTextArea userInput;
    static boolean usingComputer;
    static boolean safePuzzle;
    Game game;
    computerGame comp;
    SafePuzzle safe;
    static boolean computerBroken;
    static boolean computerCompleted;
    static boolean safeCompleted;
    static boolean safeBroken;

    private Gui() {
        JPanel mainDisplay = new JPanel();
        JPanel textDisplay = new JPanel();
        JPanel responseDisplay = new JPanel();
        add(mainDisplay, BorderLayout.NORTH);
        add(responseDisplay, BorderLayout.CENTER);
        add(textDisplay, BorderLayout.SOUTH);


        JTextArea response;
        responseDisplay.add(response = new JTextArea());
        response.setLineWrap(true);
        response.setPreferredSize(new Dimension(780, 100));
        response.setEditable(false);
        response.setBackground(Color.black);
        response.setForeground(Color.green);
        response.setFont(new Font("Arial", Font.PLAIN, 20));


        JTextArea output;
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

        // Puzzle and game init
        game = new Game(output, this.userInput, response);
        comp = new computerGame(output, response, userInput);
        safe = new SafePuzzle(output, response,userInput);
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
            if(com.company.logic.Gui.usingComputer){
                Gui.comp.readUserInput(command);
            }else if(com.company.logic.Gui.safePuzzle){
                Gui.safe.readUserInput(command);
            }
            else {
                Gui.game.readUserInput(command);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}