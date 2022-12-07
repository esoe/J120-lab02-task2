package ru.molokoin;

import java.util.ArrayList;
import java.util.HashMap;

import ru.molokoin.core.Parcer;

public class Script {
    private ArrayList<String> commands;
    private HashMap <String, Integer> varlist;

    Script(String scriptString){
        varlist = new HashMap<>();
        commands = new ArrayList<>();
        /**
         * Заполняем список commands
         * по хорошему, сразу получать в виде списка данные,
         * без прогонки лишнего цикла.
         */
        Parcer parcer = new Parcer(scriptString);
        for (String line : parcer.getCmd()) {
            commands.add(line);
        }
    }
    
    /**
     * обработка строки скрипта с командой set<p>
     * Убираем пробелы в строке = это не информативные символы<p>
     * и исключаем при этом возможные ошибки пользователя при составлении текста скрипта (больше он пробелов впечатал или меньше)<p>
     * <p>
     * Знак равенства в выражении только один, поэтому смело<p>
     * делим выражние на две части (*.split("=")):<p>
     * - "Левая часть выражения" до знака равно "="<p>
     * - "Правая часть выражения" после знака равно "="<p>
     * <p>
     * Обрабатываем левую часть выражения:<p>
     * - откидываем наименование команды "set", а лучше строку уже передавать без нее<p>
     * остается только наименование переменной, которое можно записать в качестве ключа в varlist<p>
     * <p>
     * Обрабатываем правую часть выражения (в итоге получаем одно целочисленное значение или сообщение об ошибке скрипта).<p>
     * Основные исходные:<p>
     * - справа могут быть только переменные пользователя (vars), целочисленные значения (int) и виды математических операций (+/-)<p>
     * Поэтому можно разбить строку (split) по видам применимых математических операций (+/-)<p>
     * в полученном  массиве останутся только наименования пользовательских перменных, начинающиеся с "$", или целочисленные значения.<p>
     * - значения переменных запрашиваем в карте<p>
     * - целочисленные значения берем из буфера intValue<p>
     * <p>
     * Обработали первое значение, если второго нет то вернули резултат<p>
     * если есть второе, обработали второе значение, провели указанную в скрипте операцию,<p>
     * результат записали в буфер и проверили наличие следующего значения ...<p>
     * результат вычислений записали в карту<p>
     * 
     * 
     * @param setString
     */
    public void set(String setString){
        //
    }
}
