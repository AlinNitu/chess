package pieces;

import chess.Board;
import chess.ChessGame;
import chess.Location;

import java.util.ArrayList;

// General characteristics of a piece
public abstract class Piece implements PieceInterface {

    protected ChessGame chessGame;
    protected String owner;
    protected Location chessLocation;
    protected char id;
    protected ArrayList<Location> unsafeLocation;

    protected abstract void updateUnsafeLocation();

// Setting the members of Piece
    public Piece(String owner, Location initialLocation, ChessGame game) {
        this.owner = owner;
        chessLocation = null;
        chessGame = game;
        unsafeLocation = new ArrayList<>();
        chessGame.getBoard().putPieceTo(this, initialLocation);
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
        if (start.getCol() - end.getCol() == start.getRow() - end.getRow()) {
            int one = (start.getRow() - end.getRow() < 0) ? 1: -1;
            for (int inc = one; Math.abs(inc) < Math.abs(start.getRow() - end.getRow()); inc += one) {
                if (chessGame.getBoard().spotHasPiece(start.getRow() + inc, start.getCol() + inc)) {
                    return false;
                }
            }
            return true;
        } else if (start.getCol() - end.getCol() * -1 ==
                start.getRow() - end.getCol()) {

            int one = (start.getRow() - end.getRow() < 0) ? 1: -1;
            int negOne = one * -1;
            for (int i = one; Math.abs(i) < Math.abs(start.getRow() - end.getRow()); i += one) {
                if (chessGame.getBoard().spotHasPiece(start.getRow() + i, start.getCol() + (i * negOne))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    protected void updateVertical(int one) {
        Location location = new Location(chessLocation.getRow() + one, chessLocation.getCol());
        //int i = one;
        while (Board.locationOnBoard(location)) {
            Piece piece = chessGame.getBoard().getPieceFrom(location);
            if (piece != null) {
                if (!piece.getOwner().equalsIgnoreCase(owner)) {
                    unsafeLocation.add(location);
                    return;
                } else if (!location.equals(location)) {
                    unsafeLocation.add(new Location(location.getRow() - one, location.getCol()));
                    return;
                }
            } else {
                location = new Location(location.getRow() + one, location.getCol());
            }
        }
    }

    protected void updateHorizontal(int one){
        Location location = new Location(chessLocation.getRow(), chessLocation.getCol() + one);
        while (Board.locationOnBoard(location)) {
            Piece piece = chessGame.getBoard().getPieceFrom(location);
            if (piece != null) {
                if (!piece.getOwner().equalsIgnoreCase(owner)) {
                    unsafeLocation.add(location);
                    return;
                } else if (!chessLocation.equals(location)) {
                    unsafeLocation.add(new Location(location.getRow(), location.getCol() - one));
                    return;
                }
            } else {
                location = new Location(location.getRow(), location.getCol() + one);
            }
        }
    }

    protected void updateDiagonal(int rowOne, int colOne) {
        Location location = new Location(chessLocation.getRow() + rowOne, chessLocation.getCol() + colOne);
        while (Board.locationOnBoard(location)) {
            Piece piece = chessGame.getBoard().getPieceFrom(location);
            if (piece != null) {
                if (!piece.getOwner().equalsIgnoreCase(owner)) {
                    unsafeLocation.add(location);
                    return;
                } else if (!chessLocation.equals(location)) {
                    unsafeLocation.add(new Location(location.getRow() - rowOne, location.getCol() - colOne));
                    return;
                }
            } else {
                location = new Location(location.getRow() + rowOne, location.getCol() + colOne);
            }
        }
    }


    public boolean moveTo(Location newLocation) {
        Board board = chessGame.getBoard();
        Piece oldPiece = board.getPieceFrom(newLocation);

        if (oldPiece == null || oldPiece.getOwner() != owner) {
            board.putPieceTo(this, newLocation);
            return true;
        }
        return false;
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

    public String getOwner() {
        return owner;
    }

    public ArrayList<Location> getUnsafeLocation() {
        return unsafeLocation;
    }
}
