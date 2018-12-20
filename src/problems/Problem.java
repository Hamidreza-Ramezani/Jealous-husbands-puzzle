package problems;

import java.util.ArrayList;

public interface Problem {

    public State initialState();

    public ArrayList<Action> actions(State s); //possible actions

    public ArrayList<State> result(State s, Action a);

    public boolean goalTest(State s);

    public int utility(Action a);

    public int heuristic(State s);

}
