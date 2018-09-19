package edu.sdsu.cs;

import java.util.ArrayList;
import java.util.Collections;

public class UniqueTokenLister {

    public UniqueTokenLister(){
        ArrayList<String> defaultStringList = new ArrayList<>();
        listBuilder(defaultStringList);
        System.out.println(defaultStringList);
        findUniqueToken(defaultStringList);
    }

    public UniqueTokenLister(ArrayList<String> inputArray){
        findUniqueToken(inputArray);
    }

    private void listBuilder(ArrayList<String> stringArrayList) {
        stringArrayList.add("test1");
        stringArrayList.add("test2");
        stringArrayList.add("test2");
        stringArrayList.add("test3");
        stringArrayList.add("test3");
        stringArrayList.add("test3");
        stringArrayList.add("test4");
        stringArrayList.add("test4");
        stringArrayList.add("test4");
        stringArrayList.add("test4");
        stringArrayList.add("test5");
        stringArrayList.add("test5");
        stringArrayList.add("test5");
        stringArrayList.add("test5");
        stringArrayList.add("test5");
    }

    private void removeDuplicates(ArrayList<String> stringArrayList) {

        Object[] lst = stringArrayList.toArray();

        for (Object token : lst) {
            if (stringArrayList.indexOf(token) != stringArrayList.lastIndexOf(token)) {
                stringArrayList.remove(stringArrayList.lastIndexOf(token));
            }
        }
    }

    public void findUniqueToken(ArrayList<String> stringArrayList){

        ArrayList<String> duplicatesList = new ArrayList<>();
        for(String str: stringArrayList){
            duplicatesList.add(str + ":" + Collections.frequency(stringArrayList,
                    str));
        }
        removeDuplicates(duplicatesList);
        System.out.println(duplicatesList);
    }
}