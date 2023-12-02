package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class King extends AbstractPiece {
    public King(Team team, BoardSquare location) {
        super(team, location);
    }

    //AbstractPiece.getEmptyBoardMoves returns the squares that piece could move to on an empty board
    @Override
    protected List<BoardSquare> getEmptyBoardMoves(AbstractPiece piece) {
        List<BoardSquare> emptyBoardMoves = new ArrayList<>();
        int x = piece.getLocation().getX();
        int y = piece.getLocation().getY();

//        hard-coding it because too tired to think of a way to loop this
        if (x < 7 && x > 0 && y < 7 && y > 0) {
            emptyBoardMoves.add(new BoardSquare(x + 1, y));
            emptyBoardMoves.add(new BoardSquare(x + 1, y + 1));
            emptyBoardMoves.add(new BoardSquare(x + 1, y - 1));
            emptyBoardMoves.add(new BoardSquare(x - 1, y + 1));
            emptyBoardMoves.add(new BoardSquare(x - 1, y - 1));
            emptyBoardMoves.add(new BoardSquare(x - 1, y));
            emptyBoardMoves.add(new BoardSquare(x, y + 1));
            emptyBoardMoves.add(new BoardSquare(x, y - 1));
        }

        return emptyBoardMoves;
    }

    @Override
    public boolean hasClearPath(BoardSquare destination, Map<BoardSquare, AbstractPiece> allPieces, AbstractPiece piece) {
        for (BoardSquare otherLoc : allPieces.keySet()) {
            if (BoardUtil.isSameSquare(destination, otherLoc)) return false;
        }
        return true;
    }
}