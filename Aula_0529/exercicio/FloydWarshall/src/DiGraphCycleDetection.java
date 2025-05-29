public class DiGraphCycleDetection{
    private enum color {WHITE, GREY, BLACK};
    private color[] mark;
    private boolean containsCycle;

    public DiGraphCycleDetection(Digraph g) {

        mark = new color[g.V()];
        for(int i=0; i<mark.length; i++)
            mark[i]= color.WHITE;


        containsCycle=false;
        
        for(int v=0; v<g.V(); v++)
            if(mark[v]==color.WHITE)
                if(visit(g, v)){
                    containsCycle=true;
                    break;
                }
    }

    public boolean hasCycle() {
        return containsCycle;
    }

    private boolean visit(Digraph g, int vertex){
        mark[vertex]=color.GREY;
        for(int adj: g.adj(vertex))
            if(mark[adj]==color.GREY)
                return true;
            else if(mark[adj]==color.WHITE){
                if(visit(g, adj))
                    return true;
            }
        mark[vertex]=color.BLACK;
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