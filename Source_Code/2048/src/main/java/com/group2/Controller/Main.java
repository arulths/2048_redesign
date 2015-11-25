package com.group2.Controller;

import com.group2.Model.Board;
import com.group2.View.GameView;
import com.group2.View.GameWindow;

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
        new GameWindow().setLocationRelativeTo(null);
    }

}