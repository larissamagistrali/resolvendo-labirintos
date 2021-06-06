
package trabalest2_Mazes;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Graph {
    private LinkedList<Block> lst[];
    private Stack<Block> stack;
    private Block [][] matrix;
    private Block start, end;
    
    public Graph(int nVertices){
        lst = new LinkedList[nVertices];
        stack = new Stack<>();
        for(int i=0; i < nVertices; i++){
            lst[i] = new LinkedList<>();
        }
    }
    
    public void createGraph(Block [][]mat, int m, int n){
        int cont=0;
        matrix = mat;
        for(int i=0; i < m; i++){
            for(int j=0; j < n; j++){
                lst[cont].add(matrix[i][j]);
               
                if(matrix[i][j].getTop() == 0 && i != 0){
                    lst[cont].add(matrix[i-1][j]);
                }else if(matrix[i][j].getTop() == 0 && i == 0){
                    if(start == null){
                        start = matrix[i][j];
                    }else{
                        end = matrix[i][j];
                    }
                }
                
                if(matrix[i][j].getRight()== 0 && j != n-1){
                    lst[cont].add(matrix[i][j+1]);
                }else if(matrix[i][j].getRight()== 0 && j == n-1){
                    if(start == null){
                        start = matrix[i][j];
                    }else{
                        end = matrix[i][j];
                    }
                }
                
                if(matrix[i][j].getBot() == 0 && i != m-1){
                    lst[cont].add(matrix[i+1][j]);
                }else if(matrix[i][j].getBot()== 0 && i == m-1){
                    if(start == null){
                        start = matrix[i][j];
                    }else{
                        end = matrix[i][j];
                    }
                }
                
                if(matrix[i][j].getLeft() == 0 && j != 0){
                    lst[cont].add(matrix[i][j-1]);
                }else if(matrix[i][j].getLeft()== 0 && j == 0){
                    if(start == null){
                        start = matrix[i][j];
                    }else{
                        end = matrix[i][j];
                    }
                }
            cont++;
            }
        }
        System.out.println("Start: "+start.getId());
        System.out.println("Finish: "+end.getId());
    }
    
    public void depthSearch(){
        Block aux = start;
        int count = aux.getId() - 1;
        boolean b = true;
            
        for(int i=0; i < lst.length; i++){
            
            
            while(lst[aux.getId()-1].size() == 1 || stack.contains(lst[aux.getId()-1].get(1))){
                
                if(lst[aux.getId()-1].size() == 1){
                    aux = stack.pop();
                }else{
                lst[aux.getId()-1].get(0).getId();
                lst[aux.getId()-1].remove(1).getId();
                }
            }
            
                stack.push(aux);
            System.out.println("ID: "+stack.peek().getId()+", X: "+stack.peek().getX()+" - "+stack.peek().getY()); //Printa o caminho
            
            aux = lst[aux.getId()-1].remove(1);
           
           
            
            if(aux.getId() == end.getId()){
                stack.push(aux);
                System.out.println(aux.getId());
                System.out.println("Labirinto completo");
                break;
                
            }
        }
    }
    
    public void generatePath(String caminho){
        try {
            FileWriter arq = new FileWriter(caminho);
            PrintWriter prt = new PrintWriter(arq);
            for(int i=0; i<stack.size(); i++){
                prt.println(stack.get(i).getX()+" "+stack.get(i).getY());
            }
                    
            arq.close();
        } catch (IOException ex) {
            Logger.getLogger(Graph.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void print(){
        int cont=0;
        for(int i=0; i< lst.length; i++){
            System.out.print("| ");
            while(cont < lst[i].size()){
                System.out.print(lst[i].get(cont).getId()+" -> ");
                cont++;
            }
            System.out.println();
            cont=0;
        }
    }
}
