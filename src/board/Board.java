package squarefy.src.board;

import squarefy.src.pieces.*;

import java.awt.*;

/**
 * a class to represent board
 * Created by Lethiraj on 6/28/2015.
 */

public class Board {

    private static Square board[][], whiteKingPosition, blackKingPosition;
    public King whiteKing, blackKing;
    public static boolean isRunning;
    /**
     * Assigns chess pieces to their orthodox starting positions
     */
    public Board() {
        board = new Square[8][8];
        for (int i = 0; i < 8; i = i + 1) {
            for (int j = 0; j < 8; j = j + 1) {
                switch (i) {
                    case 1:
                        board[i][j] = new Square(new Pawn(Color.WHITE, (char) (65 + j), 2), (i + j) % 2 == 0 ? Color.BLACK : Color.WHITE, i, j);
                        break;
                    case 6:
                        board[i][j] = new Square(new Pawn(Color.BLACK, (char) (65 + j), 7), (i + j) % 2 == 0 ? Color.BLACK : Color.WHITE, i, j);
                        break;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        board[i][j] = new Square(null, (i + j) % 2 == 0 ? Color.BLACK : Color.WHITE, i, j);
                        break;
                }
            }
        }
        board[0][0] = new Square(new Rook(Color.WHITE, 'A', 1), Color.BLACK, 0, 0);
        board[0][1] = new Square(new Knight(Color.WHITE, 'B', 1), Color.WHITE, 0, 1);
        board[0][2] = new Square(new Bishop(Color.WHITE, 'C', 1), Color.BLACK, 0, 2);
        board[0][3] = new Square(new Queen(Color.WHITE, 'D', 1), Color.WHITE, 0, 3);
        whiteKing = new King(Color.WHITE, 'E', 1);
        board[0][4] = new Square(whiteKing, Color.BLACK, 0, 4);
        board[0][5] = new Square(new Bishop(Color.WHITE, 'F', 1), Color.WHITE, 0, 5);
        board[0][6] = new Square(new Knight(Color.WHITE, 'G', 1), Color.BLACK, 0, 6);
        board[0][7] = new Square(new Rook(Color.WHITE, 'H', 1), Color.WHITE, 0, 7);
        board[7][0] = new Square(new Rook(Color.BLACK, 'A', 8), Color.WHITE, 7, 0);
        board[7][1] = new Square(new Knight(Color.BLACK, 'B', 8), Color.BLACK, 7, 1);
        board[7][2] = new Square(new Bishop(Color.BLACK, 'C', 8), Color.WHITE, 7, 2);
        board[7][3] = new Square(new Queen(Color.BLACK, 'D', 8), Color.BLACK, 7, 3);
        blackKing = new King(Color.BLACK, 'E', 8);
        board[7][4] = new Square(blackKing, Color.WHITE, 7, 4);
        board[7][5] = new Square(new Bishop(Color.BLACK, 'F', 8), Color.BLACK, 7, 5);
        board[7][6] = new Square(new Knight(Color.BLACK, 'G', 8), Color.WHITE, 7, 6);
        board[7][7] = new Square(new Rook(Color.BLACK, 'H', 8), Color.BLACK, 7, 7);
        whiteKingPosition = squareOf(whiteKing);
        blackKingPosition = squareOf(blackKing);
    }

    public static Square[][] getBoard() {
        return board;
    }

    public static Square getKingPosition(Color color){
        return color == Color.WHITE ? whiteKingPosition : blackKingPosition;
    }

    public static Square squareOf(Piece piece){
        return piece.getSquare();
    }

    public static boolean isBlocked(Square source, Square target){
        int x = source.row, y = source.column;
        while(x != target.row && y != target.column && y > 0 && y < 7 && x > 0 && x < 7){
            if(x < target.row) x = x + 1;
                else if (x > target.row) x = x - 1;
            if(y < target.column) y = y + 1;
                else if(y > target.column) y = y - 1;
            if(!board[x][y].isEmpty() && x != target.row && y != target.column) return true;
        }
        return false;
    }

    public static boolean isKingChecked(Square source){
        Square kingSquare = getKingPosition(source.getPiece().getColor());
        int x = kingSquare.row, y = kingSquare.column, delta_x, delta_y;
        if(x < source.row) delta_x = 1;
            else if(x > source.row) delta_x = -1;
            else delta_x = 0;
        if(y < source.column) delta_y = 1;
            else if(y > source.column) delta_y = -1;
            else delta_y = 0;
        System.out.println(delta_x + "," + delta_y);
        while(y > 0 && y < 7 && x > 0 && x < 7){
            x = x + delta_x;
            y = y + delta_y;
            if(board[x][y].getPiece() != null){
                return (board[x][y].getPiece().getColor() != kingSquare.getPiece().getColor()
                        && (board[x][y].getPiece() instanceof Bishop && delta_x*delta_y == -1
                            || board[x][y].getPiece() instanceof Queen
                            || board[x][y].getPiece() instanceof Rook && delta_x*delta_y == 0));

            }
        }
        return false;
    }

    public void move(Square source, Square target){
        target.setPiece(source.getPiece());
        source.setPiece(null);
        if(target.getPiece() instanceof King) updateKingPosition(target.getPiece().getColor(), target);
    }

    private void updateKingPosition(Color color, Square target) {
        if(color == Color.WHITE) whiteKingPosition = target;
        else blackKingPosition = target;
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
        System.out.println();
    }

    public static void main(String[] args){
        Board board = new Board();
        board.print();
        Square[][] array = Board.getBoard();
        board.move(array[1][4], array[3][4]);
        board.move(array[6][4], array[4][4]);
        board.move(array[7][3], array[7][4]);
        board.move(array[3][4], array[5][5]);
        board.move(array[4][4], array[6][6]);
        board.move(array[0][3], array[1][4]);
        board.print();
        System.out.println(array[1][4].getPiece().getMoves());
    }
}
