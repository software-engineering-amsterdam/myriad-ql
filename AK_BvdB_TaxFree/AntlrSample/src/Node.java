/**
 * Created by Alex on 7-2-2017.
 */
public class Node<T> {

    private final Class<T> type;

    public Node(Class<T> type) {
        this.type = type;
    }

    public Class<T> getMyType() {
        return this.type;
    }
}
