package squarefy.src.board;

import squarefy.src.pieces.Piece;

import java.awt.*;

/**
 * a class to represent a unit of a board
 * Created by Lethiraj on 6/28/2015.
 */
public class Square {

    private Piece piece;
    private Color color;
    public int row;
    public int column;

    public Square(Piece piece, Color color, int row, int column) {
        this.piece = piece;
        this.color = color;
        this.row = row;
        this.column = column;
    }

    protected Piece getPiece() {

        return piece;
    }

    protected Color getColor() { return color;}

    protected void setPiece(Piece piece) {
        this.piece = piece;
        if(piece != null){
            piece.setFile((char)(this.column + 65));
            piece.setRank(this.row + 1);
        }
    }

    protected void setColor(Color c) {
        color = c;
    }

    @Override
    public String toString(){
        return piece == null ? "X " : piece.toString();
    }

    public boolean isEmpty(){
        return piece == null;
    }

}
