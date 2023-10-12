import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Move {
    private boolean across;
    private List<Placement> placements = new ArrayList<>();

    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isAcross() {
        return across;
    }

    public void setAcross(boolean across) {
        this.across = across;
    }

    public List<Placement> getPlacements() {
        return placements;
    }

    public void setPlacements(List<Placement> placements) {
        this.placements = placements;
    }

    public int getNoOfPlayedTiles() {
        return (int) getPlacements().stream().filter(a -> !a.isExisting()).count();
    }

    public List<String> getPlayedTiles() {
        return getPlacements().stream().filter(a -> !a.isExisting()).map(Placement::getTile).collect(Collectors.toList());
    }

    public List<String> getExistingTiles() {
        return getPlacements().stream().filter(a -> a.isExisting()).map(Placement::getTile).collect(Collectors.toList());
    }

    public String getFirstCoordinates() {
        String row = String.valueOf(placements.get(0).getRow() + 1);
        char col = (char) (placements.get(0).getCol() + 65);
        if (isAcross()) {
            return "" + row + col;
        } else {
            return "" + col + row;
        }
    }

    @Override
    public String toString() {
        if (getPlacements().isEmpty()) return "To be evaluated";
        return  getFirstCoordinates() +
                " " + String.join("", placements.stream().map(Placement::getTile).collect(Collectors.toList())) +
                " " + score;
    }
}
