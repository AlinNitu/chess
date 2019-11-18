package chess;

import pieces.King;
import pieces.Piece;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ChessGame chessGame = new ChessGame();
        boolean gameOver = false;
        String currPlayer = "player1";
        Scanner scanner = new Scanner(System.in);
        String input;

        Location newLocation;
        Piece currPiece;
        King king;

        while (!gameOver) {
            try {
                System.out.println(chessGame.getBoard().boardPrint());
                System.out.println(currPlayer + " is your turn:");
                System.out.println("M - Move a piece");
                System.out.println("Q - Quit game");
                System.out.println("R - Reset the game");
                input = scanner.nextLine();

                if (input.equalsIgnoreCase("Q")) {
                    gameOver = true;
                    System.out.println("Game is over!");
                    continue;

                } else if (input.equalsIgnoreCase("R")) {
                    chessGame = new ChessGame();
                    System.out.println("The game was restarted!");
                    continue;

                } else if (input.equalsIgnoreCase("M")) {
                    if(currPlayer.equalsIgnoreCase("player1")) {
                        king = chessGame.getPlayer1King();
                    } else {
                        king = chessGame.getPlayer2King();
                    }

                    Piece checkAlert = king.check();

                    if (checkAlert != null) {
                        System.out.println("The King is in check caused by the piece at row " + checkAlert.getChessLocation().getRow()
                        + " and column " + checkAlert.getChessLocation().getCol());
                    }


                    currPiece = getCurrentPiece(chessGame, currPlayer);
                    newLocation = getNewLocation();

                    if (currPiece.moveTo(newLocation)) {
                        currPlayer = (currPlayer.equalsIgnoreCase("player1")) ? "player2": "player1";
                    } else {
                        System.out.println("Invalid move, try again.");
                    }
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Cannot parse the input.");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("We don't really need this I think but just in case.");
                e.printStackTrace();
            }
        }
    }

    private static Piece getCurrentPiece(ChessGame chessGame, String currentPlayer) {
        Scanner scanner = new Scanner(System.in);
        String input;
        Location currLocation;
        Piece currPiece;

        while (true) {
            System.out.println("Move from: row, col");
            input = scanner.nextLine();
            currLocation = createChessLocation(input);
            if (!Board.locationOnBoard(currLocation)) {
                System.out.println("Location not on board, try again.");
                continue;
            }
            currPiece = chessGame.getBoard().getPieceFrom(currLocation);
            if (currPiece == null) {
                System.out.println("Invalid piece selected, out of bounds.");
            } else if (currPiece.getOwner().equalsIgnoreCase(currentPlayer)) {
                return currPiece;
            } else {
                System.out.println("Invalid piece selected, not your piece.");
            }
        }

    }

    private static Location getNewLocation() {
        Scanner scanner = new Scanner(System.in);
        String input;

        Location newLocation;

        while (true) {
            System.out.println("Move to: row, col");
            input = scanner.nextLine();
            newLocation = createChessLocation(input);
            if (!Board.locationOnBoard(newLocation)) {
                System.out.println("Invalid location selected, out of bounds.");
            } else {
                return newLocation;
            }
        }
    }


    private static Location createChessLocation(String input) {
        int row = Integer.parseInt(input.split(",")[0].trim());
        int col = Integer.parseInt(input.split(",")[1].trim());
        return new Location(row, col);
    }

}
