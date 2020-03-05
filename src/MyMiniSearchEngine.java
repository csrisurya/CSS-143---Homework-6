import java.lang.reflect.Array;
import java.util.*;

public class MyMiniSearchEngine {

    private Map<String, List<List<Integer>>> indexes;

    // disable default constructor
    private MyMiniSearchEngine() {
    }

    public MyMiniSearchEngine(List<String> documents) {
        index(documents);
    }

    // each item in the List is considered a document.
    // assume documents only contain alphabetical words separated by white spaces.
    private void index(List<String> texts) {
        indexes = new HashMap<>();

        for(int i = 0; i < texts.size(); i++){
            String[] array = texts.get(i).split(" ");
            for(int j = 0; j < array.length; j++){
                List<List<Integer>> bigList = new ArrayList<>();
                for(int k = 0; k < texts.size(); k++){
                    bigList.add(new ArrayList<>());
                }
                if(!indexes.containsKey(array[j])){
                    indexes.put(array[j], bigList);
                }
                indexes.get(array[j]).get(i).add(j);
            }
        }
    }

    // search(key) return all the document ids where the given key phrase appears.
    // key phrase can have one or two words in English alphabetic characters.
    // return an empty list if search() finds no match in all documents.
    public List<Integer> search(String keyPhrase) {

        List<List<Integer>> docIDs = new ArrayList<>();
        String[] words = keyPhrase.split(" ");
        for(int i = 0; i < words.length; i++) {
            // checks to see if the word exists in the map at all (to avoid NullPointer Exception)
            // if one word in String sequence is not found, return a list with the value -1
            if(!indexes.containsKey(words[i].toLowerCase())){
                List<Integer> result = new ArrayList<>();
                result.add(-1);
               return result;
            }
            // gets all the ids of the docs containing each word
            List<Integer> docsWithWord = new ArrayList<>();
            int j = 0;
            for (List<Integer> list : indexes.get(words[i].toLowerCase())) {
                if (list.size() > 0) {
                    docsWithWord.add(j);
                }
                j++;
            }
            docIDs.add(docsWithWord);
        }

        // find the common docs among the words
        List<Integer> commonDocIDs = docIDs.get(0);
        for(List<Integer> list : docIDs){
            commonDocIDs.retainAll(list);
        }

        // getting the positions of the words in each doc
        List<Integer> result = new ArrayList<>();
        for(int docID : commonDocIDs) {
            List<Integer> locationForSpecificDoc = new ArrayList<>();
            // subtracts each location for each word by increment of one (first word -0, second word -1,etc)
            // within a document and sees if it's in the correct order
            for (int i = 0; i < words.length; i++) {
                locationForSpecificDoc.add(indexes.get(words[i].toLowerCase()).get(docID).get(0) - i);
            }
            boolean docContainsKeyWord = true;
            int firstDocID = locationForSpecificDoc.get(0);
            for(int num : locationForSpecificDoc){
                if(num != firstDocID) {
                    docContainsKeyWord = false;
                }
            }
            if(docContainsKeyWord) {
                result.add(docID);
            }
        }

    // if the words in the String sequence exist in map but are in incorrect order
    // return a list with the value -1
     if(result.isEmpty()){
         result.add(-1);
         return result;
     }
     // otherwise, return a list that contains the doc ids where the String sequence exists
      return result;
    }


}
