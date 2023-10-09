import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Scrabble {
    private static List<String> myList = new ArrayList<>();


    public static void main(String[] arg) throws IOException {

        Game game1 = new Game();
        while (!game1.getPreviousTurn().getRack().isEmpty()) {
            game1.evaluateNextTurn();
        }
//        Map<String, Move>[][] v = Util.findVerticalSingleTileValidPlacement(game1.board);
//        Map<String, Move>[][] h = Util.findHorizontalSingleTileValidPlacement(game1.board);
//        Dict dict = Dict.getDict();
//
//        String[] row2 = {"A", "_", "_", "B", "_", "_", "C", "_", "D", "E", "_", "F", "_", "G", "_"};
//
//        String[][] board = new String[15][15];
//        List<String> bag = new ArrayList<>();
//        for (var entry : TileInfo.tileFrequency.entrySet()) {
//            for (int i = entry.getValue(); i > 0; i--)
//                bag.add(entry.getKey());
//        }
//        Collections.shuffle(bag);
//        System.out.println(bag);
//
//        List<String> p1 = new ArrayList<>();
//        List<String> p2 = new ArrayList<>();
//        while (p1.size() < 7 && !bag.isEmpty()) {
//            p1.add(bag.get(0));
//            bag.remove(0);
//        }
//        System.out.println(bag);
//
//        while (p2.size() < 7 && !bag.isEmpty()) {
//            p2.add(bag.get(0));
//            bag.remove(0);
//        }
//        System.out.println(p1);

//        while (!game1.getBag().isEmpty()) {
//            if (game1.getP1Turns().size() == game1.getP2Turns().size()) {
////                Turn turn = game1.getP1Turns();
//                List<Move> moves = Util.findPossibleMoves(game1.board, game1.getP1Turns().get(game1.getP1Turns().size()-1).getRack());
//
//            }
//        }

//        String str = "ABCDE";
//
//        permutation(str);
//        System.out.println(myList);

//        Util.findPossibleMoves(new String[15][15], new ArrayList(Arrays.asList("_", "_", "Q", "Q", "Q", "Q", "Q")));
//        Util.findPossibleMoves(new String[15][15], p1);

//        List<Map<Integer, String>> s = new ArrayList<>();
//        for (int i = 0; i < row2.length; i++) {
//            if (row2[i].equals("_")) {
//                int a = i - 1;
//                String c = "?";
//                while (a >= 0 && !row2[a].equals("_")) {
//                    c = row2[a] + c;
//                    a--;
//                }
//                int b = i + 1;
//                while (b < 15 && !row2[b].equals("_")) {
//                    c = c + row2[b];
//                    b++;
//                }
//                if (!c.equals("?")) {
//                    Map<Integer, String> m = new HashMap<>();
//                    m.put(i, c);
//                    s.add(m);
//                }
//
//            }
//        }
//        System.out.println(s);
//        Tile[][] board2 = new Tile[15][15];
//
//        board2[8][7] = Tile.B;
//        board2[8][8] = Tile.A;
//        board2[8][9] = Tile.D;
//
//        List<Map<Point, String>> d = new ArrayList<>();


    }


}
