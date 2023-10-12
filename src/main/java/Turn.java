import java.util.ArrayList;
import java.util.List;

public class Turn {
    List<String> rack = new ArrayList<>();
    List<Move> moves = new ArrayList<>();
    Move move = new Move();
    List<String> exchange = new ArrayList<>();
    boolean pass = false;

    public List<String> getRack() {
        return rack;
    }

    public void setRack(List<String> rack) {
        this.rack = rack;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public List<String> getExchange() {
        return exchange;
    }

    public void setExchange(List<String> exchange) {
        this.exchange = exchange;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        if (pass) {
            return "Pass" + " with rack: " + rack;
        } else if (!exchange.isEmpty()) {
            return "Exchange";
        } else {
            return "[" + move + "] with rack: " + rack;
        }
    }
}
