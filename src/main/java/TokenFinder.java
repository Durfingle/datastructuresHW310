package edu.sdsu.cs;

import java.util.ArrayList;
import java.util.Collections;

public class TokenFinder {

    private ArrayList<Token> defaultTokenList = new ArrayList<>();

    public TokenFinder() {
        listBuilder(defaultTokenList);
    }

    public TokenFinder(ArrayList<Token> inputArray) {
        this.defaultTokenList = inputArray;
    }

    private void listBuilder(ArrayList<Token> tokenArrayListArrayList) {
        tokenArrayListArrayList.add(new Token("test1"));
        tokenArrayListArrayList.add(new Token("test2"));
        tokenArrayListArrayList.add(new Token("test2"));
        tokenArrayListArrayList.add(new Token("test1"));
        tokenArrayListArrayList.add(new Token("test3"));
        tokenArrayListArrayList.add(new Token("test1"));
        tokenArrayListArrayList.add(new Token("test4"));
        tokenArrayListArrayList.add(new Token("test1"));
        tokenArrayListArrayList.add(new Token("test4"));
        tokenArrayListArrayList.add(new Token("test1"));
        tokenArrayListArrayList.add(new Token("test4"));
        tokenArrayListArrayList.add(new Token("test1"));
        tokenArrayListArrayList.add(new Token("test4"));


    }

    private void removeDuplicates() {

        Token tkLst[] = new Token[defaultTokenList.size()];
        tkLst = defaultTokenList.toArray(tkLst);
        for (Token tk : tkLst) {
            int nextTokenIndex = this.defaultTokenList.lastIndexOf(tk);

            if (tk.getString().compareToIgnoreCase(//the .getString of the
                    // next token)){
                this.defaultTokenList.remove(this.defaultTokenList.lastIndexOf(tk));
            }

        }

    }

    private void removeDuplicates(ArrayList<Token> tokenArrayList) {

        Object[] objList = tokenArrayList.toArray();
        for (Object obj : objList) {
            if (tokenArrayList.indexOf(obj) != tokenArrayList.lastIndexOf(obj)) {
                tokenArrayList.remove(tokenArrayList.lastIndexOf(obj));
            }
        }
    }


    public void findUniqueToken(ArrayList<Token> tokenArrayList) {

        for (Token tok : tokenArrayList) {
            tok.setNumOfTimesSeen(Collections.frequency(tokenArrayList, tok));
        }
        removeDuplicates(tokenArrayList);
    }

    public void findUniqueToken() {

        for (Token tok : this.defaultTokenList) {
            tok.setNumOfTimesSeen(Collections.frequency(this.defaultTokenList, tok));
        }
        removeDuplicates(this.defaultTokenList);
    }

    public ArrayList<Token> getList() {
        return defaultTokenList;
    }

    private String getTokenString(ArrayList<Token> tokenArrayList, int index){
        Token retrievedToken = tokenArrayList.get(index);
        return retrievedToken.getString();
    }

    public void printList(ArrayList<Token> tokenArrayList) {
        for (Token tok : tokenArrayList) {
            System.out.println(tok.getString() + " : " + tok.getNumOfTimesSeen());
        }
    }

    public void printList() {
        for (Token tok : this.defaultTokenList) {
            System.out.println(tok.getString() + ":" + tok.getNumOfTimesSeen());
        }
    }
}