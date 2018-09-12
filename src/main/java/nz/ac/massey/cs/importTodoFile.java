package nz.ac.massey.cs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

public class importTodoFile {

    static List<Task> importedTaskList = new ArrayList<Task>();

    public static void main(String[] args) {
        try {
            importTodoFile itf = new importTodoFile();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void filetoTaskList(String filelines, String name) throws IOException {

        String[] lines = filelines.split("\n");

        System.out.println("\n");

        String projectTitle = null;
        String dueDate;
        String description ="";

        regex2 regex_ = new regex2(filelines);
        if (regex_.matchFound()) {
            projectTitle = regex_.returnMatch();
        }
        else {
            throw new IOException("No project title found");
        }

        for (String line : lines) {
            if (line.equals(" ")) {continue;}
            description = "";
            Task task1 = new Task(name, "", "");
            task1.setProjectTitle(projectTitle);
            if ( line.charAt(0) ==  '[' && line.charAt(1) == ' ' ) {
                line = line.substring(0, 1) + '0' + line.substring(2);
            }
            String[] parts = line.split(" ");
            boolean isTaskLine = true;
            for (String bit : parts) {
                if (bit.startsWith("#")) {
                    isTaskLine = false;
                    break;
                } else if (bit.startsWith("[")) {
                    if ('X' == bit.charAt(1)) {
                        task1.setCompleted(true);
                    } else {
                        task1.setCompleted(false);
                    }
                } else if (bit.startsWith("due:")) {
                    dueDate = bit.substring(4);
                    task1.setDueDate(dueDate);
                } else if (bit.startsWith("(")) {
                    continue;
                } else {
                    description += bit + " ";
                }
            }
            if (isTaskLine) {
                task1.setDescription(description);
                importedTaskList.add(task1);
            }

        }
    }


    public importTodoFile() throws IOException {
        TaskList importedTaskList = new TaskList();
        List<Path> files = Files.walk(Paths.get("data/")).filter(Files::isRegularFile).collect(Collectors.toList());
        for (Path p: files) {
            if (p.toString().contains(".DS_Store")) {
                continue;
            }
            String name = findName(p.toString());
            String filelines = streamFile_Buffer(new File(p.toString()));
            filetoTaskList(filelines, name);
        }


    }
    public static String findName(String s) {
        String line = s.toString();
        String pattern = "(^\\w*\\/\\w*\\/)(\\w*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);
        m.find();
        return m.group(2);
    }

    public static List<Task> returnTaskList() {
        return importedTaskList;
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


 private static String streamFile_Buffer(File file) throws IOException {
        //From https://stackoverflow.com/questions/326390/how-do-i-create-a-java-string-from-the-contents-of-a-file?answertab=active#tab-top
        BufferedReader reader = new BufferedReader( new FileReader( file ) );
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }
}

class regex2 {

    private String line;

    private String pattern = "^(# )(.*)";

    public regex2(String line1) {
        line = line1;
    }

    Pattern r;
    Matcher m;

    boolean matchFound() {
        r = Pattern.compile(pattern);
        m = r.matcher(line);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

    String returnMatch() {
        return m.group(2);

    }
}

