package com.company.tools;

import com.company.exceptions.InvalidBidException;
import com.company.model.Action;
import com.company.model.Bid;
import com.company.model.Direction;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class BidHandlerTest {

    @Test(expected = InvalidBidException.class)
    public void testAddActionWithSameId() throws CloneNotSupportedException, InvalidBidException {
        List<Bid> bids = new LinkedList<>();
        bids.add(new Bid(1, "tool", Action.ADD, Direction.BUY, 100, 10));
        bids.add(new Bid(1, "tool", Action.ADD, Direction.SELL, 200, 10));

        BidHandler.getHandledBids(bids);
    }

    @Test(expected = InvalidBidException.class)
    public void testCancelActionWithoutAdd() throws CloneNotSupportedException, InvalidBidException {
        List<Bid> bids = new LinkedList<>();
        bids.add(new Bid(1, "tool", Action.CANCEL, Direction.BUY, 100, 10));

        BidHandler.getHandledBids(bids);
    }

    @Test(expected = InvalidBidException.class)
    public void testCancelActionCancelledBid() throws CloneNotSupportedException, InvalidBidException {
        List<Bid> bids = new LinkedList<>();
        bids.add(new Bid(1, "tool", Action.ADD, Direction.BUY, 100, 10));
        bids.add(new Bid(1, "tool", Action.CANCEL, Direction.BUY, 100, 10));
        bids.add(new Bid(1, "tool", Action.CANCEL, Direction.BUY, 100, 10));

        BidHandler.getHandledBids(bids);
    }

    @Test(expected = InvalidBidException.class)
    public void testUpdateActionWithoutAdd() throws CloneNotSupportedException, InvalidBidException {
        List<Bid> bids = new LinkedList<>();
        bids.add(new Bid(1, "tool", Action.EXECUTE, Direction.BUY, 100, 10));

        BidHandler.getHandledBids(bids);
    }

    @Test(expected = InvalidBidException.class)
    public void testUpdateActionCancelledBid() throws CloneNotSupportedException, InvalidBidException {
        List<Bid> bids = new LinkedList<>();
        bids.add(new Bid(1, "tool", Action.ADD, Direction.BUY, 100, 10));
        bids.add(new Bid(1, "tool", Action.CANCEL, Direction.BUY, 100, 10));
        bids.add(new Bid(1, "tool", Action.EXECUTE, Direction.BUY, 100, 20));

        BidHandler.getHandledBids(bids);
    }

    @Test
    public void positiveTest() throws CloneNotSupportedException, InvalidBidException {
        List<Bid> bids = new LinkedList<>();
        bids.add(new Bid(1, "tool", Action.ADD, Direction.BUY, 100, 10));
        bids.add(new Bid(2, "tool", Action.ADD, Direction.BUY, 100, 15));
        bids.add(new Bid(1, "tool", Action.EXECUTE, Direction.BUY, 100, 8));
        bids.add(new Bid(2, "tool", Action.CANCEL, Direction.BUY, 100, 15));
        bids.add(new Bid(3, "tool", Action.ADD, Direction.BUY, 100, 13));

        List<Bid> actualBids = BidHandler.getHandledBids(bids);

        List<Bid> expectedBids = new LinkedList<>();
        expectedBids.add(new Bid(1, "tool", Action.ADD, Direction.BUY, 100, 10));
        expectedBids.add(new Bid(2, "tool", Action.ADD, Direction.BUY, 100, 25));
        expectedBids.add(new Bid(1, "tool", Action.EXECUTE, Direction.BUY, 100, 23));
        expectedBids.add(new Bid(2, "tool", Action.CANCEL, Direction.BUY, 100, 15));
        expectedBids.add(new Bid(3, "tool", Action.ADD, Direction.BUY, 100, 21));

        for (int i = 0; i < expectedBids.size(); i++) {
            Assert.assertEquals(expectedBids.get(i).getVolume(), actualBids.get(i).getVolume());
        }
    }
}
