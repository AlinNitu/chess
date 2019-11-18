package chess;

public class Location {

    private int row;
    private int col;

    public Location(int row, int col) {

        this.row = row;
        this.col = col;

    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof Location){
            Location loc = (Location) obj;
            return (row == loc.getRow() && col == loc.getCol());
        }
        return false;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
