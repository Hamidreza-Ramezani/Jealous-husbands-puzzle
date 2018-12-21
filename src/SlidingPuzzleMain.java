
import algorithm.BFS;
import algorithm.DFS;
import algorithm.UCS;
import java.util.ArrayList;
import java.util.Scanner;
import problems.Action;
import problems.SlidingPuzzle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hamid
 */
public class SlidingPuzzleMain {

    public static void main(String[] args) {
        //Solve 8 Puzzle with BFS (Tree Search)
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 8 Puzzle : ");
        int[][] puzzle = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                puzzle[i][j] = scanner.nextInt();
            }
        }
        SlidingPuzzle slidingPuzzle = new SlidingPuzzle(puzzle);

//        ArrayList<Action> actions6 = UCS.search(slidingPuzzle);
//        if (actions6 != null) {
//            for (Action a : actions6) {
//                System.out.print(a.actionCode + " ");
//            }
//        }
//        System.out.println("");

        
        //Solve 8 Puzzle with DFS (Depth Limit 8)
        ArrayList<Action> actions4 = DFS.search(slidingPuzzle,8);
        if(actions4 != null){
            for(Action a : actions4) System.out.print(a.actionCode + " ");
        }
        System.out.println("");

    }

}
