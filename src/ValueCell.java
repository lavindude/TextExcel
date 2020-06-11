/*  Lavindu Devadithya
    6/10/2020

    Lab 11: TextExcel

    Replicate what Microsoft Excel can do with cells in a grid and implements certain
    functions, such as adding two cells together. */

public class ValueCell extends RealCell {

    //sends String to RealCell
    public ValueCell(String c) {
        super(c);
    }

    public String fullCellText() {
        return Double.parseDouble(super.getMessage()) * 1.0 + "";
    }

    public String abbreviatedCellText() {
        String parsed = Double.parseDouble(super.getMessage()) * 1.0 + "";

        int difference = 10 - parsed.length();

        for (int i = 0; i < difference; i++) {
            parsed += " ";
        }

        if (super.getMessage().length() > 10) {
            return parsed.substring(0, 10);
        }

        return parsed;
    }

    //no point in getDoubleValue() method because general method in RealCell does same thing

    //problem: put decimal if space, if not, just an int
}
