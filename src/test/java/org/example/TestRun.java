package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TestRun {
    Board board = new Board();
    Player player = new Player(board);

    @Test
    void noDuplicatePiecesSameLocation() {
        Pawn p1 = new Pawn(Team.WHITE, new BoardSquare(0, 0));
        Pawn p2 = new Pawn(Team.WHITE, new BoardSquare(0, 0));
        Rook r = new Rook(Team.WHITE, new BoardSquare(0, 0));

        player.addPiece(p1);
        player.addPiece(p2);
        player.addPiece(r);

        assertEquals(1, player.getAllPieces().size());
    }

    @Test
    void noDuplicatePiecesSameLocation2() {
        Pawn p1 = new Pawn(Team.WHITE, new BoardSquare(0, 0));
        Pawn p2 = new Pawn(Team.WHITE, new BoardSquare(0, 0));
        Rook r = new Rook(Team.WHITE, new BoardSquare(0, 0));

        player.addPieces(new ArrayList<>(List.of(p1, p2, r)));

        assertEquals(1, player.getAllPieces().size());
    }


    @Test
    void takeNextTurnTestFalse() {
//        Gonna be false cuz player got no pieces
        assertFalse(player.takeNextTurn());
    }

    @Test
    void takeNextTurnTestTrue() {
        AbstractPiece rook = new Rook(Team.WHITE, new BoardSquare(2, 0));
        AbstractPiece rook2 = new Rook(Team.WHITE, new BoardSquare(5, 1));
        AbstractPiece pawn1 = new Pawn(Team.WHITE, new BoardSquare(0, 1));
        AbstractPiece pawn2 = new Pawn(Team.WHITE, new BoardSquare(1, 1));
        AbstractPiece pawn3 = new Pawn(Team.WHITE, new BoardSquare(2, 1));
        AbstractPiece bishop = new Bishop(Team.WHITE, new BoardSquare(7, 7));
        player.addPieces(new ArrayList<>(List.of(rook, rook2, pawn1, pawn2, pawn3, bishop)));
        assertTrue(player.takeNextTurn());
    }

    @Test
    void moveRookTrue() {
        AbstractPiece rook = new Rook(Team.WHITE, new BoardSquare(0, 1));
        AbstractPiece bishop = new Bishop(Team.WHITE, new BoardSquare(0, 0));

//        made this addPieces method just now
        player.addPieces(new ArrayList<>(List.of(rook, bishop)));

        //        bishop blocking the way
        assertTrue(player.move(rook, new BoardSquare(0, 7)));
    }


    @Test
    void moveRookFalse() {
        AbstractPiece rook = new Rook(Team.WHITE, new BoardSquare(0, 1));
        AbstractPiece bishop = new Bishop(Team.WHITE, new BoardSquare(0, 2));

//        made this addPieces method just now
        player.addPieces(new ArrayList<>(List.of(rook, bishop)));

        //        bishop blocking the way
        assertFalse(player.move(rook, new BoardSquare(0, 3)));
    }

    @Test
    void moveBishopTrue() {
        AbstractPiece rook = new Rook(Team.WHITE, new BoardSquare(0, 1));
        AbstractPiece bishop = new Bishop(Team.WHITE, new BoardSquare(0, 0));

        player.addPieces(new ArrayList<>(List.of(rook, bishop)));

//        put bishop above of rook
        assertTrue(player.move(bishop, new BoardSquare(1, 1)));
    }

    //    Checks if bishop can jump over other pieces, should not be the case
    @Test
    void moveBishopFalse() {
        AbstractPiece rook = new Rook(Team.WHITE, new BoardSquare(1, 1));
        AbstractPiece bishop = new Bishop(Team.WHITE, new BoardSquare(0, 0));

        player.addPieces(new ArrayList<>(List.of(rook, bishop)));

        assertFalse(player.move(bishop, new BoardSquare(2, 2)));
    }

    @Test
    void getAllPieceValidMovesNullRook() {
        AbstractPiece rook = new Rook(Team.WHITE, new BoardSquare(0, 0));
        AbstractPiece pawn = new Pawn(Team.WHITE, new BoardSquare(0, 1));
        AbstractPiece king = new King(Team.WHITE, new BoardSquare(1, 0));

        player.addPieces(new ArrayList<>(List.of(rook, pawn, king)));

        Map<AbstractPiece, List<BoardSquare>> rookMoves = player.getAllPieceValidMoves(rook);

        rookMoves.get(rook).forEach(rm -> System.out.printf("(%d,%d)\n", rm.getX(), rm.getY()));

        assertEquals(0, rookMoves.get(rook).size());
    }

    @Test
    void getAllPieceValidMovesNullBishop() {
        AbstractPiece bishop = new Bishop(Team.WHITE, new BoardSquare(0, 0));
        AbstractPiece pawn = new Pawn(Team.WHITE, new BoardSquare(1, 1));

        player.addPieces(new ArrayList<>(List.of(bishop, pawn)));

        Map<AbstractPiece, List<BoardSquare>> bishopMoves = player.getAllPieceValidMoves(bishop);

        bishopMoves.get(bishop).forEach(rm -> System.out.printf("(%d,%d)\n", rm.getX(), rm.getY()));

        assertEquals(0, bishopMoves.get(bishop).size());
    }

    @Test
    void getAllPieceValidMovesOnlyOneRook() {
        AbstractPiece rook = new Rook(Team.WHITE, new BoardSquare(0, 0));
        AbstractPiece pawn = new Pawn(Team.WHITE, new BoardSquare(0, 2));
        AbstractPiece king = new King(Team.WHITE, new BoardSquare(1, 0));

        player.addPieces(new ArrayList<>(List.of(rook, pawn, king)));

        Map<AbstractPiece, List<BoardSquare>> rookMoves = player.getAllPieceValidMoves(rook);

        assertEquals(1, rookMoves.get(rook).size());
        assertTrue(BoardUtil.isSameSquare(new BoardSquare(0, 1), rookMoves.get(rook).get(0)));
    }

    @Test
    void getAllPieceValidMovesOnlyOneBishop() {
        AbstractPiece bishop = new Bishop(Team.WHITE, new BoardSquare(0, 0));
        AbstractPiece pawn = new Pawn(Team.WHITE, new BoardSquare(2, 2));

        player.addPieces(new ArrayList<>(List.of(bishop, pawn)));

        Map<AbstractPiece, List<BoardSquare>> bishopMoves = player.getAllPieceValidMoves(bishop);

//        bishopMoves.get(bishop).forEach(rm -> System.out.printf("(%d,%d)\n", rm.getX(), rm.getY()));

        assertEquals(1, bishopMoves.get(bishop).size());
        assertTrue(BoardUtil.isSameSquare(new BoardSquare(1, 1), bishopMoves.get(bishop).get(0)));
    }

    // Checks bishop get all moves logic. should only return 6,6 since it starts from 7,7 and a piece is blocking 5,5
    @Test
    void getAllPieceValidMovesOnlyOneBishopOtherDiagonal() {
        AbstractPiece bishop = new Bishop(Team.WHITE, new BoardSquare(7, 7));
        AbstractPiece pawn = new Pawn(Team.WHITE, new BoardSquare(5, 5));

        player.addPieces(new ArrayList<>(List.of(bishop, pawn)));

        Map<AbstractPiece, List<BoardSquare>> bishopMoves = player.getAllPieceValidMoves(bishop);

//        bishopMoves.get(bishop).forEach(rm -> System.out.printf("(%d,%d)\n", rm.getX(), rm.getY()));

        assertEquals(1, bishopMoves.get(bishop).size());
        assertTrue(BoardUtil.isSameSquare(new BoardSquare(6, 6), bishopMoves.get(bishop).get(0)));
    }


    @Test
    void getAllPiecesNotNull() {
        player.addPiece(new Rook(Team.WHITE, new BoardSquare(0, 0)));
        player.getAllPieces();
        assertNotNull(player.getAllPieces());
    }

    //    This test actually failed at first because I didn't return null if player had no pieces
    @Test
    void getAllPiecesNull() {
        Player player = new Player(board);
        assertNull(player.getAllPieces());
    }
}