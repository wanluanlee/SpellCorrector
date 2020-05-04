package spell;

import java.util.ArrayList;
import java.util.Arrays;

public class Trie implements ITrie {

    private int numUniqueWord = 0;
    private int numNode = 1;
    private char letter;
    private Node root = new Node('\u0000');
    //StringBuilder str = new StringBuilder();
    //StringBuilder newWord = new StringBuilder();
    ArrayList<String> wordToDisplay = new ArrayList<String>();
    boolean ifequal = false;

    @Override
    public void add(String word) {
        char[] charArray = word.toCharArray();

        Node current = root;


        if (find(word) == null) {
            numUniqueWord++;
        }

        int[] indexArray = toIndex(word);

        for (int i = 0; i < indexArray.length; i++) {

            if (current.getArray()[indexArray[i]] == null) {

                current.getArray()[indexArray[i]] = new Node(charArray[i]);
                numNode++;

            }

            current = current.getArray()[indexArray[i]];


            //current.setisEndOfWord(true);

        }

        current.setIfEndOfWord();
        current.incrementValue();
    }


    @Override
    public INode find(String word) {

        Node current = root;
        int[] indexArray = toIndex(word);
        for (int i = 0; i < word.length(); ++i) {
            if (current.getArray()[indexArray[i]] == null) {
                return null;

            } else {
                current = current.getArray()[indexArray[i]];
            }
        }


        if (current.getIfEndOfWord() != true) {
            return null;
        }

        return current;
    }

    @Override
    public int getWordCount() {
        return numUniqueWord;
    }

    @Override
    public int getNodeCount() {
        return numNode;
    }

    public int[] toIndex(String word) {
        int index;
        char[] charArray = word.toCharArray();
        int[] indexArray = new int[charArray.length];
        for (int i = 0; i < charArray.length; ++i) {
            index = charArray[i] - 'a';
            indexArray[i] = index;

        }

        return indexArray;
    }

    public Node[] getArray() {
        return root.getArray();
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }

    @Override
    public String toString() {
        int level = 0;
        StringBuilder str = new StringBuilder();
        StringBuilder newWord = new StringBuilder();
        StringBuilder output = new StringBuilder();


        String myStr = Display(root, str, newWord, level);

        String[] list = myStr.split("\\s+");
        Arrays.sort(list);

        for (String thisString : list) {
            output.append(thisString);
            output.append("\n");
        }
        return output.toString();
    }

    public String Display(Node root, StringBuilder str, StringBuilder newWord, int level) {
        //StringBuilder str = new StringBuilder();


        for (int i = 0; i < 26; i++) {
            if (root.getArray()[i] != null) {
                char myChar = (char) (i + 'a');
                str.append(myChar);
                Display(root.getArray()[i], str, newWord, level + 1);
                str.deleteCharAt(str.length() - 1);

            }

        }

        if (root.getValue() != 0) {
            //str.deleteCharAt(str.length()-1);
            newWord.append(str.toString());
            newWord.append("\n");
            //wordToDisplay.add(str.toString());

        }

        return newWord.toString();
    }

    public Node getRoot() {
        return root;
    }

    public int hashCode() {
        int firstNode = 0;
        int code = 0;
        for (int i = 0; i < 26; ++i) {
            if (root.getArray()[i] != null) {
                firstNode = i + 1;
                break;
            }
        }
        code = numUniqueWord * numNode * 31 * (firstNode);
        return code;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Trie == false) {
            return false;
        }

        Trie tree = (Trie) obj;

        if (compare(tree.getRoot(), this.getRoot()) == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean compare(Node root1, Node root2) {
        for (int i = 0; i < 26; i++) {

            if(root1.getArray()[i] == null && root2.getArray()[i] != null)
            {
                return false;
            }

            if(root1.getArray()[i] != null && root2.getArray()[i] == null)
            {
                return false;
            }


            if (root1.getArray()[i] != null && root2.getArray()[i] != null) {

                if(!compare(root1.getArray()[i], root2.getArray()[i])) {
                    return false;
                }

            }

            if (root1.getValue() != root2.getValue()) {
                return false;
            }
        }
        return true;

    }
}
