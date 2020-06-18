/*  Lavindu Devadithya
    6/17/2020

    Lab 11: TextExcel

    Replicate what Microsoft Excel can do with cells in a grid and implements certain
    functions, such as adding two cells together. */

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FormulaCell extends RealCell {
    private boolean isError = false;
    private Spreadsheet spreadsheet;

    //send String to RealCell, then to TextCell
    public FormulaCell(String c, Spreadsheet spreadsheet) {
        super(c.toUpperCase());
        this.spreadsheet = spreadsheet;
    }

    public double getDoubleValue() {
        try {
            String[] message = super.getMessage().split(" ");
            double answer = Double.parseDouble(message[1]);

            for (int i = 2; i < message.length; i += 2) {
                if (message[i].equals("+")) {
                    answer += Double.parseDouble(message[i + 1]);
                } else if (message[i].equals("-")) {
                    answer -= Double.parseDouble(message[i + 1]);
                } else if (message[i].equals("*")) {
                    answer *= Double.parseDouble(message[i + 1]);
                } else if (message[i].equals("/")) {
                    answer /= Double.parseDouble(message[i + 1]);
                }

            }

            return answer;
        }

        catch (Exception e) {
            try {
                return formCellAdvanced(super.getMessage());
            }

            catch(Exception k) {
                this.isError = true;
                return 0.0;
            }
        }
    }

    //RealCell reference = (RealCell) this.spreadsheet.getCell(new SpreadsheetLocation(split[i]))
    public double formCellAdvanced(String form) {
        String[] split = form.split(" ");

        if (split[1].toLowerCase().equals("sum") || split[1].toLowerCase().equals("avg")) {
            String cells = split[2].toLowerCase();

            int findDash = cells.indexOf("-");
            char startL = cells.charAt(0);
            int startN = Integer.parseInt(cells.substring(1, findDash));
            char endL = cells.charAt(findDash + 1);
            int endN = Integer.parseInt(cells.substring(findDash + 2));

            ArrayList<Double> range = new ArrayList<Double>();

            //add elements in range to arraylist 'range'
            for (char i = startL; i <= endL; i++) {
                for (int k = startN; k <= endN; k++) {
                    RealCell reference = (RealCell) this.spreadsheet.getCell(new SpreadsheetLocation(i + "" + k));
                    range.add(reference.getDoubleValue());
                }
            }


            //add everything in new ArrayList
            if (split[1].toLowerCase().equals("sum")) {
                double sum = 0;
                for (int i = 0; i < range.size(); i++) {
                    sum += range.get(i);
                }

                return sum;
            }

            //if command is average
            int count = 0;
            double sum = 0;
            for (int i = 0; i < range.size(); i++) {
                sum += range.get(i);
                count++;
            }

            return sum / count;

        }

        ArrayList<String> allDoubles = new ArrayList<String>();

        //turn everything into doubles so it makes it easier
        for (int i = 1; i < split.length - 1; i++) {
            try {
                //operations
                if (split[i].equals("*") || split[i].equals("+") || split[i].equals("-") || split[i].equals("/")) {
                    allDoubles.add(split[i]);
                }

                else {
                    double parsed = Double.parseDouble(split[i]);
                    allDoubles.add(parsed + "");
                }
            }

            catch (Exception e) {
                RealCell reference = (RealCell) this.spreadsheet.getCell(new SpreadsheetLocation(split[i]));
                allDoubles.add(reference.getDoubleValue() + "");
            }
        }

        double ret = Double.parseDouble(allDoubles.get(0));

        for (int i = 1; i < allDoubles.size(); i += 2) {
            if (allDoubles.get(i).equals("+")) {
                ret += Double.parseDouble(allDoubles.get(i + 1));
            }

            else if (allDoubles.get(i).equals("-")) {
                ret -= Double.parseDouble(allDoubles.get(i + 1));
            }

            else if (allDoubles.get(i).equals("*")) {
                ret *= Double.parseDouble(allDoubles.get(i + 1));
            }

            else if (allDoubles.get(i).equals("/")) {
                ret /= Double.parseDouble(allDoubles.get(i + 1));
            }
        }

        return ret;
    }

    public String abbreviatedCellText() {
        String ret = this.getDoubleValue() + "";
        int difference = 10 - ret.length();

        for (int i = 0; i < difference; i++) {
            ret += " ";
        }

        if (ret.length() > 10) {
            return ret.substring(0, 10);
        }

        return ret;
    }

    public String fullCellText() {
        return super.getMessage();
    }

    public boolean getIsError() {
        return this.isError;
    }
}
