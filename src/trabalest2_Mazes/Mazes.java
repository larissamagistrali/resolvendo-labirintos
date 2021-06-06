
package trabalest2_Mazes;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Mazes {
    private List<String> maze = new LinkedList<>();
    private Graph graph;
    private Block [][] mat;
    private int m;
    private int n;
    private String caminho;
    
public void read(String caminho) { //Lê o arquivo
    this.caminho = caminho;
    try {
        File f = new File("casos\\"+this.caminho+".txt");
        Scanner in = new Scanner(f);
        m = Integer.parseInt(in.next());
        n = Integer.parseInt(in.next());
        while(in.hasNext()){
            maze.add(in.next());
        }
    }catch(Exception e) {
        System.out.println(e);
    }
}

public void create(){ //Cria uma matriz com os numeros binarios de cada celula do maze
    mat = new Block[m][n];
    graph = new Graph(m*n);
    int cont =0;
    String bit;
    int[] num = new int[4];
    for(int i=0; i < m; i++){
        for(int j=0; j < n; j++){
            bit = Integer.toString(Integer.parseInt(maze.get(cont), 16), 2);
            bit = String.format("%04d", Integer.parseInt(bit));
            for(int k=0; k<4; k++){
                num[k] = Character.getNumericValue(bit.charAt(k));
                
            }
            mat[i][j] = new Block(num, i,j);
            cont++; 
        }
    }
    graph.createGraph(mat, m, n);
    graph.depthSearch();
    graph.generatePath("casos\\"+caminho+"Path.txt");
    
    /*for(int i=0; i < m; i++){
        for(int j=0; j < n; j++){
            System.out.print(String.format("%04d | ",mat[i][j] ));
        }
        System.out.println("");
    }*/
}



public void print(){ //Printa as celulas do maze em HEXA
    System.out.println(m+" X "+n);
    System.out.println(maze.toString());
    System.out.println("top :"+mat[0][0].getBlock()+", Id: "+mat[0][0].getId());
}

}
