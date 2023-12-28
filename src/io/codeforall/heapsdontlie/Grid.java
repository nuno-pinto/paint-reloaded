package io.codeforall.heapsdontlie;

import io.codeforall.heapsdontlie.Cells.Cell;

import java.io.*;
import java.util.LinkedList;

public class Grid {


    private final int CELL_SIZE = 15;
    private final int PADDING = 10;
    private int colNum;
    private int rowNum;

    private Cell[][] cells;

    public Grid(int cols, int rows) {
        this.colNum = cols;
        this.rowNum = rows;
        cells = new Cell[colNum][rowNum];

    }

    public void init() {

        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                cells[i][j] = new Cell(i, j, this);
                cells[i][j].getRectangle().draw();
            }
        }

    }

    public void saveFile() {

        LinkedList<String> cellsFilled = new LinkedList<>();

        for(Cell[] cols : cells) {
            for (Cell cell : cols) {
                if (cell.isFilled()) {
                    cellsFilled.add(cell.getX() + ":" + cell.getY());
                }
            }
        }

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("file/picture.txt"));

            for (String str : cellsFilled) {
                bufferedWriter.write(str + "\n");
            }

            bufferedWriter.close();

            System.out.println("Saved picture");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadFile() {


        clearImage();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("file/picture.txt"));

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {

                int cellCol = Integer.valueOf(line.split(":")[0]);
                int cellRow = Integer.valueOf(line.split(":")[1]);

                cells[cellCol][cellRow].paint();

            }

            bufferedReader.close();

            System.out.println("Loaded picture");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clearImage() {
        for(Cell[] cols : cells) {
            for (Cell cell : cols) {
                cell.erase();
            }
        }

        System.out.println("Cleared image");
    }

    public int getCellSize() {
        return CELL_SIZE;
    }

    public int getPadding() {
        return PADDING;
    }

    public int getCol() {
        return colNum;
    }

    public int getRow() {
        return rowNum;
    }

    public Cell[][] getCells() {
        return cells;
    }
}
