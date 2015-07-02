package com.company;

import com.company.exceptions.InvalidBidException;
import com.company.model.Bid;
import com.company.tools.*;

import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException, InvalidBidException {
        Reader reader = new Reader(new File("input.txt"));
        List<Bid> bids = BidParser.getBidList(reader.getStringList());
        List<Bid> handledBids = BidHandler.getHandledBids(bids);
        List<String> strings = BidFormatter.getFileStrings(handledBids);
        Writer writer = new Writer(new File("output.txt"));
        writer.write(strings);
    }
}
