import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Scrabble {
    private static final Logger logger = LoggerFactory.getLogger(Scrabble.class);

    private static List<String> myList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        logger.info("This is an info message.");
        logger.error("This is an error message.");
//        FileOutputStream fileOutputStream = new FileOutputStream("output.log");
//
//        // Create a PrintStream that writes to the FileOutputStream
//        PrintStream printStream = new PrintStream(fileOutputStream);
//
//        // Redirect the standard output (System.out) to the PrintStream
//        System.setOut(printStream);
        int n = Integer.parseInt(args[0]);
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
