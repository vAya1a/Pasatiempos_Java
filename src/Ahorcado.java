import java.util.Scanner;

public class Ahorcado {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String palabraOculta = seleccionarPalabra(); // Puedes agregar más palabras si lo deseas
        char[] palabraAdivinada = new char[palabraOculta.length()];
        int vidas = 6;

        inicializarPalabraAdivinada(palabraAdivinada);

        while (vidas > 0 && !esPalabraAdivinada(palabraAdivinada)) {
            System.out.println("Palabra: " + new String(palabraAdivinada));
            System.out.println("Vidas restantes: " + vidas);
            System.out.print("Ingresa una letra: ");
            char letra = scanner.next().charAt(0);

            if (adivinarLetra(letra, palabraOculta, palabraAdivinada)) {
                System.out.println("¡Bien hecho! La letra está en la palabra.");
            } else {
                System.out.println("Incorrecto. Pierdes una vida.");
                vidas--;
            }
        }

        scanner.close();

        if (esPalabraAdivinada(palabraAdivinada)) {
            System.out.println("¡Felicidades! Has adivinado la palabra: " + palabraOculta);
        } else {
            System.out.println("¡Oh no! Te quedaste sin vidas. La palabra era: " + palabraOculta);
        }
    }

    static String seleccionarPalabra() {
        String[] palabras = { "java", "programacion", "ahorcado", "computadora", "openai" };
        int indice = (int) (Math.random() * palabras.length);
        return palabras[indice];
    }

    static void inicializarPalabraAdivinada(char[] palabraAdivinada) {
        for (int i = 0; i < palabraAdivinada.length; i++) {
            palabraAdivinada[i] = '_';
        }
    }

    static boolean adivinarLetra(char letra, String palabraOculta, char[] palabraAdivinada) {
        boolean acertado = false;

        for (int i = 0; i < palabraOculta.length(); i++) {
            if (palabraOculta.charAt(i) == letra) {
                palabraAdivinada[i] = letra;
                acertado = true;
            }
        }

        return acertado;
    }

    static boolean esPalabraAdivinada(char[] palabraAdivinada) {
        for (char letra : palabraAdivinada) {
            if (letra == '_') {
                return false;
            }
        }
        return true;
    }
}
