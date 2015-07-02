package com.company.tools;

import com.company.model.Bid;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

public class BidFormatter {

    public static List<String> getFileStrings(List<Bid> bids) {
        List<String> fileStrings = new LinkedList<>();

        for (Bid bid: bids) {
            StringBuilder builder = new StringBuilder();

            try {
                builder.append("действие: ").append(bid.getAction().getOutputValue());
                builder.append(", инструмент: ").append(bid.getTool());
                builder.append(", направление: ").append(bid.getDirection().getTitle());
                builder.append(", цена: ").append(bid.getPrice());
                builder.append(", объем: ").append(bid.getVolume());

                fileStrings.add(builder.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return fileStrings;
    }
}
