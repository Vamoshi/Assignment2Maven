package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bishop extends AbstractPiece {
    public Bishop(Team team, BoardSquare location) {
        super(team, location);
    }
    public Bishop() {
        super();
    }

    protected List<BoardSquare> getEmptyBoardMoves(AbstractPiece piece) {
        int x = piece.getLocation().getX();
        int y = piece.getLocation().getY();

        List<BoardSquare> emptyBoardMoves = new ArrayList<>();

//      up right diagonal (x+1, y+1)
        for (int i = 1; (x + i < 8) && (y + i < 8); i++) {
            emptyBoardMoves.add(new BoardSquare(x + i, y + i));
        }
//      down right diagonal (x+1, y-1)
        for (int i = 1; (x + i < 8) && (y - i >= 0); i++) {
            emptyBoardMoves.add(new BoardSquare(x + i, y - i));
        }
//      up left diagonal (x-1, y+1)
        for (int i = 1; (x - i >= 0) && (y + i < 8); i++) {
            emptyBoardMoves.add(new BoardSquare(x - i, y + i));
        }
//      down left diagonal (x-1, y-1)
        for (int i = 1; (x - i >= 0) && (y - i >= 0); i++) {
            emptyBoardMoves.add(new BoardSquare(x - i, y - i));
        }

        return emptyBoardMoves;
    }

    @Override
    protected List<BoardSquare> getEmptyBoardMoves() {
        return getEmptyBoardMoves(this);
    }

    public boolean hasClearPath(BoardSquare destination, Map<BoardSquare, AbstractPiece> allPieces, AbstractPiece piece) {
        int destX = destination.getX();
        int destY = destination.getY();

        for (BoardSquare otherLoc : allPieces.keySet()) {
            int otherX = otherLoc.getX();
            int otherY = otherLoc.getY();
//          left to right is x+y = x+y
            if (destX + destY == otherX + otherY) {
                int otherDiff = otherY - otherX;
                int destDiff = destY - destX;
                int pieceDiff = piece.getLocation().getY() - piece.getLocation().getX();
                if (otherDiff > destDiff && otherDiff < pieceDiff) {
                    return false;
                } else if (otherDiff < destDiff && otherDiff > pieceDiff) {
                    return false;
                }
//          right to left is x-y = x-y
            } else if (destX - destY == otherX - otherY) {
                int otherSum = otherY + otherX;
                int destSum = destY + destX;
                int pieceSum = piece.getLocation().getY() + piece.getLocation().getX();
                if (otherSum > destSum && otherSum < pieceSum) {
                    return false;
                } else if (otherSum < destSum && otherSum > pieceSum) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean hasClearPath(BoardSquare destination, Map<BoardSquare, AbstractPiece> allPieces) {
        return hasClearPath(destination, allPieces, this);
    }
}
