import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Dict {
    private static Dict single_dict = null;

    private Dict() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/csw19.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Stream<String> lines = reader.lines();
        lines.forEachOrdered(this::process);
    }

    public static Dict getDict() {
        if (single_dict == null) {
            try {
                single_dict = new Dict();
            } catch (IOException e) {
                System.out.println("Failed to load dictionary");
            }
        }

        return single_dict;
    }

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
