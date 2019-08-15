package sample.readFileDictionary;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class WriteFileDictionary {
    public static void Ouputfile(TreeMap<String,String> outputlist,String path){
        try {
            FileOutputStream out = new FileOutputStream(path);
            OutputStreamWriter osw = new OutputStreamWriter(out,"UTF-8");
            BufferedWriter writer = new BufferedWriter(osw);
            for (Map.Entry<String,String> tree : outputlist.entrySet()){
                writer.write(tree.getKey() +"\t" +tree.getValue());
                writer.newLine();
            }
            writer.close();
            osw.close();
            out.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    public static void OutFileSetting(ArrayList<Double> settinglist,String path){
        File file = new File(path);
        try(PrintWriter pw = new PrintWriter(file)) {
           for (Double sw : settinglist){
               pw.println(sw);
           }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    //out file test
    public static void OutFileTwo(TreeMap<String,String> outputlist,String path){
        File file = new File(path);
        try(PrintWriter pw = new PrintWriter(file)) {
            for (Map.Entry<String,String> tree : outputlist.entrySet()){
                pw.println(tree.getKey() + "\t" +tree.getValue());
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
