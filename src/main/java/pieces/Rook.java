package pieces;

import chess.ChessGame;
import chess.Location;

public class Rook extends Piece {

    public Rook(String owner, Location initialLocation, ChessGame game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            id = 'R';
        } else if (owner.equalsIgnoreCase("player2")) {
            id = 'r';
        }
    }

    @Override
    public boolean moveTo(Location location) {
        if ((chessLocation.getRow() == location.getRow()) !=
                (chessLocation.getCol() == location.getCol())) {

            return checkFreeLine(chessLocation, location) && super.moveTo(location);
        }
        return false;
    }

    @Override
    protected void updateUnsafeLocation() {
        unsafeLocation.clear();

        super.updateVertical(1);
        super.updateVertical(-1);
        super.updateHorizontal(1);
        super.updateHorizontal(-1);
    }
}
