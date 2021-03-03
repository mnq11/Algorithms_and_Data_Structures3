package Task3;

public class Task3 {


    public static void main(String[] args) {
        String[] lookupWords = {"this", "two", "fat", "that","gekii"};
        char[][] puzzle = { {'t', 'h', 'i', 's'},
                            {'w', 'a', 't', 's'},
                            {'o', 'a', 'h', 'g'},
                            {'f', 'd', 'g', 't'}};

        Solver solver = new Solver(puzzle, lookupWords);
        solver.solve();

        System.out.println(solver);
    }

    }

