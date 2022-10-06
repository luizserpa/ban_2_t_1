package Utilitarios;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class ProjetoUtil {

    Scanner user_input = null;

    public ProjetoUtil() {
        this.user_input = new Scanner(System.in);
    }

    public static Integer dateParaInt(Date date){
        int year = date.getYear();
        int month = date.getMonth() + 1;
        int day = date.getDate();

        System.out.println(year);
        if (year < 1950){
            year = 0;
            month = 0;
            day = 0;
        }

        int data = year * 10000 + month * 100 + day;
        System.out.println(data);
        return data;
    }

    public static Date intParaDate(Integer date){
        int dia = date % 100;
        int temp = date / 100;
        int mes = temp % 100;
        int ano = temp / 100;
        return new Date(ano, mes - 1, dia);
    }

    public int scannInt(){
        Integer valor;
        do {
            try {
                valor = Integer.parseInt(user_input.nextLine());
            }catch (Exception e){
                System.out.println("Valor digitado não é valido.");
                valor = null;
            }
        } while (valor == null);

        return valor;
    }

    public String scannString(){
        return user_input.nextLine();
    }



    public static void clearScreen() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    public Double scannDouble() {
        Double valor;
        do {
            try {
                valor = Double.parseDouble(user_input.nextLine());
            }catch (Exception e){
                System.out.println("Valor digitado não é valido.");
                valor = null;
            }
        } while (valor == null);
        return valor;
    }
}



