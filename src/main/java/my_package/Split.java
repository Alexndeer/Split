package my_package;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class Split {

    @Option(name = "-o", usage = "Default ofile name")
    private String ofile = "x";

    @Option(name = "-d", usage = "Numbering ofile name")
    private boolean nameCondition;

    @Option(name = "-l", usage = "my_package.Split by lines", forbids = {"-c", "-n"})
    private int l = 100;

    @Option(name = "-c", usage = "my_package.Split by symbols", forbids = {"-l", "-n"})
    private int c;

    @Option(name = "-n", usage = "my_package.Split by number", forbids = {"-c", "-l"})
    private int n;

    @Argument(usage = "InputFile", required = true)
    private String file;

    public static void main(String[] args) throws IOException {
        new Split().doMain(args);
    }

    public void doMain(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Expected argument: split [-d] [-l num] [-c num] [-n num] [-o ofile] file");
            parser.printUsage(System.err);
        }
        SplitFunctional.splitFun(file, ofile, nameCondition, l, c, n);
    }
}
