package com.company.tools;

import com.company.exceptions.InvalidActionException;
import com.company.exceptions.InvalidDirectionException;
import com.company.model.Action;
import com.company.model.Bid;
import com.company.model.Direction;

import java.util.LinkedList;
import java.util.List;

public class BidParser {

    public static List<Bid> getBidList(List<String> fileStrings) {
        List<Bid> bids = new LinkedList<>();

        for (String fileString: fileStrings) {
            try {
                String[] fields = fileString.split(", ");
                Integer id = Integer.parseInt(fields[0].split(": ")[1]);
                String tool = fields[1].split(": ")[1];
                Action action = Action.getActionByInputValue(fields[2].split(": ")[1]);
                Direction direction = Direction.getDirectionByTitle(fields[3].split(": ")[1]);
                Integer price = Integer.parseInt(fields[4].split(": ")[1]);
                Integer volume = Integer.parseInt(fields[5].split(": ")[1]);

                bids.add(new Bid(id, tool, action, direction, price, volume));

            } catch (InvalidActionException | InvalidDirectionException | NumberFormatException e) {
                e.printStackTrace();
            }
        }

        return bids;
    }
}
