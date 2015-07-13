package squarefy.src.board;

import squarefy.src.pieces.*;

import java.awt.*;

/**
 * a class to represent board
 * Created by Lethiraj on 6/28/2015.
 */
public class Board {

    private static Square board[][];

    /**
     * Assigns chess pieces to their orthodox starting positions
     */
    public Board() {
        board = new Square[8][8];
        for(int i = 0; i < 8; i = i + 1){
            for(int j = 0; j < 8; j = j + 1){
                switch(i) {
                    case 1:
                        board[i][j] = new Square(new Pawn(Color.WHITE, (char) (65 + j), 2), (i + j) % 2 == 0 ? Color.BLACK : Color.WHITE, i, j);
                        break;
                    case 6:
                        board[i][j] = new Square(new Pawn(Color.BLACK, (char) (65 + j), 7), (i + j) % 2 == 0 ? Color.BLACK : Color.WHITE, i , j);
                        break;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        board[i][j] = new Square(null, (i + j) % 2 == 0 ? Color.BLACK : Color.WHITE, i , j);
                        break;
                }
            }
        }
        board[0][0] = new Square(new Rook(Color.WHITE, 'A', 1), Color.BLACK, 0, 0);
        board[0][1] = new Square(new Knight(Color.WHITE, 'B', 1), Color.WHITE, 0, 1);
        board[0][2] = new Square(new Bishop(Color.WHITE, 'C' , 1), Color.BLACK, 0, 2);
        board[0][3] = new Square(new Queen(Color.WHITE, 'D', 1), Color.WHITE, 0, 3);
        board[0][4] = new Square(new King(Color.WHITE, 'E', 1), Color.BLACK, 0, 4);
        board[0][5] = new Square(new Bishop(Color.WHITE, 'F', 1), Color.WHITE, 0, 5);
        board[0][6] = new Square(new Knight(Color.WHITE, 'G', 1), Color.BLACK, 0, 6);
        board[0][7] = new Square(new Rook(Color.WHITE, 'H', 1), Color.WHITE, 0, 7);
        board[7][0] = new Square(new Rook(Color.BLACK, 'A', 8), Color.WHITE, 7, 0);
        board[7][1] = new Square(new Knight(Color.BLACK, 'B', 8), Color.BLACK, 7, 1);
        board[7][2] = new Square(new Bishop(Color.BLACK, 'C' , 8), Color.WHITE, 7, 2);
        board[7][3] = new Square(new Queen(Color.BLACK, 'D', 8), Color.BLACK, 7, 3);
        board[7][4] = new Square(new King(Color.BLACK, 'E', 8), Color.WHITE, 7, 4);
        board[7][5] = new Square(new Bishop(Color.BLACK, 'F', 8), Color.BLACK, 7 ,5);
        board[7][6] = new Square(new Knight(Color.BLACK, 'G', 8), Color.WHITE, 7, 6);
        board[7][7] = new Square(new Rook(Color.BLACK, 'H', 8), Color.BLACK, 7, 7);
    }

    public static Square[][] getBoard() {
        return board;
    }

    public static boolean isBlocked(Square source, Square target){
        int x = source.row, y = source.column;
        Square start = board[x][y];
        Square end = board[target.row][target.column];
        while(x != target.row && y != target.column){
            if(x < target.row) x = x + 1;
                else if (x > target.row) x = x - 1;
            if(y < target.column) y = y + 1;
                else if(y > target.column) y = y - 1;
            if(!board[x][y].isEmpty()) return false;
        }
        return false;
    }

    public Piece getPieceAt(char file, int rank) {
        return board[rank - 1][(int)(file) - 65].getPiece();
    }

    /**
     * Prints a view of the board from a normal perspective of play
     */
    private void print() {
        System.out.println("  A  B  C  D  E  F  G  H");
        for(int i = 7; i >= 0; i = i - 1) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < 8; j = j + 1) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print(i + 1);
            System.out.println();
        }
        System.out.println("  A  B  C  D  E  F  G  H");
    }

    public void swap(Square a, Square b){

    }

    public static void main(String[] args){
        Board board = new Board();
        board.print();
    }
}
