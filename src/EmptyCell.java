/*  Lavindu Devadithya
    5/19/2020

    Lab 11: TextExcel

    Replicate what Microsoft Excel can do with cells in a grid and implements certain
    functions, such as adding two cells together. */

public class EmptyCell implements Cell {

    //10 spaces
    public String abbreviatedCellText() {
        return "          ";
    }

    //empty string
    public String fullCellText() {
        return "";
    }

}