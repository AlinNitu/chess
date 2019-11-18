package chess;

import pieces.*;

public class ChessGame {

    private Board board;
    private King player1King;
    private King player2King;

    public ChessGame() {
        board = new Board();
        setupTeam(0,"player1");
        setupTeam(7, "player2");
    }


    private void setupTeam (int side, String player) {

        int one = (side > 0) ? -1: 1;
        int colIncrement = 0;

        Piece r1 = new Rook(player, new Location(side, colIncrement), this);
        Piece r2 = new Rook(player, new Location(side, 7-colIncrement), this);
        colIncrement += 1;


        Piece n1 = new Knight(player, new Location(side, colIncrement), this);
        Piece n2 = new Knight(player, new Location(side, 7-colIncrement), this);
        colIncrement += 1;


        Piece b1 = new Bishop(player, new Location(side, colIncrement), this);
        Piece b2 = new Bishop(player, new Location(side, 7-colIncrement), this);
        colIncrement += 1;


        if (player.equalsIgnoreCase("player1")) {
            player1King = new King(player, new Location(side, colIncrement), this);
        } else {
            player2King = new King(player, new Location(side, colIncrement), this);
        }

        Piece q = new Queen(player, new Location(side, 7-colIncrement), this);


        for (int i = 0; i < 8; i++) {
            Piece p = new Pawn(player, new Location(side + one, i), this);
        }


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
