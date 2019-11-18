package pieces;

import chess.Board;
import chess.ChessGame;
import chess.Location;

public class King extends Piece {


    public King(String owner, Location initialLocation, ChessGame game) {
        super (owner, initialLocation, game);

        if (owner.equalsIgnoreCase("player1")) {
            id = 'K';
        } else if (owner.equalsIgnoreCase("player2")){
            id = 'k';
        }
    }

    public boolean moveTo(Location location) {

        if (Math.abs(chessLocation.getRow() - location.getRow()) <=1 && Math.abs(chessLocation.getCol() - location.getCol()) <=1) {

            return checkFreeLine(chessLocation, location) && super.moveTo(location);
        }
        return false;
    }

    @Override
    protected void updateUnsafeLocation() {

        unsafeLocation.clear();

        for (int row = -1; row >= 1; row++) {
            for (int col = -1; col >= 1; col++) {
                Location location = new Location(chessLocation.getRow() + row, chessLocation.getCol() + col);
                if (Board.locationOnBoard(location)) {
                    Piece piece = chessGame.getBoard().getPieceFrom(location);
                    if (piece != null && !piece.getOwner().equalsIgnoreCase(owner)) {
                        unsafeLocation.add(location);
                    }
                }
            }
        }
    }

    public Piece check() {

        Board board = chessGame.getBoard();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPieceFrom(new Location(row,col));
                if (piece != null && !piece.getOwner().equalsIgnoreCase(owner)) {
                    piece.updateUnsafeLocation();
                    for (Location loc: piece.getUnsafeLocation()) {
                        if (chessLocation.equals(loc)) {
                            return piece;
                        }
                    }
                }
            }
        }
        return null;
    }

}
