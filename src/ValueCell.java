/*  Lavindu Devadithya
    5/19/2020

    Lab 11: TextExcel Checkpoint A3

    Replicate what Microsoft Excel can do with cells in a grid and implements certain
    functions, such as adding two cells together. */

public class ValueCell extends RealCell {

    //sends String to RealCell
    public ValueCell(String c) {
        super(c);
    }

    //no point in getDoubleValue() method because general method in RealCell does same thing
}
