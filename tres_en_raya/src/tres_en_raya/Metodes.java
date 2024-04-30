package tres_en_raya;

import java.util.Scanner;

public class Metodes {

    private static final char JUGADOR = 'X';
    private static final char RIVAL = 'O';
    private static final char CASILLA = ' ';

    private static char[][] tabler;
    private static int moviment;

    public static void initializarTabler() {
        tabler = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabler[i][j] = CASILLA;
            }
        }
        moviment = 0;
    }

    public static void ComenzarJoc() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            GenerarTabler();
            MovimentJugador(scanner);
            if (ComprovarWin(JUGADOR) || isBoardFull()) {
                break;
            }
            MovimentRival();
            if (ComprovarWin(RIVAL) || isBoardFull()) {
                break;
            }
        }
        GenerarTabler();
        if (ComprovarWin(JUGADOR)) {
            System.out.println("¡Felicidades, has ganado!");
        } else if (ComprovarWin(RIVAL)) {
            System.out.println("Lo siento, la computadora ha ganado.");
        } else {
            System.out.println("¡Es un empate!");
        }
    }

    private static void GenerarTabler() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tabler[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void MovimentJugador(Scanner scanner) {
        int row, col;
        do {
            System.out.print("Ingresa la fila (1-3): ");
            row = scanner.nextInt() - 1;
            System.out.print("Ingresa la columna (1-3): ");
            col = scanner.nextInt() - 1;
        } while (!MovimentValid(row, col));
        tabler[row][col] = JUGADOR;
        moviment++;
    }

    private static void MovimentRival() {
        int row, col;
        do {
            row = (int) (Math.random() * 3);
            col = (int) (Math.random() * 3);
        } while (!MovimentValid(row, col));
        tabler[row][col] = RIVAL;
        moviment++;
    }

    private static boolean MovimentValid(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && tabler[row][col] == CASILLA;
    }

    private static boolean ComprovarWin(char symbol) {
        // Verificar filas
        for (int i = 0; i < 3; i++) {
            if (tabler[i][0] == symbol && tabler[i][1] == symbol && tabler[i][2] == symbol) {
                return true;
            }
        }
        // Verificar columnas
        for (int i = 0; i < 3; i++) {
            if (tabler[0][i] == symbol && tabler[1][i] == symbol && tabler[2][i] == symbol) {
                return true;
            }
        }
        // Verificar diagonales
        if (tabler[0][0] == symbol && tabler[1][1] == symbol && tabler[2][2] == symbol) {
            return true;
        }
        return tabler[0][2] == symbol && tabler[1][1] == symbol && tabler[2][0] == symbol;
    }

    private static boolean isBoardFull() {
        return moviment == 9;
    }

}
