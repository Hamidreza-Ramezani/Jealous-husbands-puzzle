package problems;

import java.util.ArrayList;

public class Maze implements Problem {

    int[][] Map;
    int MapSizeX;
    int MapSizeY;

    public Maze(int[][] map) {
        MapSizeY = 8;
        MapSizeX = 8;
        Map = map;
    }

    @Override
    public State initialState() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Map[i][j] == 1) {
                    return new MazeState(i, j);
                }
            }
        }
        return new MazeState(0, 0);
    }

    @Override
    public ArrayList<Action> actions(State s) {
        MazeState ms = (MazeState) s;
        ArrayList<Action> acts = new ArrayList<>();

        //0 : +2,+1
        //1 : +2,-1
        //2 : -2,+1
        //3 : -2,-1
        //4 : +1,+2
        //5 : +1,-2
        //6 : -1,+2
        //7 : -1,-2
        if (ms.x < 6 && ms.y < 7 && Map[ms.x + 2][ms.y + 1] != -1) {
            acts.add(new Action(0));
        }
        if (ms.x < 6 && ms.y > 1 && Map[ms.x + 2][ms.y - 1] != -1) {
            acts.add(new Action(1));
        }
        if (ms.x > 2 && ms.y < 7 && Map[ms.x - 2][ms.y + 1] != -1) {
            acts.add(new Action(2));
        }
        if (ms.x > 2 && ms.y > 1 && Map[ms.x - 2][ms.y - 1] != -1) {
            acts.add(new Action(3));
        }
        if (ms.x < 7 && ms.y < 6 && Map[ms.x + 1][ms.y + 2] != -1) {
            acts.add(new Action(4));
        }
        if (ms.x < 7 && ms.y > 2 && Map[ms.x + 1][ms.y - 2] != -1) {
            acts.add(new Action(5));
        }
        if (ms.x > 1 && ms.y < 6 && Map[ms.x - 1][ms.y + 2] != -1) {
            acts.add(new Action(6));
        }
        if (ms.x > 1 && ms.y > 2 && Map[ms.x - 1][ms.y - 2] != -1) {
            acts.add(new Action(7));
        }

        return acts;
    }

    @Override
    public ArrayList<State> result(State s, Action a) {
        MazeState ms = (MazeState) s;

        ArrayList<State> singleState = new ArrayList<>();
        switch (a.actionCode) {
            case 0:
                singleState.add(new MazeState(ms.x - 1, ms.y));
                break;
            case 1:
                singleState.add(new MazeState(ms.x, ms.y - 1));
                break;
            case 2:
                singleState.add(new MazeState(ms.x + 1, ms.y));
                break;
            case 3:
                singleState.add(new MazeState(ms.x, ms.y + 1));
                break;
        }
        return singleState;
    }

    @Override
    public boolean goalTest(State s) {
        MazeState ms = (MazeState) s;
        boolean b = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Map[i][j] == 2) {
                    b = b || (ms.x == i && ms.y == j);
                }
            }
        }
        return b;
    }

    @Override
    public int utility(Action a) {
        return 1;
    }

    @Override
    public int heuristic(State s) {
        MazeState ms = (MazeState) s;
        ArrayList<MazeState> goals = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Map[i][j] == 2) {
                    goals.add(new MazeState(i, j));
                }
            }
        }
        //manhattan

        int out = 15;
        for (int i = 0; i < goals.size(); i++) {
            int x = goals.get(i).x;
            int y = goals.get(i).y;
            int h1 = Math.abs(x - ms.x);
            int h2 = Math.abs(y - ms.y);
            out = Math.min(out, h1 + h2);
        }

        return out;
    }

}

class MazeState implements State {

    public int x;
    public int y;

    public MazeState(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isEquals(State s) {
        MazeState ms = (MazeState) s;
        return (ms.x == x && ms.y == y);
    }

}
