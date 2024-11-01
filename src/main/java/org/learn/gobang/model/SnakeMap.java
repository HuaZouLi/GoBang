package org.learn.gobang.model;

public class SnakeMap {
    private int[] map;

    private int col;
    private int row;


    public int[] getMap() {
        return map;
    }

    public void setMap(int[] map) {
        this.map = map;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public SnakeMap(int col, int row) {
        this.col = col;
        this.row = row;
        map = new int[col * row];
    }


}
