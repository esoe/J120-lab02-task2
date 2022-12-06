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
        private CommandBulder cmd;

    public Command(CommandBulder cmd){
        this.cmd = cmd;
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
     * чтение команды поэлементно
     * формирование результирующей строки
     */





    public Command parce(){
        char [] chars = getScript().toCharArray();
        int index = 0;
        /**
         * определили тип команды print/set
         */
        if (script.startsWith("print")){
            cmd.setType(Mode.PRINT);
            index += 5;//переместили индекс в позицию после типа команды
        }
        if (script.startsWith("set")){
            cmd.setType(Mode.SET);
            index += 3;//переместили индекс в позицию после типа команды
        }
        /**
         * читаем дальше, после наименования команды должен стоять пробел
         */
        System.out.println(">>>>>>> Синтаксис <<<<<<<<");
        while (index < chars.length){
            char c = chars[index];
            if (c == Syntax.QUOTE.getChar()){
                cmd.setQuoted(!isQuoted);
            }
            if (cmd.isQuoted && (c != Syntax.QUOTE.getChar())){
                System.out.print(c);//надо писать в переменную
            }
            if(!isQuoted){
                if (c == Syntax.DOLLAR.getChar()){
                    isDollared = true;
                    isName = true;
                }
                if (isDollared){

                    //$name = value
                }
            }
            index++;
        }
        return new Command(cmd);
    }
    /**
     * читаем соманду сплитами
     */
    public void read(){
        /**
         * обработка по пробелам
         */
        System.out.println("======= Обработка по пробелам =======");
        String[] s = script.split(" ");
        //setType(s[0]);//определили тип команды (print/set)
        System.out.println("Содержимое команды: ");
        for (String string : s) {
            System.out.println(string);
        }
        /**
         * обработка строковых переменных (по ковычкам)
         * делим команду по ковычкам,
         * содержимое пишем в список
         * - первая запись всегда будет без ковычек (содержит тип команды)
         * - вторая и последующие четные всегда являются строковыми переменными
         * 
         */
        System.out.println("======= Обработка по ковычкам =======");
         s = script.split("\"");
        for (String string : s) {
            System.out.println(string);
        }
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

    public void out() {
        System.out.println(getResult());
    }

}
