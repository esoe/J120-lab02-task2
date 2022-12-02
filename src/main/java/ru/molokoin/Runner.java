package ru.molokoin;

import java.io.File;
/**
 * Класс, запускает выполнение скриптового языка
 */
public class Runner {
    /**
     * Метод запускает выполнение программы "Scripting"
     * @param args
     */
    public static void main(String[] args) {
        Runner.hello();
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
        System.out.println(Storage.get(readme));
    }
}
