package nz.ac.massey.cs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class importTodoFile {

    public static void main(String args[]) throws IOException {

        String fileName = "/Users/jonathan/Desktop/webapp 4/data/files/alice/Data.md";
        List<String> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            list = stream.collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        list.forEach(System.out::println);

        String string = "[X] (A) Clean data due:2018-08-10";
        String[] parts = string.split("\n");


        //String to be scanned to find the pattern.




        System.out.println("\n");

        String projectTitle = null;
        boolean completed = false;
        String priority = null;
        String name = null;
        Date dueDate = null;
        String description ="";

        regex1 regex_ = new regex1(string);
        if (regex_.matchFound()) {
            projectTitle = regex_.returnMatch();
        }
        else {
            throw new IOException("No project title found");
        }



        for (String line : lines) {
            Task task1 = new Task("");


        }
        for (String bit :parts) {


           if (bit.startsWith("#")) {}

           else if (bit.startsWith("[")) {

               if ('X' == bit.charAt(1)) {
                   completed = true;
               }
               else {completed = false;}
            }

            else if (bit.startsWith("due:")) {
                dueDate = stringDatetoDateObject(bit.substring(4));
            }

            else if (bit.startsWith("(")) {
                continue;
            }

            else {
                description+=bit + " ";
            }


        }
        System.out.println(completed);
        System.out.println(description);
        System.out.println(dueDate);

    }

    static Date stringDatetoDateObject(String dateString) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}

class regex1 {

    private String line;

    private String pattern = "(# )(.*)";

    public regex1(String line1) {
        line = line1;
    }

    boolean matchFound() {
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);
        if (m.find( )) {
            return true;
        }else {
            return false;
        }
    }

    String returnMatch() {
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);
        return m.group(2);

    }


}