package org.example;

import java.util.List;
import java.util.Map;

public abstract class AbstractPiece {
    private boolean firstMove = true;
    private Team team;
    private BoardSquare location;

    public AbstractPiece(Team team, BoardSquare location) {
        this.team = team;
        this.location = location;
    }

    public AbstractPiece() {
    }

    protected abstract List<BoardSquare> getEmptyBoardMoves(AbstractPiece piece);
    //checks if the path to the boardSquare is empty
    public abstract boolean hasClearPath(BoardSquare destination, Map<BoardSquare, AbstractPiece> allPieces, AbstractPiece piece);

//  DEFAULT IS "THIS"
    protected List<BoardSquare> getEmptyBoardMoves() {
        return getEmptyBoardMoves(this);
    }
    public boolean hasClearPath(BoardSquare destination, Map<BoardSquare, AbstractPiece> allPieces) {
        return hasClearPath(destination, allPieces, this);
    }
    public BoardSquare getLocation() {
        return this.location;
    }

    public void setLocation(BoardSquare newLocation) {
        this.location = newLocation;
    }

    public Team getTeam() {
        return team;
    }

    public void moved() {
        firstMove = false;
    }

    protected boolean isFirstMove() {
        return firstMove;
    }
}