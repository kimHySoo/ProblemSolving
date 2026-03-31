package DataStructrue;
import java.util.ArrayList;
import java.util.List;

public class Trie02 {
    static class Node {
        boolean isEnd;
        char c;
        Node[] children;

        Node(char c){
            this.c = c;
            children = new Node[26];
        }

        Node(){
            children = new Node[26];
        }
    }

    private static Node root;

    Trie(){
        root = new Node();
    }

    public void insert(String word){
        Node curr = root;
        for(int i=0; i< word.length(); i++){
            int num = word.charAt(i) - 'a';
            if(curr.children[num] == null){
                curr.children[num] = new Node(word.charAt(i));
            }
            curr = curr.children[num];
        }
        curr.isEnd = true;
    }

    public boolean startsWith(String pattern){
        Node curr = root;
        for(int i=0; i< pattern.length(); i++){
            int num = pattern.charAt(i) - 'a';
            if(curr.children[num] == null)
                return false;
            curr = curr.children[num];
        }
        return true;
    }

    public List<String> search(String pattern){

        Node curr = root;
        for(int i=0; i<pattern.length(); i++){
            int num = pattern.charAt(i) - 'a';
            if(curr.children[num] == null)
                return null;
            curr = curr.children[num];
        }

        List<String> words = new ArrayList<>();
        search(curr, pattern, words);

        return words;
    }

    private void search(Node curr, String pattern, List<String> words){
        if(curr.isEnd) words.add(pattern);
        for(int i=0; i<26; i++){
            if(curr.children[i] != null){
                search(curr.children[i], pattern + (char)(i + 'a'), words);
            }
        }
    }

    public void printTrie(){
        printTrie(root, 0);
    }

    private void printTrie(Node curr, int depth){
        if(curr == null)
            return;
        for(int i=0; i<depth; i++)
            System.out.print("  ");
        if(curr.isEnd) System.out.println(curr.c + ".");
        else
            System.out.println(curr.c);
        for(int i=0; i<26; i++){
            printTrie(curr.children[i], depth+1);
        }
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("app");
        trie.insert("apple");
        trie.insert("apply");
        trie.insert("ace");
        trie.insert("act");
        trie.insert("acid");
        trie.insert("bat");
        trie.insert("ball");
        trie.insert("ban");
        trie.printTrie();

        System.out.println("===");
        System.out.println("ac: " + trie.startsWith("ac"));
        System.out.println("appl: " + trie.startsWith("appl"));
        System.out.println("ad: " + trie.startsWith("ad"));
    
        System.out.println("===");
        List<String> words = trie.search("ba");
        for (String w : words){
            System.out.println(w);
        }

        System.out.println("===");
        words = trie.search("a");
        for (String w : words){
            System.out.println(w);
        }
    }
}
