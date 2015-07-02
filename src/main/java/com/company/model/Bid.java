package com.company.model;

import com.company.tools.Encoder;

import java.io.UnsupportedEncodingException;

public class Bid implements Cloneable {

    private Integer id;
    private String tool;
    private Action action;
    private Direction direction;
    private Integer price;
    private Integer volume;

    public Bid(Integer id, String tool, Action action, Direction direction, Integer price, Integer volume) {
        this.id = id;
        this.tool = tool;
        this.action = action;
        this.direction = direction;
        this.price = price;
        this.volume = volume;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    @Override
    public Bid clone() throws CloneNotSupportedException {
        Bid bid = (Bid) super.clone();

        bid.setId(new Integer(this.getId()));
        bid.setTool(new String(this.getTool()));
        bid.setPrice(new Integer(this.getPrice()));
        bid.setVolume(new Integer(this.getVolume()));

        return bid;
    }

    @Override
    public String toString() {
        try {
            return Encoder.convert("Заявка № " + id
                    + ". Действие: " + action.getOutputValue()
                    + ". Направление: " + direction.getTitle()
                    + ". Инструмент: " + tool
                    + ". Цена: " + price
                    + ". Объем: " + volume + ".");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
