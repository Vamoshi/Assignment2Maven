package org.example;

import org.example.*;

import java.util.HashMap;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
//        // Press Alt+Enter with your caret at the highlighted text to see how
//        // IntelliJ IDEA suggests fixing it.
//        System.out.print("Hello and welcome!");
//
//        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
//        for (int i = 1; i <= 5; i++) {
//
//            // Press Shift+F9 to start debugging your code. We have set one breakpoint
//            // for you, but you can always add more by pressing Ctrl+F8.
//            System.out.println("i = " + i);
//        }

//        testIsSameLocation();
//        testIsEmpty();
//        testClearBoardSquare();

        Board board = new Board();
        Player player = new Player(board);

        BoardSquare location = new BoardSquare(0,0);
        player.addPiece(location, new Bishop(Team.WHITE, location));
        player.takeNextTurn();

        Map<BoardSquare, AbstractPiece> pieces = player.getAllPieces();

//        for (BoardSquare square : pieces.keySet()) {
//            AbstractPiece piece = pieces.get(square);
//            System.out.printf("IN MAIN Square(%s,%s) = %s (%s, %s) \n", square.getX(), square.getY(), piece.getClass(), piece.getLocation().getX(), piece.getLocation().getY()) ;
//        }
    }

    private static void testClearBoardSquare() {
        Map<BoardSquare, String> pieces = new HashMap<>();
        pieces.put(new BoardSquare(0, 0), "b1");
        pieces.put(new BoardSquare(1, 1), "b2");

        BoardSquare toClear = new BoardSquare(1, 1);
        BoardSquare temp = new BoardSquare(0, 0);
        for (BoardSquare bs : pieces.keySet()) {
            if (bs.getX() == toClear.getX() && bs.getY() == toClear.getY()) {
                temp = bs;
                break;
            }
        }
        pieces.remove(temp);

        for (String p : pieces.values()) {
            System.out.printf(p);
        }
    }

    private static void testIsEmpty() {
        Map<BoardSquare, String> pieces = new HashMap<>();
        pieces.put(new BoardSquare(0, 0), "b1");
        pieces.put(new BoardSquare(1, 1), "b2");
        BoardSquare toCheck = new BoardSquare(2, 2);

        for (BoardSquare bs : pieces.keySet()) {
            if (bs.getX() == toCheck.getX() && bs.getY() == toCheck.getY()) {
                System.out.print("False");
                return;
            }
        }
        System.out.print("True");
    }

    private static void testIsSameLocation() {
        BoardSquare b1 = new BoardSquare(0, 0);
        BoardSquare b2 = new BoardSquare(0, 0);
        System.out.printf(String.valueOf(BoardUtil.isSameSquare(b1, b2)));
        b2 = new BoardSquare(1, 1);
        BoardUtil.isSameSquare(b1, b2);
        System.out.printf(String.valueOf(BoardUtil.isSameSquare(b1, b2)));
    }
}