
import algorithm.*;
import problems.*;

import java.util.ArrayList;

public class MainDriver {

    public static void main(String[] args) {
        Couples couples = new Couples();
        ArrayList<Action> actions3 = BFS.search(couples,true);
        if (actions3 != null) {
            for (Action a : actions3) {
                System.out.print(a.actionCode + " ");
            }
        }
        System.out.println("");
    }

}
