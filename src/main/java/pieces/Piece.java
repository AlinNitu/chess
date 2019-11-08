package pieces;

import chess.ChessGame;
import chess.Location;

import java.util.ArrayList;

// General characteristics of a piece
public abstract class Piece implements PieceInterface {

    protected ChessGame chessGame;
    protected String owner;
    protected Location chessLocation;
    protected char id;
    protected ArrayList<Location> unsafeLocations;

    protected abstract void updateUnsafeLocations();

// Setting the members of Piece
    public Piece(String owner, Location initialLocation, ChessGame game) {
        this.owner = owner;
        chessLocation = null;
        chessGame = game;
        unsafeLocations = new ArrayList<>();
        //chessGame
    }

// Checks until where the move is available (horizontal, vertical and diagonal)
    protected boolean checkFreeLine (Location start, Location end) {

        //horizontal
        if (start.getRow() == end.getRow()) {
            int one = (start.getCol() - end.getCol() < 0) ? 1: -1;
            for (int col = start.getCol() + one; col < end.getCol(); col += one) {
                if (chessGame.getBoard().spotHasPiece(start.getRow(), col)) {
                    return false;
                }
            }
            return true;
        }

        //vertical
        if (start.getCol() == end.getCol()) {
            int one = (start.getRow() - end.getRow() < 0) ? 1: -1;
            for (int row = start.getRow() + one; row < end.getRow(); row += one) {
                if (chessGame.getBoard().spotHasPiece(row, start.getCol())) {
                    return false;
                }
            }
            return true;
        }

        //diagonal
        return true;
    }




    public Location getChessLocation() {
        return chessLocation;
    }

    public void setChessLocation(Location loc) {
        chessLocation = loc;
    }

    public char getId() {
        return id;
    }

}
