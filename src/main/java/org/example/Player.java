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

    public boolean takeNextTurn() {
        if (pieces.isEmpty()) return false;

//        Gets all possible valid moves
        Map<AbstractPiece, List<BoardSquare>> allValidMoves = getAllValidMoves();

//        gets a random piece
        AbstractPiece pieceKey = allValidMoves.keySet().iterator().next();
//        takes the first move of random piece
        BoardSquare square = allValidMoves.get(pieceKey).getFirst();

        return this.move(pieceKey, square);
    }

    public Map<AbstractPiece, List<BoardSquare>> getAllValidMoves() {
        Map<AbstractPiece, List<BoardSquare>> allValidMoves = new HashMap<>();

        for (AbstractPiece piece : pieces) {
            //gets all valid moves of all player's pieces
            allValidMoves.put(piece, board.getValidMoves(piece, pieces));
        }

        return allValidMoves;
    }

    public Map<AbstractPiece, List<BoardSquare>> getAllPieceValidMoves(AbstractPiece piece) {
        Map<AbstractPiece, List<BoardSquare>> allValidMoves = new HashMap<>();

        //gets all valid moves of all player's pieces
        allValidMoves.put(piece, board.getValidMoves(piece, pieces));

        return allValidMoves;
    }

    //Board.move attempts to move a piece into a given square, and returns true if it was able to
    public boolean move(AbstractPiece piece, BoardSquare destination) {
        List<BoardSquare> allValidMoves = getAllPieceValidMoves(piece).get(piece);

        boolean validMove = false;

        for (BoardSquare m : allValidMoves) {
            if (BoardUtil.isSameSquare(m, destination)) {
                validMove = true;
            }
        }

        if (board.squareIsEmpty(destination, this.pieces) && validMove) {
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

    public boolean addPiece(AbstractPiece piece) {
        for (AbstractPiece p : pieces) {
            if (BoardUtil.isSameSquare(piece.getLocation(), p.getLocation())){
                return false;
            }
        }
        pieces.add(piece);

        return true;
    }

    public Map<AbstractPiece, Boolean> addPieces(List<AbstractPiece> pieces) {
        Map<AbstractPiece, Boolean> result = new HashMap<>();

        pieces.forEach(piece -> {
            result.put(piece, addPiece(piece));
        });

        return result;
    }
}
