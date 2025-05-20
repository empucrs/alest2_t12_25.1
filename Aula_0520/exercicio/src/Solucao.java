import java.util.ArrayList;

public class Solucao {

    private static class Ponto{
        private double x,y;
        public Ponto(double _x, double _y) {
            x=_x;
            y=_y;
        }
        public double getX() {
            return x;
        }
        public double getY() {
            return y;
        }
        @Override
        public String toString() {
            return "{"+x+";"+y+"}";
        }
        
    }

    public static void main(String[] args) {
        //1. Ler o arquivo dados.csv
        System.out.println(args[0]);
        In leCSV = new In(args[0]);
        ArrayList<Ponto> vertices = new ArrayList<>();

        //  1.1. salvar os dados em alguma estrutura adequada
        while(leCSV.hasNextLine()){
            String [] valores = leCSV.readLine().split(";");
            Double x = Double.parseDouble(valores[0]);
            Double y = Double.parseDouble(valores[1]);

            vertices.add(new Ponto(x, y));
        }

        for(Ponto p: vertices)
            System.out.println(p);

        //2. Criar o grafo não dirigido e valorado
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertices.size());

        //  2.1. calcular as distancias entre pares de vertices
        for(int v=0; v<vertices.size(); v++){
            MinPQ<Edge> mpq = new MinPQ<>();
            for(int w=0; w<vertices.size(); w++){
                if(v!=w){
                    Ponto pv = vertices.get(v);
                    Ponto pw = vertices.get(w);
                    // calculo da distancia euclediana
                    double d = Math.sqrt( Math.pow((pv.getX()-pw.getX()), 2) + Math.pow(pv.getY()-pw.getY(),2));

                    Edge e = new Edge(v, w, d);
                    mpq.insert(e);
                }
            }

            for(int x=0; x<3; x++){
            //  2.2. escolher os 3 menos distantes
                Edge top3 = mpq.delMin();
            //  2.3. salvar no grafo valorado
                ewg.addEdge(top3);
            }

        }

        //3. Alimentar a solucao de MST
        PrimMST pmst = new PrimMST(ewg);

        //4. gerar o arquivo de saída
        Out omst = new Out("resultado.txt");

        for(Edge e: pmst.edges()){
            omst.printf("%d %d 1\n",e.either(), e.other(e.either()) );
        }

        omst.close();

    }
    
}
