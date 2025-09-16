package practice1;
import java.util.Scanner;
/**
 *
 * @author santiago cardenas claros
 */
public class PracticaExamen1 {
    
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== SISTEMA DE ANÁLISIS DE NOTAS ===");
        System.out.print("Cantidad de estudiantes: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();
        
        String[] nombres = new String[cantidad];
        double[] notas = new double[cantidad];
        
        for (int i = 0; i < cantidad; i++) {
            System.out.println("\nEstudiante #" + (i + 1));
            System.out.print("Nombre: ");
            nombres[i] = scanner.nextLine();
            System.out.print("Nota: ");
            notas[i] = scanner.nextDouble();
            scanner.nextLine();
            
            while (notas[i] < 0.0 || notas[i] > 5.0) {
                System.out.print("Nota inválida, ingrese otra: ");
                notas[i] = scanner.nextDouble();
                scanner.nextLine();
            }
        }
        
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Ver estadísticas generales");
            System.out.println("2. Buscar estudiante");
            System.out.println("3. Ver aprobados");
            System.out.println("4. Ver reprobados");
            System.out.println("5. Ver reporte completo");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    mostrarEstadisticas(nombres, notas);
                    break;
                case 2:
                    System.out.print("Nombre a buscar: ");
                    String nombreBuscar = scanner.nextLine();
                    buscarEstudiante(nombres, notas, nombreBuscar);
                    break;
                case 3:
                    mostrarAprobados(nombres, notas);
                    break;
                case 4:
                    mostrarReprobados(nombres, notas);
                    break;
                case 5:
                    mostrarReporteCompleto(nombres, notas);
                    break;
                case 6:
                    salir = true;
                    System.out.println("Sistema Terminado");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
        scanner.close();
    }
    
    public static void mostrarEstadisticas(String[] nombres, double[] notas) {
        System.out.println("\n=== ESTADÍSTICAS ===");
        double[] min = notaMinima(nombres, notas);
        double[] max = notaMaxima(nombres, notas);
        double media = mediaNotas(notas);
        double desviacion = desviacionEstandar(notas);
        double porcentaje = porcentajeAprobacion(notas);
        
        System.out.printf("Nota más alta: %.1f (%s)%n", max[0], nombres[(int)max[1]]);
        System.out.printf("Nota más baja: %.1f (%s)%n", min[0], nombres[(int)min[1]]);
        System.out.printf("Promedio del curso: %.1f%n", media);
        System.out.printf("Desviación estándar: %.1f%n", desviacion);
        System.out.printf("Porcentaje de aprobación: %.1f%%%n", porcentaje);
    }
    
