import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Bienvenido al juego de Blackjack!");
            System.out.println("1. ¡Jugar al Blackjack!");
            System.out.println("2. Manual de Instrucciones");
            System.out.println("0. Salir");

            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 0) {
                System.out.println("¡Hasta luego!");
                break;
            } else if (opcion == 1) {
                jugarBlackjack(scanner);
            } else if (opcion == 2) {
                mostrarManualBlackjack();
            } else {
                System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }

        // Cerrar el scanner
        scanner.close();
    }

    private static void mostrarManualBlackjack() {
        System.out.println("Manual de Instrucciones - Juego de Blackjack:");
        System.out.println("1. Elige la opción '¡Jugar al Blackjack!' en el menú principal.");
        System.out.println("2. Se te repartirán dos cartas iniciales y se mostrará una carta del crupier.");
        System.out.println("3. Tu objetivo es obtener una puntuación más cercana a 21 que el crupier sin pasarte.");
        System.out.println(
                "4. Las cartas numéricas tienen su valor nominal, las figuras (J, Q, K) valen 10 y el As vale 11 o 1.");
        System.out.println("5. Puedes pedir más cartas ('S') o quedarte con tu mano ('N').");
        System.out.println("6. Si tu puntuación supera 21, pierdes automáticamente.");
        System.out.println("7. El crupier seguirá sacando cartas hasta alcanzar una puntuación de al menos 17.");
        System.out.println("8. Al final, se compararán las puntuaciones y se determinará al ganador.");
        System.out.println(
                "9. Puedes seleccionar la opción 'Salir' en cualquier momento para regresar al menú principal.");
        System.out.println("10. ¡Diviértete jugando al Blackjack!");
    }

    private static void jugarBlackjack(Scanner scanner) {
        List<String> mazo = generarMazo();
        Collections.shuffle(mazo);

        List<String> manoJugador = new ArrayList<>();
        List<String> manoCrupier = new ArrayList<>();

        // Inicializar manos
        repartirCarta(mazo, manoJugador);
        repartirCarta(mazo, manoJugador);
        repartirCarta(mazo, manoCrupier);
        repartirCarta(mazo, manoCrupier);

        // Mostrar mano inicial del jugador y una carta del crupier
        System.out.println("Tu mano: " + manoJugador + " (Total: " + calcularPuntuacion(manoJugador) + ")");
        System.out.println("Carta visible del crupier: " + manoCrupier.get(0));

        // Jugar la mano del jugador
        while (calcularPuntuacion(manoJugador) < 21) {
            System.out.print("¿Quieres pedir otra carta? (S/N): ");
            char respuesta = scanner.next().charAt(0);

            if (respuesta == 'S' || respuesta == 's') {
                repartirCarta(mazo, manoJugador);
                System.out.println("Tu siguiente carta es...");
                esperar(1000);
                System.out.println("Tu mano: " + manoJugador + " (Total: " + calcularPuntuacion(manoJugador) + ")");
            } else if (respuesta == 'N' || respuesta == 'n') {
                break;
            } else {
                System.out.println("Respuesta no válida. Inténtalo de nuevo.");
            }
        }

        // Jugar la mano del crupier
        System.out.println("El crupier revela su segunda carta...");
        esperar(1000);
        while (calcularPuntuacion(manoCrupier) < 17) {
            repartirCarta(mazo, manoCrupier);
            System.out.println("El crupier pide otra carta...");
            esperar(1000);
        }

        // Mostrar manos finales
        System.out.println("Tu mano final: " + manoJugador + " (Total: " + calcularPuntuacion(manoJugador) + ")");
        System.out.println("Mano del crupier: " + manoCrupier + " (Total: " + calcularPuntuacion(manoCrupier) + ")");

        // Determinar ganador
        determinarGanador(calcularPuntuacion(manoJugador), calcularPuntuacion(manoCrupier));
    }

    private static List<String> generarMazo() {
        List<String> mazo = new ArrayList<>();
        String[] palos = { "Corazones", "Diamantes", "Treboles", "Picas" };
        String[] valores = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

        for (String palo : palos) {
            for (String valor : valores) {
                mazo.add(valor + " de " + palo);
            }
        }

        return mazo;
    }

    private static void repartirCarta(List<String> mazo, List<String> mano) {
        mano.add(mazo.remove(0));
    }

    private static int calcularPuntuacion(List<String> mano) {
        int puntuacion = 0;
        int ases = 0;

        for (String carta : mano) {
            String valor = carta.split(" ")[0];

            if (valor.equals("A")) {
                ases++;
                puntuacion += 11;
            } else if (valor.equals("K") || valor.equals("Q") || valor.equals("J")) {
                puntuacion += 10;
            } else {
                puntuacion += Integer.parseInt(valor);
            }
        }

        // Manejar ases si la puntuación es mayor a 21
        while (puntuacion > 21 && ases > 0) {
            puntuacion -= 10;
            ases--;
        }

        return puntuacion;
    }

    private static void determinarGanador(int puntuacionJugador, int puntuacionCrupier) {
        if (puntuacionJugador > 21) {
            System.out.println("¡Has perdido! Tu puntuación supera 21.");
        } else if (puntuacionCrupier > 21 || puntuacionJugador > puntuacionCrupier) {
            System.out.println("¡Felicidades! Has ganado.");
        } else if (puntuacionJugador == puntuacionCrupier) {
            System.out.println("Es un empate.");
        } else {
            System.out.println("¡Has perdido! La puntuación del crupier es mayor.");
        }
    }

    private static void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
