package nz.ac.massey.cs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class importTodoFile {


    public static void main(String args[]) throws IOException {

        String filelines = streamFile_Buffer(new File("/Users/jonathan/Desktop/webapp/data/files/alice/HRApp.md"));
        String[] lines = filelines.split("\n");

        System.out.println("\n");

        String projectTitle = null;
        Date dueDate = null;
        String description ="";

        regex2 regex_ = new regex2(filelines);
        if (regex_.matchFound()) {
            projectTitle = regex_.returnMatch();
        }
        else {
            throw new IOException("No project title found");
        }

        TaskList importedTaskList = new TaskList();

        for (String line : lines) {
            Task task1 = new Task("", "", "");
            task1.setProjectTitle(projectTitle);
            if ( line.charAt(0) ==  '[' ) {
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
                    dueDate = stringDatetoDateObject(bit.substring(4));
                    task1.setDate(dueDate);
                } else if (bit.startsWith("(")) {
                    continue;
                } else {
                    description += bit + " ";
                }
            }
            if (isTaskLine) {
                 task1.setDescription(description);
                 importedTaskList.addTask(task1);
            }

        }
        System.out.println("Hello World");

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

