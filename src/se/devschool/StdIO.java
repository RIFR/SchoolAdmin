package se.devschool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StdIO
{
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String readLine() throws IOException{
        String txt = reader.readLine();
        return txt;
    }

    public static void writeLine(String txt) {
        System.out.println(txt);
    }

    public static void write(String txt) {
        System.out.print(txt);
    }

    public static void clearScreen() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
