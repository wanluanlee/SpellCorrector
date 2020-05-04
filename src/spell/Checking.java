package spell;

import java.util.ArrayList;

public class Checking {

    ArrayList<String> wordList = new ArrayList<>();

    public ArrayList<String> deletion(String word)
    {
        StringBuilder newWord = new StringBuilder(word);

        for (int i = 0; i < word.length();++i)
        {
            newWord.deleteCharAt(i);
            wordList.add(newWord.toString());
            newWord = new StringBuilder(word);
        }

        return wordList;
    }

    public ArrayList<String> transposition(String word)
    {
        char ch[] = word.toCharArray();

        for (int i = 0; i < word.length()-1; ++i)
        {
            char temp = ch[i];
            ch[i] = ch[i+1];
            ch[i+1] = temp;

            String newWord = new String(ch);
            wordList.add(newWord);
            ch = word.toCharArray();

        }

        return wordList;
    }

    public ArrayList<String> Alteration(String word)
    {
        String letter = "abcdefghijklmnopqrstuvwxyz";
        char letters[] = letter.toCharArray();
        char ch[] = word.toCharArray();

        for (int i = 0; i  < word.length(); ++i)
        {
            for (int j = 0; j < letters.length;++j)
            {
                char temp = ch[i];
                ch[i] = letters[j];

                String newWord = new String(ch);
                wordList.add(newWord);
                ch = word.toCharArray();
            }

        }

        return wordList;
    }

    public ArrayList<String> Insertion(String word)
    {
        String letter = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder newWord = new StringBuilder(word);
        char letters[] = letter.toCharArray();
        char ch[] = word.toCharArray();

        for (int i = 0; i  < word.length() + 1; ++i)
        {
            for (int j = 0; j < letters.length;++j)
            {
                newWord.insert(i,letters[j]);
                wordList.add(newWord.toString());
                newWord = new StringBuilder(word);
            }

        }

        return wordList;

    }
}
