package squarefy.src.pieces;

import squarefy.src.board.Board;
import squarefy.src.board.Square;

import java.awt.*;
import java.util.ArrayList;

/**
 * A knight is valued at 3 points
 */

public class Knight extends Piece {

    public Knight(Color color, char file, int rank) {
        super(color, 3 * (color == Color.WHITE ? 1 : -1), file, rank);
        canMove = true;
        canCapture = false;
    }

    public String toString() {
        return ((this.color == Color.WHITE) ? "w" : "b") + "N";
    }

    @Override
    public ArrayList<Square> getMoves(){
        if(_rank < 6 && _file > 0) moves.add(board[_rank + 2][_file - 1]);
        if(_rank < 6 && _file < 7) moves.add(board[_rank + 2][_file + 1]);
        if(_rank < 7 && _file > 2) moves.add(board[_rank + 1][_file - 2]);
        if(_rank < 7 && _file < 6) moves.add(board[_rank + 1][_file + 2]);
        if(_rank > 1 && _file > 0) moves.add(board[_rank - 2][_file - 1]);
        if(_rank > 1 && _file < 7) moves.add(board[_rank - 2][_file + 1]);
        if(_rank > 0 && _file > 1) moves.add(board[_rank - 1][_file - 2]);
        if(_rank > 0 && _file < 6) moves.add(board[_rank - 1][_file + 2]);
        //validate(moves);
        return moves;
    }

    @Override
    public boolean canMove() {
        return !moves.isEmpty();
    }

    @Override
    public boolean canCapture() {
        return canCapture;
    }

    @Override
    public void validate(ArrayList<Square> moves) {
        Square source = this.getSquare();
        for(Square square : moves){
            if(Board.isKingChecked(source)) moves.remove(square);

        }
    }


}
