import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Adivinanzas {

    private static final Map<String, String[]> pistasPorPalabra = new HashMap<>();
    private static final Map<String, Boolean> palabrasAdivinadas = new HashMap<>();

    static {
        pistasPorPalabra.put("java", new String[] { "Lenguaje de programación", "Plataforma independiente",
                "Utilizado en desarrollo web", "Nombrado por una isla" });
        pistasPorPalabra.put("python", new String[] { "Lenguaje de programación interpretado", "Fácil de aprender",
                "Ampliamente utilizado en inteligencia artificial", "Nombrado por los Monty Python" });
        pistasPorPalabra.put("javascript", new String[] { "Lenguaje de programación del lado del cliente",
                "Utilizado para desarrollo web", "Compatible con todos los navegadores", "No es lo mismo que Java" });
        pistasPorPalabra.put("ruby", new String[] { "Lenguaje de programación interpretado", "Orientado a objetos",
                "Enfocado en la simplicidad y productividad", "Usado en desarrollo web" });
        pistasPorPalabra.put("csharp", new String[] { "Lenguaje de programación de Microsoft", "Orientado a objetos",
                "Utilizado en desarrollo de aplicaciones Windows", "Parte de la plataforma .NET" });

        for (String palabra : pistasPorPalabra.keySet()) {
            palabrasAdivinadas.put(palabra, false);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("¡Bienvenido al juego de adivinanzas sobre lenguajes de programación!");

            for (String palabra : pistasPorPalabra.keySet()) {
                if (palabrasAdivinadas.get(palabra)) {
                    System.out.println((obtenerNumeroPalabra(palabra) + 1) + ". "
                            + palabra.substring(0, 1).toUpperCase() + palabra.substring(1));
                } else {
                    System.out.println(
                            (obtenerNumeroPalabra(palabra) + 1) + ". Palabra " + (obtenerNumeroPalabra(palabra) + 1));
                }
            }

            System.out.println("6. Manual de Instrucciones");
            System.out.println("0. Salir");

            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();

            if (opcion > 0 && opcion <= pistasPorPalabra.size()) {
                String palabraElegida = obtenerPalabraPorNumero(opcion - 1);
                jugar(palabraElegida, scanner);
            } else if (opcion == 6) {
                mostrarManual();
            } else if (opcion != 0) {
                System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

        } while (opcion != 0);

        System.out.println("¡Hasta luego!");

        scanner.close();
    }

    private static void jugar(String palabraSecreta, Scanner scanner) {
        String[] pistas = pistasPorPalabra.get(palabraSecreta);

        for (int intento = 0; intento < pistas.length; intento++) {
            System.out.println("Pista " + (intento + 1) + ": " + pistas[intento]);
            System.out.print("Intento " + (intento + 1) + ": ");
            String respuestaUsuario = scanner.next().toLowerCase();

            if (respuestaUsuario.equals(palabraSecreta)) {
                System.out.println("¡Correcto! Has adivinado el lenguaje de programación.");
                palabrasAdivinadas.put(palabraSecreta, true);
                return;
            } else {
                if (intento < pistas.length - 1) {
                    System.out.println("Incorrecto. Aquí tienes otra pista.");
                }
            }
        }

        System.out.println("Lo siento, has agotado tus intentos. La palabra correcta era: " + palabraSecreta);
    }

    private static int obtenerNumeroPalabra(String palabra) {
        for (int i = 0; i < pistasPorPalabra.size(); i++) {
            if (obtenerPalabraPorNumero(i).equals(palabra)) {
                return i;
            }
        }
        return -1;
    }

    private static String obtenerPalabraPorNumero(int numero) {
        return pistasPorPalabra.keySet().toArray(new String[0])[numero];
    }

    private static void mostrarManual() {
        System.out.println("Manual de Instrucciones:");
        System.out.println("1. Elige un número del 1 al 5 para seleccionar un lenguaje de programación.");
        System.out.println("2. Se te darán pistas sobre el lenguaje de programación.");
        System.out.println("3. Intenta adivinar el lenguaje de programación escribiendo su nombre.");
        System.out.println("4. Si adivinas, la palabra se mostrará correctamente en el menú.");
        System.out.println("5. Tienes un total de 5 intentos para adivinar cada lenguaje.");
        System.out.println(
                "6. Puedes seleccionar la opción 'Manual de Instrucciones' en el menú para ver estas instrucciones.");
        System.out.println("7. ¡Diviértete adivinando los lenguajes de programación!");
    }
}
