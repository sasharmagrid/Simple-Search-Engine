package search;
import search.SearchEngine.Search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;



public class Main {

    public static void main(String[] args) {
        Search search = new Search();
        if(args[0].equals("--data")) {
            File file = new File(args[1]);
//        File file = new File("/Users/sasharma/IdeaProjects/Simple Search Engine (Java)/Simple Search Engine (Java)/task/src/search/names.txt");
            search.enterDetails(file);
            search.chooseOption();
        }
    }
}
