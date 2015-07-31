package squarefy.src.pieces;

import squarefy.src.board.Square;

import java.awt.*;
import java.util.ArrayList;

/**
 * A bishop is valued at 3 points
 */

public class Bishop extends Piece {

    public Bishop(Color color, char file, int rank) {
        super(color, 3 * (color == Color.WHITE ? 1 : -1), file, rank);
        canMove = canCapture = false;
    }

    public String toString() {
        return ((this.color == Color.WHITE) ? "w" : "b") + "B";
    }

    @Override
    public ArrayList<Square> getMoves() {
        int constraint = Math.min(_rank, _file), i, j;
        for(i = _rank - constraint, j = _file + constraint; i <= _file + constraint; i= i + 1, j = j - 1){
            moves.add(board[i][j]);
        }
        return moves;
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public boolean canCapture() {
        return false;
    }

    @Override
    public void validate(ArrayList<Square> moves) {

    }

}
