package com.group2.View;

import com.group2.Controller.Keyboard;
import com.group2.Model.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Josh Voskamp on 11/25/2015.
 */
public class GameWindow extends JFrame {

    public GameWindow(){
        Board board = new Board(4);
        GameView game = new GameView(board);
        add(game);
        addKeyListener(new Keyboard(game, board));

        setName("2048");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        JMenuBar menuBar = new JMenuBar();

        // Add the menubar to the frame
        setJMenuBar(menuBar);

        // Define and add two drop down menu to the menubar
        JMenu boardSizeMenu = new JMenu("Board Size");
        menuBar.add(boardSizeMenu);

        JMenuItem new4x4 = new JMenuItem("4 x 4");
        JMenuItem new5x5 = new JMenuItem("5 x 5");
        JMenuItem new6x6 = new JMenuItem("6 x 6");

        new4x4.addActionListener(addMenuClick(4));
        new5x5.addActionListener(addMenuClick(5));
        new6x6.addActionListener(addMenuClick(6));

        boardSizeMenu.add(new4x4);
        boardSizeMenu.add(new5x5);
        boardSizeMenu.add(new6x6);

        pack();
        setVisible(true);
    }

    //Creates the ActionListeners for each new board size
    private ActionListener addMenuClick(int w){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                getContentPane().removeAll();
                Board board = new Board(w);
                GameView game = new GameView(board);
                add(game);
                addKeyListener(new Keyboard(game, board));
                pack();
                setLocationRelativeTo(null);
            }
        };
    }
}
