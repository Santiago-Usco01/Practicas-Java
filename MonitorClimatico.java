package practice1;
import java.util.Random;
import java.util.ArrayList;
/**
 *
 * @author Santiago Cardenas Claros
 */
public class MonitorClimatico {

    public static void main(String[] args) {

        int[] array = generaArrayInt(0, 30);
        System.out.print("Temperaturas registradas: ");
        imprimirArray(array);

        System.out.println("Temperatura Mínima: " + minimoArrayInt(array) + "°C");
        System.out.println("Temperatura Máxima: " + maximoArrayInt(array) + "°C");
        System.out.println("Temperatura Promedio: " + mediaArrayInt(array) + "°C");
        System.out.println("Desviacion Estandar de las temperaturas: " + desviacionArrayInt(array));
        System.out.println("¿Está la temperatura 16°C? " + estaEnArrayInt(array, 16));
        System.out.println("Dia que está la temperatura: " + posicionEnArray(array, 16));

        System.out.print("Temperaturas Invertidas: ");
        imprimirArray(volteaArrayInt(array));

        System.out.print("Temperaturas rotadas 2 dias a la derecha: ");
        imprimirArray(rotaDerechaArrayInt(array, 2));

        System.out.print("Temperaturas rotadas 2 a la izquierda: ");
        imprimirArray(rotaIzquierdaArrayInt(array, 2));

        String[] clasificaciones = clasificarTemperaturas(array);
        System.out.println("\nClasificación de temperaturas:");
        imprimirClasificaciones(clasificaciones);

        int[] diasAnomalos = detectarAnomalias(array);
        imprimirAnomalias(array, diasAnomalos);

       
    }

    public static int[] generaArrayInt(int minimo, int maximo) {

        int[] array = new int[7];
        Random random = new Random();

        for (int i = 0; i < 7; i++) {
            array[i] = random.nextInt(maximo - minimo + 1) + minimo;
        }
        return array;
    }

    public static int minimoArrayInt(int[] array) {

        int minimo = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minimo) {
                minimo = array[i];
            }
        }
        return minimo;
    }

    public static int maximoArrayInt(int[] array) {

        int maximo = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maximo) {
                maximo = array[i];
            }
        }
        return maximo;
    }

    public static int mediaArrayInt(int[] array) {

        int suma = 0;
        for (int num : array) {
            suma += num;
        }

        return suma / array.length;
    }

    public static double desviacionArrayInt(int[] array) {

        double media = mediaArrayInt(array);
        double sumaDiferenciasCuadradas = 0;

        for (int num : array) {
            double diferencia = num - media;
            sumaDiferenciasCuadradas += diferencia * diferencia;
        }

        double varianza = sumaDiferenciasCuadradas / array.length;
        return Math.sqrt(varianza);

    }

    public static String estaEnArrayInt(int[] array, int numero) {

        for (int num : array) {
            if (num == numero) {
                return ("La temperatura sí se encuentra registrada");
            }
        }
        return ("La temeperatura no se encuentra registrada");
    }

    public static int posicionEnArray(int[] array, int numero) {

        for (int i = 0; i < array.length; i++) {
            if (array[i] == numero) {
                return i;
            }
        }
        return -1;
    }

    public static int[] volteaArrayInt(int[] array) {

        int[] volteado = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            volteado[i] = array[array.length - 1 - i];
        }
        return volteado;
    }

    public static int[] rotaDerechaArrayInt(int[] array, int n) {

        n = n % array.length;
        if (n == 0) {
            return array.clone();
        }

        int[] rotado = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            rotado[(i + n) % array.length] = array[i];
        }
        return rotado;
    }

    public static int[] rotaIzquierdaArrayInt(int[] array, int n) {

        n = n % array.length; 
        if (n == 0) {
            return array.clone();
        }

        int[] rotado = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            rotado[i] = array[(i + n) % array.length];
        }
        return rotado;
    }

    public static String[] clasificarTemperaturas(int[] array) {
        

        String[] clasificaciones = new String[array.length];

        for (int i = 0; i < array.length; i++) {
            int temp = array[i];

            if (temp < 18) {
                clasificaciones[i] = "Frío";
            } else if (temp >= 18 && temp <= 25) {
                clasificaciones[i] = "Templado";
            } else {
                clasificaciones[i] = "Caliente";
            }
        }

        return clasificaciones;
    }

    public static int[] detectarAnomalias(int[] array) {
       

        if (array.length == 1) {
            return new int[0];
        }

        double media = mediaArrayInt(array);
        double desviacion = desviacionArrayInt(array);
        double umbralSuperior = media + (1.5 * desviacion);
        double umbralInferior = media - (1.5 * desviacion);

        ArrayList<Integer> diasAnomalos = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            double temp = array[i];

            if (temp > umbralSuperior || temp < umbralInferior) {
                diasAnomalos.add(i);
            }
        }

        int[] resultado = new int[diasAnomalos.size()];
        for (int i = 0; i < diasAnomalos.size(); i++) {
            resultado[i] = diasAnomalos.get(i);
        }

        return resultado;
    }

    public static void imprimirClasificaciones(String[] clasificaciones) {
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

        for (int i = 0; i < clasificaciones.length; i++) {
            System.out.println(dias[i] + ": " + clasificaciones[i]);
        }
    }

    public static void imprimirAnomalias(int[] array, int[] diasAnomalos) {
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

        if (diasAnomalos.length == 0) {
            System.out.println("No se detectaron temperaturas anómalas");
            return;
        }

        System.out.println("Temperaturas anómalas detectadas:");
        for (int dia : diasAnomalos) {
            System.out.println(dias[dia] + ": " + array[dia] + "°C");
        }
    }

    public static void imprimirArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "°C");
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

}
