
/**
 * Write a description of class ArrayFun here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ArrayFun
{
    public static void main(String[] args) {
        
        int x = 1;
        int y = 1;
        int [][] grid = {{1, 2},{3, 4}};
        while(x < 2) {
            while(y < 2) {
                System.out.println(grid[x][y]);
                y = y + 1;
            }
            x = x + 1;
        } 
    }
}
