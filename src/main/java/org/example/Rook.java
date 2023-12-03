package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rook extends AbstractPiece {
    public Rook(Team team, BoardSquare location) {
        super(team, location);
    }

    public Rook() {
        super();
    }

    protected List<BoardSquare> getEmptyBoardMoves(AbstractPiece piece) {
        List<BoardSquare> emptyBoardMoves = new ArrayList<>();

        int x = piece.getLocation().getX();
        int y = piece.getLocation().getY();

        for (int i = 0; i < 8; i++) {
            if (y != i) {
//              Rook moves up to down, so all 0-7 of y is added
                emptyBoardMoves.add(new BoardSquare(x, i));
            }
            if (x != i) {
//              Rook moves left to right, so all 0-7 of x is added
                emptyBoardMoves.add(new BoardSquare(i, y));
            }
        }
        return emptyBoardMoves;
    }

    public boolean hasClearPath(BoardSquare destination, List<AbstractPiece> allPieces, AbstractPiece piece) {
        int x = piece.getLocation().getX();
        int y = piece.getLocation().getY();

        for (AbstractPiece otherPiece : allPieces) {
            BoardSquare otherLoc = otherPiece.getLocation();
//          Check Vertical
            if (destination.getX() == otherLoc.getX()) {
//              if y > otherLoc.y and otherloc.y > dest.y that means otherloc is in the middle
                if (y > otherLoc.getY() && destination.getY() < otherLoc.getY()) {
                    return false;
                } else if (y < otherLoc.getY() && destination.getY() > otherLoc.getY()) {
                    return false;
                }
//          Check horizontal
            } else if (destination.getY() == otherLoc.getY()) {
                if (x < otherLoc.getX() && destination.getX() > otherLoc.getX()) {
                    return false;
                } else if (x > otherLoc.getX() && destination.getX() < otherLoc.getX()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean hasClearPath(BoardSquare destination, List<AbstractPiece> allPieces) {
        return hasClearPath(destination, allPieces, this);
    }

    @Override
    protected List<BoardSquare> getEmptyBoardMoves() {
        return getEmptyBoardMoves(this);
    }
}