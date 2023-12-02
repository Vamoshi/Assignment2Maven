package org.example;

public class BoardUtil {
    private BoardUtil(){}
    public static boolean isSameSquare(BoardSquare b1, BoardSquare b2){
        return b1.getX() == b2.getX() && b1.getY() == b2.getY();
    }
}
