package com.group2.Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Josh Voskamp on 10/19/2015.
 */

public class Board {
    private Tile[] myTiles;
    boolean myWin = false;
    boolean myLose = false;
    int myScore = 0;

    /**
    * Creates a fresh Game Board
    */
    public Board(){
        resetGame();
    }
    /**
    * Sets the parameters for resetting the game
    */
    public void resetGame() {
        myScore = 0;
        myWin = false;
        myLose = false;
        myTiles = new Tile[4 * 4];
        for (int i = 0; i < myTiles.length; i++) {
            myTiles[i] = new Tile();
        }
        addTile();
        addTile();
    }
    /**
    * Simulates a move on the Gameboard to the left, determines how tiles are merged together and whether new tiles need to be spawned.
    */
    public void left() {
        boolean needAddTile = false;
        for (int i = 0; i < 4; i++) {
            Tile[] line = getLine(i);
            Tile[] merged = mergeLine(moveLine(line));
            setLine(i, merged);
            if (!needAddTile && !compare(line, merged)) {
                needAddTile = true;
            }
        }

        if (needAddTile) {
            addTile();
        }
    }
    /**
    * Simulates a move on the Game board to the right, uses left() logic.
    */
    public void right() {
        myTiles = rotate(180);
        left();
        myTiles = rotate(180);
    }
    /**
    * Simulates an upwards movement on the Game board, uses left() logic.
    */
    public void up() {
        myTiles = rotate(270);
        left();
        myTiles = rotate(90);
    }
    /**
    * Simulates downwards movement on the Game board, uses left() logic.
    */
    public void down() {
        myTiles = rotate(90);
        left();
        myTiles = rotate(270);
    }

    private Tile tileAt(int x, int y) {
        return myTiles[x + y * 4];
    }
    /**
    * Pseudo-randomly determines where to place a new tile on the Game board.
    */
    private void addTile() {
        List<Tile> list = availableSpace();
        if (!availableSpace().isEmpty()) {
            int index = (int) (Math.random() * list.size()) % list.size();
            Tile emptyTime = list.get(index);
            emptyTime.value = Math.random() < 0.9 ? 2 : 4;
        }
    }
    /**
    * Determines if there are any spaces on the gameboard.
    * @return list, list of tiles
    */
    private List<Tile> availableSpace() {
        final List<Tile> list = new ArrayList<Tile>(16);
        for (Tile t : myTiles) {
            if (t.isEmpty()) {
                list.add(t);
            }
        }
        return list;
    }
    /**
    * Determines whethere there are any available spaces on the Game board.
    * @return boolean, if there are any available spaces
    */
    private boolean isFull() {
        return availableSpace().size() == 0;
    }
    /**
    * Determines if the player can make a move or not
    * @return boolean, whether player can make a move or not
    */
    public boolean canMove() {
        if (!isFull()) {
            return true;
        }
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                Tile t = tileAt(x, y);
                if ((x < 3 && t.value == tileAt(x + 1, y).value)
                        || ((y < 3) && t.value == tileAt(x, y + 1).value)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
    * Determines ??????????
    */
    private boolean compare(Tile[] line1, Tile[] line2) {
        if (line1 == line2) {
            return true;
        } else if (line1.length != line2.length) {
            return false;
        }

        for (int i = 0; i < line1.length; i++) {
            if (line1[i].value != line2[i].value) {
                return false;
            }
        }
        return true;
    }
    /**
    * Determines where a tile would be located at once a player makes a move
    * @return newTiles, Tile objects with new tile positioning
    */
    private Tile[] rotate(int angle) {
        Tile[] newTiles = new Tile[4 * 4];
        int offsetX = 3, offsetY = 3;
        if (angle == 90) {
            offsetY = 0;
        } else if (angle == 270) {
            offsetX = 0;
        }

        double rad = Math.toRadians(angle);
        int cos = (int) Math.cos(rad);
        int sin = (int) Math.sin(rad);
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                int newX = (x * cos) - (y * sin) + offsetX;
                int newY = (x * sin) + (y * cos) + offsetY;
                newTiles[(newX) + (newY) * 4] = tileAt(x, y);
            }
        }
        return newTiles;
    }
    /**
    * Determines how 
    */
    private Tile[] moveLine(Tile[] oldLine) {
        LinkedList<Tile> l = new LinkedList<Tile>();
        for (int i = 0; i < 4; i++) {
            if (!oldLine[i].isEmpty())
                l.addLast(oldLine[i]);
        }
        if (l.size() == 0) {
            return oldLine;
        } else {
            Tile[] newLine = new Tile[4];
            ensureSize(l, 4);
            for (int i = 0; i < 4; i++) {
                newLine[i] = l.removeFirst();
            }
            return newLine;
        }
    }

    private Tile[] mergeLine(Tile[] oldLine) {
        LinkedList<Tile> list = new LinkedList<Tile>();
        for (int i = 0; i < 4 && !oldLine[i].isEmpty(); i++) {
            int num = oldLine[i].value;
            if (i < 3 && oldLine[i].value == oldLine[i + 1].value) {
                num *= 2;
                myScore += num;
                int ourTarget = 2048;
                if (num == ourTarget) {
                    myWin = true;
                }
                i++;
            }
            list.add(new Tile(num));
        }
        if (list.size() == 0) {
            return oldLine;
        } else {
            ensureSize(list, 4);
            return list.toArray(new Tile[4]);
        }
    }

    private static void ensureSize(java.util.List<Tile> l, int s) {
        while (l.size() != s) {
            l.add(new Tile());
        }
    }

    private Tile[] getLine(int index) {
        Tile[] result = new Tile[4];
        for (int i = 0; i < 4; i++) {
            result[i] = tileAt(i, index);
        }
        return result;
    }

    private void setLine(int index, Tile[] re) {
        System.arraycopy(re, 0, myTiles, index * 4, 4);
    }

    public void setMyTiles(Tile[] myTiles) {
        this.myTiles = myTiles;
    }

    public void setMyWin(boolean myWin) {
        this.myWin = myWin;
    }

    public void setMyLose(boolean myLose) {
        this.myLose = myLose;
    }

    public void setMyScore(int myScore) {
        this.myScore = myScore;
    }

    public Tile[] getMyTiles() {
        return myTiles;
    }

    public boolean isMyWin() {
        return myWin;
    }

    public boolean isMyLose() {
        return myLose;
    }

    public int getMyScore() {
        return myScore;
    }

}