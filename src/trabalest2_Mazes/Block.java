
package trabalest2_Mazes;

public class Block {
    private int x;
    private int y;
    
    private int top;
    private int left;
    private int right;
    private int bot;
    private int id;
    private static int cont=0;
    
    public Block(int[] b, int x, int y){
        this.x = x;
        this.y = y;
        
        top = b[0];
        right = b[1];
        bot = b[2];
        left = b[3];
        cont++;
        id=cont;
    }

   
    public int getTop() {
        return top;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getBot() {
        return bot;
    }
    
    public int getId(){
        return id;
    }
    
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    
    public Integer getBlock(){
        int aux = Integer.parseInt(Integer.toString(top)+Integer.toString(right)+Integer.toString(bot)+Integer.toString(left));
        return aux;
    }
    
    
}
