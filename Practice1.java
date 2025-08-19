package practice1;

import java.util.Scanner;

/**
 *
 * Santiago Cardenas Claros  u20251231614
 */
public class Practice1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        double discount10 = 0.10;
        double discount20 = 0.20;
        double discount40 = 0.40;
        

        System.out.println("Cuantos computadoras desea comprar? ");

        int computersQuantity = sc.nextInt();
   
        int computerPrice = 500;
        
        int initialPrice = (computersQuantity*computerPrice);
        
        
        
        if (computersQuantity < 5) {
            System.out.println("El valor de su compra es de: "+"U"+"$"+initialPrice);
            System.out.println("Se le aplicara un descuento del 10% " );
            double discountPrice10 = (initialPrice*discount10);
            double finalPrice10 = (initialPrice-discountPrice10);
            System.out.println("El valor final de su compra es de: "+"U"+"$"+finalPrice10);
            
    } else if (computersQuantity < 10) {
        System.out.println("El valor de su compra es de: "+"U"+"$"+initialPrice);
            System.out.println("Se le aplicará un descuento del 20% ");
            double discountPrice20 = (initialPrice*discount20);
            double finalPrice20 = (initialPrice-discountPrice20);
            System.out.println("El valor final de su compra es de: "+"U"+"$"+finalPrice20);
            

        } else if (computersQuantity>=10){
            System.out.println("El valor de su compra es de: "+"U"+"$"+initialPrice);
            System.out.println("Se le aplicará un descuento del 40% ");
            double discountPrice40 = (initialPrice*discount40);
            double finalPrice40 = (initialPrice-discountPrice40);
            System.out.println("El valor final de su compra es de: "+"U"+"$"+finalPrice40);
    }
        
        
    }
    
}
