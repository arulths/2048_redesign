package com.group2.Controller;

import com.group2.Model.Board;
import com.group2.View.GameView;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


/**
 * Created by Josh Voskamp on 10/19/2015.
 */

public class Main {
    /**
    * Main class used to set up and initialize a game
    */
    public static void main(String[] args) {
        JFrame game = new JFrame("2048");
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setResizable(false);

        Board board = new Board();
        GameView view = new GameView(board);
        game.add(view);
        game.addKeyListener(new Keyboard(view,board));

        game.pack();

        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }
}