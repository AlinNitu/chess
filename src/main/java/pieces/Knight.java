package pieces;

import chess.Board;
import chess.ChessGame;
import chess.Location;

public class Knight extends Piece {

    public Knight(String owner, Location initialLocation, ChessGame game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            id = 'N';
        } else if (owner.equalsIgnoreCase("player2")) {
            id = 'n';
        }
    }

    @Override
    public boolean moveTo(Location location) {
        if (Math.abs(chessLocation.getRow() - location.getRow()) == 2 &&
                Math.abs(chessLocation.getCol() - location.getCol()) == 1) {

            return super.moveTo(location);
        } else if (Math.abs(chessLocation.getRow() - location.getRow()) == 1 &&
                Math.abs(chessLocation.getCol() - location.getCol()) == 2) {

            return super.moveTo(location);
        }
        return false;
    }

    @Override
    protected void updateUnsafeLocation() {
        int[] rowMoves = { -2, -1, 1, 2, -2, -1, 1, 2 };
        int[] colMoves = { 1, 2, 2, 1, -1, -2, -2, -1 };

        unsafeLocation.clear();
        for (int i = 0; i < 8; i++) {
            Location location = new Location(rowMoves[i], colMoves[i]);
            if (Board.locationOnBoard(location)) {
                Piece piece = chessGame.getBoard().getPieceFrom(location);

                if (piece != null &&
                        !piece.getOwner().equals(owner)) {

                    unsafeLocation.add(location);
                }
            }
        }
    }
}
