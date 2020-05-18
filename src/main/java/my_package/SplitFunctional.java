package my_package;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.Math.floor;
import static java.lang.Math.log;

public class SplitFunctional {

    private static int counter = 1;

    public static int getAndIncCounter() {
        return counter++;
    }

    public static void splitFun(String file, String ofile, boolean nameCondition, int l, int c, int n) throws IOException {
        List<String> list = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        if (ofile.equals("-")) {
            ofile = file;
        }
        if (c > 0) {
            splitBySymbol(list, c, nameCondition, ofile);
        } else if (n > 0) {
            splitByFiles(list, n, nameCondition, ofile);
        } else {
            splitByLines(list, l, nameCondition, ofile);
        }
    }

    private static void splitByLines(List<String> list, int l, boolean nameCondition, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(getFile(nameCondition, fileName));
        int tmp = 1;
        for (String str : list) {
            fileWriter.write(str + "\n");
            if (tmp++ == l) {
                fileWriter.close();
                fileWriter = new FileWriter(getFile(nameCondition, fileName));
                tmp = 1;
            } else {
                fileWriter.flush();
            }
        }
    }

    private static String getString(int n) {
        char[] buf = new char[(int) floor(log(25 * (n + 1)) / log(26))];
        for (int i = buf.length - 1; i >= 0; i--) {
            n--;
            buf[i] = (char) ('a' + n % 26);
            n /= 26;
        }
        return new String(buf);
    }

    private static File getFile(boolean nameCondition, String fileName) throws IOException {
        File file;
        if (nameCondition) {
            file = new File(fileName + getAndIncCounter() + ".txt");
        } else {
            file = new File(fileName + getString(getAndIncCounter()) + ".txt");
        }
        file.createNewFile();
        return file;
    }

    private static void splitBySymbol(List<String> list, int c, boolean nameCondition, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(getFile(nameCondition, fileName));
        int tmp = 1;
        for (String str : list) {
            for (Character ch : str.toCharArray()) {
                fileWriter.write(ch + "\n");
                if (tmp++ == c) {
                    fileWriter.close();
                    fileWriter = new FileWriter(getFile(nameCondition, fileName));
                    tmp = 1;
                } else {
                    fileWriter.flush();
                }
            }
        }

    }

    private static void splitByFiles(List<String> list, int n, boolean nameCondition, String fileName) throws IOException {
        File file = getFile(nameCondition, fileName);
        FileWriter fileWriter = new FileWriter(file);
        int tmp = 0;
        long fileLength = new File("C:\\Users\\Пользователь\\IdeaProjects\\Split\\test").length();
        long ofileSize = fileLength / n;
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                while (tmp < list.size()) {
                    fileWriter.write(list.get(tmp++) + "\n");
                }
                fileWriter.flush();
                fileWriter.close();
                break;
            } else {
                while (file.length() < ofileSize) {
                    fileWriter.write(list.get(tmp++) + "\n");
                    fileWriter.flush();
                }
            }
            fileWriter.close();
            file = getFile(nameCondition, fileName);
            fileWriter = new FileWriter(file);
        }
    }

}

