import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManager {
    
    static ArrayList<String> availableBooks = new ArrayList<>();
    static String[] archivedBooks = new String[5];
    static final String AVAILABLE_FILE = "availableBooks.txt";
    static final String ARCHIVE_FILE = "archivedBooks.txt";
    
    public static void main(String[] args) {
        availableBooks();
        availableBooks();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("--- Library Manager ---");
            System.out.println("1. Add Books");
            System.out.println("2. View Available Books");
            System.out.println("3. Archive Books");
            System.out.println("4. View Archived Books");
            System.out.println("5. Exit");

            int choice = getValidInt("Choose an option");
            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewdAvailableBooks();
                case 3 -> archivedBooks();
                case 4 -> viewdAvailableBooks();
                case 5 -> {saveAvailableBooks();
                
                    System.out.println("Data saved. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }


static void addBook() {
    String book = getNonEmptyString("Enter book title: ");
    availableBooks.add(book);
    System.out.println("Book added.");
}

static void viewdAvailableBooks() {
    if (availableBooks.isEmpty()) {
        System.out.println("No Books availble.");
    } else {
        System.out.println("Available Books:");
        for (int i = 0; i < availableBooks.size(); i++) {
            System.out.println(i + "; " + availableBooks.get(i));
        }
    }
}

static void archivedBooks() {
    viewdAvailableBooks();
    if (availableBooks.isEmpty()) return;

    int index = getValidInt("Enter index of book to archive: ");
    try {
        String book = availableBooks.remove(index);
        for (int i = 0; i < archivedBooks.length; i++) {
            if (archivedBooks[i] == null) {
                archivedBooks[i] = book;
                System.out.println("Book archived.");
                return;
            }
        }
        System.out.println("Archive full. Book not archived.");
        availableBooks.add(book);
    } catch (IndexOutOfBoundsException e) {
        System.out.println("Invalid index.");
    }
}

static void viewArchivedBooks() {
    System.out.println("Archived Books:");
    for (String book : archivedBooks) {
        if (book != null) {
            System.out.println("- " + book);
        }
    }
}


static void saveAvailableBooks() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(AVAILABLE_FILE))) {
        for (String book : availableBooks) {
            writer.write(book);
            writer.newLine();
        }
        } catch (IOException e) {
            System.out.println("Error saving available book: " + e.getMessage());
        }
}


static void saveArchivedBooks() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVE_FILE))) {
        for (String book : archivedBooks) {
            if (book != null) {
                writer.write(book);
                writer.newLine();
            }
        }
    } catch (IOException e) {
        System.out.println("Error saving archived book: " + e.getMessage());
    }
}


static void availableBooks() {
    try (BufferedReader reader = new BufferedReader(new FileReader(AVAILABLE_FILE))) {
    String line;
    while ((line = reader.readLine()) != null) {
        availableBooks.add(line);
    
        }
    }  catch (IOException e) {
        System.out.println("No available book file round, starting fresh.");
    }
     
} 


static void loadArchivedBooks() {
    try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVE_FILE))) {
        String line;
        int i = 0;
        while ((line = reader.readLine()) != null && i < archivedBooks.length) {
            archivedBooks[i++] = line;
       
        }
    } catch (IOException e) {
        System.out.println("No archive file found, starting fresh.");
    }
}

static int getValidInt(String prompt) {
    System.out.print(prompt);
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    try {
        return Integer.parseInt(input);
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Try again.");
        return getValidInt(prompt);
    }
}


static String getNonEmptyString(String prompt) {
    System.out.print(prompt);
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    if (input. trim().isEmpty()) {
        System.out.println("Input cannot be empty.");
        return getNonEmptyString(prompt);
    }
    return input; 
    }

}

