public class DiGraphCycleDetection{
    public DiGraphCycleDetection(Digraph g) {
    }

    public boolean hasCycle() {
        return false;
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        DiGraphCycleDetection cd = new DiGraphCycleDetection(G);

        if(cd.hasCycle())
            System.out.println("O grafo possui ciclos");
        else        
            System.out.println("O grafo não possui ciclos");            

    }



}