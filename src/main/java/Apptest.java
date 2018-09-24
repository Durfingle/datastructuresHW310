import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

class ScannedStringStats {
    public int getLengthOfLongest() {
        return lengthOfLongest;
    }

    public void setLengthOfLongest(int lengthOfLongest) {
        this.lengthOfLongest = lengthOfLongest;
    }

    public double getAverageLineLength() {
        return averageLineLength;
    }

    public void setAverageLineLength(double averageLineLength) {
        this.averageLineLength = averageLineLength;
    }

    private int lengthOfLongest;
    private double averageLineLength;

    public List<String> getAllTokens() {
        return allTokens;
    }

    public void setAllTokens(List<String> allTokens) {
        this.allTokens = allTokens;
    }
    public int getAllTokensLength() {
        return this.allTokens.size();
    }

    List<String> allTokens = new ArrayList<String>();

}
class Token {

    public Token() {
        this.setString("");
        this.setNumOfTimesSeen(0);
    }
    public Token(String stringToSet) {
        this.setString(stringToSet);
        this.setNumOfTimesSeen(1);
    }
    public Token(String stringToSet, int numOfTimesSeen) {
        this.setString(stringToSet);
        this.setNumOfTimesSeen(numOfTimesSeen);
    }
    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public int getNumOfTimesSeen() {
        return numOfTimesSeen;
    }

    public void setNumOfTimesSeen(int numOfTimesSeen) {
        this.numOfTimesSeen = numOfTimesSeen;
    }
    public void incrementNumOfTimesSeen() {
        this.numOfTimesSeen++;
    }

    private String string;
    private int numOfTimesSeen;
}


class TokenArrayList<E> extends ArrayList {
    public int getNumOfTimesExists() {
        return numOfTimesExists;
    }

    public void setNumOfTimesExists(int numOfTimesExists) {
        this.numOfTimesExists = numOfTimesExists;
    }

    public void incrementNumOfTimesExists() {
        this.numOfTimesExists++;
    }

    private int numOfTimesExists = 0;

}

public class Apptest {
    protected ArrayList<Token> arrayListEater(ArrayList<String> arrayList) {
        ArrayList<Token> tokenArrayList = new ArrayList<Token>();

        for (int index = 0; index < arrayList.size() ; index++) {
            String stringToTry = arrayList.get(index);
            boolean stringIsInHolderIndex = false;
            for (int tokenHolderIndex = 0; tokenHolderIndex < arrayList.size() ; tokenHolderIndex++) {
                String stringInHolderToCompare = tokenArrayList.get(tokenHolderIndex).getString();
                if (stringInHolderToCompare.equals(stringToTry)) {
                    stringIsInHolderIndex = true;
                    tokenArrayList.get(tokenHolderIndex).incrementNumOfTimesSeen();
                }
            }
            if (stringIsInHolderIndex == false) {
                tokenArrayList.add(new Token(stringToTry));
            }
        }

        return tokenArrayList;
    }

    protected int searchStringLength(String stringToSearch) {
        int stringLength = 0;
        for (int index = 0; index < stringToSearch.length() ; index++) {
            if (stringToSearch.charAt(index) != ' ') {
                stringLength++;
            }
        }
        return stringLength;
    }

    protected List<String> searchLineForTokens(String stringToSearch) {
        int stringLength = 0;
        boolean currentIndexIsASpace = false;
        boolean foundFirstRealCharacter = false;
        List<String> stringArrayList = new ArrayList<String>();
        StringBuilder stringBeingBuilt = new StringBuilder();
        for (int index = 0; index < stringToSearch.length() ; index++) {
            if (stringToSearch.charAt(index) != ' ') {
                currentIndexIsASpace = false;
                foundFirstRealCharacter = true;
                stringBeingBuilt.append(stringToSearch.charAt(index));
                if (index == stringToSearch.length() - 1) {
                    stringArrayList.add(stringBeingBuilt.toString());
                }
            }

            else {
                if (currentIndexIsASpace == false && foundFirstRealCharacter) {
                    currentIndexIsASpace = true;
                    stringArrayList.add(stringBeingBuilt.toString());
                    stringBeingBuilt.setLength(0);
                }
            }
        }
        System.out.println("Tokens: " + stringArrayList);
        return stringArrayList;
    }

