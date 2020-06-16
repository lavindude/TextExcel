/*  Lavindu Devadithya
    6/10/2020

    Lab 11: TextExcel

    Replicate what Microsoft Excel can do with cells in a grid and implements certain
    functions, such as adding two cells together. */

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Spreadsheet implements Grid {
    private Cell[][] cells;

    public Spreadsheet() {
        this.cells = new Cell[this.getRows()][this.getCols()];

        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getCols(); c++) {
                EmptyCell e = new EmptyCell();
                cells[r][c] = e;
            }
        }
    }

    //can handle errors
    @Override
    public String processCommand(String command) {
        // TODO Auto-generated method stub
        if (command.equals("quit")) {
            return "See you later!";
        }

        String[] arr = command.split(" ");

        //tries this code to see if there are any errors in the command
        try {
            //if empty string
            if (command.equals("")) {
                return "";
            }

            //sort from least to greatest
            if (arr[0].equals("sorta")) {
                sortAscending(arr[1]);
                return this.getGridText();
            }

            //sort from greatest to least
            if (arr[0].equals("sortd")) {
                sortDescending(arr[1]);
                return this.getGridText();
            }

            //for one word commands
            if (arr.length == 1) {
                if (command.toLowerCase().equals("clear")) {
                    for (int r = 0; r < getRows(); r++) {
                        for (int c = 0; c < getCols(); c++) {
                            cells[r][c] = new EmptyCell();
                        }
                    }

                    return this.getGridText();
                }

                //return value of a cell
                else if (command.length() == 2 || command.length() == 3) {

                    SpreadsheetLocation location = new SpreadsheetLocation(command);
                    int row = location.getRow();
                    int col = location.getCol();

                    //return value of cell
                    return cells[row][col].fullCellText();
                }
            }

            else {
                //setting cell equal to something
                if (arr[1].equals("=")) {
                    //error handling
                    char colCheck = arr[0].toLowerCase().charAt(0);
                    int rowCheck = Integer.parseInt(arr[0].substring(1));

                    if (colCheck > 'l' || rowCheck > 20) {
                        return "ERROR: Invalid command.";
                    }

                    //assign to string value
                    SpreadsheetLocation location = new SpreadsheetLocation(arr[0]);
                    int row = location.getRow();
                    int col = location.getCol();

                    Cell t;
                    if (arr.length == 3) {
                        //if string
                        if (command.contains("\"")) {
                            //even more error handling for string if only 1 quote
                            int index = command.indexOf("\"");
                            String check = command.substring(index + 1);
                            if (!check.contains("\"")) {
                                return "ERROR: Invalid command.";
                            }

                            t = new TextCell(arr[2]);
                        }

                        //if percent cell
                        else if (command.contains("%")) {
                            t = new PercentCell(arr[2]);
                        }

                        //if value cell
                        else {
                            t = new ValueCell(arr[2]);
                        }

                        cells[row][col] = t;

                        return this.getGridText();
                    }

                    else if (arr.length > 3) {
                        if (command.contains("\"")) {
                            int equals = command.indexOf("\"");
                            String special = command.substring(equals);

                            Cell c = new TextCell(special);
                            cells[row][col] = c;


                            return this.getGridText();
                        }

                        //formula cell

                        else if (command.contains("(")) {
                            String formula = "";
                            for (int i = 2; i < arr.length; i++) {
                                formula += arr[i] + " ";
                            }

                            Cell c = new FormulaCell(formula.substring(0, formula.length() - 1), this);
                            cells[row][col] = c;

                            //error handling:
                            FormulaCell formCell = new FormulaCell(formula.substring(0, formula.length() - 1), this);
                            if (formCell.getIsError()) {
                                cells[row][col] = new EmptyCell();
                                return "ERROR: Invalid command.";
                            }

                            return this.getGridText();
                        }


                    }

                }

                //clears a certain cell
                else if (arr[0].toLowerCase().equals("clear")) {
                    //clear only certain cell arr[1]
                    SpreadsheetLocation location = new SpreadsheetLocation(arr[1]);
                    int row = location.getRow();
                    int col = location.getCol();
                    cells[row][col] = new EmptyCell();

                    return this.getGridText();
                }
            }

            return "ERROR: Invalid command.";
        }

        //catches invalid command in try block above
        catch (Exception e) {
            return "ERROR: Invalid command.";
        }
    }

    public void sortAscending(String range) {
        int dash = range.indexOf("-");
        char firstLetter = range.charAt(0);
        int firstNum = Integer.parseInt(range.substring(1, dash));
        char secondLetter = range.charAt(dash + 1);
        int secondNum = Integer.parseInt(range.substring(dash + 2));

        //initialize everything into an ArrayList
        ArrayList<Cell> cellsT = new ArrayList<Cell>();
        for (char i = firstLetter; i <= secondLetter; i++) {
            for (int k = firstNum; k <= secondNum; k++) {
                Cell c = getCell(new SpreadsheetLocation(i + k + ""));
                cellsT.add(c);
            }
        }

        Cell c = this.getCell(new SpreadsheetLocation(firstLetter + firstNum + ""));

        //SelectionSort for RealCell
        if (c instanceof RealCell) { //if RealCell
            for (int i = 0; i < cellsT.size(); i++) {
                for (int k = i + 1; k < cellsT.size(); k++) {
                    if (cellsT.get(k).fullCellText().compareTo(cellsT.get(i).fullCellText()) < 0) {
                        Cell temp = cellsT.get(k);
                        cellsT.set(k, cellsT.get(i));
                        cellsT.set(i, temp);
                    }
                }
            }

            int index = 0;
            int startCol = firstLetter - 65;
            int endCol = secondLetter - 65;

            for (int i = startCol; i <= endCol; i++) {
                for (int k = firstNum; k <= secondNum; k++) {
                    Cell replace = cellsT.get(index);
                    cells[i][k] = replace;
                }
            }
        }

        else { //if TextCell
            System.out.println("fail");
        }
    }

    public void sortDescending(String range) {

    }

    @Override
    public int getRows() {
        // TODO Auto-generated method stub
        return 20;
    }

    @Override
    public int getCols() {
        // TODO Auto-generated method stub
        return 12;
    }

    @Override
    public Cell getCell(Location loc) {
        // TODO Auto-generated method stub
        return cells[loc.getRow()][loc.getCol()];
    }

    @Override
    public String getGridText() {
        // TODO Auto-generated method stub
        String grid = "   ";
        char letter = 'A';

        //makes first row of Grid
        for (int i = 0; i < 12; i++) {
            grid += ("|" + letter + "         ");
            letter++;
        }

        grid += "|\n";

        //makes the rest of the rows
        for (int r = 0; r < this.getRows(); r++) {
            if (r < 9) {
                grid += r + 1 + "  ";
            }

            else {
                grid += r + 1 + " ";
            }

            for (int c = 0; c < this.getCols(); c++) {
                grid += "|" + cells[r][c].abbreviatedCellText();
            }

            grid += "|\n";
        }

        return grid;
    }
}