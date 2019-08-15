package sample.Supporter;

import java.util.*;

public class TrieNode {
    private TreeMap<Character,TrieNode> children;
    private int size = 0;
    private boolean iswords;
    private char character;
    private boolean hasChildren;
    private String prefix;
    private static List<String> mylist = new ArrayList<String>();

    public TrieNode(){
        children = new TreeMap<>();
        iswords = false;
        hasChildren = false;
    }
    public TrieNode(String prefix){
        this();
        this.prefix = prefix;
    }
    public TrieNode(char character){
        this();
        this.character = character;
    }
    public int getSize(){
        return this.size;
    }
    public void setSize(int size) {this.size = size;}
    public void addWords(String word){
        if(word == null){
            return;
        }
        if(word.length() == 0){
            iswords = true;
            return;
        }
        char firstchar = word.charAt(0);
        if(children.get(firstchar)==null){//if current node has no letter , this is parent and add next character
            this.hasChildren = true;
            children.put(firstchar,new TrieNode(firstchar));
        }
        size++;
        children.get(firstchar).addWords(word.substring(1));
    }
    public List<String> getword(TrieNode trieNode, String chars){
        chars += trieNode.character;
        if(trieNode.iswords)//add when it's a words
        {
            mylist.add(chars.trim());
        }
        if(trieNode.hasChildren){
            for (Character child : trieNode.children.keySet()){
                getword(trieNode.children.get(child),chars);
            }
        }
        else {chars = "";}
        Collections.sort(mylist);
        return mylist;
    }
    static boolean hasWord = false;
    public boolean containWord(TrieNode trieNode,String word){
        //every node have one character and one variable boolean iswords
        //if node point node not null. it's not end words else it's is words
        char letter = word.charAt(0);
        if(trieNode.children.get(letter) == null){
            hasWord = false;
        }
        else if (word.length() == 1){
            if(trieNode.children.get(letter).iswords) hasWord = true;
            else hasWord = false;
        }
        else containWord(trieNode.children.get(letter),word.substring(1));
        return hasWord;
    }
    //reference on github.com
    //tham khảo trên github
    public boolean removeHelper(TrieNode trieNodeCurrent,String word,int index){
        if(word.length() == index){
            //when word were deleted , is word = true,node point true;
            if(!trieNodeCurrent.iswords){
                return false;
            }
            trieNodeCurrent.iswords = false;
            return trieNodeCurrent.children.size() == 0;
        }
        char ch = word.charAt(index);
        TrieNode node = trieNodeCurrent.children.get(ch);
        if(node == null) return false;
        boolean deleteCurrentNode = removeHelper(node,word,index+1);
        //if true we delete node of trie
        if(deleteCurrentNode){
            trieNodeCurrent.children.remove(ch);
            // return true if size of node children = true;
            return trieNodeCurrent.children.size() == 0;
        }
        return false;
    }
    public void InsertWord(String s,TrieNode root){
        //passing through each character . if character not exits then added it
        TrieNode currentNode = root;
        for(int i = 0; i < s.length() ; i++){
            if(!currentNode.children.containsKey(s.charAt(i))){
                currentNode.children.put(s.charAt(i),new TrieNode(s.substring(0,i+1)));
            }
            currentNode = currentNode.children.get(s.charAt(i));
            if(i == s.length()-1)
            {
                size++;
                currentNode.iswords = true;
            }
        }
    }
    //find all word start prefix
    public List<String> getWordFromPrefix(String pref,TrieNode root){
        List<String> mylist = new LinkedList<>();
        TrieNode current = root;
        //find last node of prefix
        for (Character c : pref.toCharArray()){
            if(current.children.containsKey(c)){
                current = current.children.get(c);
            }
            else
            {
                return mylist;
            }
        }
        InOrderfindAllChildWord(current,mylist);
        Collections.sort(mylist);
        return mylist;
    }
    // recursion for traversal through every nodes of trie
    public void InOrderfindAllChildWord(TrieNode node,List<String> result){
        if(node.iswords){
            result.add(node.prefix);// if it's is words because traversal end of node
            //add on list
        }
        //traversal in-order - depth first traversal visited through every node contains character.
        for (Character c : node.children.keySet()) {
            InOrderfindAllChildWord(node.children.get(c), result);
        }
    }
}
