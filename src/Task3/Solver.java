package Task3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Solver {
    private final Grid grid;
    private final String[] words;
    private final Map<String, Coord> solutions = new HashMap<>();

    public Solver(char[][] grid, String[] words) {
        this.grid = new Grid(grid);
        this.words = words;
    }

    public void solve() {
        for (String word : words)
            findWord(word);
    }

    private void findWord(String word) {
        char firstLetter = word.charAt(0);

        for (int y = 0; y < grid.height(); y++) {
            for (int x = 0; x < grid.width(); x++) {
                if (grid.at(x, y) == firstLetter) {
                    if (this.checkLeft(x, y, word))
                        solutions.put(word, new Coord(x, y, (x - word.length() + 1), y));
                    else if (this.checkRight(x, y, word))
                        solutions.put(word, new Coord(x, y, (x + word.length() - 1), y));
                    else if (this.checkDown(x, y, word))
                        solutions.put(word, new Coord(x, y, x, (y + word.length() - 1)));
                    else if (this.checkUp(x, y, word))
                        solutions.put(word, new Coord(x, y, x, (y - word.length() + 1)));
                    else if (this.checkLeftUp(x, y, word))
                        solutions.put(word, new Coord(x, y, (x - word.length() + 1), (y - word.length() + 1)));
                    else if (this.checkRightDown(x, y, word))
                        solutions.put(word, new Coord(x, y, (x + word.length() - 1), (y + word.length() - 1)));
                    else if (this.checkLeftDown(x, y, word))
                        solutions.put(word, new Coord(x, y, (x - word.length() + 1), (y + word.length() - 1)));
                    else if (this.checkRightUp(x, y, word))
                        solutions.put(word, new Coord(x, y, (x + word.length() - 1), (y - word.length() + 1)));
                }
            }
        }
    }

    private boolean checkLeft(int x, int y, String word) {
        if ((x + 1) - word.length() < 0)
            return false;

        int index = x;
        for (char letter : word.toCharArray()) {
            if (grid.at(index, y) != letter)
                return false;
            index--;
        }

        return true;
    }

    private boolean checkRight(int x, int y, String word) {
        if (x + word.length() > grid.width())
            return false;

        int index = x;
        for (char letter : word.toCharArray()) {
            if (grid.at(index, y) != letter)
                return false;
            index++;
        }

        return true;
    }

    private boolean checkDown(int x, int y, String word) {
        if (y + word.length() > grid.height())
            return false;

        int index = y;
        for (char letter : word.toCharArray()) {
            if (grid.at(x, index) != letter)
                return false;
            index++;
        }

        return true;
    }

    private boolean checkUp(int x, int y, String word) {
        if ((y + 1) - word.length() < 0)
            return false;

        int index = y;
        for (char letter : word.toCharArray()) {
            if (grid.at(x, index) != letter)
                return false;
            index--;
        }

        return true;
    }

    private boolean checkLeftUp(int x, int y, String word) {
        if ((y + 1) - word.length() < 0 || (x + 1) - word.length() < 0)
            return false;

        int indexX = x, indexY = y;
        for (char letter : word.toCharArray()) {
            if (grid.at(indexX, indexY) != letter)
                return false;
            indexX--;
            indexY--;
        }

        return true;
    }

    private boolean checkRightDown(int x, int y, String word) {
        if (y + word.length() > grid.height() || x + word.length() > grid.width())
            return false;

        int indexX = x, indexY = y;
        for (char letter : word.toCharArray()) {
            if (grid.at(indexX, indexY) != letter)
                return false;
            indexX++;
            indexY++;
        }

        return true;
    }

    private boolean checkLeftDown(int x, int y, String word) {
        if (y + word.length() > grid.height() || (x + 1) - word.length() < 0)
            return false;

        int indexX = x, indexY = y;
        for (char letter : word.toCharArray()) {
            if (grid.at(indexX, indexY) != letter)
                return false;
            indexX--;
            indexY++;
        }

        return true;
    }

    private boolean checkRightUp(int x, int y, String word) {
        if ((y + 1) - word.length() < 0 || x + word.length() > grid.width())
            return false;

        int indexX = x, indexY = y;
        for (char letter : word.toCharArray()) {
            if (grid.at(indexX, indexY) != letter)
                return false;
            indexX++;
            indexY--;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("     0   1   2   3  ");
        output.append("\n    _______________\n");
        for (int y = 0; y < grid.height(); y++) {

            output.append(y).append(" |  ");
            for (int x = 0; x < grid.width(); x++)

                output.append(grid.at(x, y)).append("   ");

            output.append("\n");


        }
        output.append("\n");
        Set set = solutions.entrySet();

        for (Object o : set) {
            Map.Entry entry = (Map.Entry) o;

            Coord coord = (Coord) entry.getValue();
            output.append(entry.getKey()).append(": ").append(coord.toString());
            output.append("\n");
        }

        return output.toString();
    }


    public static class Coord {
        public int startX, startY, endX, endY;

        public Coord(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }

        @Override
        public String toString() {

            return "Start at " +
                    startX + "." + startY + " end " +
                    endX + "." + endY;

        }
    }
}