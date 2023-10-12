import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Scrabble {
    private static final Logger logger = LoggerFactory.getLogger(Scrabble.class);

    public static void main(String[] args) throws IOException {

        // Default to play 1 game only if there is no parameter or the 1st parameter is not an integer
        int n = 1;
        try {
            n = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("1 game will be played.");
        }

        // If parameter for file name (2nd parameter) is present, print result to a file
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
            System.out.println("End of game no. " + i);
        }
    }


}
