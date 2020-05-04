package spell;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class SpellCorrector implements ISpellCorrector {

    ArrayList<String> wordCollection = new ArrayList<>();
    ArrayList<String> possibleWords = new ArrayList<>();
    //HashMap<String, Integer> myMap = new HashMap<String, Integer>();
    HashMap<Integer, String> frequency = new HashMap<Integer, String>();
    Trie myTrie = new Trie();
    Trie newTrie;
    Checking checkMe = new Checking();



    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {

        Trie myTrie = new Trie();
        File myFile = new File(dictionaryFileName);
        Scanner scan = new Scanner(myFile);
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] words = line.split(" ");
            for (int i = 0; i < words.length; ++i) {
                myTrie.add(words[i]);
                //wordCollection.add(words[i]);
            }

        }

        newTrie = myTrie;


    }


    @Override
    public String suggestSimilarWord(String inputWord) {
        inputWord = inputWord.toLowerCase();
        List<Integer> sortedlist = new ArrayList<>();
        String suggestion = "";
        ArrayList<String> existingList = new ArrayList<>();
        ArrayList<String> mostString = new ArrayList<>();
        boolean secondSerarch = true;

        if (newTrie.find(inputWord) != null) {
            suggestion = inputWord;
        } else if (newTrie.find(inputWord) == null) {
            ArrayList<String> wordList1 = checkMe.deletion(inputWord);
            ArrayList<String> wordList2 = checkMe.transposition(inputWord);
            ArrayList<String> wordList3 = checkMe.Alteration(inputWord);
            ArrayList<String> wordList4 = checkMe.Insertion(inputWord);
            possibleWords.addAll(wordList1);
            possibleWords.addAll(wordList2);
            possibleWords.addAll(wordList3);
            possibleWords.addAll(wordList4);
            int maxNum;
            int numOfMax;

            for (int i = 0; i < possibleWords.size(); ++i) {
                if (newTrie.find(possibleWords.get(i)) != null) {
                    secondSerarch = false;
                    break;
                } else {
                    secondSerarch = true;
                }
            }

            if (secondSerarch == false) {
                for (int i = 0; i < possibleWords.size(); ++i) {
                    Node lastNode = (Node) newTrie.find(possibleWords.get(i));
                    if (lastNode != null) {
                        sortedlist.add(newTrie.find(possibleWords.get(i)).getValue());
                        frequency.put(newTrie.find(possibleWords.get(i)).getValue(), possibleWords.get(i));
                        existingList.add(possibleWords.get(i));
                    }
                }

                Collections.sort(sortedlist);
                maxNum = sortedlist.get(sortedlist.size() - 1);
                numOfMax = Collections.frequency(sortedlist, maxNum);

                if (numOfMax == 1) {
                    suggestion = frequency.get(numOfMax);
                } else {
                    for (int x = 0; x < existingList.size(); ++x) {
                        if (newTrie.find(existingList.get(x)).getValue() == maxNum) {
                            mostString.add(existingList.get(x));
                        }
                    }

                    Collections.sort(mostString);
                    suggestion = mostString.get(0);
                }


            } else if (secondSerarch == true) {
                wordList1.clear();
                wordList2.clear();
                wordList3.clear();
                wordList4.clear();

                for (int i = 0; i < possibleWords.size(); ++i) {
                    wordList1 = checkMe.deletion(possibleWords.get(i));
                    wordList2 = checkMe.transposition(possibleWords.get(i));
                    wordList3 = checkMe.Alteration(possibleWords.get(i));
                    wordList4 = checkMe.Insertion(possibleWords.get(i));
                }

                    ArrayList<String> possibleWords2 = new ArrayList<>();
                    possibleWords2.addAll(wordList1);
                    possibleWords2.addAll(wordList2);
                    possibleWords2.addAll(wordList3);
                    possibleWords2.addAll(wordList4);

                    wordList1.clear();
                    wordList2.clear();
                    wordList3.clear();
                    wordList4.clear();

                    boolean ifFind = false;
                    for (int j = 0; j < possibleWords2.size(); ++j) {

                        if (newTrie.find(possibleWords2.get(j) )!= null ) {

                            ifFind = true;
                            //System.out.println(possibleWords2.get(j));
                            break;
                        }
                    }

                    if (ifFind == true) {
                        for (int j = 0; j < possibleWords2.size(); ++j)
                        {
                            if (newTrie.find(possibleWords2.get(j)) != null) {
                                sortedlist.add(newTrie.find(possibleWords2.get(j)).getValue());
                                frequency.put(newTrie.find(possibleWords2.get(j)).getValue(), possibleWords2.get(j));
                                existingList.add(possibleWords2.get(j));
                            }
                        }

                        Collections.sort(sortedlist);
                        maxNum = sortedlist.get(sortedlist.size() - 1);
                        numOfMax = Collections.frequency(sortedlist, maxNum);

                        if (numOfMax == 1) {
                            suggestion = frequency.get(numOfMax);
                        } else {

                            for (int x = 0; x < existingList.size(); ++x) {
                                if (newTrie.find(existingList.get(x)).getValue() == maxNum) {
                                    mostString.add(existingList.get(x));
                                }
                            }

                            Collections.sort(mostString);
                            suggestion = mostString.get(0);
                        }

                    } else if (ifFind == false) {
                        suggestion = null;
                    }
                }

            wordList1.clear();
            wordList2.clear();
            wordList3.clear();
            wordList4.clear();
        }

        possibleWords = new ArrayList<>();


        return suggestion;
    }

}