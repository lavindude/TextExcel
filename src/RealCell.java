/*  Lavindu Devadithya
    6/17/2020

    Lab 11: TextExcel

    Replicate what Microsoft Excel can do with cells in a grid and implements certain
    functions, such as adding two cells together. */


//it seems reasonable to do this to avoid duplicated code
public class RealCell extends TextCell implements Cell {

    public RealCell(String c) {
        super(c);
    }

    //might need later
    public String getMessage() {
        return super.fullCellText();
    }

    //general getDoubleValue() method for subclasses
    public double getDoubleValue() {
        return Double.parseDouble(super.fullCellText());
    }

    public int compareTo(Object other) {
        if (other instanceof RealCell) {
            if (this.getDoubleValue() < ((RealCell) other).getDoubleValue()) {
                return -1;
            } else if (this.getDoubleValue() == ((RealCell) other).getDoubleValue()) {
                return 0;
            } else {
                return 1;
            }
        }

        return 0;
    }
}
