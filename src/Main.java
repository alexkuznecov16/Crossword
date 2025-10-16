import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userWord = "";

        Crossword crossword1 = new Crossword(10, 10);
        crossword1.setVerticalWord("void", 2, 2, "(2, 2) The return type of a method that does not return anything.");
        crossword1.setVerticalWord("class", 0, 1, "(0, 1) Blueprint for creating objects.");
        crossword1.setVerticalWord("catch", 0 ,6, "(0, 6) Used to handle exceptions after try.");
        crossword1.setVerticalWord("static", 4, 8, "(4, 8) Keyword used for variables or methods that belong to the class, not instances.");
        crossword1.setVerticalWord("float", 5, 4, "(5, 4) A primitive data type for decimal numbers.");

        crossword1.setHorizontalWord("java", 2, 0, "(2, 0) Used to create Android apps, web apps, and server-side programs.");
        crossword1.setHorizontalWord("object", 3, 2, "(3, 2) An instance of a class.");
        crossword1.setHorizontalWord("access", 0, 0, "(0, 0) Controls who can use a variable or method.");
        crossword1.setHorizontalWord("default", 5, 2, "(5, 2) Specifies a value if nothing else is assigned.");
        crossword1.setHorizontalWord("loop", 7, 2, "(7, 2) A structure for repeating code.");

        crossword1.fillCells();

        while (true) {
            System.out.println(crossword1.getField());
            System.out.println(crossword1.getHelps());

            System.out.print("Enter guessed word: ");
            try {
                userWord = scanner.nextLine();

                if (userWord.isEmpty()) {
                    System.out.println("String cannot be empty.");
                    continue;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (crossword1.isAlreadyGuessed(userWord)) {
                System.out.println("The word (" + userWord + ") is already guessed!");
                continue;
            }

            if (crossword1.guessWord(userWord)) {
                System.out.println("Word (" + userWord + ") is found!");
            }

            if (crossword1.isWin()) {
                System.out.println("=======================================\n\n\n");
                break;
            }
        }
        // CROSSWORD END =================================

        System.out.println(crossword1.getField());
        System.out.println("Victory!");
    }
}

class Crossword {
    private char[][] field;
    private int rows;
    private int cols;
    private List<String> helps = new ArrayList<>(); // list of words help
    private List<String> words = new ArrayList<>();
    private List<String> userWords = new ArrayList<>(); // list of guessed words
    private List<Integer> wordRows = new ArrayList<>();
    private List<Integer> wordCols = new ArrayList<>();
    private List<Boolean> wordHorizontal = new ArrayList<>();

    public Crossword(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.field = new char[this.rows][this.cols];
    }

    // fill field's cells with '_' if it is a char
    public void fillCells() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == '\u0000') {
                    field[i][j] = ' ';
                } else if (Character.isLetter(field[i][j])) {
                    field[i][j] = '_';
                }
            }
        }
    }

    // return current field
    public String getField() {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("   ");
        for (int col = 0; col < cols; col++) {
            stringBuilder1.append(col).append(' ');
        }
        stringBuilder1.append('\n');

        for (int row = 0; row < rows; row++) {
            stringBuilder1.append(row).append("  ");
            for (int col = 0; col < cols; col++) {
                stringBuilder1.append(field[row][col]).append(' ');
            }
            stringBuilder1.append('\n');
        }

        return stringBuilder1.toString();
    }

    public String getHelps() {
        String text = "";
        for (String item : helps) {
            text += item + '\n';
        }

        return text;
    }

    public void setHorizontalWord(String word, int row, int col, String help) {
        for (int i = 0; i < word.length(); i++) {
            field[row][col + i] = word.charAt(i);
        }
        helps.add(help);
        words.add(word);
        wordRows.add(row);
        wordCols.add(col);
        wordHorizontal.add(true);
    }

    public void setVerticalWord(String word, int row, int col, String help) {
        for (int i = 0; i < word.length(); i++) {
            field[row + i][col] = word.charAt(i);
        }
        helps.add(help);
        words.add(word);
        wordRows.add(row);
        wordCols.add(col);
        wordHorizontal.add(false);
    }

    public boolean guessWord(String userWord) {
        userWord = userWord.toLowerCase();
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).equalsIgnoreCase(userWord)) {
                int row = wordRows.get(i);
                int col = wordCols.get(i);
                boolean horizontal = wordHorizontal.get(i);

                // iterate each char of word
                for (int k = 0; k < userWord.length(); k++) {
                    if (horizontal) field[row][col + k] = words.get(i).charAt(k);
                    else field[row + k][col] = words.get(i).charAt(k);
                }

                userWords.add(userWord);
                return true;
            }
        }
        return false;
    }

    public boolean isAlreadyGuessed(String userWord) {
        return userWords.contains(userWord);
    }

    public boolean isWin() {
        return userWords.size() == words.size();
    }
}