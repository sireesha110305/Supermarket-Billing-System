import java.sql.*;
import java.util.Scanner;

class RailwayTicketBooking
{
    static Connection con;

    // Connect to DB
    static void connect() throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:C:/SqlLite/railway.db");
    }

    // Book Ticket
    static void bookTicket(Scanner sc) throws Exception
    {
        String sql = "INSERT INTO RailwayTicket VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        System.out.print("Ticket Number: ");
        ps.setInt(1, sc.nextInt());
        sc.nextLine();

        System.out.print("Passenger Name: ");
        ps.setString(2, sc.nextLine());

        System.out.print("Source: ");
        ps.setString(3, sc.nextLine());

        System.out.print("Destination: ");
        ps.setString(4, sc.nextLine());

        ps.executeUpdate();
        System.out.println("Ticket booked successfully.\n");
        ps.close();
    }

    // View Tickets
    static void viewTickets() throws Exception
    {
        String sql = "SELECT * FROM RailwayTicket";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        System.out.println("\nTicket  Name     Source     Destination");
        System.out.println("----------------------------------------");

        while (rs.next())
        {
            System.out.println(
                rs.getInt(1) + "   " +
                rs.getString(2) + "   " +
                rs.getString(3) + "   " +
                rs.getString(4)
            );
        }
        rs.close();
        ps.close();
    }

    // Search Ticket
    static void searchTicket(Scanner sc) throws Exception
    {
        String sql = "SELECT * FROM RailwayTicket WHERE TicketNo=?";
        PreparedStatement ps = con.prepareStatement(sql);

        System.out.print("Enter Ticket Number: ");
        ps.setInt(1, sc.nextInt());

        ResultSet rs = ps.executeQuery();

        if (rs.next())
        {
            System.out.println(rs.getInt(1) + " " +
                               rs.getString(2) + " " +
                               rs.getString(3) + " " +
                               rs.getString(4));
        }
        else
        {
            System.out.println("Ticket not found.");
        }

        rs.close();
        ps.close();
    }

    // Update Destination
    static void updateDestination(Scanner sc) throws Exception
    {
        String sql = "UPDATE RailwayTicket SET Destination=? WHERE TicketNo=?";
        PreparedStatement ps = con.prepareStatement(sql);

        sc.nextLine();
        System.out.print("New Destination: ");
        ps.setString(1, sc.nextLine());

        System.out.print("Ticket Number: ");
        ps.setInt(2, sc.nextInt());

        ps.executeUpdate();
        System.out.println("Destination updated.\n");
        ps.close();
    }

    // Cancel Ticket
    static void cancelTicket(Scanner sc) throws Exception
    {
        String sql = "DELETE FROM RailwayTicket WHERE TicketNo=?";
        PreparedStatement ps = con.prepareStatement(sql);

        System.out.print("Ticket Number: ");
        ps.setInt(1, sc.nextInt());

        ps.executeUpdate();
        System.out.println("Ticket cancelled.\n");
        ps.close();
    }

    public static void main(String[] args) throws Exception
    {
        connect();
        Scanner sc = new Scanner(System.in);
        int choice;

        do
        {
            System.out.println("\n--- Railway Ticket Booking System ---");
            System.out.println("1. Book Ticket");
            System.out.println("2. View Tickets");
            System.out.println("3. Search Ticket");
            System.out.println("4. Update Destination");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. Exit");
            System.out.print("Choose: ");
            choice = sc.nextInt();

            switch (choice)
            {
                case 1: bookTicket(sc); break;
                case 2: viewTickets(); break;
                case 3: searchTicket(sc); break;
                case 4: updateDestination(sc); break;
                case 5: cancelTicket(sc); break;
                case 6: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice");
            }
        }
        while (choice != 6);

        con.close();
        sc.close();
    }
}
