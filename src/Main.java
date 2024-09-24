import java.util.*;

public class Main {

    // ArrayList to hold positions
    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    /**
     * method to print the board to the CLI
     * @param board - with the current positions
     */
    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char element : row) {
                System.out.print(element);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        // Building the game board
        char[][] board = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        printBoard(board);

        Scanner scan = new Scanner(System.in);

        Random generator = new Random();

        boolean endGame = false;

        // Game's main running environment
        while (!endGame) {

            while (true) {
                System.out.println();
                System.out.println("1|2|3");
                System.out.println("-+-+-");
                System.out.println("4|5|6");
                System.out.println("-+-+-");
                System.out.println("7|8|9");
                System.out.print("Enter your placement! - (1 - 9) : ");
                int playerPlace = scan.nextInt();

                // To make sure there are slots available
                if (playerPositions.contains(playerPlace) || cpuPositions.contains(playerPlace)) {
                    System.out.println("Position already taken! try again");
                } else { // To get a position that is not taken already
                    playerPositions.add(playerPlace);
                    setPosition(board, playerPlace, "player");
                    break;
                }
            }

            while (true) {
                int cpuPlace = generator.nextInt(9) + 1;

                // Making sure there are available positions
                if (playerPositions.size() + cpuPositions.size() == 9) {
                    break;
                }
                // To generate a position that is not taken already
                if (!(playerPositions.contains(cpuPlace) || cpuPositions.contains(cpuPlace))) {
                    cpuPositions.add(cpuPlace);
                    setPosition(board, cpuPlace, "cpu");
                    break;
                }
            }

            System.out.println();
            printBoard(board);

            endGame = checkWinner(); // Checking for winner

        }

    }

    /**
     * Method to set the positions of either the user or the CPU on the board
     * @param board - The board before the current placement
     * @param place - where the piece has to placed
     * @param user - has 2 valid values on this program
     *             1. player - indicates that the piece belongs to the user, so that piece is 'X'
     *             2. user - indicates that the piece belongs to the CPu, so that the piece is 'O'
     */
    public static void setPosition(char[][] board, int place, String user) {
        char element = ' ';
        if (user.equals("player")) {
            element = 'X';
        } else if (user.equals("cpu")){
            element = 'O';
        }

        // Modifying the position based on user input
        switch (place) {
            case 1:
                board[0][0] = element;
                break;
            case 2:
                board[0][2] = element;
                break;
            case 3:
                board[0][4] = element;
                break;
            case 4:
                board[2][0] = element;
                break;
            case 5:
                board[2][2] = element;
                break;
            case 6:
                board[2][4] = element;
                break;
            case 7:
                board[4][0] = element;
                break;
            case 8:
                board[4][2] = element;
                break;
            case 9:
                board[4][4] = element;
                break;
            default:
                System.out.println("Invalid input!");

        }
    }

    /**
     * Method to check if there is a winner, after each round
     * @return
     */
    public static boolean checkWinner() {

        // All possible winning positions
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        // Adding all of these columns into a separate list to make it easier
        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(bottomRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List condition : winning) {
            if (playerPositions.containsAll(condition)) {
                System.out.println("Player wins!");
                return true;
            } else if (cpuPositions.containsAll(condition)) {
                System.out.println("You Lost!");
                return true;
            } else if (playerPositions.size() + cpuPositions.size() == 9) { // If the board is full and there is no winner, the game is a tie
                System.out.println("Tie!");
                return true;
            }
        }

        return false;
    }

}