package edu.sdsu.cs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TokenFinder {

    private ArrayList<Token> defaultTokenList = new ArrayList<>();
    private ArrayList<String> defaultStringList = new ArrayList<>();

    /**
     * Throw an Exception for. NO NULL ARRAYS ALLOWED.
     */
    public TokenFinder() {
        stringListBuilder(this.defaultStringList);
    }

    public TokenFinder(ArrayList<Token> tokenArrayList) {
        this.defaultTokenList = tokenArrayList;
    }

    /**
     * ONLY FOR BUILDING FROM THE DEFAULT STRING LIST. NOT NEEDED FOR SUBMISSION
     */
    private void buildTokenListFromStringList() {
        for (String str : defaultStringList) {
            defaultTokenList.add(new Token(str,
                    Collections.frequency(defaultStringList, str)));
        }
    }

    private void buildTokenList(){
        for(Token tok: defaultTokenList){
            defaultStringList.add(tok.getString());
        }
        defaultTokenList.clear();
        for(String str: defaultStringList){
            defaultTokenList.add(new Token(str,
                    Collections.frequency(defaultStringList, str)));
        }
    }

    protected void sortByFrequency() {
        Collections.sort(this.defaultTokenList, new SortByFrequency());
    }

    protected void removeDuplicateTokens(ArrayList<Token> tokenArrayList) {
        ArrayList<Token> uniqueTokens = new ArrayList<>();
        ArrayList<String> tokenStrings = new ArrayList<>();

        for (Token token : tokenArrayList) {
            if (!tokenStrings.contains(token.getString())) {
                tokenStrings.add(token.getString());
                uniqueTokens.add(token);
            }
        }
        this.defaultTokenList = uniqueTokens;
        Collections.sort(defaultTokenList, new SortByFrequency());
    }

    protected void removeDuplicateTokens() {
        ArrayList<Token> uniqueTokens = new ArrayList<>();
        ArrayList<String> tokenStrings = new ArrayList<>();

        for (Token token : defaultTokenList) {
            if (!tokenStrings.contains(token.getString())) {
                tokenStrings.add(token.getString());
                uniqueTokens.add(token);
            }
        }
        this.defaultTokenList = uniqueTokens;
        Collections.sort(defaultTokenList, new SortByFrequency());
    }

    private void setDefaultTokenList(ArrayList<Token> tokenListToSet) {
        this.defaultTokenList = tokenListToSet;
    }

    private void setDefaultStringList(ArrayList<String> stringListToSet) {
        this.defaultStringList = stringListToSet;
    }

    public ArrayList<Token> getDefaultTokenList() {
        return defaultTokenList;
    }

    public ArrayList<String> getDefaultStringList() {
        return defaultStringList;
    }

    private void stringListBuilder(ArrayList<String> stringArrayList) {

        stringArrayList.add("test1");
        stringArrayList.add("test2");
        stringArrayList.add("test1");
        stringArrayList.add("test3");
        stringArrayList.add("test3");
        stringArrayList.add("test3");
        stringArrayList.add("test3");
        stringArrayList.add("test4");
        stringArrayList.add("test7");
        stringArrayList.add("test4");
        stringArrayList.add("test5");
        stringArrayList.add("test5");
        stringArrayList.add("test3");
        stringArrayList.add("test5");
        stringArrayList.add("test5");
    }

    class SortByFrequency implements Comparator<Token> {

        public int compare(Token tkA, Token tkB) {
            return tkA.getNumOfTimesSeen() - tkB.getNumOfTimesSeen();
        }

    }

    /**
     * For Testing Purposes. REMOVE BEFORE SUBMITTING!!!
     */
    public void standardRun() {
        System.out.println("Unsorted List");
        for (String str : defaultStringList) {
            System.out.println(str);
        }
        System.out.println("_Build Token List_");
        buildTokenListFromStringList();
        System.out.println("__Sort By Frequency__");
        sortByFrequency();
        System.out.println("__Remove Duplicates__");
        removeDuplicateTokens(defaultTokenList);
        for(Token tok: defaultTokenList){
            System.out.println(tok);
        }
    }

    public void noArgRun(){
        System.out.println("Unsorted List");
        for (Token tok: defaultTokenList){
            System.out.println(tok.getString());
        }
        buildTokenList();
        System.out.println("__Sort By Frequency__");
        sortByFrequency();
        System.out.println("__Remove Duplicates__");
        removeDuplicateTokens();
        for(Token tok: defaultTokenList){
            System.out.println(tok);
        }
    }

}