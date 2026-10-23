import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        char[][] matriz = new char[10][10];

        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        int vidas = 10;
        int filaR = 0;
        int colR = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                matriz[i][j] = '*';
            }
        }

        matriz[filaR][colR] = 'R';
        matriz[9][9] = 'F';

        int obstaculosColocados = 0;

        while (obstaculosColocados < 5) {
            int f = rand.nextInt(10);
            int c = rand.nextInt(10);

            if (matriz[f][c] == '*') {
                matriz[f][c] = 'X';
                obstaculosColocados++;

            }
        }

        int bateriasColocadas = 0;

        while (bateriasColocadas < 1) {
            int f = rand.nextInt(10);
            int c = rand.nextInt(10);

            if (matriz[f][c] == '*') {
                matriz[f][c] = 'B';
                bateriasColocadas++;

            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                System.out.print(matriz[i][j] + "  ");

            }

            System.out.println();
        }

        System.out.println("Vidas: " + vidas);

        do {
            System.out.print("Introduce orden de movimiento [W, S, A, D]: ");

            char orden = sc.next().charAt(0);

            int proxFila = filaR;
            int proxCol = colR;

            boolean ordenValida = true;

            switch (orden) {
                case 'W':
                    proxFila--;
                    break;

                case 'S':
                    proxFila++;
                    break;

                case 'A':
                    proxCol--;
                    break;

                case 'D':
                    proxCol++;
                    break;

                default:
                    System.out.println("Error: Letra no válida.");
                    ordenValida = false;
                    break;
            }

            if (ordenValida) {
                if (proxFila < 0 || proxFila > 9 || proxCol < 0 || proxCol > 9) {

                    System.out.println("Movimiento fuera del matriz");

                } else {

                    char destino = matriz[proxFila][proxCol];

                    switch (destino) {
                        case 'X':
                            System.out.println("Movimiento bloqueado");
                            break;

                        case 'F':
                            System.out.println("Rescate completado");
                            matriz[filaR][colR] = '*';
                            filaR = proxFila;
                            colR = proxCol;
                            matriz[filaR][colR] = 'R';
                            break;

                        case 'B':
                            System.out.println("Batería encontrada [+2] vidas");
                            vidas += 2;
                            matriz[filaR][colR] = '*';
                            filaR = proxFila;
                            colR = proxCol;
                            matriz[filaR][colR] = 'R';
                            break;

                        default:
                            matriz[filaR][colR] = '*';
                            filaR = proxFila;
                            colR = proxCol;
                            matriz[filaR][colR] = 'R';
                            break;
                    }
                }
            }

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {

                    System.out.print(matriz[i][j] + "  ");
                }

                System.out.println();
            }

            System.out.println("Vidas finales: " + vidas);

        } while (matriz[filaR][colR] != matriz[9][9]);

    }
}
