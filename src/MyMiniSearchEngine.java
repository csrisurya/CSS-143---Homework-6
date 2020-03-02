import java.lang.reflect.Array;
import java.util.*;

public class MyMiniSearchEngine {
    // default solution. OK to change.
    // do not change the signature of index()
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
        System.out.println(indexes.toString());

    }

    // search(key) return all the document ids where the given key phrase appears.
    // key phrase can have one or two words in English alphabetic characters.
    // return an empty list if search() finds no match in all documents.
    public List<Integer> search(String keyPhrase) {
        // homework
        return new ArrayList<>(); // place holder
    }


}
