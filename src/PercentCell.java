/*  Lavindu Devadithya
    6/10/2020

    Lab 11: TextExcel

    Replicate what Microsoft Excel can do with cells in a grid and implements certain
    functions, such as adding two cells together. */

public class PercentCell extends RealCell {

    //sends this to RealCell
    public PercentCell(String c) {
        super(c);
    }

    //overides abbreviatedCellText() in TextCell
    public String abbreviatedCellText() {
        //String abrev = super.getMessage();
        double decimal = Double.parseDouble(super.fullCellText().substring(0, super.fullCellText().length() - 1));
        //decimal *= 100;
        int cast = (int) decimal;
        String ret = cast + "%";
        int difference = 10 - ret.length();

        for (int i = 0; i < difference; i++) {
            ret += " ";
        }

        return ret;
    }

    //converts to percent from double
    public String fullCellText() {
        Double parse = Double.parseDouble(super.fullCellText().substring(0, super.fullCellText().length() - 1));

        return parse + "%";
    }

    //overrides getDoubleValue() method in RealCell
    public double getDoubleValue() {
        String parse = super.fullCellText().substring(0, super.fullCellText().length() - 1);

        return Double.parseDouble(parse) / 100.0;
    }

}
