/*  Lavindu Devadithya
    5/19/2020

    Lab 11: TextExcel

    Replicate what Microsoft Excel can do with cells in a grid and implements certain
    functions, such as adding two cells together. */

public class SpreadsheetLocation implements Location {
    private int row;
    private int col;

    //Example: B20
    public SpreadsheetLocation(String cellName) {
        //algorithm to set row and column
        String letter = cellName.substring(0, 1).toLowerCase();
        int num = Integer.parseInt(cellName.substring(1));
        this.row = num - 1;

        switch (letter) {
            case "a":
                this.col = 0;
                break;
            case "b":
                this.col = 1;
                break;
            case "c":
                this.col = 2;
                break;
            case "d":
                this.col = 3;
                break;
            case "e":
                this.col = 4;
                break;
            case "f":
                this.col = 5;
                break;
            case "g":
                this.col = 6;
                break;
            case "h":
                this.col = 7;
                break;
            case "i":
                this.col = 8;
                break;
            case "j":
                this.col = 9;
                break;
            case "k":
                this.col = 10;
                break;
            case "l":
                this.col = 11;
                break;
        }
    }

    @Override
    public int getRow() {
        // TODO Auto-generated method stub
        return this.row;
    }

    @Override
    public int getCol() {
        // TODO Auto-generated method stub
        return this.col;
    }

}
