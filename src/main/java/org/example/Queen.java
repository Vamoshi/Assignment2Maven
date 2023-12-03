package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Queen extends AbstractPiece {
    //  Delegate deez guys cuz i can reuse the code for rook and bishop since a queen is basically a combined rook and bishop...right?
    AbstractPiece rook = new Rook();
    AbstractPiece bishop = new Bishop();

    public Queen(Team team, BoardSquare location) {
        super(team, location);
    }

    @Override
    protected List<BoardSquare> getEmptyBoardMoves(AbstractPiece piece) {
        List<BoardSquare> emptyBoardMoves = new ArrayList<>();
        emptyBoardMoves.addAll(rook.getEmptyBoardMoves(this));
        emptyBoardMoves.addAll(bishop.getEmptyBoardMoves(this));

        return emptyBoardMoves;
    }

//  If any of them return a false, or path isn't clear, then result is false
    @Override
    public boolean hasClearPath(BoardSquare destination, List<AbstractPiece> allPieces, AbstractPiece piece) {

        return rook.hasClearPath(destination, allPieces, this) &&
                bishop.hasClearPath(destination, allPieces, this);
    }
}
