package sample.Supporter;

import java.util.List;

public class Trie {
    TrieNode root;
    List<String> autocomlete;
    // constructor
    public Trie(){
        root = new TrieNode("");
    }
    public Trie(String words){
        this();
        this.root.InsertWord(words,root);
    }
    //add
    public void insert(String word){
        root.InsertWord(word.toLowerCase(),root);
    }
    //remove
    public void delete(String words){
        root.setSize(root.getSize() - 1);
        root.removeHelper(root,words,0);
    }
    //size of trie
   public int Size(){
        return root.getSize();
   }
   //contains
    public boolean Contains(String words){
        return root.containWord(root,words);
    }
    //autocomplete
    public List<String> Suggestion(String prefix){
        autocomlete = root.getWordFromPrefix(prefix,root);
        return autocomlete;
    }
}
