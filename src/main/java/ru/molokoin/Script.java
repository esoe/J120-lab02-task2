package ru.molokoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;

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
        /**
         * выполняем скрипт:
         * перебираем команды, запускаем выполнение строки в метод set() или в print()
         */
        for (String cmd : commands) {
            if (cmd.startsWith("set")){
                set(cmd);
            }else{
                print(cmd);
            }
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
        System.out.println("Прочитана команда \"set\" >>> " + setString);
        //откидываем из строки название команды, дальше оно не нужно
        String current = setString.substring(3);//set содержит 3 символа
        System.out.println("Откинули название команды: " + current);

        //убираем пробелы, все пробельные символы
        current = current.replaceAll("\\s", "");
        System.out.println("Убрали пробелы: " + current);

        /**
         * разбиваем строку на правую и левую части по знаку "="
         * - для использования в скрипте строковых выражений такой подход не пойдет,
         * в строке может оказаться знак "=".
         * - но в нашем случае, когда в команде set  могут содержаться только наименования переменных и целочисленные значения - нормально.
         */
        String[] parts = current.split("=");
        String left = parts[0];//можно в таком виде писать в карту пользовательских переменных "varlist"
        String right = parts[1];
        System.out.println("Левая часть выражения: " + left);
        System.out.println("Правая часть выражения: " + right);

        /**
         * Левая часть выражения готова к записи в карту,
         * обрабатываем правую часть
         */
        parts = right.split("[+-]+");//тут я теряю операции, которые надо провести с числами
        System.out.println("Разбили правую часть по знакам +/- :" + Arrays.toString(parts));
        /**
         * если в правой части выражения, в массиве parts содержится только один элемент,
         * то нам надо получить его Integer значение и записать вместе с ключем "left" в карту
         * а если больше одного, то рекурсивно начать вычисления правой части
         */
        int index = 0;
        Integer result = 0;
        /**
         * Задаем начальное значение result
         * перед ним отсутствует знак, потому мы не можем провести эту операцию в цикле.
         * либо можно в начало списка знаков добавить "+"
         */
        //если строка начинается с "$", берем значение из карты и добавляем к результату
        if (parts[index].startsWith("$")){
            result = varlist.get(parts[index]);
            index++;
        }else{
            result = Integer.valueOf(parts[index]);
            index++;
        }
        System.out.println("Извлекли первый блок правой части выражения: " + result);
        
        /**
         * надо разобраться со знаками сложения и вычитания и после этого приступать к обработке остальных блоков выражения
         */
        String marks = right.replaceAll("[0-9a-z$]", "");//строка содержащая знаки операций с блоками выражения
        System.out.println("Знаки использованные в выражении: " + marks);

        /**
         * обработка второго и последующего плоков
         * -добавить обработку знаков +/-
         */
        while (index < parts.length){
            if (parts[index].startsWith("$")){
                if ("+".charAt(0) == marks.charAt(index-1)){
                    result = result + varlist.get(parts[index]);
                    index++;

                }//"-"
                else{
                    result = result - varlist.get(parts[index]);
                    index++;
                }
            }else{
                if ("+".charAt(0) == marks.charAt(index-1)){
                    result += Integer.valueOf(parts[index]);
                    index++;
                }//"-"
                else{
                    result -= Integer.valueOf(parts[index]);
                    index++;
                }
            }
            System.out.println("Обработка блока: " + index);
        }
        System.out.println("Результат вычисления правой части выражения: " + result);

        /**
         * пишем результат вычислений в varlist
         */
        System.out.println("Записываем результат вычисления в varlist...");
        varlist.put(left, result);
        System.out.println("key > " + left + " : " + "value > " + varlist.get(left));
    }
    public void print(String setString){
        System.out.println("Прочитана команда \"read\" >>> " + setString);
    }
}
