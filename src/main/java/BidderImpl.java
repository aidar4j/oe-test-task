import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.rank.Median;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BidderImpl implements Bidder {
    private int productionQuantityLeft;
    private int amountOfSets; // production quantity divided to 2
    private int initialCash;
    private int ownCashLeft;
    private int opponentCashLeft;
    private int ownPurchasedProduction;
    private int opponentPurchasedProduction;
    private List<Integer> winningBids = new ArrayList<>();
    private Random random = new Random();

    @Override
    public void init(int quantity, int cash) {
        this.productionQuantityLeft = quantity;
        this.initialCash = cash;
        this.ownCashLeft = cash;
        this.opponentCashLeft = cash;
        this.amountOfSets = quantity / 2;
        this.opponentPurchasedProduction = 0;
        this.ownPurchasedProduction = 0;
    }

    @Override
    public int placeBid() {
        //if there are no cash left or no number of sets, then bid 0
        if (ownCashLeft == 0 || amountOfSets == 0) {
            return 0;
        }

        //if there are 2 productionQuantity, then bid all cash
        if (amountOfSets == 1) {
            return ownCashLeft;
        }

        //first bid is random of initialized cash / numberOfSets
        if (winningBids.size() == 0) {
            return random.nextInt(initialCash / amountOfSets);
        }

        //second and next bids based on mean (average number of winning bids)
        int mean = (int) new Mean().evaluate(winningBids.stream().mapToDouble(d -> d).toArray());

        //second and next bids based on median (middle element of winning bids)
        //int median = (int) new Median().evaluate(winningBids.stream().mapToDouble(d -> d).toArray());

        //advantage is additional value to mean, that is randomly generated from 40% of initialCash / amountOfSets
        int advantage = random.nextInt((int)(initialCash / amountOfSets * (40 / 100.0f)));
        int nextBid = mean + advantage;

        //place the min cause we cannot bid more cash than we have
        return Math.min(nextBid, ownCashLeft);
    }

    @Override
    public void bids(int own, int other) {
        if (own > other) {
            ownPurchasedProduction += 2;
            productionQuantityLeft -= 2;
            winningBids.add(own);
        } else if (own == other) {
            ownPurchasedProduction++;
            opponentPurchasedProduction++;
            productionQuantityLeft -= 2;
        } else {
            opponentPurchasedProduction += 2;
            productionQuantityLeft -= 2;
            winningBids.add(other);
        }

        ownCashLeft -= own;
        opponentCashLeft -= other;
        amountOfSets--;

        printInfo(own, other);
    }

    private void printInfo(int own, int other) {
        System.out.format("Own bid: %d\n", own);
        System.out.format("Opponent bid: %d\n", other);
        System.out.format("Production quantity left: %d\n", productionQuantityLeft);
        System.out.format("Own cash left: %d\n", ownCashLeft);
        System.out.format("Opponent cash left: %d\n", opponentCashLeft);
        System.out.format("Own purchased production: %d\n", ownPurchasedProduction);
        System.out.format("Opponent purchased production: %d\n", opponentPurchasedProduction);
        System.out.println();
    }
}
