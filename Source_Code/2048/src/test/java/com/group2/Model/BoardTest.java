package com.group2.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {
    private Board board;

    @Before
    public void setUp(){
        board = new Board();
        cleanBoard();
    }

    @After
    public void tearDown(){
        board = null;
    }

    @Test
    public void testLeft(){
        setTile(0,0,2);
        setTile(0,1,2);
        board.left();
        assertEquals(getTile(0,0),4);
        cleanBoard();
    }

    @Test
    public void testRight(){
        setTile(0,0,2);
        setTile(0,1,2);
        board.right();
        assertEquals(getTile(0,3),4);
        cleanBoard();
    }

    @Test
    public void testUp(){
        setTile(0,1,2);
        setTile(1,1,2);
        board.up();
        assertEquals(getTile(0,1),4);
        cleanBoard();
    }

    @Test
    public void testDown(){
        setTile(0,1,2);
        setTile(1,1,2);
        board.down();
        assertEquals(getTile(3,1),4);
        cleanBoard();
    }

    @Test
    public void getGameState(){
        assertEquals(Board.State.IN_PROGRESS,board.getGameState());
    }

    //Clears entire board
    public void cleanBoard(){
        Tile[][] temp = board.getGrid();
        for(int i = 0; i < temp.length; i++){
            for(int j = 0; j < temp[i].length;j++){
                temp[i][j].setValue(0);
            }
        }
    }

    public void setTile(int row, int col, int v){
        board.getGrid()[row][col].setValue(v);
    }

    public int getTile(int row, int col){
        return board.getGrid()[row][col].getValue();
    }

}