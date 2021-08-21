package fr.bxcchus_.util;

import java.io.*;

public class TwitifyFileUtils {

    public static void createFile(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveFile(File file, String content) {
        FileWriter writer;
        try {
            createFile(file);
            writer = new FileWriter(file);
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(File file) {
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                reader.close();
                return builder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

}