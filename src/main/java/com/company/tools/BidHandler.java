package com.company.tools;

import com.company.exceptions.InvalidBidException;
import com.company.model.Action;
import com.company.model.Bid;

import java.util.*;

public class BidHandler {

    public static List<Bid> getHandledBids(List<Bid> bids) throws InvalidBidException, CloneNotSupportedException {
        Map<Integer, List<Bid>> allPreviousBids = new HashMap<>();
        Map<Integer, Integer> currentVolumes = new HashMap<>();

        for (Bid bid: bids) {
            validateBid(allPreviousBids, bid);
            Integer price = bid.getPrice();
            if (!allPreviousBids.containsKey(price)) {
                List<Bid> bidList = new LinkedList<>();
                bidList.add(bid.clone());

                allPreviousBids.put(price, bidList);
                currentVolumes.put(price, bid.getVolume());
            } else {
                Bid unchangedBid = bid.clone();
                Integer volume = currentVolumes.get(price);
                List<Bid> inputBidsByPrice = allPreviousBids.get(price);

                if (Action.ADD.equals(bid.getAction())) {
                    volume = volume + bid.getVolume();
                    bid.setVolume(volume);
                } else if (Action.CANCEL.equals(bid.getAction())) {
                    volume = volume - bid.getVolume();
                } else {
                    Bid previousBid = null;
                    Collections.reverse(inputBidsByPrice);
                    for (Bid b: inputBidsByPrice) {
                        if (b.getId().equals(bid.getId())) {
                            previousBid = b;
                            break;
                        }
                    }
                    Collections.reverse(inputBidsByPrice);
                    volume = volume - previousBid.getVolume() + bid.getVolume();
                    bid.setVolume(volume);
                }

                inputBidsByPrice.add(unchangedBid);
                currentVolumes.remove(price);
                currentVolumes.put(price, volume);
            }

        }

        return bids;
    }

    private static void validateBid(Map<Integer, List<Bid>> previousBids, Bid currentBid) throws InvalidBidException {
        List<Bid> bidsWithCurrentPrice = previousBids.get(currentBid.getPrice());
        List<Bid> bidsWithAllPrices = new LinkedList<>();
        for (Integer key: previousBids.keySet()) {
            bidsWithAllPrices.addAll(previousBids.get(key));
        }
        Integer id = currentBid.getId();

        if (Action.ADD.equals(currentBid.getAction())) {
            if (findBidByParameters(bidsWithAllPrices, currentBid.getId()) != null) {
                throw new InvalidBidException("It is impossible to add bid, because, the bid with id '"
                        + id + "' already exists.");
            }
        } else if (Action.CANCEL.equals(currentBid.getAction())) {
            if (findBidByParameters(bidsWithCurrentPrice, currentBid.getId(), Action.ADD) == null) {
                throw new InvalidBidException("It is impossible to cancel bid, because the bid with id '"
                        + id + "' was not found.");
            }

            if (findBidByParameters(bidsWithCurrentPrice, currentBid.getId(), Action.CANCEL) != null) {
                throw new InvalidBidException("It is impossible to cancel bid, because the bid with id '"
                        + id + "' was already cancelled.");
            }
        } else {
            if (findBidByParameters(bidsWithCurrentPrice, currentBid.getId(), Action.ADD) == null) {
                throw new InvalidBidException("It is impossible to update bid, because the bid with id '"
                        + id + "' was not found.");
            }

            if (findBidByParameters(bidsWithCurrentPrice, currentBid.getId(), Action.CANCEL) != null) {
                throw new InvalidBidException("It is impossible to update bid, because the bid with id '"
                        + id + "' was already cancelled.");
            }
        }
    }

    /**
     * Returns the value from List which satisfies conditions: id and action.
     * @param bids the list for search
     * @param id the parameter of search
     * @param action the parameter of search
     * @return the value to which the specified parameters satisfy or null if the value was not found
     */
    private static Bid findBidByParameters(List<Bid> bids, Integer id, Action action) {
        Bid desiredBid = null;
        if (bids != null) {
            for (Bid bid: bids) {
                if (bid.getId().equals(id) && action.equals(bid.getAction())) {
                    desiredBid = bid;
                    break;
                }
            }
        }

        return desiredBid;
    }

    /**
     * Returns the value from List which satisfies conditions: id.
     * @param bids the list for search
     * @param id the parameter of search
     * @return the value to which the specified parameters satisfy or null if the value was not found
     */
    private static Bid findBidByParameters(List<Bid> bids, Integer id) {
        Bid desiredBid = null;
        if (bids != null) {
            for (Bid bid: bids) {
                if (bid.getId().equals(id)) {
                    desiredBid = bid;
                    break;
                }
            }
        }

        return desiredBid;
    }
}