    protected ScannedStringStats searchFile(File fileToParse, File statsFile) {
        Scanner in = null;
        int numberOfLines = 0;
        int longestLine = 0;
        int totalLineLengthSize = 0;
        List<String> allTokens = new ArrayList<String>();
        try {
            in = new Scanner(fileToParse);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        if (in !=null) {
            Apptest apptest = new Apptest();
            while (in.hasNextLine()) {
                numberOfLines++;
                String currentLine = in.nextLine();
                System.out.println(currentLine);
                int currentLineLength = apptest.searchStringLength(currentLine);
                System.out.println("Current line length: " + currentLineLength);
                if (currentLineLength > longestLine) {
                    longestLine = currentLineLength;
                }
                totalLineLengthSize += currentLineLength;
                List<String> currentLineTokens = apptest.searchLineForTokens(currentLine);
                for (int index = 0; index < currentLineTokens.size() ; index++) {
                    allTokens.add(currentLineTokens.get(index));
                }
            }
        }
        System.out.println("Number of lines in file: " + numberOfLines);
        ScannedStringStats scannedStringStats = new ScannedStringStats();
        scannedStringStats.setLengthOfLongest(longestLine);
        Integer i = totalLineLengthSize;
        System.out.println("1-------------------------------------------------------------");
        System.out.println(allTokens);

        ArrayList<Token> noDupes = new ArrayList<Token>();
        for(String token:allTokens) {
            System.out.println("token: " + token + " | freqency: " + Collections.frequency(allTokens,token));
            boolean alreadyInNoDupes = false;
            for (Token oneOnlyToken:noDupes) {
                if(token.equals(oneOnlyToken.getString()) && !alreadyInNoDupes) {
                    alreadyInNoDupes = true;
                    System.out.println("Not going to add this one");
                    break;
                }
            }
            if (!alreadyInNoDupes) {
                System.out.println("Added above");
                if (noDupes.size() == 0){
                    noDupes.add(new Token(token, Collections.frequency(allTokens,token)));
                }
                else {
                    int index = 0;
                    boolean done = false;
                    while (done == false) {
                        if (index < noDupes.size()) {
                            if (noDupes.get(index).getNumOfTimesSeen() >= Collections.frequency(allTokens, token)) {
                                noDupes.add(index, new Token(token, Collections.frequency(allTokens,token)));
                                done = true;
                            }
                            else {
                                index++;
                            }
                        }
                        else {
                            noDupes.add(new Token(token, Collections.frequency(allTokens,token)));
                            done = true;
                        }
                    }
                }
            }
        }
        System.out.println("Done removing duplicates, printing final sorted array");
        for (Token current:noDupes) {
            System.out.println(current.getString() + " ||| " + current.getNumOfTimesSeen());
        }
        System.out.println("Done printing final sorted array");


        System.out.println("2-------------------------------------------------------------");
        scannedStringStats.setAverageLineLength(new Double(totalLineLengthSize) / new Double(numberOfLines));
        scannedStringStats.setAllTokens(allTokens);

        return scannedStringStats;
    }


    protected static void showFiles(File[] files, String currentDirectory) {
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("Directory: " + file.getName());
                showFiles(file.listFiles(), file.getPath()); // Calls same method again.
            } else {
                System.out.println("File: " + file.getName());
                String fileExtention = "";
                int indexOfLastDecimal = file.getName().lastIndexOf('.');
                if (indexOfLastDecimal > 0) {
                    fileExtention = file.getName().substring(indexOfLastDecimal+1);
                    if (fileExtention.equals("java") || fileExtention.equals("txt")) {
                        System.out.println("Found what we are looking for");
                        currentDirectory = file.getParentFile().getPath();
                        System.out.println(currentDirectory + " || test1");
                        if (!currentDirectory.equals("")) {
                            File parentDirectory = new File(currentDirectory);
                            parentDirectory.mkdir();
                            File newStatsFile = new File(parentDirectory, file.getName() + ".stats");
                            try {
                                Apptest apptest = new Apptest();
                                List lines = new ArrayList();
                                ScannedStringStats scannedStringStats = apptest.searchFile(file, newStatsFile);
                                System.out.println("Tried to search file");
                                lines.add("Length of longest line in file: " + scannedStringStats.getLengthOfLongest());
                                lines.add("Average line length in file: " + scannedStringStats.getAverageLineLength());
                                lines.add("Number of all space-delineated tokens in file: " + scannedStringStats.getAllTokensLength());
                                Path filePath = Paths.get(currentDirectory + "\\" + newStatsFile.getName());
                                Files.write(filePath, lines);
                                file.createNewFile();
                            }
                            catch(IOException exception) {
                                exception.printStackTrace();
                            }
                            System.out.println("Made a new file.");
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        //org.apache.maven archetype-quicktype
        System.out.println();
        System.out.println(args + "..!");
        if (args.length == 0) {
            File[] files = new File("C:\\Users\\mjk29\\IdeaProjects\\Apptest").listFiles();
            showFiles(files,"");
            System.out.println("yup1");
        }
        else {
            for (String arg:args) {
                File[] files = new File(arg).listFiles();
                showFiles(files, "");
                System.out.println("yup2");
            }
        }
        //readFile("C:\\Users\\mjk29\\IdeaProjects\\Apptest\\src\\main\\java","test.txt");

    }
}
