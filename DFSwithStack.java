import java.util.*;

public class DFSwithStack {
    static class Node{
        Node left;
        Node right;
        int val;
        public Node(int val){
            this.val = val;
        }
        int state = 0;
    }


    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;

        dfs(n1);
    }

    private static void dfs(Node n1) {
        Stack<Node> st = new Stack<>();
        st.push(n1);

        while (!st.empty()){
            Node n = st.peek();
            if (n.state == 2) {
                st.pop();
            } else if (n.state == 0){
                if (n.left != null) st.push(n.left);
                n.state++;
            } else {// state = 1
                if (n.right != null) st.push(n.right);
                System.out.println(n.val);
                n.state++;
            }
        }
    }
}
