import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Util {
     private static final Logger logger = LoggerFactory.getLogger(Util.class);
     public static List<Move> findPossibleMoves(String[][] board, List<String> rackWithBlanks) {
        Set<List<String>> racks = generateFullRackCombinations(rackWithBlanks);
        Set<String> subracks = new HashSet<>();
        for (List<String> rack: racks) {
            generateSubrackCombinations(subracks, rack);
        }
        List<Move> moves = new ArrayList<>();
        if (checkFirstMove(board)) {
            evaluateFirstMove(board, subracks, moves);
        } else {
            evaluateSubsequentMoves(board, subracks, moves);
        }
        logger.debug("All moves: " + moves);

        moves.sort(Comparator.comparingInt(Move::getScore).reversed());
        return moves;
    }

    @NotNull
    private static List<Move> getHvMoves(String[][] board, Map<String, Move>[][] v, Map<String, Move>[][] h) {
        logger.debug("find horizontal moves");
        List<Move> hmoves = new ArrayList<>();
        for (int i=0; i<15; i++) {
            String[] row = board[i];
            logger.debug(Arrays.deepToString(row));
            findLegalPlacementsOfRowColumn(hmoves, i, true, row, v);
        }
        logger.debug("hmoves: " + hmoves);

        logger.debug("find vertical moves");
        List<Move> vmoves = new ArrayList<>();
        for (int i=0; i<15; i++) {
            String[] row = new String[15];
            for (int j=0; j<15; j++) {
                row[j] = board[j][i];
            }
            logger.debug(Arrays.deepToString(row));
            findLegalPlacementsOfRowColumn(vmoves, i, false, row, h);
        }
        return Stream.concat(hmoves.stream(), vmoves.stream())
                .collect(Collectors.toList());
    }

    private static void evaluateFirstMove(String[][] board, Set<String> subracks, List<Move> moves) {
        Set<String> firstMoveWords = new HashSet<>();
        for (String subrack : subracks) {
            Set<String> validWords = Dict.getDict().getIndexed().get(subrack.toUpperCase());
            if (validWords != null) {
                Set<String> validWordsWithBlanks = substituteBlanks(subrack, validWords);
                firstMoveWords.addAll(validWordsWithBlanks);
            }
        }

        logger.debug("subracks: " + subracks);
        logger.debug("firstMoveWords: " + firstMoveWords);
        for (var firstMoveWord: firstMoveWords) {
            int len = firstMoveWord.length();
            for(int i= 8-len; i<8; i++) {
                Move move = new Move();
                move.setAcross(false);
                for (int j=0; j<len; j++) {
                    Placement placement = new Placement();
                    placement.setTile(firstMoveWord.substring(j,j+1));
                    placement.setRow(i+j);
                    placement.setCol(7);
                    move.getPlacements().add(placement);
                }
                move.setScore(calScore(move, board, null, null));

                moves.add(move);
            }
        }
    }

    private static void evaluateSubsequentMoves(String[][] board, Set<String> subracks, List<Move> moves) {
        Map<String, Move>[][] v = findVerticalSingleTileValidPlacement(board);
        Map<String, Move>[][] h = findHorizontalSingleTileValidPlacement(board);

        List<Move> hvMoves = getHvMoves(board, v, h);
        Map<Integer, List<String>> groupedByLength = subracks.stream()
                .collect(Collectors.groupingBy(String::length));
        for (Move tm: hvMoves) {
            List<String> usedTiles = groupedByLength.get(tm.getNoOfPlayedTiles()); //I
            if (usedTiles != null) {
                for (String s : usedTiles) {
                    String joined = s + String.join("", tm.getExistingTiles()); //QI
                    String alphabetized = Util.alphabetize2(joined.toUpperCase());
                    Set<String> validWords = Dict.getDict().getIndexed().get(alphabetized); //1 match
                    if (validWords != null) {
                        Set<String> validWordsWithBlanks = substituteBlanks(joined, validWords);
                        for (String validWord : validWordsWithBlanks) {
                            Move m = new Move();
                            m.setAcross(tm.isAcross());
                            int len = validWord.length();
                            boolean valid = true;
                            List<Placement> tps = tm.getPlacements();
                            List<Placement> ps = new ArrayList<>();
                            for (int i = 0; i < len; i++) {
                                Placement tp = tps.get(i);
                                Placement p = new Placement();
                                p.setCol(tp.getCol());
                                p.setRow(tp.getRow());
                                String tile = String.valueOf(validWord.charAt(i));
                                if (tps.get(i).isExisting()) {
                                    if (validWord.charAt(i) >= 'a' && validWord.charAt(i) <= 'z') {
                                        valid = false;
                                        break;
                                    }
                                    if (!tile.equals(tp.getTile())) {
                                        valid = false;
                                        break;
                                    }
                                    p.setExisting(true);
                                    p.setTile(tp.getTile());
                                } else {
                                    Map<String, Move> st;
                                    if (tm.isAcross()) {
                                        st = v[tp.getRow()][tp.getCol()];
                                    } else {
                                        st = h[tp.getRow()][tp.getCol()];
                                    }

                                    if (!st.containsKey(tile.toUpperCase()) && !st.containsKey("Any")) {
                                        valid = false;
                                        break;
                                    }
                                    p.setExisting(false);
                                    p.setTile(tile);
                                }
                                ps.add(p);
                            }
                            if (valid) {
                                m.setPlacements(ps);
                                m.setScore(Util.calScore(m, board, v, h));
                                moves.add(m);
                            }
                        }
//                        logger.debug("validWordsWithBlanks" + validWordsWithBlanks);
                    }
                }
            }
        }
    }

    public static Set<String> substituteBlanks(String subrack, Set<String> validWords) {
        List<Character> blanks = new ArrayList<>();
        for(int i=0; i<subrack.length();i++) {
            char c = subrack.charAt(i);
            if(c >= 'a' && c <= 'z') {
                blanks.add(c);
            }
        }
        if (blanks.isEmpty()) {
            return validWords;
        }
        Set<String> validWordsWithBlanks = new HashSet<>();
        if (blanks.size() ==1) {
            int count = validWords.stream().findFirst().get().split(String.valueOf(blanks.get(0)).toUpperCase(),-1).length-1;
            for(var validWord: validWords) {
                for (int j=0; j<count; j++) {
                    char[] ca = validWord.toCharArray();
                    ca[ordinalIndexOf(validWord, String.valueOf(blanks.get(0)).toUpperCase(), j)] = blanks.get(0);
                    validWordsWithBlanks.add(String.valueOf(ca));
                }
            }
        } else if (blanks.size() == 2) {
            int count1 = validWords.stream().findFirst().get().split(String.valueOf(blanks.get(0)).toUpperCase(),-1).length-1;
            int count2 = validWords.stream().findFirst().get().split(String.valueOf(blanks.get(1)).toUpperCase(),-1).length-1;

                for (var validWord : validWords) {
                    for (int j = 0; j < count1; j++) {
                        for (int k = 0; k < count2; k++) {
                            if (!blanks.get(0).equals(blanks.get(1)) || k > j) {
                                char[] ca = validWord.toCharArray();
                                ca[ordinalIndexOf(validWord, String.valueOf(blanks.get(0)).toUpperCase(), j)] = blanks.get(0);
                                ca[ordinalIndexOf(validWord, String.valueOf(blanks.get(1)).toUpperCase(), k)] = blanks.get(1);
//                                logger.debug(String.valueOf(ca));
                                validWordsWithBlanks.add(String.valueOf(ca));
                            }
                        }
                    }
                }

        }
        return validWordsWithBlanks;
    }

    private static void generateSubrackCombinations(Set<String> subracks, List<String> rack) {
        String sorted = alphabetize(rack);
        for (int i = 0; i < Math.pow(2, sorted.length()); i++) {
            String b = Integer.toBinaryString(i);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < b.length(); j++) {
                if (b.charAt(b.length() - 1 - j) == '1') {
                    sb.append(sorted.charAt(j));
                }
            }
            subracks.add(sb.toString());

        }
    }

    private static boolean checkFirstMove(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != " ") {
                    return false;
                }
            }
        }
        return true;
    }

    private static Set<List<String>> generateFullRackCombinations(List<String> rackWithBlanks) {
        Set<List<String>> racks = new HashSet<>();
        List<String> rack = new ArrayList<>(rackWithBlanks);
        int blankCount = Collections.frequency(rack, "_");
        rack.removeAll(Collections.singleton("_"));
        if (blankCount == 0) {
            racks.add(rack);
        } else if (blankCount == 1) {
            for (char tile = 'a'; tile <= 'z'; tile++) {
                List<String> replacedRack = new ArrayList(rack);
                replacedRack.add(String.valueOf(tile));
                racks.add(replacedRack);
            }
        } else if (blankCount == 2) {
            for (char tile = 'a'; tile <= 'z'; tile++) {
                for (char tile2 = 'a'; tile2 <= 'z'; tile2++) {
                    if (tile >= tile2) {
                        List<String> replacedRack = new ArrayList(rack);
                        replacedRack.add(String.valueOf(tile));
                        replacedRack.add(String.valueOf(tile2));
                        racks.add(replacedRack);
                    }
                }
            }
        }
        return racks;
    }

    public static PremiumSqaure getPremiumSquare(int x, int y) {
        return Board.getPremiumSquares()[x][y];
    }
    public static PremiumSqaure getPremiumSquare(Placement placement) {
        return getPremiumSquare(placement.getRow(),placement.getCol());
    }

    public static int calScore(Move move, String[][] board, Map<String, Move>[][] v, Map<String, Move>[][] h) {
        int sum = 0;
        int multiply = 1;
        int wordScoreBeforeMultiply = 0;
        boolean across = move.isAcross();
        for (Placement placement : move.getPlacements()) {
            if (!placement.isExisting()) {
                wordScoreBeforeMultiply += getPremiumSquare(placement).getLetterMultiply() * getTileValue(placement.getTile());
                multiply *= getPremiumSquare(placement).getWordMultiply();
            } else {
                wordScoreBeforeMultiply += getTileValue(placement.getTile());
            }
        }

        int wordScore = wordScoreBeforeMultiply * multiply;
        if (move.getNoOfPlayedTiles() == 7) {
            wordScore += 50;
        }
        sum += wordScore;

        // add side word scores
        if (h != null && v != null) {
            for (Placement placement : move.getPlacements()) {
                if (!placement.isExisting()) {
                    if (across) {
                        Move vmove = v[placement.getRow()][placement.getCol()].get(placement.getTile());
                        if (vmove != null)
                            sum += vmove.getScore();
                    } else {
                        Move hmove = h[placement.getRow()][placement.getCol()].get(placement.getTile());
                        if (hmove != null)
                            sum += hmove.getScore();
                    }
                }
            }
        }
        return sum;
    }

    private static int getTileValue(String tile) {
        if (tile.charAt(0) >= 'a' && tile.charAt(0) <= 'z') {
            return 0;
        }
        return TileInfo.tileValue.get(tile);
    }

    public static String alphabetize(List<String> str) {
        str.sort(String.CASE_INSENSITIVE_ORDER);
        String joined = str.stream().collect(Collectors.joining());
        return joined;
    }

    public static String alphabetize2(String line) {
        char[] c = line.toCharArray();
        Arrays.sort(c);
        return new String(c);
    }

    public static int ordinalIndexOf(String str, String substr, int n) {
        int pos = -1;
        do {
            pos = str.indexOf(substr, pos + 1);
        } while (n-- > 0 && pos != -1);
        return pos;
    }

    public static void printBoard(String[][] board) {
        System.out.println("Current board:");
        System.out.println("   A.B.C.D.E.F.G.H.I.J.K.L.M.N.O ");
        System.out.println("  _______________________________");
        for (int i=0; i<15; i++) {
            StringBuilder sb = new StringBuilder();
            if (i+1<10) {
                sb.append(" " + (i+1) );
            } else {
                sb.append(i+1);
            }
            sb.append("|");
            for (int j=0; j<15; j++) {
                sb.append(board[i][j]);
                if (j != 14) {
                    sb.append(".");
                } else {
                    sb.append("|");
                }
            }
            System.out.println(sb);
        }
        System.out.println("  -------------------------------");

    }

    public static Map<String,Move>[][] findVerticalSingleTileValidPlacement(String[][] board) {
        Map<String,Move>[][] singleTileBoard = new HashMap[15][15];
        for (int i=0; i<15; i++) {
            for (int j=0; j<15; j++) {
                Map<String,Move> map = new HashMap<>();
                if (board[i][j] == " ") {
                    int a = i - 1;
                    String prefix = "";
                    while (a >= 0 && !(board[a][j] == " ")) {
                        prefix = board[a][j] + prefix;
                        a--;
                    }
                    int b = i + 1;
                    String suffix = "";
                    while (b < 15 && !(board[b][j] == " ")) {
                        suffix = suffix + board[b][j];
                        b++;
                    }
                    if (prefix.length() != 0 || suffix.length() != 0) {
                        for (Tile tile : Tile.values()) {
                            String l = tile.getLetter();
                            String word = prefix + l + suffix;
                            if (Dict.getDict().getFullDict().contains(word.toUpperCase())) {
                                for (String t : List.of(l, l.toLowerCase())) {
                                    Move move = new Move();
                                    move.setAcross(false);
                                    for (int k = i - prefix.length(); k <= i + suffix.length(); k++) {
                                        Placement placement = new Placement();
                                        placement.setRow(k);
                                        placement.setCol(j);
                                        if (k == i) {
                                            placement.setTile(t);
                                        } else  {
                                            placement.setTile(String.valueOf(word.charAt(k - i + prefix.length())));
                                            placement.setExisting(true);
                                        }
                                        move.getPlacements().add(placement);
                                    }
                                    move.setScore(calScore(move, board, null, null));
                                    map.put(t, move);
                                }
                            }
                        }

                    } else {
                        map.put("Any", null);
                    }
                }
                singleTileBoard[i][j] = map;
            }
        }
        logger.debug("vertical single tile");
        for (int i=0; i<15; i++) {
            for (int j=0; j<15; j++) {
                if (!singleTileBoard[i][j].isEmpty() && !singleTileBoard[i][j].containsKey("Any")) {
                    logger.debug(singleTileBoard[i][j].entrySet().toString());
                }
            }
        }
        return singleTileBoard;
    }

    public static Map<String,Move>[][] findHorizontalSingleTileValidPlacement(String[][] board) {
        Map<String,Move>[][] singleTileBoard = new HashMap[15][15];
        for (int i=0; i<15; i++) {
            for (int j=0; j<15; j++) {
                Map<String,Move> map = new HashMap<>();
                if (board[i][j] == " ") {
                    int a = j - 1;
                    String prefix = "";
                    while (a >= 0 && !(board[i][a] == " ")) {
                        prefix = board[i][a] + prefix;
                        a--;
                    }
                    int b = j + 1;
                    String suffix = "";
                    while (b < 15 && !(board[i][b] == " ")) {
                        suffix = suffix + board[i][b];
                        b++;
                    }
                    if (prefix.length() != 0 || suffix.length() != 0) {
                        for (Tile tile : Tile.values()) {
                            String l = tile.getLetter();
                            String word = prefix + l + suffix;
                            if (Dict.getDict().getFullDict().contains(word.toUpperCase())) {
                                for (String t : List.of(l, l.toLowerCase())) {
                                    Move move = new Move();
                                    move.setAcross(true);
                                    for (int k = j - prefix.length(); k <= j + suffix.length(); k++) {
                                        Placement placement = new Placement();
                                        placement.setRow(i);
                                        placement.setCol(k);
                                        if (k == j) {
                                            placement.setTile(t);
                                        } else {
                                            placement.setTile(String.valueOf(word.charAt(k - j + prefix.length())));
                                            placement.setExisting(true);
                                        }
                                        move.getPlacements().add(placement);
                                    }
                                    move.setScore(calScore(move, board, null, null));
                                    map.put(t, move);
                                }
                            }
                        }
                    } else {
                        map.put("Any", null);
                    }
                }
                singleTileBoard[i][j] = map;
            }
        }
        logger.debug("horizontal single tile");
        for (int i=0; i<15; i++) {
            for (int j=0; j<15; j++) {
                if (!singleTileBoard[i][j].isEmpty() && !singleTileBoard[i][j].containsKey("Any")) {
//                    logger.debug("printing single tile board" + i + " " + j);
                    logger.debug(singleTileBoard[i][j].entrySet().toString());
                }
            }
        }
        return singleTileBoard;
    }

    public static void findLegalPlacementsOfRowColumn(List<Move> hvmoves, int n, boolean across, String[] line, Map<String, Move>[][] s) {
        List<Move> m = new ArrayList<>();
//        logger.debug("n: " + n);
        for (int i=1; i<=7; i++) {

            for (int j=0; j<15; j++) {
                if (!(j-1 >=0 && !line[j-1].equals(" ")) ) {// only proceed if the previous square is empty
                    Move move = new Move();
                    move.setAcross(across);
                    boolean valid = false;
                    int idx = j; // index of square on board (0 - 14)
                    int tilesRemaining = i; // no. of tiles remaining
                    while (idx < 15 && (tilesRemaining >= 1 || (!line[idx].equals(" ")))) {
                        Placement p = new Placement();
                        if (across) {
                            p.setRow(n);
                            p.setCol(idx);
                        } else {
                            p.setRow(idx);
                            p.setCol(n);
                        }
                        if (line[idx].equals(" ")) {
                            if (across && s[n][idx].isEmpty()) break;
                            if (!across && s[idx][n].isEmpty()) break;
//                            logger.debug("v[idx][n].isEmpty() = " + v[idx][n].isEmpty());
                            if (across && !s[n][idx].containsKey("Any")) valid = true;
                            if (!across && !s[idx][n].containsKey("Any")) valid = true;

                            p.setTile("?");
                            tilesRemaining--;
                        } else {
                            p.setTile(line[idx]);
                            p.setExisting(true);
                            valid = true;
                        }
                        move.getPlacements().add(p);
                        idx++;
                    }

                    if (valid && tilesRemaining==0) {
//                        logger.debug("Adding move: " + move);
                        m.add(move);
                    }
                }
            }
        }
        hvmoves.addAll(m);
        logger.debug(m.toString());
    }
}
