import java.util.Random;
import java.util.Scanner;

public class NumeroOculto {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int numeroOculto = random.nextInt(100) + 1;
        int intentos = 0;

        System.out.println("¡Bienvenido al juego de adivinanza de números (del 1 al 100)!");
        System.out.println("Intenta adivinar el número oculto.");

        while (true) {
            System.out.print("Ingresa tu intento: ");
            int intento = scanner.nextInt();
            intentos++;

            if (intento == numeroOculto) {
                System.out.println("¡Felicidades! Has adivinado el número en " + intentos + " intentos.");
                break;
            } else if (intento < numeroOculto) {
                System.out.println("El número oculto es mayor. Sigue intentando.");
            } else {
                System.out.println("El número oculto es menor. Sigue intentando.");
            }
        }

        scanner.close();
    }
}
