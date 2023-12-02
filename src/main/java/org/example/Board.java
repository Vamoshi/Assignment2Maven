package org.example;

import java.util.Map;
import java.util.List;

//A board is 8x8 so start is (0,0) and (7,7)
//This means that min x,y = 0 and max x,y = 7
public class Board {
    //private final Map<King, AbstractPiece> kings;
    //private BitSet occupiedSquares;

    public Board() {
    }
//    //Board.move attempts to move a piece into a given square, and returns true if it was able to
//    public boolean move(AbstractPiece piece, BoardSquare bs, Map<BoardSquare, AbstractPiece> pieces) {
//        BoardSquare oldLocation = piece.getLocation();
//
//        //checks all valid moves and moves if it is valid
//        List<BoardSquare> moves = getValidMoves(piece, pieces);
//        for (BoardSquare square:moves) {
//        //if move is valid
//            if (square.getX() == bs.getX() && square.getY() == bs.getY()){
//                //update piece
//                clearBoardSquare(oldLocation);
//                piece.setLocation(square);
//                addPiece(square, piece);
//                piece.moved();
//                return true;
//            }
//        }
//        return false;
//    }

    public List<BoardSquare> getValidMoves(AbstractPiece piece, Map<BoardSquare, AbstractPiece> pieces) {
        List<BoardSquare> boardMoves = piece.getEmptyBoardMoves();
        //if path not clear, remove move
        boardMoves.removeIf((e) -> !piece.hasClearPath(e, pieces));
        return boardMoves;
    }

    public void clearBoardSquare(BoardSquare toClear, Map<BoardSquare, AbstractPiece> pieces) {
        for (BoardSquare square : pieces.keySet()) {
//            AbstractPiece piece = pieces.get(square);

//            System.out.printf("square(%s, %s) toClear(%s, %s) \n", square.getX(), square.getY(), toClear.getX(), toClear.getY());

            if (BoardUtil.isSameSquare(square, toClear)) pieces.remove(square);
//            System.out.printf("IN BOARD Square(%s,%s) = %s (%s, %s) \n", square.getX(), square.getY(), piece.getClass(), piece.getLocation().getX(), piece.getLocation().getY()) ;
        }
    }

    //  Board.isEmpty returns true iff the square is empty on the board
    public boolean squareIsEmpty(BoardSquare toCheck, Map<BoardSquare, AbstractPiece> pieces) {
        for (BoardSquare bs : pieces.keySet()) {
            if (BoardUtil.isSameSquare(toCheck, bs)) {
                return false;
            }
        }
        return true;
    }
}
