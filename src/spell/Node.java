package spell;

public class Node implements INode {
    private char letter;
    private int count;
    private Node[] nodeArray = new Node[26];
    private boolean isEndOfWord;


    public Node(char c) {

        letter = c;
    }

    public void setLetter(char letter)
    {
        this.letter = letter;
    }

    @Override
    public int getValue()
    {
        return count;
    }

    public Node[] getArray()
    {
        return nodeArray;
    }

    public void incrementValue()
    {
        count++;
    }

    public char getLetter()
    {
        return letter;
    }

    public void setIfEndOfWord()
    {
        isEndOfWord = true;
    }

    public boolean getIfEndOfWord()
    {
        return isEndOfWord;
    }


}
