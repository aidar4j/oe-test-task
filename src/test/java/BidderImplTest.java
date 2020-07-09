import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class BidderImplTest {

    private Bidder bidder;

    @Before
    public void init() {
        this.bidder = new BidderImpl();
    }

    @Test
    public void placeBid_withNullPropertiesInClass_shouldReturn0() {
        int ownBid = bidder.placeBid();
        assertEquals(0, ownBid);
    }

    @Test
    public void placeBid_withInitializedNoCashAndNoQuantity_shouldReturn0() {
        bidder.init(0, 0);
        int ownBid = bidder.placeBid();
        assertEquals(0, ownBid);
    }

    @Test
    public void placeBid_withInitializedQuantity2_shouldReturnAllCash() {
        bidder.init(2, 38945);
        int ownBid = bidder.placeBid();
        assertEquals(38945, ownBid);
    }

    @Test
    public void placeBid_withInitializedProperties_shouldReturnFirstBid() {
        bidder.init(10, 100);
        int numberOfSets = 10 / 2;
        int ownBid = bidder.placeBid();
        assertTrue(0 <= ownBid && ownBid <= 100 / numberOfSets);
    }

    @Test
    public void placeBid_with2InitializedProperties_shouldReturnFirstBid() {
        bidder.init(10, 100);
        int numberOfSets = 10 / 2;
        int ownBid = bidder.placeBid();
        assertTrue(0 <= ownBid && ownBid <= 100 / numberOfSets);
    }

}
