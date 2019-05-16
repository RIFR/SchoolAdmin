package se.devschool;

import java.io.IOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileIO {

    private static FileReader in = null;
    private static FileWriter out = null;
    private static boolean targetOpened = false;
    private static BufferedReader reader;


    public static String[] getDir(String dirName) throws IOException {
        File file = new File(dirName);

        // array of files and directory
        return file.list();
    }

    public static void openReadFile(String fileName) throws IOException {
        reader = new BufferedReader(new FileReader(fileName));
    }

    public static String readLine() throws IOException {
        return reader.readLine();
    }


    public static void closeReadFile(String fileName) throws IOException {
        if (reader != null)
            reader.close();
    }

    public static List<String> readAllLines(String fileName) throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get(fileName));
        return allLines;
    }

    public static void copyFile(String fromName, String toName) throws IOException {
        try {
            in = new FileReader(fromName);
            if (!targetOpened)
                out = new FileWriter(toName);

            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null && !targetOpened) {
                out.close();
            }
        }
    }

    public static void openTarget(String toName) throws IOException {
        out = new FileWriter(toName);
        targetOpened = true;
    }

    public static void closeTarget() throws IOException {
        if (out != null) {
            targetOpened = false;
            out.close();
        }
    }

    public static <E> void writeObject(E theObject, String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(theObject);
            System.out.println("Serialized data is saved in "+filename);
        } catch (IOException i) {
            i.printStackTrace();
        } catch (Exception i) {
            i.printStackTrace();
        }
    }

    public static <E> E readObject(String filename) {
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            E theObject = (E) in.readObject();
            System.out.println("Deserialized data read from "+filename);
            return theObject;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Object class not found");
            c.printStackTrace();
            return null;
        }
    }


}
