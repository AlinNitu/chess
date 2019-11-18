package pieces;

import chess.ChessGame;
import chess.Location;

public class Queen extends Piece {

    public Queen(String owner, Location initialLocation, ChessGame game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            id = 'Q';
        } else if (owner.equalsIgnoreCase("player2")) {
            id = 'q';
        }
    }

    @Override
    public boolean moveTo(Location location) {
        return checkFreeLine(chessLocation, location) && super.moveTo(location);
    }

    @Override
    protected void updateUnsafeLocation() {
        unsafeLocation.clear();

        super.updateVertical(1);
        super.updateVertical(-1);

        super.updateHorizontal(1);
        super.updateHorizontal(-1);

        super.updateDiagonal(1, 1);
        super.updateDiagonal(-1, 1);
        super.updateDiagonal(1, -1);
        super.updateDiagonal(-1, -1);
    }
}
