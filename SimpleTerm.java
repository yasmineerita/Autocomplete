package autocomplete;

import java.util.Objects;

public class SimpleTerm implements Term {
    private final String query;
    private final long weight;

    public SimpleTerm(String query, long weight) {
        if (query == null){
            throw new IllegalArgumentException();
        }
        if (weight < 0){
            throw new IllegalArgumentException();
        }
        this.query = query;
        this.weight = weight;
    }

    @Override
    public String query() {
        return query;
    }

    @Override
    public String queryPrefix(int r) {
        return query.substring(0, r-1);
    }

    @Override
    public long weight() {
        return weight;
    }

    @Override
    public String toString() {
        return "SimpleTerm{" +
                "query='" + query + '\'' +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        SimpleTerm that = (SimpleTerm) o;
        return weight == that.weight &&
                Objects.equals(query, that.query);
    }

    @Override
    public int hashCode() {
        return Objects.hash(query, weight);
    }
}
