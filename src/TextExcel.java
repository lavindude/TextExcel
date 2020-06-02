/*  Lavindu Devadithya
    5/19/2020

    Lab 11: TextExcel

    Replicate what Microsoft Excel can do with cells in a grid and implements certain
    functions, such as adding two cells together. */


import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextExcel {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Spreadsheet s = new Spreadsheet();

//        TestsAll.Helper th = new TestsAll.Helper();
//        System.out.println(th.getText());

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
