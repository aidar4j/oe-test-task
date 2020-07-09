import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Bidder bidder = new BidderImpl();

        System.out.println("Enter the production quantity:");
        int quantity = scanner.nextInt();
        System.out.println("Enter the cash amount available to both parties:");
        int cash = scanner.nextInt();

        bidder.init(quantity, cash);

        for (int i = 0; i < quantity / 2; i++) {
            int own = bidder.placeBid();

            System.out.println("Your bid:");
            int consoleBid = scanner.nextInt();
            bidder.bids(own, consoleBid);
        }
    }

}
