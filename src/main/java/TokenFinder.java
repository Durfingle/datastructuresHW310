//package edu.sdsu.cs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TokenFinder {

    private ArrayList<Token> defaultTokenList = new ArrayList<>();
    private ArrayList<String> defaultStringList = new ArrayList<>();

    private ArrayList<Token> lowerCaseTokenList = new ArrayList<>();
    private ArrayList<String> lowerCaseStringList = new ArrayList<>();

    protected ArrayList<Token> getTenMostFrequentTokens() {
        return tenMostFrequentTokens;
    }

    protected ArrayList<Token> getLowerCaseTokenList() {
        return lowerCaseTokenList;
    }

    protected ArrayList<Token> getTenLeastFrequetTokens() {
        return tenLeastFrequetTokens;
    }

    private ArrayList<Token> tenMostFrequentTokens = new ArrayList<>();
    private ArrayList<Token> tenLeastFrequetTokens = new ArrayList<>();

    protected TokenFinder() {
        this.defaultStringList.add(" ");
    }

    protected TokenFinder(ArrayList<String> defaultStringList) {
        this.defaultStringList = defaultStringList;
    }

    private void buildTokenListFromStringList() {
        for (String str : defaultStringList) {
            defaultTokenList.add(new Token(str,
                    Collections.frequency(defaultStringList, str)));
        }
    }

    private void buildLowerCaseStringListFromDefaultStringList() {
        for (String str : defaultStringList) {
            lowerCaseStringList.add(str.toLowerCase());
        }
    }

    private void buildLowerCaseTokenListFromLowerCaseStringList() {
        for (String str : lowerCaseStringList) {
            lowerCaseTokenList.add(new Token(str.toLowerCase(),
                    Collections.frequency(lowerCaseStringList, str)));
        }
    }

    private void sortByFrequency() {
        Collections.sort(this.defaultTokenList, new SortByFrequency());
        Collections.sort(this.lowerCaseTokenList, new SortByFrequency());
    }

    private ArrayList<Token> removeDuplicateTokens(ArrayList<Token> outputArrayList) {
        ArrayList<Token> uniqueTokens = new ArrayList<>();
        ArrayList<String> tokenStrings = new ArrayList<>();

        for (Token token : outputArrayList) {
            if (!tokenStrings.contains(token.getString())) {
                tokenStrings.add(token.getString());
                uniqueTokens.add(token);
            }
        }
        outputArrayList = uniqueTokens;
        Collections.sort(outputArrayList, new SortByFrequency());
        return outputArrayList;
    }

    protected ArrayList<Token> getDefaultTokenList() {
        return defaultTokenList;
    }

    class SortByFrequency implements Comparator<Token> {

        public int compare(Token tkA, Token tkB) {
            return tkA.getNumOfTimesSeen() - tkB.getNumOfTimesSeen();
        }

    }

    protected void standardRun() {

        buildLowerCaseStringListFromDefaultStringList();
        buildLowerCaseTokenListFromLowerCaseStringList();
        buildTokenListFromStringList();
        sortByFrequency();
        this.defaultTokenList = removeDuplicateTokens(defaultTokenList);
        this.lowerCaseTokenList = removeDuplicateTokens(lowerCaseTokenList);

        for (int i = 0; i < 10; i++) {
            tenLeastFrequetTokens.add(lowerCaseTokenList.get(i));
        }
        for (int i = 0; i < 10; i++) {
            tenMostFrequentTokens.add(lowerCaseTokenList.get(lowerCaseTokenList.size() - 1 - i));
        }

    }

}