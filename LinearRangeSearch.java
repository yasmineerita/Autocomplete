package autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LinearRangeSearch implements Autocomplete {
    private List<Term> storage;

    /**
     * Validates and stores the given terms.
     * @throws IllegalArgumentException if terms is null or contains null
     */
    public LinearRangeSearch(Collection<Term> terms) {
        storage = new ArrayList<>();
        if (terms == null || terms.contains(null)){
            throw new IllegalArgumentException();
        }
        storage.addAll(terms);
    }

    /**
     * Returns all terms that start with the given prefix, in descending order of weight.
     * @throws IllegalArgumentException if prefix is null
     */
    public List<Term> allMatches(String prefix) {
        List<Term> list = new ArrayList<>();
        if (prefix == null){
            throw new IllegalArgumentException();
        } else {
            for (int i = 0; i < storage.size(); i++){
                if (storage.get(i).query().startsWith(prefix)){
                    list.add(storage.get(i));
                }
            }
        }
        return list;
    }
}

