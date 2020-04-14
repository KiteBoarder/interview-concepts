import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class ListIterator implements Iterator<String>{
    List<String> list;
    int pos = 0;

    public ListIterator(List<String> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        while(pos < list.size()){
            if (list.get(pos).startsWith("a"))
                return true;
            pos++;
        }
        return false;

    }

    @Override
    public String next() {
        if (hasNext())
            return list.get(pos++);
        throw new NoSuchElementException();
    }
}

public class IteratorTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("alice", "bob", "abigail", "charlie");
        ListIterator itr = new ListIterator(list);

        while(itr.hasNext())
            System.out.println(itr.next());
    }
}

