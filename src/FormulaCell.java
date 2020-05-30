/*  Lavindu Devadithya
    5/19/2020

    Lab 11: TextExcel Checkpoint A3

    Replicate what Microsoft Excel can do with cells in a grid and implements certain
    functions, such as adding two cells together. */

public class FormulaCell extends RealCell {

    //send String to RealCell, then to TextCell
    public FormulaCell(String c) {
        super(c);
    }

    public double getDoubleValue() {
        String[] message = super.getMessage().split(" ");
        double answer = Double.parseDouble(message[1]);

        for (int i = 2; i < message.length; i += 2) {
            if (message[i].equals("+")) {
                answer += Double.parseDouble(message[i + 1]);
            }

            else if (message[i].equals("-")) {
                answer -= Double.parseDouble(message[i + 1]);
            }

            else if (message[i].equals("*")) {
                answer *= Double.parseDouble(message[i + 1]);
            }

            else if (message[i].equals("/")) {
                answer /= Double.parseDouble(message[i + 1]);
            }

        }

        return answer;
    }

    public String abbreviatedCellText() {
        String ret = this.getDoubleValue() + "";
        int difference = 10 - ret.length();

        for (int i = 0; i < difference; i++) {
            ret += " ";
        }

        return ret;
    }

    public String fullCellText() {
        return this.getDoubleValue() + "";
    }
}
