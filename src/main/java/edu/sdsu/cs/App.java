package edu.sdsu.cs;
/**
 * @author Micheal Kemper
 * @author Juan Pina-Sanz
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

class ScannedStringStats {
    protected int getLengthOfLongest() {
        return lengthOfLongest;
    }

    protected void setLengthOfLongest(int lengthOfLongest) {
        this.lengthOfLongest = lengthOfLongest;
    }

    protected double getAverageLineLength() {
        return averageLineLength;
    }

    protected void setAverageLineLength(double averageLineLength) {
        this.averageLineLength = averageLineLength;
    }

    private int lengthOfLongest;
    private double averageLineLength;

    protected List<String> getAllTokens() {
        return allTokens;
    }

    protected void setAllTokens(List<String> allTokens) {
        this.allTokens = allTokens;
    }

    protected int getAllTokensLength() {
        return this.allTokens.size();
    }

    List<String> allTokens = new ArrayList<String>();

    protected TokenFinder getTf() {
        return tf;
    }

    protected void setTf(TokenFinder tf) {
        this.tf = tf;
    }

    private TokenFinder tf;

}

class Token {

    protected Token(String stringToSet, int numOfTimesSeen) {
        this.setString(stringToSet);
        this.setNumOfTimesSeen(numOfTimesSeen);
    }

    protected String getString() {
        return string;
    }

    private void setString(String string) {
        this.string = string;
    }

    protected int getNumOfTimesSeen() {
        return numOfTimesSeen;
    }

    private void setNumOfTimesSeen(int numOfTimesSeen) {
        this.numOfTimesSeen = numOfTimesSeen;
    }

    private String string;
    private int numOfTimesSeen;
}

public class App {
    private int searchStringLength(String stringToSearch) {
        int stringLength = 0;
        for (int index = 0; index < stringToSearch.length(); index++) {
            if (stringToSearch.charAt(index) != ' ') {
                stringLength++;
            }
        }
        return stringLength;
    }

    private List<String> searchLineForTokens(String stringToSearch) {
        boolean currentIndexIsASpace = false;
        boolean foundFirstRealCharacter = false;
        List<String> stringArrayList = new ArrayList<String>();
        StringBuilder stringBeingBuilt = new StringBuilder();
        for (int index = 0; index < stringToSearch.length(); index++) {
            if (stringToSearch.charAt(index) != ' ') {
                currentIndexIsASpace = false;
                foundFirstRealCharacter = true;
                stringBeingBuilt.append(stringToSearch.charAt(index));
                if (index == stringToSearch.length() - 1) {
                    stringArrayList.add(stringBeingBuilt.toString());
                }
            } else {
                if (!currentIndexIsASpace && foundFirstRealCharacter) {
                    currentIndexIsASpace = true;
                    stringArrayList.add(stringBeingBuilt.toString());
                    stringBeingBuilt.setLength(0);
                }
            }
        }
        return stringArrayList;
    }

    private ScannedStringStats searchFile(File fileToParse, File statsFile) {
        Scanner in = null;
        int numberOfLines = 0;
        int longestLine = 0;
        int totalLineLengthSize = 0;
        ArrayList<String> allTokens = new ArrayList<String>();
        try {
            in = new Scanner(fileToParse);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        if (in != null) {
            App apptest = new App();
            while (in.hasNextLine()) {
                numberOfLines++;
                String currentLine = in.nextLine();
                int currentLineLength = apptest.searchStringLength(currentLine);
                if (currentLineLength > longestLine) {
                    longestLine = currentLineLength;
                }
                totalLineLengthSize += currentLineLength;
                List<String> currentLineTokens =
                        apptest.searchLineForTokens(currentLine);
                for (int index = 0; index < currentLineTokens.size(); index++) {
                    allTokens.add(currentLineTokens.get(index));
                }
            }
        }
        ScannedStringStats scannedStringStats = new ScannedStringStats();
        scannedStringStats.setLengthOfLongest(longestLine);
        TokenFinder tokenFinder = new TokenFinder(allTokens);
        tokenFinder.standardRun();
        scannedStringStats.setTf(tokenFinder);
        scannedStringStats.setAverageLineLength(new Double(totalLineLengthSize)
                / new Double(numberOfLines));
        scannedStringStats.setAllTokens(allTokens);
        return scannedStringStats;
    }


    private static void showFiles(File[] files, String currentDirectory) {
        for (File file : files) {
            if (file.isDirectory()) {
                showFiles(file.listFiles(), file.getPath());
            } else {
                String fileExtention = "";
                int indexOfLastDecimal = file.getName().lastIndexOf('.');
                if (indexOfLastDecimal > 0) {
                    fileExtention = file.getName().substring(indexOfLastDecimal
                            + 1);
                    if (fileExtention.equals("java") || fileExtention.
                            equals("txt")) {
                        currentDirectory = file.getParentFile().getPath();
                        if (!currentDirectory.equals("")) {
                            File parentDirectory = new File(currentDirectory);
                            parentDirectory.mkdir();
                            File newStatsFile = new File(parentDirectory,
                                    file.getName() + ".stats");
                            try {
                                App apptest = new App();
                                List lines = new ArrayList();
                                ScannedStringStats scannedStringStats =
                                        apptest.searchFile(file, newStatsFile);
                                StringBuilder tempToAdd = new StringBuilder();
                                StringBuilder mostFrequentTokensOutput =
                                        new StringBuilder();
                                mostFrequentTokensOutput.append("Most " +
                                        "frequently " + "occurring token(s): " +
                                        "{ ");
                                tempToAdd.append("10 most frequent tokens with "
                                        + "their counts: {");
                                int currentMax = 0;
                                for (Token token : scannedStringStats.getTf()
                                        .getTenMostFrequentTokens()) {
                                    tempToAdd.append(" [" + token.getString() +
                                            "] seen:" + token.
                                            getNumOfTimesSeen() + ",");
                                    if (token.getNumOfTimesSeen() >= currentMax) {
                                        currentMax = token.getNumOfTimesSeen();
                                        mostFrequentTokensOutput.append("[" +
                                                token.getString() + "], ");
                                    }
                                }
                                tempToAdd.append("}");
                                mostFrequentTokensOutput.append("}");
                                lines.add("Length of longest line in file: " +
                                        scannedStringStats.
                                                getLengthOfLongest());
                                lines.add("Average line length in file: " +
                                        scannedStringStats.
                                                getAverageLineLength());
                                lines.add("Number of unique space-delineated "
                                        + "tokens (case-sensitive): " +
                                        scannedStringStats.getTf().
                                                getDefaultTokenList().size());
                                lines.add("Number of unique space-delineated "
                                        + "tokens (case-insensitive): " +
                                        scannedStringStats.getTf().
                                                getLowerCaseTokenList().size());
                                lines.add("Number of all space-delineated tokens "
                                        + "in file: " +
                                        scannedStringStats.getAllTokensLength());
                                lines.add(mostFrequentTokensOutput.toString());
                                lines.add("Count of most frequently occurring "
                                        + "token (case-insensitive): " +
                                        currentMax);
                                lines.add(tempToAdd.toString());
                                tempToAdd.setLength(0);
                                tempToAdd.append("10 least frequent tokens "
                                        + "with their counts: {");
                                for (Token token : scannedStringStats.getTf().
                                        getTenLeastFrequetTokens()) {
                                    tempToAdd.append(" [" + token.getString()
                                            + "] seen:" + token.
                                            getNumOfTimesSeen()
                                            + ",");
                                }
                                tempToAdd.append("}");
                                lines.add(tempToAdd.toString());

                                Path filePath = Paths.get(currentDirectory +
                                        "\\" + newStatsFile.getName());
                                Files.write(filePath, lines);
                                file.createNewFile();
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            Path currentRelativePath = Paths.get("");
            String currentRelativePathStr = currentRelativePath.toAbsolutePath()
                    .toString();
            File[] files = new File(currentRelativePathStr).listFiles();
            showFiles(files, "");
        } else {
            for (String arg : args) {
                File[] files = new File(arg).listFiles();
                showFiles(files, "");
            }
        }

    }
}