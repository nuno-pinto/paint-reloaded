package io.codeforall.heapsdontlie.Cells;

import io.codeforall.heapsdontlie.Grid;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Cell {

    protected Rectangle rectangle;
    protected int x;
    protected int y;
    protected Grid grid;
    private boolean isFilled = false;

    public Cell(int x, int y, Grid grid) {

        this.x = x;
        this.y = y;
        this.grid = grid;

        rectangle = new Rectangle(grid.getPadding() + x * grid.getCellSize(), grid.getPadding() + y * grid.getCellSize(), grid.getCellSize(), grid.getCellSize());
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public void paint() {
        rectangle.setColor(Color.BLACK);
        rectangle.fill();
        isFilled = true;
    }

    public void erase() {
        rectangle.draw();
        isFilled = false;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
