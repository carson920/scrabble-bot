import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Dict {
    private static Dict single_dict = null;

    private Dict() throws IOException {
//        dict.put(2, twos);
//        dict.put(3, threes);
//        dict.put(4, fours);
//        dict.put(5, fives);
//        dict.put(6, sixes);
//        dict.put(7, sevens);
//        dict.put(8, eights);
//        dict.put(9, nines);
//        dict.put(10, tens);
//        dict.put(11, elevens);
//        dict.put(12, twelves);
//        dict.put(13, thirteens);
//        dict.put(14, fourteens);
//        dict.put(15, fifteens);

        try (Stream<String> lines = Files.lines(Paths.get("csw19.txt"), Charset.defaultCharset())) {
            lines.forEachOrdered(line -> process(line));
        }
    };

    public static Dict getDict() {
        if (single_dict == null) {
            try {
                single_dict = new Dict();
            } catch (IOException e) {

            }
        }

        return single_dict;
    }

//    List<String> twos = new ArrayList<>();
//    List<String> threes = new ArrayList<>();
//    List<String> fours = new ArrayList<>();
//    List<String> fives = new ArrayList<>();
//    List<String> sixes = new ArrayList<>();
//    List<String> sevens = new ArrayList<>();
//    List<String> eights = new ArrayList<>();
//    List<String> nines = new ArrayList<>();
//    List<String> tens = new ArrayList<>();
//    List<String> elevens = new ArrayList<>();
//    List<String> twelves = new ArrayList<>();
//    List<String> thirteens = new ArrayList<>();
//    List<String> fourteens = new ArrayList<>();
//    List<String> fifteens = new ArrayList<>();

//    private static Map<Integer, List<String>> dict = new HashMap<>();
    private static Map<String, Set<String>> indexed = new HashMap<>();

    public Set<String> getFullDict() {
        return fullDict;
    }

    public void setFullDict(Set<String> fullDict) {
        this.fullDict = fullDict;
    }

    private static Set<String> fullDict = new HashSet<>();

    private void process(String line) {
//        dict.get(line.length()).add(line);
        fullDict.add(line);
        indexed.computeIfAbsent(Util.alphabetize2(line), k -> new HashSet<>()).add(line);
    }


    public Map<String, Set<String>> getIndexed() {
        return indexed;
    }
}
