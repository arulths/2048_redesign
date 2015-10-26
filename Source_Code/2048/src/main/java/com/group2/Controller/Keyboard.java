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

    Keyboard(GameView game, Board board){
        this.game = game;
        this.board = board;
    }

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

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}