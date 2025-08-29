
package practice1;

import java.util.Scanner;

/**
 *
 * @author Santiago Cardenas Claros 
 */
public class Taller1 {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de alumnos: ");

        int n = sc.nextInt();

        String[] nombreDeCadaAlumno = new String[n];

        double[] notaDeCadaAlumno = new double[n];

        sc.nextLine();

        for (int i = 0; i < n; i++) {

            System.out.println("Alumno #" + (i + 1));
            System.out.print("Nombre: ");
            nombreDeCadaAlumno[i] = sc.nextLine();

            do {
                System.out.print("Nota: ");
                notaDeCadaAlumno[i] = sc.nextDouble();
                if ((notaDeCadaAlumno[i] > 5) || (notaDeCadaAlumno[i] < 0)) {
                    System.out.println("No se puede ingresar esa nota");
                }
            } while ((notaDeCadaAlumno[i] > 5) || (notaDeCadaAlumno[i] < 0));
            sc.nextLine();
        }
        System.out.println("Notas Estudiantes");

        for (int i = 0; i < n; i++) {

            System.out.println(nombreDeCadaAlumno[i] + ": " + notaDeCadaAlumno[i]);
        }
        
        String suspendidos = ("");
        
        for (int i = 0; i < n; i++) {

            
            if (notaDeCadaAlumno[i] >= 3) {
                System.out.println(nombreDeCadaAlumno[i] + " ha aprobado con un " + notaDeCadaAlumno[i]);
            } 
            
            if (notaDeCadaAlumno[i]<3) {
                
            if(!suspendidos.isEmpty())  {  
                
                suspendidos+=", ";}
            
            suspendidos+= nombreDeCadaAlumno[i];
               
            } 
            
            }
        System.out.println("Han suspendido: " +suspendidos);
        
        
        double max = notaDeCadaAlumno[0];
        double min = notaDeCadaAlumno[0];

        String nombreMax = nombreDeCadaAlumno[0];
        String nombreMin = nombreDeCadaAlumno[0];

        for (int i = 0; i < n; i++) {

            if (notaDeCadaAlumno[i] > max) {
                max = notaDeCadaAlumno[i];
                nombreMax = nombreDeCadaAlumno[i];
            }
            if (notaDeCadaAlumno[i] < min) {
                min = notaDeCadaAlumno[i];
                nombreMin = nombreDeCadaAlumno[i];

            }
        }
        double sumaNotas = 0;

        for (int i = 0; i < n; i++) {

            sumaNotas += notaDeCadaAlumno[i];

        }

        double media = sumaNotas / n;

        System.out.println(nombreMax + " fue la nota mas alta con un " + max);
        System.out.println(nombreMin + " fue la nota mas baja con un " + min);
        System.out.println("El promedio de las notas del curso fue: " + media);
        
                                  
         
    }
    
    
}
