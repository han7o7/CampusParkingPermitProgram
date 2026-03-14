import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PricingCalculator calculator = new PricingCalculator();
        Receipt receipt = new Receipt();

        System.out.println("Campus Parking Permit Program");

        while (true) {
            try {
                PermitType permitType = readPermitType(scanner);
                VehicleType vehicleType = readVehicleType(scanner);
                boolean carpool = readCarpool(scanner);
                int months = readMonths(scanner);

                PermitSelection selection = new PermitSelection(permitType, vehicleType, carpool, months);
                PricingResult result = calculator.calculate(selection);

                System.out.println(receipt.format(selection, result));
                break;

            } catch (InvalidSelectionException ex) {
                System.out.println("Error: " + ex.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        scanner.close();
    }

    private static PermitType readPermitType(Scanner scanner) {
        System.out.print("Enter permit type (Resident or Commuter): ");
        String input = scanner.nextLine().trim().toUpperCase();

        if (input.equals("RESIDENT")) {
            return PermitType.RESIDENT;
        } else if (input.equals("COMMUTER")) {
            return PermitType.COMMUTER;
        }

        throw new InvalidSelectionException("Invalid permit type. Enter Resident or Commuter.");
    }

    private static VehicleType readVehicleType(Scanner scanner) {
        System.out.print("Enter vehicle type (Car, SUV, Motorcycle): ");
        String input = scanner.nextLine().trim().toUpperCase();

        if (input.equals("CAR")) {
            return VehicleType.CAR;
        } else if (input.equals("SUV")) {
            return VehicleType.SUV;
        } else if (input.equals("MOTORCYCLE")) {
            return VehicleType.MOTORCYCLE;
        }

        throw new InvalidSelectionException("Invalid vehicle type. Enter Car, SUV, or Motorcycle.");
    }

    private static boolean readCarpool(Scanner scanner) {
        System.out.print("Carpool? (Y/N): ");
        String input = scanner.nextLine().trim().toUpperCase();

        if (input.equals("Y")) {
            return true;
        } else if (input.equals("N")) {
            return false;
        }

        throw new InvalidSelectionException("Invalid carpool choice. Enter Y or N.");
    }

    private static int readMonths(Scanner scanner) {
        System.out.print("Enter number of months (1-12): ");
        String input = scanner.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            throw new InvalidSelectionException("Months must be a whole number between 1 and 12.");
        }
    }
}
