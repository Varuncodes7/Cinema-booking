import java.util.Scanner;

public class Cinema {
    static final int SCREENS = 5;
    static final int ROWS = 25; // Assuming 25 rows
    static final int SEATS_PER_ROW = 20; // 20 seats per row, total = 500 seats
    static boolean[][][] seats = new boolean[SCREENS][ROWS][SEATS_PER_ROW]; // [screen][row][seat]
    static String[] movies = { "AMARAN", "KANGUVA", "3", "VIDAMUYARCHI", "M.S DHONI" };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Cinema Ticket Booking System ===");
            System.out.println("1. View Seats for a Screen");
            System.out.println("2. Book Seats");
            System.out.println("3. Display Booked Seats for a Screen");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewSeats(scanner);
                    break;
                case 2:
                    bookSeat(scanner);
                    break;
                case 3:
                    displayBookedSeats(scanner);
                    break;
                case 4:
                    System.out.println("Thank you for using the booking system!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    // Display seating arrangement for a specific screen
    public static void viewSeats(Scanner scanner) {
        int screen = selectScreen(scanner);
        System.out.println("\nSeating Arrangement for " + movies[screen - 1] + " (Screen " + screen + ")");
        System.out.println("O = Available, X = Booked:");
        for (int i = 0; i < ROWS; i++) {
            System.out.print("Row " + (i + 1) + ": ");
            for (int j = 0; j < SEATS_PER_ROW; j++) {
                System.out.print(seats[screen - 1][i][j] ? "X " : "O ");
            }
            System.out.println();
        }
    }

    // Book multiple seats
    public static void bookSeat(Scanner scanner) {
        int screen = selectScreen(scanner);

        System.out.print("Enter the number of seats to book: ");
        int numberOfSeats = scanner.nextInt();

        for (int n = 1; n <= numberOfSeats; n++) {
            System.out.println("Booking seat " + n + ":");
            System.out.print("Enter row number (1 to " + ROWS + "): ");
            int row = scanner.nextInt();
            System.out.print("Enter seat number (1 to " + SEATS_PER_ROW + "): ");
            int seat = scanner.nextInt();

            if (row < 1 || row > ROWS || seat < 1 || seat > SEATS_PER_ROW) {
                System.out.println("Invalid row or seat number. Please try again.");
                n--; // Retry the current seat booking
            } else if (seats[screen - 1][row - 1][seat - 1]) {
                System.out.println("Seat is already booked. Please choose another seat.");
                n--; // Retry the current seat booking
            } else {
                seats[screen - 1][row - 1][seat - 1] = true;
                System.out.println("Seat successfully booked for " + movies[screen - 1] + " (Screen " + screen + ")");
            }
        }
    }

    // Display all booked seats for a specific screen
    public static void displayBookedSeats(Scanner scanner) {
        int screen = selectScreen(scanner);
        System.out.println("\nBooked Seats for " + movies[screen - 1] + " (Screen " + screen + "):");
        boolean anyBooked = false;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < SEATS_PER_ROW; j++) {
                if (seats[screen - 1][i][j]) {
                    System.out.println("Row " + (i + 1) + " Seat " + (j + 1));
                    anyBooked = true;
                }
            }
        }
        if (!anyBooked) {
            System.out.println("No seats are booked yet.");
        }
    }

    // Utility to select a screen
    public static int selectScreen(Scanner scanner) {
        System.out.println("\nSelect a Screen:");
        for (int i = 0; i < SCREENS; i++) {
            System.out.println((i + 1) + ". " + movies[i]);
        }
        System.out.print("Enter screen number (1 to " + SCREENS + "): ");
        int screen = scanner.nextInt();
        if (screen < 1 || screen > SCREENS) {
            System.out.println("Invalid screen number. Please try again.");
            return selectScreen(scanner);
        }
        return screen;
    }
}