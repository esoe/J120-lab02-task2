package ru.molokoin.core;

import ru.molokoin.core.Command.CommandBulder;

/**
 * Преобразует текстовую строку скрипта в объект команды
 */
public class ScriptReader {
    private String script;

    public ScriptReader(String script){
        this.script = script;
    }
    /**
     * Формирование объекта Command строки скрипта
     * @param script
     * @return
     */
    public Command read(String script){
        Command.CommandBulder cmd = new CommandBulder(script);
        int index = 0;//текущий элемент строки, на котором идет чтение скрипта
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
         * чтение команды поэлементно
         * формирование результирующей строки
         */
        char [] chars = script.toCharArray();
        while (index < chars.length){
            char c = chars[index];
            // Syntax current = 
            // switch (Syntax.) {
            //     case c:
                    
            //         break;
            
            //     default:
            //         break;
            }


            // switch Syntax

        // }
        
            
        return new Command(cmd);
    }

    
}
