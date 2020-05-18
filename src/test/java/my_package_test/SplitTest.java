package my_package_test;
import my_package.SplitFunctional;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
public class SplitTest {

    @Test
    void splitByLines () throws IOException {
        SplitFunctional splitFunctional = new SplitFunctional();
        SplitFunctional.splitFun("C:\\Users\\Пользователь\\IdeaProjects\\Split\\test", "output", true, 30, 0, 0);
        File file1 = new File("output1.txt");
        File file2 = new File("output2.txt");

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        Scanner scanner = new Scanner(file1);
        while (scanner.hasNextLine()){
            list1.add(scanner.nextLine());
        }
        scanner = new Scanner(file2);
        while (scanner.hasNextLine()){
            list2.add(scanner.nextLine());
        }

        assertTrue(file1.exists());
        assertTrue(file2.exists());

        assertEquals(30, list1.size());
        assertEquals(26, list2.size());
    }

    @Test
    void splitBySymbol () throws IOException {
        SplitFunctional splitFunctional = new SplitFunctional();
        SplitFunctional.splitFun("C:\\Users\\Пользователь\\IdeaProjects\\Split\\test", "output", false, 0, 1000, 0);
        File file1 = new File("outputa.txt");
        File file2 = new File("outputb.txt");

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        Scanner scanner = new Scanner(file1);
        while (scanner.hasNextLine()){
            list1.add(scanner.nextLine());
        }
        scanner = new Scanner(file2);
        while (scanner.hasNextLine()){
            list2.add(scanner.nextLine());
        }

        assertTrue(file1.exists());
        assertTrue(file2.exists());

        assertEquals(1000, list1.size());
        assertEquals(702, list2.size());
    }

    @Test
    void splitByFiles () throws IOException {
        SplitFunctional splitFunctional = new SplitFunctional();
        SplitFunctional.splitFun("C:\\Users\\Пользователь\\IdeaProjects\\Split\\test", "output", false, 0, 0, 3);
        File file1 = new File("outputa.txt");
        File file2 = new File("outputb.txt");
        File file3 = new File("outputc.txt");

        assertTrue(file1.exists());
        assertTrue(file2.exists());
        assertTrue(file3.exists());

        assertEquals(619, file1.length());
        assertEquals(619, file2.length());
        assertEquals(520, file3.length());

    }

}

