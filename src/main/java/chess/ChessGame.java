package chess;

import pieces.King;

public class ChessGame {

    private Board board;
    private King player1King;
    private King player2King;


    private void setupTeam (int side, String player) {

        int one = side > 0 ? 1: -1;
        int colIncrement = 0;



    }

    public Board getBoard() {
        return board;
    }

    public King getPlayer1King() {
        return player1King;
    }

    public King getPlayer2King() {
        return player2King;
    }
}
