/**
 * Created by JeremyW on 2/2/17.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Easy301 {
    private static File dictionary = new File (Easy301.class.getResource("enable1.txt").getPath());
    public static void main(String[] args) throws FileNotFoundException {
        int groupNum = 0;
        StringBuilder input = new StringBuilder(args[0]);
        String[] pattern = new String[input.length()];
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != ' ') {
                pattern[i] = "(\\S)";
                for (int j = i + 1; j < input.length(); j++) {
                    if (input.charAt(j) == input.charAt(i)) {
                        pattern[j] = "\\" + (groupNum + 1);
                        input.setCharAt(j, ' ');
                    }
                }
                groupNum++;
            }
        }
        for (int i = 1; i < pattern.length; i++) {
            pattern[0] += pattern[i];
        }
        Pattern givenPattern = Pattern.compile(".*" + pattern[0] + ".*");
        Scanner dictReader = new Scanner(dictionary);
        while (dictReader.hasNext()) {
            String currentWord = dictReader.nextLine();
            if (givenPattern.matcher(currentWord).matches()) System.out.println(currentWord);
        }
    }
}
