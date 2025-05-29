public class FloydWarshall {

    private int nVertex;
    private int [][] pred; // vertices para alcançar um destino
    private double [][] dist; // custos anotados no grafo;

    private boolean DEBUG = true;


    public FloydWarshall(String filename) {
        inicializacao(filename);
        if(DEBUG) printMatrix();
        System.out.println("\n\n");
        runFloydWarshall();
        if(DEBUG) printMatrix();
    }

    private void printMatrix(){
        System.out.println("-= Dist. Matrix =-");
        System.out.print("   |");
        for(int tgt=0; tgt<nVertex; tgt++)
            System.out.printf("%2d   |", tgt);
        System.out.println();
        for(int src=0; src<nVertex; src++){
          System.out.printf("%2d |", src);
          for(int tgt=0; tgt<nVertex; tgt++)
            System.out.printf("%.2f |", dist[src][tgt]);
            System.out.println();
        }

        System.out.println("\n\n-= Pred. Matrix =-");
        System.out.print("   |");
        for(int tgt=0; tgt<nVertex; tgt++)
            System.out.printf("%2d |", tgt);
        System.out.println();
        for(int src=0; src<nVertex; src++){
          System.out.printf("%2d |", src);
          for(int tgt=0; tgt<nVertex; tgt++)
            System.out.printf("%2d |", pred[src][tgt]);
          System.out.println();
        }
        
    }

    private void runFloydWarshall(){

        for(int inter=0; inter<nVertex; inter++)
            for(int src=0; src<nVertex; src++)
                for(int tgt=0; tgt<nVertex; tgt++){
                    if(dist[src][tgt]>(dist[src][inter]+dist[inter][tgt])){
                        dist[src][tgt]=(dist[src][inter]+dist[inter][tgt]);
                        pred[src][tgt]=pred[inter][tgt];
                    }
                }
    }

    private void inicializacao(String filename){

        In entrada = new In(filename);
        String nVertices = entrada.readLine().trim();

        // Primeiro campo é consumido e considerado na anotação
        // Nro de vertices
        nVertex = Integer.parseInt(nVertices);

        // Criar as matrizes de distancia e caminho
        dist = new double[nVertex][nVertex];
        pred = new int[nVertex][nVertex];

        // inicialização das matrizes
        for(int src=0; src<nVertex; src++)
          for(int tgt=0; tgt<nVertex; tgt++){
            if(src!=tgt){
                dist[src][tgt]=Double.POSITIVE_INFINITY;
                pred[src][tgt]=-1;
            }
            else{
                dist[src][tgt]=0;
                pred[src][tgt]=src;
            }
          }

        // Segundo campo é consumido, mas ignorado
        // Nro de arestas
        String nArestas = entrada.readLine().trim();

        // Terceiro campo são as arestas, com src, tgt, weigth
        while (entrada.hasNextLine()) {
            String [] dados = entrada.readLine().split(" ");
            int src, tgt;
            double weigth;

            src    = Integer.parseInt(dados[0]);
            tgt    = Integer.parseInt(dados[1]);
            weigth = Double.parseDouble(dados[2]);

            dist[src][tgt] = weigth;
            pred[src][tgt] = src;
            
        }
        entrada.close();
    }

    public static void main(String[] args) {

        FloydWarshall fw = new FloydWarshall(args[0]);
        
    }
    
}

