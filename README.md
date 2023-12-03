# Assignment2Maven
COMP 371 Assignment3

Some changes i made that were not in the diagram:
- Added a BoardUtil class
- removed Map<BoardSquare, AbstractPiece>, instead opted to put the location in each piece
- since all pieces have different logic of getting empty board moves and finding the clear paths, i added the hasClearPath and getEmptyBoardMoves into the pieces so board can just call it by piece.hasClearPath/piece.getEmptyBoardMoves for all pieces. Also, this adds the bonus of being able to delegate rook and bishop to queen cuz a quuen is basically rook + bishop.
- 
