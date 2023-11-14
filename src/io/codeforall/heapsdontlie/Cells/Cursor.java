package io.codeforall.heapsdontlie.Cells;

import io.codeforall.heapsdontlie.Grid;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Cursor extends Cell implements KeyboardHandler {

    private Keyboard keyboard;
    private boolean spaceIsHeld = false;
    private boolean dIsHeld = false;

    public Cursor(Grid grid) {

        super(0, 0, grid);

        keyboard = new Keyboard(this);
        rectangle.setColor(Color.MAGENTA);
        rectangle.fill();
        init();
    }


    private void init() {
        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent space = new KeyboardEvent();
        space.setKey(KeyboardEvent.KEY_SPACE);
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent spaceRelease = new KeyboardEvent();
        spaceRelease.setKey(KeyboardEvent.KEY_SPACE);
        spaceRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent d = new KeyboardEvent();
        d.setKey(KeyboardEvent.KEY_D);
        d.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent dRelease = new KeyboardEvent();
        dRelease.setKey(KeyboardEvent.KEY_D);
        dRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent s = new KeyboardEvent();
        s.setKey(KeyboardEvent.KEY_S);
        s.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent l = new KeyboardEvent();
        l.setKey(KeyboardEvent.KEY_L);
        l.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent c = new KeyboardEvent();
        c.setKey(KeyboardEvent.KEY_C);
        c.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(left);
        keyboard.addEventListener(right);
        keyboard.addEventListener(up);
        keyboard.addEventListener(down);
        keyboard.addEventListener(space);
        keyboard.addEventListener(spaceRelease);
        keyboard.addEventListener(d);
        keyboard.addEventListener(dRelease);
        keyboard.addEventListener(s);
        keyboard.addEventListener(l);
        keyboard.addEventListener(c);
    }

    private void moveLeft() {

        if(x > 0){
            rectangle.translate(-grid.getCellSize(), 0);
            x--;

        }
    }

    private void moveRight() {

        if (x < grid.getCol() - 1) {
            rectangle.translate(grid.getCellSize(), 0);
            x++;

        }
    }

    private void moveUp() {
        if(y > 0){
            rectangle.translate(0, -grid.getCellSize());
            y--;

        }
    }

    private void moveDown() {
        if(y < grid.getRow() - 1){
            rectangle.translate(0, grid.getCellSize());
            y++;

        }
    }

    private void updateCell() {
        if (spaceIsHeld) {
            colorCell();
        }
        if (dIsHeld) {
            unColorCell();
        }
    }

    private void colorCell() {
        grid.getCells()[x][y].paint();
    }

    private void unColorCell() {

        grid.getCells()[x][y].erase();
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                moveLeft();
                updateCell();
                break;
            case KeyboardEvent.KEY_RIGHT:
                moveRight();
                updateCell();
                break;
            case KeyboardEvent.KEY_UP:
                moveUp();
                updateCell();
                break;
            case KeyboardEvent.KEY_DOWN:
                moveDown();
                updateCell();
                break;
            case KeyboardEvent.KEY_SPACE:
                if (!spaceIsHeld) {
                    spaceIsHeld = true;
                    colorCell();
                }
                break;
            case KeyboardEvent.KEY_D:
                if (!dIsHeld) {
                    dIsHeld = true;
                    unColorCell();
                }
                break;
            case KeyboardEvent.KEY_S:
                grid.saveFile();
                break;
            case KeyboardEvent.KEY_L:
                grid.loadFile();
                break;
            case KeyboardEvent.KEY_C:
                grid.clearImage();
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_SPACE:
                spaceIsHeld = false;
                break;
            case KeyboardEvent.KEY_D:
                dIsHeld = false;
                break;
        }
    }
}
