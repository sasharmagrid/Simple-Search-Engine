package search.SearchEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Search {
    Scanner sc = new Scanner(System.in);
    Map<Integer, String> mp = new HashMap<>();
    Map<String, ArrayList<Integer>> wordMap = new HashMap<>();
    private SearchInterface strategy;

    // Set the strategy dynamically
    public void setSearchStrategy(SearchInterface strategy) {
        this.strategy = strategy;
    }

    public void enterDetails(File f) {
        int sum = 0;

        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                mp.put(sum, line);
                try {
                    String[] words = line.split(" ");
                    for (var word : words) {
                        wordMap.putIfAbsent(word.toLowerCase(), new ArrayList<>());
                        wordMap.get(word.toLowerCase()).add(sum);
                    }
                    sum++;
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file " + f + " was not found.");
        }
    }

    public void searchQuery() {
        System.out.println("Enter a name or email to search all suitable people.");
        String wordToSearch = sc.nextLine().toLowerCase();
        if (strategy != null) {
            strategy.search(wordToSearch, this);
        } else {
            System.out.println("No search strategy set.");
        }
    }

    public void printPeople() {
        System.out.println("Found people:");
        for (var s : mp.entrySet()) {
            System.out.println(s.getValue());
        }
    }

    public void chooseOption() {
        while (true) {
            System.out.println("=== Menu ===");
            System.out.println("1. Find a person");
            System.out.println("2. Print all people");
            System.out.println("0. Exit");
            int option = sc.nextInt();
            sc.nextLine();
            if (option == 0) {
                System.out.println("Bye!");
                break;
            } else if (option == 1) {
                System.out.println("Select a matching strategy: ALL, ANY, NONE");
                selectMethod();
            } else if (option == 2) {
                System.out.println("=== List of people ===");
                printPeople();
            } else {
                System.out.println("Incorrect option! Try again.");
            }
        }
    }

    public void selectMethod() {
        System.out.print(">");
        String strategy = sc.nextLine();
        switch (strategy) {
            case "ANY":
                setSearchStrategy(new SearchAny());
                break;
            case "ALL":
                setSearchStrategy(new SearchAll());
                break;
            case "NONE":
                setSearchStrategy(new SearchNone());
                break;
            default:
                System.out.println("Invalid strategy.");
                return;
        }
        searchQuery();
    }
}
