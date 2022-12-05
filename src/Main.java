import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Main {

    static Log log = new Log();
    private static String getLogMessage(String file, String path, String e) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String s = "[Main] DateTime: " + dateFormat.format(date) +
                (file.equals("")? "; path: " + path :
                "; file name: " + file + "; path: " + path);
        return e.equals("")? s : s + "; has error: " + e;
    }
    private static void create(ArrayList<String> list, String path) {
        File root = new File(path);
        if (!root.exists()) {
            try {
                root.mkdir();
                log.add(getLogMessage("", path, ""));
            }
            catch (Exception e) {
                log.add(getLogMessage("", path, e.getMessage()));
            }
        }
        for (String a: list) {
            if (a.contains(".")) {
                File file = new File(path + "//" + a);
                try {
                    file.createNewFile();
                    log.add(getLogMessage(a, path, ""));
                }
                catch (Exception e) {
                    log.add(getLogMessage(a, path, e.getMessage()));
                }
            }
            else {
                File dir = new File(path + "//" + a);
                try {
                    dir.mkdir();
                    log.add(getLogMessage(dir.getName(), path, ""));
                }
                catch (Exception e) {
                    log.add(getLogMessage(dir.getName(), path, e.getMessage()));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        create(new ArrayList<>(Arrays.asList(
                "src", "res", "savegames", "temp"
        )), "Games");

        create(new ArrayList<>(Arrays.asList(
                "main", "test"
        )), "Games//src");

        create(new ArrayList<>(Arrays.asList(
                "Main.java", "Utils.java"
        )), "Games//src//main");

        create(new ArrayList<>(Arrays.asList(
                "drawables", "vectors", "icons"
        )), "Games//res");

        create(new ArrayList<>(Arrays.asList(
                "temp.txt"
        )), "Games//temp");

        File logger = new File("Games//temp//temp.txt");
        FileWriter fileWriter = new FileWriter(logger);
        fileWriter.write(log.getoLog().toString());
        fileWriter.flush();
        fileWriter.close();
    }
}