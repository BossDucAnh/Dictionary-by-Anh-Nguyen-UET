package sample.readFileDictionary;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class ReadFile {
    public static TreeMap<String,String> readFileDictionary(String path){
        TreeMap<String,String> listdata = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        try {
            FileInputStream in = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(in,"UTF-8");
            BufferedReader brd = new BufferedReader(isr);
            String line = brd.readLine();
            while(line != null) {
                line = line.replace("\uFEFF", "");
                if(line.length()>0) {
                    String[] word = line.split("\\t");
                    listdata.put(word[0], word[1]);
                }
                line = brd.readLine();
            }
            brd.close();
            isr.close();
            in.close();
            return listdata;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static ArrayList<Double> readFileSetting(String path){
        File file = new File(path);
        ArrayList<Double> listFile = new ArrayList<>();
        try(Scanner sc = new Scanner(file)){
            while (sc.hasNext()){
                double dob = sc.nextDouble();
                listFile.add(dob);
            }
            return listFile;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
