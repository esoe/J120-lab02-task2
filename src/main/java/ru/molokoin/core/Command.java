package ru.molokoin.core;

import java.util.LinkedHashMap;

public class Command {
    private String result;
        private  String script;
        private Mode type;
        private boolean isQuoted = false;//активна ли область кавычек
        private boolean isDollared = false;//активна ли область считывания переменной
        private boolean isName = false;
        private boolean isValue = false;
        private StringBuilder current;//промежуточное значение имени переменной
        

    public Command(CommandBulder cmd){
        script = cmd.script;
        type = cmd.type;
        isQuoted = cmd.isQuoted;
        isDollared = cmd.isDollared;
        isName = cmd.isName;
        isValue = cmd.isValue;
        current = cmd.current;
        result = cmd.result;
    }

    /**
     * @return the script
     */
    public String getScript() {
        return script;
    }
    /**
     * @return the type
     */
    public Mode getType() {
        return type;
    }
    /**
     * @return the current
     */
    public StringBuilder getCurrent() {
        return current;
    }
    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }
    /**
     * @return the isQuoted
     */
    public boolean isQuoted() {
        return isQuoted;
    }
    /**
     * @return the isDollared
     */
    public boolean isDollared() {
        return isDollared;
    }
    /**
     * @return the isName
     */
    public boolean isName() {
        return isName;
    }
    /**
     * @return the isValue
     */
    public boolean isValue() {
        return isValue;
    }

    /**
     * Класс хранит параментры/состояния читаемой команды,
     * определяет какой символ строки как именно трактовать и что с ним делать
     */
    public static class CommandBulder{
        private String result;
        private  String script;
        private Mode type;
        private boolean isQuoted = false;//активна ли область кавычек
        private boolean isDollared = false;//активна ли область считывания переменной
        private boolean isName = false;
        private boolean isValue = false;
        private StringBuilder current;//промежуточное значение имени переменной
        /**
         * key - наименование пользовательской переменной
         * value - значение пользовательской переменной
         */
        private LinkedHashMap<String, String> vars;

        public CommandBulder(String script){
            setScript(script);
        }
        /**
         * @param type the type to set
         */
        public CommandBulder setType(Mode type) {
            this.type = type;
            return this;
        }
        /**
         * @param isQuoted the isQuoted to set
         */
        public CommandBulder setQuoted(boolean isQuoted) {
            this.isQuoted = isQuoted;
            return this;
        }
        /**
         * @param isDollared the isDollared to set
         */
        public CommandBulder setDollared(boolean isDollared) {
            this.isDollared = isDollared;
            return this;
        }
        /**
         * @param isName the isName to set
         */
        public CommandBulder setName(boolean isName) {
            this.isName = isName;
            return this;
        }
        /**
         * @param isValue the isValue to set
         */
        public CommandBulder setValue(boolean isValue) {
            this.isValue = isValue;
            return this;
        }
        /**
         * @param current the current to set
         */
        public CommandBulder setCurrent(StringBuilder current) {
            this.current = current;
            return this;
        }
        /**
         * @param script the script to set
         */
        public CommandBulder setScript(String script) {
            this.script = script;
            return this;
        }
        /**
         * @param result the result to set
         */
        public CommandBulder setResult(String result) {
            this.result = result;
            return this;
        }
        /**
         * обработка текста скрипта
         * @return
         */
        public Command build(){
            return new Command(this);
        }
    }
}
