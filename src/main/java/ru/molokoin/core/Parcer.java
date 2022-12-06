package ru.molokoin.core;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Класс:
 * - получает текстовый файл
 * - разбивает файл на строки (команды)
 * - разбивает строки на операторы
 */
public class Parcer {
    private String[] cmd;
    
    public Parcer(String script){
        cmd = script.split("\n");//разбили скрипт на строки-команды
    }
    /**
     * Выводит текущую команду в консоль
     * @param index
     */
    public void printCommand(int index){
        System.out.println(cmd[index]);
    }
    /**
     * @return the cmd
     */
    public String[] getCmd() {
        return cmd;
    }
    /**
     * @param cmd the cmd to set
     */
    public void setCmd(String[] cmd) {
        this.cmd = cmd;
    }
}
