package org.example;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

//A board is 8x8 so start is (0,0) and (7,7)
//This means that min x,y = 0 and max x,y = 7
public class Board {
    public Board() {
    }

    public List<BoardSquare> getValidMoves(AbstractPiece piece, List<AbstractPiece> pieces) {
        List<BoardSquare> boardMoves = piece.getEmptyBoardMoves();
        //if path not clear, remove move
        boardMoves.removeIf((e) -> !piece.hasClearPath(e, pieces));
        return boardMoves;
    }

    public void clearBoardSquare(BoardSquare toClear, ArrayList<AbstractPiece> pieces) {
        for (AbstractPiece piece : pieces) {
            BoardSquare pieceLoc = piece.getLocation();

//            System.out.printf("square(%s, %s) toClear(%s, %s) \n", pieceLoc.getX(), pieceLoc.getY(), toClear.getX(), toClear.getY());

            if (BoardUtil.isSameSquare(pieceLoc, toClear)){
                pieces.remove(piece);
                break;
            };
//            System.out.printf("IN BOARD Square(%s,%s) = %s (%s, %s) \n", pieceLoc.getX(), pieceLoc.getY(), piece.getClass(), piece.getLocation().getX(), piece.getLocation().getY()) ;
        }
    }

    //  Board.isEmpty returns true iff the square is empty on the board
    public boolean squareIsEmpty(BoardSquare toCheck, List<AbstractPiece> pieces) {
        for (AbstractPiece otherPiece : pieces) {
            BoardSquare otherLoc = otherPiece.getLocation();
            if (BoardUtil.isSameSquare(toCheck, otherLoc)) {
                return false;
            }
        }
        return true;
    }
}
