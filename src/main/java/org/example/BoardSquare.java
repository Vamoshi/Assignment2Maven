package org.example;

public class BoardSquare {
    private int x;
    private int y;

    public BoardSquare(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public void printLocation() {
        System.out.printf("(%d,%d) \n",x,y);
    }
}
