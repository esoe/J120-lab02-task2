package ru.molokoin.core;

/**
 * space - пробел " "
 * subtraction - вычитание "-"
 * sum - сложение "+"
 * calculation - вычисление "="
 * quote - ковычки
 * dollar - "$"
 */
public enum Syntax {
    SPACE (" "),
    COMMA (","),
    SUBTRACTION ("-"),
    SUM ("+"),
    CALCULATION ("="),
    QUOTE("\""),
    DOLLAR("$");

    private String value;

    Syntax(String value){
        setValue(value);
    }
    public String geString(){
        return value;
    }
    public char getChar(){
        return value.charAt(0);
    }
    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
}
