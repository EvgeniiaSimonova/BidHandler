package com.company.tools;

import com.company.model.Action;
import com.company.model.Bid;
import com.company.model.Direction;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class BidFormatterTest {

    @Test
    public void testFormatting() {
        List<Bid> bids = new LinkedList<>();
        bids.add(new Bid(3, "sbrf", Action.EXECUTE, Direction.SELL, 100, 30));
        bids.add(new Bid(5, "sbrf", Action.ADD, Direction.BUY, 130, 40));

        List<String> actualStrings = BidFormatter.getFileStrings(bids);

        List<String> expectedStrings = new LinkedList<>();
        expectedStrings.add("��������: �������� �������, ����������: sbrf, �����������: sell, ����: 100, �����: 30");
        expectedStrings.add("��������: �������� �������, ����������: sbrf, �����������: buy, ����: 130, �����: 40");

        for (int i = 0; i < expectedStrings.size(); i++) {
            String actual = actualStrings.get(i);
            String expected = expectedStrings.get(i);

            Assert.assertEquals(expected, actual);
        }
    }
}
