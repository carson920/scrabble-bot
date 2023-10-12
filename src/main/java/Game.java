import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    public Game() {
        for (var entry : TileInfo.tileFrequency.entrySet()) {
            for (int i = entry.getValue(); i > 0; i--)
                bag.add(entry.getKey());
        }
        Collections.shuffle(bag);
        Turn p1Turn = new Turn();
        p1Turn.setRack(replenishRack(new ArrayList<>()));
        System.out.println("Player 1 starting rack: " + p1Turn.getRack());
        p1Turns.add(p1Turn);
        Turn p2Turn = new Turn();
        p2Turn.setRack(replenishRack(new ArrayList<>()));
        System.out.println("Player 2 starting rack: " + p2Turn.getRack());
        p2Turns.add(p2Turn);
        for (int i=0; i<15; i++) {
            for (int j=0; j<15; j++) {
                board[i][j] = " ";
            }
        }
    }

    String[][] board = new String[15][15];
    List<String> bag = new ArrayList<>();

    List<Turn> p1Turns = new ArrayList<>();
    List<Turn> p2Turns = new ArrayList<>();

    public String[][] getBoard() {
        return board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    public List<String> getBag() {
        return bag;
    }

    public void setBag(List<String> bag) {
        this.bag = bag;
    }

    public int getP1Score() {
        return getTotalScore(p1Turns);
    }

    public int getP2Score() {
        return getTotalScore(p2Turns);
    }

    private static int getTotalScore(List<Turn> turns) {
        return turns.stream().mapToInt(a -> a.getMove().getScore()).sum();
    }

    public List<Turn> getP1Turns() {
        return p1Turns;
    }

    public void setP1Turns(List<Turn> p1Turns) {
        this.p1Turns = p1Turns;
    }

    public List<Turn> getP2Turns() {
        return p2Turns;
    }

    public void setP2Turns(List<Turn> p2Turns) {
        this.p2Turns = p2Turns;
    }

    public Turn getCurrentTurn() {
        if (getP1Turns().size() == getP2Turns().size()) {
            return getP1Turns().get(getP1Turns().size()-1);
        } else {
            return getP2Turns().get(getP2Turns().size()-1);
        }
    }
    public Turn getPreviousTurn() {
        if (getP1Turns().size() == getP2Turns().size()) {
            return getP2Turns().get(getP2Turns().size()-1);
        } else {
            return getP1Turns().get(getP1Turns().size()-1);
        }
    }

    public List<Turn> getCurrentPlayerTurns() {
        if (getP1Turns().size() == getP2Turns().size()) {
            return getP1Turns();
        } else {
            return getP2Turns();
        }
    }

    public List<Turn> getAnotherPlayerTurns() {
        if (getP1Turns().size() == getP2Turns().size()) {
            return getP2Turns();
        } else {
            return getP1Turns();
        }
    }

    public boolean checkIfPlayerPassedLastTurn(List<Turn> turns) {
        if (turns.size() > 1) {
            return turns.get(turns.size()-2).isPass();
        } else {
            return false;
        }
    }

    public boolean checkIfBothPlayerPassed() {
        return checkIfPlayerPassedLastTurn(getCurrentPlayerTurns()) &&
                checkIfPlayerPassedLastTurn(getAnotherPlayerTurns());
    }

    public String getCurrentPlayer() {
        if (getP1Turns().size() == getP2Turns().size()) {
            return "Player 1";
        } else {
            return "Player 2";
        }
    }

    public void evaluateNextTurn() {
        Turn turn = getCurrentTurn();
        System.out.println("Current Player: " + getCurrentPlayer());
        System.out.println("Rack to play: " + turn.getRack());
        List<Move> moves = Util.findPossibleMoves(board, turn.getRack());
//        System.out.println("All moves" + moves);
        if (moves.isEmpty()) {
            turn.setPass(true);
        } else {
            turn.setMoves(moves);
            chooseTopScoreMove(turn, moves);
            System.out.println("Top 10 moves: " + moves.subList(0,Math.min(moves.size(),10)));
//            System.out.println("Highest scoring move: " + turn.getMove());
        }
        updateBoard(turn.getMove());
        Turn newTurn = new Turn();
        List<String> newRack = new ArrayList<>(turn.getRack());
        List<String> convertedBlanks = turn.getMove().getPlayedTiles().stream()
                .map(a -> a.charAt(0) >= 'a' && a.charAt(0) <= 'z' ? "_" : a).toList();
        System.out.println("Played Tiles: " + convertedBlanks);
        System.out.println("Chosen turn: " + turn);
        System.out.println("Current total score of " + getCurrentPlayer() + " is " + getTotalScore(getCurrentPlayerTurns()));

        convertedBlanks.forEach(newRack::remove);
        System.out.println("Rack before replenishment: " + newRack);
        newRack = replenishRack(newRack);
        Collections.sort(newRack);
//        System.out.println("newRack" + newRack);
        newTurn.setRack(newRack);
        getCurrentPlayerTurns().add(newTurn);
        System.out.println("Rack after replenishment: " + newTurn.getRack());
        System.out.println("No. of tiles in bag: " + bag.size());
        Util.printBoard(board);
    }

    private void updateBoard(Move move) {
        for (Placement placement : move.getPlacements()) {
            board[placement.getRow()][placement.getCol()] = placement.getTile();
        }
    }

    private void chooseTopScoreMove(Turn turn, List<Move> moves) {
        turn.setMove(moves.stream().findFirst().get());
    }

    public List<String> replenishRack(List<String> rack) {
        while (rack.size() < 7 && !bag.isEmpty()) {
            rack.add(bag.get(0));
            bag.remove(0);
        }
        return rack;
    }
}
