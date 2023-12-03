package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pawn extends AbstractPiece {
    public Pawn(Team team, BoardSquare location) {
        super(team, location);
    }

    //AbstractPiece.getEmptyBoardMoves returns the squares that piece could move to on an empty board
    @Override
    protected List<BoardSquare> getEmptyBoardMoves(AbstractPiece piece) {
        List<BoardSquare> emptyBoardMoves = new ArrayList<>();

        int move1 = piece.getTeam() == Team.WHITE ? 1 : -1;
        int move2 = piece.getTeam() == Team.WHITE ? 2 : -2;
        int y = piece.getLocation().getY();
        int x = piece.getLocation().getX();

        //if y == 1, that means pawn is at starting point, which means it can move 2 spaces
        if (piece.isFirstMove() && (y == 1 || y == 6)) emptyBoardMoves.add(new BoardSquare(x, y + move2));
        if (y < 7 && y > 0) emptyBoardMoves.add(new BoardSquare(x, y + move1));
        return emptyBoardMoves;
    }
    //Check if anything is in path, if anything in path, return true
    @Override
    public boolean hasClearPath(BoardSquare destination, List<AbstractPiece> allPieces, AbstractPiece piece) {
        //if first move, then nothing will be in front of pawn. always gonna be true.
        if (piece.isFirstMove()) return true;
        for (AbstractPiece otherPiece : allPieces) {


            if (BoardUtil.isSameSquare(piece.getLocation(), otherPiece.getLocation())) return false;

            BoardSquare otherLoc = otherPiece.getLocation();
            if (BoardUtil.isSameSquare(destination, otherLoc)) {
                return false;
            }
        }
        return true;
    }
}