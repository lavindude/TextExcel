/*  Lavindu Devadithya
    6/10/2020

    Lab 11: TextExcel

    Replicate what Microsoft Excel can do with cells in a grid and implements certain
    functions, such as adding two cells together. */


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextExcel {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Spreadsheet s = new Spreadsheet();

//        s.processCommand("A9 = -13.2");
//        s.processCommand("A10 = 19.2");
//        s.processCommand("A1");
//        s.processCommand("A12 = 607.1");
//        s.processCommand("-13.2");
//        s.processCommand("B9 = 88.2");
//        s.processCommand("B10 = -190.1");
//        s.processCommand("B11 = 1.2");
//        s.processCommand("B12 = 607.2");
//        s.processCommand("B13 = -0.02");
//
//        s.processCommand("SoRTd A9-B13");

        System.out.println(s.getGridText());

        String user = "";

        //greet user
        System.out.println("Hello, welcome!");

        while (!user.equals("quit")) {
            System.out.print("What do you want to do? ");
            user = scan.nextLine();
            System.out.println(s.processCommand(user));
        }
    }

}
