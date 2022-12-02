package ru.molokoin;

import java.io.Console;

public class UseConsole implements ScriptingFace{

    /**
     * Задаем путь к файлу-источнику
     */
    public static String setSourcePath() {
        Console console = System.console();
        if (console == null) {
            System.err.println("Консоль не обнаружена ...");
            System.exit(1);
        }
        System.out.println("Для выхода из приложения введите команду: \\q");
        System.out.println("Для выполнения скрипта, укажите путь к источнику файла: ");
        /**
         * TODO обработать случаи, когда пользователь ввел текст не являющимся адресом или командой выхода
         * обрабтать исключения тут, а не в методе Storage.get()
         */
        String input = console.readLine();
        return input;
    }

    @Override
    public void setTargetPath() {
        // TODO Auto-generated method stub
    }
    
}
