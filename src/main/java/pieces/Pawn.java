package pieces;

import chess.ChessGame;
import chess.Location;

public class Pawn extends Piece {

    private boolean firstMove;
    private int one;

    public Pawn(String owner, Location initialLocation, ChessGame game) {
        super(owner, initialLocation, game);

        if (owner.equalsIgnoreCase("player1")) {
            id = 'P';
            one = 1;
        } else if (owner.equalsIgnoreCase("player2")) {
            id = 'p';
            one = -1;
        }
        firstMove = true;
    }

    @Override
    public boolean moveTo(Location location){
        if (location.getCol() == chessLocation.getCol()) {
            if (location.getRow() - chessLocation.getRow() == one) {
                if (firstMove) {
                    firstMove = false;
                }
                return !chessGame.getBoard().spotHasPiece(location.getRow(), location.getCol()) && super.moveTo(location);
            } else if (firstMove && (location.getRow() - chessLocation.getRow() == (one * 2))) {
                if (firstMove) {
                    firstMove = false;
                }
                return !chessGame.getBoard().spotHasPiece(location.getRow(), location.getCol()) && super.moveTo(location);
            }
        } else if (Math.abs(location.getCol() - chessLocation.getCol()) == 1) {
            if (chessGame.getBoard().spotHasPiece(location.getRow(), location.getCol()) &&
                    location.getRow() - chessLocation.getRow() == one) {

                if (firstMove) {
                    firstMove = false;
                }
                return super.moveTo(location);
            }
        }
        return false;
    }

    @Override
    protected void updateUnsafeLocation() {
        int one = 0;
        if (owner.equalsIgnoreCase("player1") &&
                chessLocation.getRow() <= 6) {
            one = 1;
        } else if (owner.equalsIgnoreCase("player2") &&
                chessLocation.getRow() >= 1) {
            one = -1;
        }

        unsafeLocation.clear();

        if (chessLocation.getCol() >= 1) {
            unsafeLocation.add(new Location(chessLocation.getRow() + one, chessLocation.getCol() - 1));
        }
        if (chessLocation.getCol() <= 6) {
            unsafeLocation.add(new Location(chessLocation.getRow() + one, chessLocation.getCol() + 1));
        }
    }

}
