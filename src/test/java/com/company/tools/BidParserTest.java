package com.company.tools;

import com.company.model.Action;
import com.company.model.Bid;
import com.company.model.Direction;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class BidParserTest {

    @Test
    public void testParsing() {
        List<String> stringList = new LinkedList<>();
        stringList.add("Заявка номер: 7, инструмент: sbrf, действие: add, направление: sell, цена: 200, объем: 400");
        stringList.add("Заявка номер: 8, инструмент: sbrf, действие: exec, направление: buy, цена: 300, объем: 300");

        List<Bid> actualBids = BidParser.getBidList(stringList);

        List<Bid> expectedBids = new LinkedList<>();
        expectedBids.add(new Bid(7, "sbrf", Action.ADD, Direction.SELL, 200, 400));
        expectedBids.add(new Bid(8, "sbrf", Action.EXECUTE, Direction.BUY, 300, 300));

        for (int i = 0; i < expectedBids.size(); i++) {
            Bid actualBid = actualBids.get(i);
            Bid expectedBid = expectedBids.get(i);

            Assert.assertEquals(expectedBid.getId(), actualBid.getId());
            Assert.assertEquals(expectedBid.getTool(), actualBid.getTool());
            Assert.assertEquals(expectedBid.getAction(), actualBid.getAction());
            Assert.assertEquals(expectedBid.getDirection(), actualBid.getDirection());
            Assert.assertEquals(expectedBid.getPrice(), actualBid.getPrice());
            Assert.assertEquals(expectedBid.getVolume(), actualBid.getVolume());
        }
    }
}
