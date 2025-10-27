package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        while (exit==false) {
            System.out.println("¿Que desea hacer?");
            System.out.println("1");
            System.out.println("2");
            System.out.println("3");
            System.out.println("4");
            System.out.println("5");
            System.out.println("6");
            if(sc.hasNextInt()) {
                int eleccion = sc.nextInt();
                if (eleccion < 7 && eleccion > 0) {

                    switch (eleccion) {
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            exit = true;
                            break;
                    }
                } else {
                    System.out.println("Debe elegir un valor entre los disponibles del menú");
                }
            }
            else{
                System.out.println("Debe elegir un numero valido");
                sc.next();
            }
        }
        sc.close();
    }
}
