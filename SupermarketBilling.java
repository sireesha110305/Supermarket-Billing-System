import java.util.Scanner;

class SupermarketBilling {
    static Scanner sc = new Scanner(System.in);
    static double totalBill = 0;

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n--- Supermarket Billing System ---");
            System.out.println("1. Add Item");
            System.out.println("2. View Total Bill");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    viewBill();
                    break;
                case 3:
                    System.out.println("Thank you! Visit again.");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        } while (choice != 3);
    }

    static void addItem() {
        System.out.print("Enter item price: ");
        double price = sc.nextDouble();
        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        totalBill += price * qty;
        System.out.println("Item Added Successfully");
    }

    static void viewBill() {
        System.out.println("Total Bill Amount: ₹" + totalBill);
    }
}