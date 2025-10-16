
# Java Crossword Game

This is a console-based **Java crossword game** focused on Java programming topics. The game presents a 10x10 crossword grid with **5 horizontal** and **5 vertical words**. Users can see questions (clues) and guess words to fill the crossword.

---

## Features

- **2D array grid:** Stores and displays the crossword puzzle.
- **Horizontal and vertical words:** Words can overlap like a real crossword.
- **Clues displayed:** Users can see hints for each word.
- **Word guessing:** Users enter words, and correctly guessed letters are revealed.
- **Tracks progress:** Already guessed words are stored to avoid repetition.
- **Victory detection:** Game ends when all words are correctly guessed.

---

## How to Play

1. Run the program.
2. The crossword grid is displayed with underscores `_` representing hidden letters.
3. Clues for all words are printed.
4. Enter a word you think fits a clue.
5. If correct, the letters are revealed in the grid.
6. If already guessed, you are notified.
7. The game continues until all words are revealed.

---

## Example Clues

- `(0, 0) Controls who can use a variable or method.` → `access`
- `(2, 0) Used to create Android apps, web apps, and server-side programs.` → `java`
- `(4, 8) Keyword used for variables or methods that belong to the class, not instances.` → `static`
- `(2, 2) The return type of a method that does not return anything.` → `void`
- `(7, 2) A structure for repeating code.` → `loop`

---

## How It Works

- Words are stored in lists (`words`) along with their positions (`wordRows`, `wordCols`) and orientation (`wordHorizontal`).
- The field is a 2D `char[][]` array.
- `fillCells()` hides letters by replacing them with `_`.
- `guessWord(String userWord)` checks the input, reveals letters, and adds the word to `userWords`.
- `isAlreadyGuessed(String userWord)` prevents duplicates.
- `isWin()` returns `true` when all words are guessed.

---

## Requirements

- Java JDK 8 or higher.
- Any console or IDE that can run Java programs.

---

## Example Usage

```java
Crossword crossword1 = new Crossword(10, 10);
crossword1.setHorizontalWord("java", 2, 0, "Used to create Android apps, web apps, and server-side programs.");
crossword1.setVerticalWord("void", 2, 2, "The return type of a method that does not return anything.");

crossword1.fillCells();
System.out.println(crossword1.getField());
System.out.println(crossword1.getHelps());
```

---

## Victory

Once all words are correctly guessed:

```
Victory!
```

All letters are revealed on the grid.

---

## Author

Created as a Java programming practice project by Alexander Kuznecov.
