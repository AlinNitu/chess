package chess;

import pieces.Piece;


//Chess board that is also responsible of moving/removing pieces
public class Board {

    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];
    }

    public boolean spotHasPiece (int row, int col) {
        return board[row][col] != null;
    }

    private void putPieceTo (Piece piece, Location location) {

        if (spotHasPiece(location.getRow(), location.getCol())) {
            removePieceFrom(location);
        }
        if (piece.getChessLocation() != null) {
            removePieceFrom(piece.getChessLocation());
        }
        board[location.getRow()][location.getCol()] = piece;
        piece.setChessLocation(location);
    }

    private void removePieceFrom (Location location) {
        board[location.getRow()][location.getCol()] = null;
    }

    public static boolean locationOnBoard(Location location) {
        return location.getRow() >= 0 && location.getRow() < 8 &&
                location.getCol() >=0 && location.getCol() < 8;

    }

    public Piece getPieceFrom (Location location) {

        return board[location.getRow()][location.getCol()];
    }


    public String boardPrint() {
        String s = " 0 1 2 3 4 5 6 7\n";
        for (int row = 0; row < 8; row++) {
            s += row;

            for (int col = 0; col < 8; col++) {
                if (board[row][col] != null) {
                    s += " " + board[row][col].getId();
                } else {
                    s += " -";
                }
            }
            s += "\n";
        }
        return s;
    }
}
