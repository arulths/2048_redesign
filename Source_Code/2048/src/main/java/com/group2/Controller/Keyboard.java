package com.group2.Controller;

import com.group2.Model.Board;
import com.group2.View.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Josh Voskamp on 10/19/2015.
 */

public class Keyboard implements KeyListener {

    private GameView  game;
    private Board board;
    /**
     * Constructor Creates a controller that links a GameView and Board together
     * @param game a GameView
     * @param board a Board
    */
    Keyboard(GameView game, Board board){
        this.game = game;
        this.board = board;
    }
    /**
     * Modifies the model based on the key pressed, updates GameView to display any changes made
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            board.resetGame();
        }
        if (!board.canMove()) {
            board.setMyLose(true);
        }

        if (!board.isMyWin() && !board.isMyLose()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    board.left();
                    break;
                case KeyEvent.VK_RIGHT:
                    board.right();
                    break;
                case KeyEvent.VK_DOWN:
                    board.down();
                    break;
                case KeyEvent.VK_UP:
                    board.up();
                    break;
            }
        }

        if (!board.isMyWin() && !board.canMove()) {
            board.setMyLose(true);
        }

        game.repaint();
    }
    /**
     * Not Used
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
    /**
     * Not Used
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }
}