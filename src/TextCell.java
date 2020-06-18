/*  Lavindu Devadithya
    6/17/2020

    Lab 11: TextExcel

    Replicate what Microsoft Excel can do with cells in a grid and implements certain
    functions, such as adding two cells together. */

public class TextCell implements Cell, Comparable {
    private String text;

    public TextCell(String full) {
        this.text = full;
    }

    //made this helper method so I can reuse code to implement RealCells
    public void setText(String t) {
        this.text = t;
    }

    @Override
    public String abbreviatedCellText() {
        int len = this.text.length();
        String abrev = "";

        //if string
        if (this.text.contains("\"")) {
            abrev = this.text.substring(1, this.text.length() - 1);
            len = this.text.length() - 2;
        }

        else {
            abrev = this.text;
        }

        //if long string
        if (len >= 11) {
            //if string
            return abrev.substring(0, 10);
        }

        //length less than 11
        String ret = "";

        ret = abrev;
        int difference = 10 - len;
        for (int i = 0; i < difference; i++) {
            ret += " ";
        }

        return ret;

    }

    @Override
    public String fullCellText() {
        return this.text;
    }

    public int compareTo(Object other) {
        if (other instanceof TextCell) {
            return this.fullCellText().compareTo(((TextCell) other).fullCellText());
        }

        return 0;
    }
}
