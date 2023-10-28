import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Scrabble {
    private static final Logger logger = LoggerFactory.getLogger(Scrabble.class);

    public static void main(String[] args) throws IOException {

        // Default to play 1 game only if there is no parameter, or the first parameter is not an integer
        int n = 1;
        try {
            n = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("1 game will be played.");
        }

        // If the second parameter (file name) exists, print result to a file
        try {
            String fileName = args[1];
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            PrintStream printStream = new PrintStream(fileOutputStream);
            System.setOut(printStream);
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.info("Printing results to console");
        }

        for (int i=1; i<=n; i++) {
            System.out.println("Starting game no. " + i);
            Game game = new Game();
            while (!game.getPreviousTurn().getRack().isEmpty() && !game.checkIfBothPlayerPassed()) {
                game.evaluateNextTurn();
            }
            showSummary(game);
            System.out.println("End of game no. " + i);
        }
    }

    private static void showSummary(Game game) {
        System.out.println("Player 1 final score: " + game.getPlayer1Score());
        System.out.println("Player 2 final score: " + game.getPlayer2Score());
        Move p1HighWordMove = game.getP1Turns().stream().map(a -> a.getMove()).max(Comparator.comparingInt(Move::getScore)).get();
        Move p2HighWordMove = game.getP2Turns().stream().map(a -> a.getMove()).max(Comparator.comparingInt(Move::getScore)).get();
        System.out.println("Player 1 high word score: " + p1HighWordMove);
        System.out.println("Player 2 high word score: " + p2HighWordMove);
        List<Move> p1Bingos = game.getP1Turns().stream().map(a -> a.getMove()).filter(a -> a.getNoOfPlayedTiles() == 7).collect(Collectors.toList());
        List<Move> p2Bingos = game.getP2Turns().stream().map(a -> a.getMove()).filter(a -> a.getNoOfPlayedTiles() == 7).collect(Collectors.toList());
        List<Move> allBingos = new ArrayList<>();
        allBingos.addAll(p1Bingos);
        allBingos.addAll(p2Bingos);
        System.out.println("All bingos: " + allBingos);
    }


}