    public static double[] notaMinima(String[] nombres, double[] notas) {
        double min = notas[0];
        int indice = 0;
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] < min) {
                min = notas[i];
                indice = i;
            }
        }
        return new double[]{min, indice};
    }
    
    public static double[] notaMaxima(String[] nombres, double[] notas) {
        double max = notas[0];
        int indice = 0;
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] > max) {
                max = notas[i];
                indice = i;
            }
        }
        return new double[]{max, indice};
    }
    
    public static double mediaNotas(double[] notas) {
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.length;
    }
    
    public static double desviacionEstandar(double[] notas) {
        double media = mediaNotas(notas);
        double suma = 0;
        for (double nota : notas) {
            suma += Math.pow(nota - media, 2);
        }
        return Math.sqrt(suma / notas.length);
    }
    
    public static void buscarEstudiante(String[] nombres, double[] notas, String nombre) {
        boolean encontrado = false;
        for (int i = 0; i < nombres.length; i++) {
            if (nombres[i].equalsIgnoreCase(nombre)) {
                System.out.printf("%s: %.1f - %s%n", nombres[i], notas[i], 
                    estaAprobado(notas[i]) ? "Aprobado" : "Reprobado");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Estudiante no encontrado");
        }
    }
    
    public static boolean estaAprobado(double nota) {
        return nota >= 3.0;
    }
    
    public static void mostrarAprobados(String[] nombres, double[] notas) {
        System.out.println("\n=== ESTUDIANTES APROBADOS ===");
        for (int i = 0; i < notas.length; i++) {
            if (estaAprobado(notas[i])) {
                System.out.printf("%s: %.1f%n", nombres[i], notas[i]);
            }
        }
    }
    
    public static void mostrarReprobados(String[] nombres, double[] notas) {
        System.out.println("\n=== ESTUDIANTES REPROBADOS ===");
        for (int i = 0; i < notas.length; i++) {
            if (!estaAprobado(notas[i])) {
                System.out.printf("%s: %.1f%n", nombres[i], notas[i]);
            }
        }
    }
    
    public static double porcentajeAprobacion(double[] notas) {
        int aprobados = 0;
        for (double nota : notas) {
            if (estaAprobado(nota)) {
                aprobados++;
            }
        }
        return (aprobados * 100.0) / notas.length;
    }
    
    public static void mostrarReporteCompleto(String[] nombres, double[] notas) {
        System.out.println("\n=== REPORTE COMPLETO DEL CURSO ===");
        System.out.println("Cantidad de estudiantes: " + nombres.length);
        
        System.out.println("\n--- LISTA COMPLETA DE ESTUDIANTES ---");
        for (int i = 0; i < nombres.length; i++) {
            System.out.printf("%d. %s: %.1f%n", (i + 1), nombres[i], notas[i]);
        }
        
        double[] min = notaMinima(nombres, notas);
        double[] max = notaMaxima(nombres, notas);
        double media = mediaNotas(notas);
        double desviacion = desviacionEstandar(notas);
        
        System.out.println("\n--- ESTADÍSTICAS GENERALES ---");
        System.out.printf("Nota más alta: %.1f (%s)%n", max[0], nombres[(int)max[1]]);
        System.out.printf("Nota más baja: %.1f (%s)%n", min[0], nombres[(int)min[1]]);
        System.out.printf("Promedio del curso: %.1f%n", media);
        System.out.printf("Desviación estándar: %.1f%n", desviacion);
        
        int excelente = 0, sobresaliente = 0, aceptable = 0, insuficiente = 0;
        for (double nota : notas) {
            if (nota > 4.5) excelente++;
            else if (nota >= 3.6) sobresaliente++;
            else if (nota >= 3.0) aceptable++;
            else insuficiente++;
        }
        
        System.out.println("\n--- DISTRIBUCIÓN DE RENDIMIENTO ---");
        System.out.printf("Excelente (>4.5): %d estudiante(s)%n", excelente);
        System.out.printf("Sobresaliente (3.6-4.5): %d estudiante(s)%n", sobresaliente);
        System.out.printf("Aceptable (3.0-3.5): %d estudiante(s)%n", aceptable);
        System.out.printf("Insuficiente (<3.0): %d estudiante(s)%n", insuficiente);
        
        double porcentajeAprob = porcentajeAprobacion(notas);
        System.out.println("\n--- PORCENTAJES DE APROBACIÓN ---");
        System.out.printf("Aprobados: %.1f%%%n", porcentajeAprob);
        System.out.printf("Reprobados: %.1f%%%n", 100 - porcentajeAprob);
        
        System.out.println("\n--- CASOS ESPECIALES ---");
        boolean hayDestacados = false;
        boolean hayRiesgo = false;
        
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] > 4.5) {
                if (!hayDestacados) {
                    System.out.print("Estudiantes destacados (>4.5): ");
                    hayDestacados = true;
                }
                System.out.printf("%s (%.1f) ", nombres[i], notas[i]);
            }
        }
        if (!hayDestacados) System.out.println("Estudiantes destacados: Ninguno");
        else System.out.println();
        
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] < 2.0) {
                if (!hayRiesgo) {
                    System.out.print("Estudiantes en riesgo (<2.0): ");
                    hayRiesgo = true;
                }
                System.out.printf("%s (%.1f) ", nombres[i], notas[i]);
            }
        }
        if (!hayRiesgo) System.out.println("Estudiantes en riesgo: Ninguno");
        else System.out.println();
        
        System.out.println("\n--- ORDEN DE MÉRITO (TOP 3) ---");
        String[] nombresOrdenados = nombres.clone();
        double[] notasOrdenadas = notas.clone();
        
        for (int i = 0; i < notasOrdenadas.length - 1; i++) {
            for (int j = i + 1; j < notasOrdenadas.length; j++) {
                if (notasOrdenadas[j] > notasOrdenadas[i]) {
                    double tempNota = notasOrdenadas[i];
                    notasOrdenadas[i] = notasOrdenadas[j];
                    notasOrdenadas[j] = tempNota;
                    
                    String tempNombre = nombresOrdenados[i];
                    nombresOrdenados[i] = nombresOrdenados[j];
                    nombresOrdenados[j] = tempNombre;
                }
            }
        }
        
        int top = Math.min(3, nombres.length);
        for (int i = 0; i < top; i++) {
            System.out.printf("%d. %s: %.1f%n", (i + 1), nombresOrdenados[i], notasOrdenadas[i]);
        }
        
        System.out.println("\n=== FIN DEL REPORTE ===");
    }
        
    
}