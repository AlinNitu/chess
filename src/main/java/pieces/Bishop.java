package pieces;

import chess.ChessGame;
import chess.Location;

public class Bishop extends Piece {

    public Bishop (String owner, Location initialLocation, ChessGame game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            id = 'B';
        } else if (owner.equalsIgnoreCase("player2")) {
            id = 'b';
        }
    }

    @Override
    public boolean moveTo(Location location) {
        if (Math.abs(chessLocation.getRow() - location.getRow()) ==
                Math.abs(chessLocation.getCol() - location.getCol())) {
            return checkFreeLine(chessLocation, location) && super.moveTo(location);
        }
        return false;
    }

    protected void updateUnsafeLocation() {
        unsafeLocation.clear();
        super.updateDiagonal(1,1);
        super.updateDiagonal(-1, 1);
        super.updateDiagonal(1, -1);
        super.updateDiagonal(-1, -1);
    }
}
