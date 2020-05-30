/*  Lavindu Devadithya
    5/19/2020

    Lab 11: TextExcel Checkpoint A3

    Replicate what Microsoft Excel can do with cells in a grid and implements certain
    functions, such as adding two cells together. */

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

        String[] arr = command.split(" ");

        //tries this code to see if there are any errors in the command
        try {
            //if empty string
            if (command.equals("")) {
                return "";
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

                            Cell c = new FormulaCell(formula);
                            cells[row][col] = c;

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