package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    //Player.takeNextTurn is described in the sequence diagram, but broadly it works out all available moves, takes the first one it found, and makes it.
    Board board;
    private final ArrayList<AbstractPiece> pieces;


    public Player(Board board) {
        this.board = board;
        this.pieces = new ArrayList<>() {
        };
    }

    public void takeNextTurn() {
        Map<AbstractPiece, List<BoardSquare>> allValidMoves = getAllValidMoves();

        AbstractPiece pieceKey = allValidMoves.keySet().iterator().next();
        BoardSquare square = allValidMoves.get(pieceKey).getFirst();

        System.out.println(this.move(pieceKey, square));
    }

    public Map<AbstractPiece, List<BoardSquare>> getAllValidMoves() {
        Map<AbstractPiece, List<BoardSquare>> allValidMoves = new HashMap<>();

        for (AbstractPiece piece : pieces) {
            //gets all valid moves of all player's pieces
            allValidMoves.put(piece, board.getValidMoves(piece, pieces));
        }

        return allValidMoves;
    }

    //Board.move attempts to move a piece into a given square, and returns true if it was able to
    public boolean move(AbstractPiece piece, BoardSquare destination) {

        if (board.squareIsEmpty(destination, this.pieces) && piece.hasClearPath(destination, pieces)) {
            board.clearBoardSquare(piece.getLocation(), this.pieces);
            piece.setLocation(destination);
            addPiece(piece);
            piece.moved();
            return true;
        }
        return false;
    }

    public List<AbstractPiece> getAllPieces() {
        return pieces.isEmpty() ? null : pieces;
    }

    public void addPiece(AbstractPiece piece) {
        pieces.add(piece);
    }

    public void addPieces(List<AbstractPiece> pieces){
        this.pieces.addAll(pieces);
    }
}
