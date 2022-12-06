package ru.molokoin;

import java.io.File;

import ru.molokoin.core.Command;
import ru.molokoin.core.Parcer;
/**
 * Класс, запускает выполнение скриптового языка
 */
public class Runner {
    /**
     * Метод запускает выполнение программы "Scripting"
     * @param args
     */
    public static void main(String[] args) {
        /**
         * приветствие пользователя в программе
         */
        Runner.hello();

        /**
         * Инициализация данных файла со скриптом UseConsole
         */
        String scriptString = "";
        String input = UseConsole.setSourcePath();
        while (!input.equals("\\q")){
            System.out.println("Указано пользователем: " + input);
            File source = new File(input);
            //получаем данные из файла, для анализа
            scriptString = StorageTools.get(source);
            System.out.println("Программой получен скрипт:");
            System.out.println("-----------------------------------");
            System.out.println(scriptString);
            System.out.println("-----------------------------------");
            System.out.println("Обработка скрипта ...");
            /**
             * Обработка данных со скриптом /Parcer
             * - идентификация команд / одна строка - одна команда
             * - запуск исполнения команд/ build result
             */
            Parcer parcer = new Parcer(scriptString);
            for (String line : parcer.getCmd()) {
                System.out.println(line);
                Command cmd = new Command.CommandBulder(line).build();
                cmd.parce().out();
                System.out.println("command type: " + cmd.getType());
            }
            /**
             * предлагаем пользователю запустить новый скрипт на выполнение
             */
            input = UseConsole.setSourcePath();
        }

        /**
         * Описание скриптовых команд - семантика языка
         * - строковые значения/ ""
         * - целочисленные значения/ n
         * - сложение/ +
         * - вычитание/ -
         * - создание переменных/ set $n = 
         * - использование переменных/ = $n
         * - печать / print
         */

        


        /**
         * Выполнение команд скрипта
         */
    }

    /**
     * Метод выводит в консоль:<p>
     * - приветствие пользователю<p>
     * - руководство по использованию программы MANUAL.man<p>....
     * @param readme
     */
    public static void hello(){
        /**
         * доработать:
         * - чтобы путь брался из *.property файла;
         */
        File readme = new File("MANUAL.man");
        System.out.println("Scripting (интерпретатор скриптового языка) запущен ...");
        System.out.println("Прежде чем приступить к использованию пограммы, ОБЯЗВТЕЛЬНО_ОЗНАКОМЬТЕСЬ:");
        System.out.println(StorageTools.get(readme));
    }
}
