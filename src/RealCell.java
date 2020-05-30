/*  Lavindu Devadithya
    5/19/2020

    Lab 11: TextExcel Checkpoint A3

    Replicate what Microsoft Excel can do with cells in a grid and implements certain
    functions, such as adding two cells together. */


//it seems reasonable to do this to avoid duplicated code
public class RealCell extends TextCell implements Cell  {

    public RealCell(String c) {
        super(c);
        super.setText(this.getDoubleValue() + "");
    }

    //might need later
    public String getMessage() {
        return super.fullCellText();
    }

    //general getDoubleValue() method for subclasses
    public double getDoubleValue() {
        return Double.parseDouble(super.fullCellText());
    }
}