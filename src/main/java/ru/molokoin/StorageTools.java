package ru.molokoin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StorageTools {
    /**
     * Метод возвращает в виде строки текст файла<p>
     * при чтении файлаидет проверка условий:<p>
     * - наличие ссылки на файл в переменной<p>
     * - наличие файла физически, по указанному адресу<p>
     * - доступность файла для чтения<p>
     * 
     * @param sourceFile
     * @return
     */
    public static String get(File sourceFile){
        String line = "";
        if((sourceFile != null)&&(sourceFile.exists())&&(sourceFile.canRead())){
            try(FileReader reader = new FileReader(sourceFile)){
                char[]buf = new char[5];
                int c =0;
                while((c=reader.read(buf))!=-1){
                    line += new String(buf, 0, c);
                }
            }catch (IOException e) {
                System.out.println("Файл по указанному пути отсутствует.");
                System.out.println(e.getMessage());
            }
        }else{
            throw new IllegalArgumentException("Ошибка: чтение файла " + sourceFile.getAbsolutePath() + " не удалось...");
        }
        return line;
    }

    /**
     * Метод пишет в файл содержимое строки.<p>
     * при записи в файл нужно проверить необходимые условия:<p>
     * -<p>
     * -<p>
     * 
     * @param content
     * @param targetFile
     */
    public static void put(String content, File targetFile){
        try(FileWriter writer = new FileWriter(targetFile, false)){
            writer.append(content);
        }catch(IOException e){
            System.out.println("Файл по указанному пути отсутствует.");
            System.out.println(e.getMessage());
        }
        System.out.println("Созданный файл размещен по адресу: " + targetFile.getAbsolutePath());
    }
}
