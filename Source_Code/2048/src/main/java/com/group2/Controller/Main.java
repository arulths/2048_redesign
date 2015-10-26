package com.group2.Controller;

import com.group2.Model.Board;
import com.group2.View.GameView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Josh Voskamp on 10/19/2015.
 */

public class Main {

    public static void main(String[] args) {
        JFrame game = new JFrame();
        game.setTitle("2048 Game");
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int height = ScreenHeight();
        int scale = (height/7);
        int h = (int)(scale*4.26);
        int w = scale*5;
        game.setSize(h,w);

        Board board = new Board();
        GameView view = new GameView(board,scale);
        game.add(view);
        game.addKeyListener(new Keyboard(view,board));

        game.setResizable(false);
        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }

    public static int ScreenHeight(){
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int height = gd.getDisplayMode().getHeight();
        return height;
    }
}