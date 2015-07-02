package com.company.model;

import com.company.exceptions.InvalidActionException;
import com.company.tools.Encoder;

import java.io.UnsupportedEncodingException;

public enum Action {
    ADD("add", "добавить уровень"),
    EXECUTE("exec", "изменить уровень"),
    CANCEL("cancel", "удалить уровень");

    private String inputValue;
    private String outputValue;

    Action(String inputValue, String outputValue) {
        this.inputValue = inputValue;
        this.outputValue = outputValue;
    }

    public static Action getActionByInputValue(String inputValue) throws InvalidActionException {
        switch (inputValue) {
            case "add": return ADD;
            case "exec": return EXECUTE;
            case "cancel": return CANCEL;
        }

        throw new InvalidActionException("It is impossible to find Action by input value: " + inputValue);
    }

    public String getInputValue() {
        return inputValue;
    }

    public String getOutputValue() throws UnsupportedEncodingException {
        return Encoder.convert(outputValue);
    }
}
