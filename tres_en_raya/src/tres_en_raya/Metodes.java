package tres_en_raya;

import java.util.Scanner;

public class Metodes {

    private static final char PLAYER_SYMBOL = 'X';
    private static final char COMPUTER_SYMBOL = 'O';
    private static final char EMPTY_CELL = ' ';

    private static char[][] board;
    private static int moves;

    public static void initializeBoard() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
        moves = 0;
    }

    public static void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayBoard();
            playerMove(scanner);
            if (checkWin(PLAYER_SYMBOL) || isBoardFull()) {
                break;
            }
            computerMove();
            if (checkWin(COMPUTER_SYMBOL) || isBoardFull()) {
                break;
            }
        }
        displayBoard();
        if (checkWin(PLAYER_SYMBOL)) {
            System.out.println("¡Felicidades, has ganado!");
        } else if (checkWin(COMPUTER_SYMBOL)) {
            System.out.println("Lo siento, la computadora ha ganado.");
        } else {
            System.out.println("¡Es un empate!");
        }
    }

    private static void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void playerMove(Scanner scanner) {
        int row, col;
        do {
            System.out.print("Ingresa la fila (1-3): ");
            row = scanner.nextInt() - 1;
            System.out.print("Ingresa la columna (1-3): ");
            col = scanner.nextInt() - 1;
        } while (!isValidMove(row, col));
        board[row][col] = PLAYER_SYMBOL;
        moves++;
    }

    private static void computerMove() {
        int row, col;
        do {
            row = (int) (Math.random() * 3);
            col = (int) (Math.random() * 3);
        } while (!isValidMove(row, col));
        board[row][col] = COMPUTER_SYMBOL;
        moves++;
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == EMPTY_CELL;
    }

    private static boolean checkWin(char symbol) {
        // Verificar filas
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return true;
            }
        }
        // Verificar columnas
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
                return true;
            }
        }
        // Verificar diagonales
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;
        }
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true;
        }
        return false;
    }

    private static boolean isBoardFull() {
        return moves == 9;
    }

}
