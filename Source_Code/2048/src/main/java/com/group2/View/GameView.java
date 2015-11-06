package com.group2.View;

import javax.swing.*;
import java.awt.*;

import com.group2.Model.Board;
import com.group2.Model.Tile;


/**
 * Created by Josh Voskamp on 10/19/2015.
 */

public class GameView extends JPanel{
    private final Color BG_COLOR = new Color(0xbbada0);
    private final String FONT_NAME = "Arial";
    private Board board;
    private int TILE_SIZE;
    private int TILES_MARGIN = 15;
    /**
    * Initializes the Game Board's title and margin size
    * @param board a Board object
    * @param scale an int argument
    */
    public GameView(Board board,int scale){
        this.board = board;
        this.TILE_SIZE = scale;
        this.TILES_MARGIN = scale/20;
    }
    /**
     * Draws the graphics
     * @param g is a graphics object
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                drawTile(g, board.getMyTiles()[x + y * 4], x, y);
            }
        }
    }

    /**
    * Sets Board up and displays notifications for when the game is lost or won or can be restarted.
    * @param g2 a Graphics object
    * @param tile a tile object
    * @param x an int argument
    * @param y an in argument
    */
    private void drawTile(Graphics g2, Tile tile, int x, int y) {
        Graphics2D g = ((Graphics2D) g2);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        int value = tile.getValue();
        int xOffset = offsetCoors(x);
        int yOffset = offsetCoors(y);
        g.setColor(tile.getBackground());

        g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 14, 14);
        g.setColor(tile.getForeground());

        //This needs to scale
        final int size = value < 100 ? 200 : value < 1000 ? 175 : 135;

        final Font font = new Font(FONT_NAME, Font.BOLD, size);
        g.setFont(font);

        String s = String.valueOf(value);
        final FontMetrics fm = getFontMetrics(font);

        final int w = fm.stringWidth(s);
        final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];

        if (value != 0)

            //Fix this line to center the number on the tiles
            g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2);

        if (board.isMyWin() || board.isMyLose()) {
            g.setColor(new Color(255, 255, 255, 30));
            g.fillRect(0, 0, getWidth(), getHeight());

            //This needs to scale
            g.setFont(new Font(FONT_NAME, Font.PLAIN, 50));
            g.setColor(new Color(128, 128, 128, 128));

            //Change this line to scale
            drawCenteredString("Press ESC to play again", getWidth(), getHeight() * 2 - g.getFontMetrics().getHeight()-100, g);

            g.setColor(new Color(78, 139, 202));

            //This needs to scale
            g.setFont(new Font(FONT_NAME, Font.BOLD, 48));

            if (board.isMyWin()) {
                //Change this line to scale
                drawCenteredString("You won!", getWidth(), getHeight(), g);
            }
            if (board.isMyLose()) {
                //Change these lines to scale
                drawCenteredString("Game over!", getWidth(), getHeight()-getHeight()/5,g);
                drawCenteredString("You lose!", getWidth(), getHeight()+getHeight()/5,g);
            }
        }

        g.setColor(new Color(128, 128, 128, 128));

        // /This needs to be scaled
        g.setFont(new Font(FONT_NAME, Font.PLAIN, 70));

        //Change this line to scale
        drawCenteredString(("Score: " + board.getMyScore()), this.getWidth(), this.getHeight() * 2, g);

    }

    /**
     * Calculates the coordinate to start drawing a tile at using the tile number.
     * @param arg an int argument
     * @return int, the coordinates
     */

    private int offsetCoors(int arg) {
        return arg * (this.TILES_MARGIN + this.TILE_SIZE) + this.TILES_MARGIN;
    }

    /**
     * Calculates where a string should be displayed based on screen size.
     * @param s a string argument
     * @param w an integer argument
     * @param h an integer argument
     * @param g a graphics object
     * @return graphics object displaying a string
     */

    public void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
    }

}