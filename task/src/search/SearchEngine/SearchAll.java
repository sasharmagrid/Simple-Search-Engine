package search.SearchEngine;

import java.util.ArrayList;

public class SearchAll implements SearchInterface {
    @Override
    public void search(String query, Search search) {
        String[] words = query.toLowerCase().split(" ");
        ArrayList<String> people = new ArrayList<>();
        for (var index : search.mp.entrySet()) {
            boolean flag = true;
            for (var word : words) {
                if (!index.getValue().toLowerCase().contains(word)) {
                    flag = false;
                    break;
                }
            }
            if (flag) people.add(index.getValue());
        }
        int numberOfPeople = people.size();

        System.out.println(numberOfPeople + " persons found:");
        for (String name : people) {
            System.out.println(name);
        }
        System.out.println();
    }
}
