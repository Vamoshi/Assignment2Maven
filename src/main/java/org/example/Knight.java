package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Knight extends AbstractPiece {
    public Knight(Team team, BoardSquare location) {
        super(team, location);
    }

    @Override
    protected List<BoardSquare> getEmptyBoardMoves(AbstractPiece piece) {
//      AHHH IM DYING I'LL JUST HARD CODE ALL POSSIBLE MOVES OF DE HORS WHAT LOOP
        List<BoardSquare> emptyBoardMoves = new ArrayList<>();
        int x = piece.getLocation().getX();
        int y = piece.getLocation().getY();

        if (x + 2 < 8) {
            if (y + 1 < 8) {
                emptyBoardMoves.add(new BoardSquare(x + 2, y + 1));
            }
            if (y - 1 >= 0) {
                emptyBoardMoves.add(new BoardSquare(x + 2, y - 1));
            }
        }
        if (x - 2 >= 0) {
            if (y + 1 < 8) {
                emptyBoardMoves.add(new BoardSquare(x - 2, y + 1));
            }
            if (y - 1 >= 0) {
                emptyBoardMoves.add(new BoardSquare(x - 2, y - 1));
            }
        }
        if (y + 2 < 8) {
            if (x + 1 < 8) {
                emptyBoardMoves.add(new BoardSquare(x + 1, y + 2));
            }
            if (x - 1 > 0) {
                emptyBoardMoves.add(new BoardSquare(x - 1, y + 2));
            }
        }
        if (y - 2 >= 0) {
            if (x + 1 < 8) {
                emptyBoardMoves.add(new BoardSquare(x + 1, y - 2));
            }
            if (x - 1 >= 0) {
                emptyBoardMoves.add(new BoardSquare(x - 1, y - 2));
            }
        }

        return emptyBoardMoves;
    }

    @Override
    public boolean hasClearPath(BoardSquare destination, List<AbstractPiece> allPieces, AbstractPiece piece) {
        for (AbstractPiece otherPiece : allPieces) {
            BoardSquare otherLoc = otherPiece.getLocation();
            if (BoardUtil.isSameSquare(destination, otherLoc)) return false;
        }
        return true;
    }
}
