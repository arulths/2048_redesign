package com.josh.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Josh Voskamp on 9/17/2015.
 */
public class keyboard implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left();
                return;
            case KeyEvent.VK_RIGHT:
                right();
                return;
            case KeyEvent.VK_DOWN:
                down();
                return;
            case KeyEvent.VK_UP:
                up();
                return();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
