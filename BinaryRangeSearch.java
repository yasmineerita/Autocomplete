package autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BinaryRangeSearch implements Autocomplete {
    private List<Term> storage;

    /**
     * Validates and stores the given terms.
     * @throws IllegalArgumentException if terms is null or contains null
     */
    public BinaryRangeSearch(Collection<Term> terms) {
        if (terms == null){
            throw new IllegalArgumentException();
        }
        storage = new ArrayList<>();
        storage.addAll(terms);
        storage.sort(Term::compareTo);
    }

    /**
     * Returns all terms that start with the given prefix, in descending order of weight.
     * @throws IllegalArgumentException if prefix is null
     */
    public List<Term> allMatches(String prefix) {
        Term prefixTerm = new SimpleTerm(prefix, 0);
        if (prefix == null) {
            throw new IllegalArgumentException();
        }
        List<Term> list = new ArrayList<>();
        int left = 0;
        int right = storage.size()-1;
        int mid = (left + right) / 2;
        int indexOfFirstQuery = -1;

        //find the first query prefix
        while (left <= right) {
            if (storage.get(mid).compareToByPrefixOrder(prefixTerm, prefix.length()) > 0) {
                right = mid - 1;
                mid = (left + right) / 2;
            } else if (storage.get(mid).compareToByPrefixOrder(prefixTerm, prefix.length()) < 0){
                left = mid + 1;
                mid = (left + right) / 2;
            } else if (storage.get(mid).compareToByPrefixOrder(prefixTerm, prefix.length()) == 0){
                if (mid == 0){
                    indexOfFirstQuery = 0;
                    left = right + 1;
                }
                else if (!storage.get(mid-1).query().startsWith(prefix)){
                    indexOfFirstQuery = mid;
                    left = right + 1;
                } else {
                    right = mid - 1;
                    mid = (left + right) / 2;
                }
            }
        }

        left = 0;
        right = storage.size()-1;
        mid = (left + right) / 2;
        int indexOfLastQuery = -1;
        //find the last query with prefix
        while (left <= right) {
            if (storage.get(mid).compareToByPrefixOrder(prefixTerm, prefix.length()) > 0) {
                right = mid - 1;
                mid = (left + right) / 2;
            } else if (storage.get(mid).compareToByPrefixOrder(prefixTerm, prefix.length()) < 0){
                left = mid + 1;
                mid = (left + right) / 2;
            } else if (storage.get(mid).compareToByPrefixOrder(prefixTerm, prefix.length()) == 0){
                if (mid == storage.size()-1){
                    indexOfLastQuery = mid;
                    left = right + 1;
                }
                else if (!storage.get(mid+1).query().startsWith(prefix)){
                    indexOfLastQuery = mid;
                    left = right + 1;
                } else {
                    left = mid + 1;
                    mid = (left + right) / 2;
                }
            }
        }
        if (indexOfFirstQuery == -1 || indexOfLastQuery == -1) {
            return new ArrayList<>();
        }
        for (int i = indexOfFirstQuery; i <= indexOfLastQuery; i++){
            list.add(storage.get(i));
        }
        list.sort(Term::compareToByReverseWeightOrder);
        return list;
    }

}
