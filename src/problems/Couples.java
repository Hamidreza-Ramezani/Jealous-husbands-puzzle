/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problems;

import java.util.ArrayList;

/**
 *
 * @author hamid
 */
public class Couples implements CouplesInterface {

    private int[] initial = new int[8];
    int boatSide1;

    public Couples() {
        boatSide1 = 1;
        for (int i = 0; i < 8; i++) {
            initial[i] = 0;
        }
    }

    @Override
    public State initialState() {
        return new CouplesState();
    }

    @Override
    public ArrayList<Action> actions(State s) {
        CouplesState couplesState = (CouplesState) s;
        int[] arr = couplesState.array;
        int boatSide = couplesState.boatSide;
//        CouplesState couplesState1 = couplesState.clone();
//        int[] arr1 = couplesState1.array;
        ArrayList<Action> acts = new ArrayList<>();

        ArrayList<Integer> leftPeople = new ArrayList<>();
        ArrayList<Integer> rightPeople = new ArrayList<>();
        boolean b = false;

        for (int t = 0; t < 8; t++) {
            if (arr[t] == 1) {
                b = true;
            }
        }

        if (b) {
            if (boatSide == 1) {
                acts.add(new Action(888));
            } else {
                acts.add(new Action(999));
            }
            return acts;
        }
        //abc
        // a : boat side's code
        // b : first person's code
        // c : second person's code
        if (boatSide == 1) {

            for (int i = 0; i < 8; i++) {
                if (arr[i] == 0) {
                    leftPeople.add(i);
                    int k = boatSide * 100 + i * 10 + 9; // when someone is alone in the boat
                    acts.add(new Action(k));             // when someone is alone in the boat
                }
            }

            if (leftPeople.size() > 1) {
                for (int j = 0; j < leftPeople.size(); j++) {
                    for (int k = j + 1; k < leftPeople.size(); k++) {
                        CouplesState couplesState1 = couplesState.clone();
                        int[] arr1 = couplesState1.array;
                        arr1[leftPeople.get(j)] = 1;
                        arr1[leftPeople.get(k)] = 1;
                        if (isValidConfiguration(arr1)) {
                            int h = boatSide * 100 + leftPeople.get(j) * 10 + leftPeople.get(k);
                            acts.add(new Action(h));
                        }
                    }
                }
            }

        } else {
            for (int i = 0; i < 8; i++) {
                if (arr[i] == 2) {
                    rightPeople.add(i);
                    int k = boatSide * 100 + i * 10 + 9; // when someone is alone in the boat
                    acts.add(new Action(k));             // when someone is alone in the boat
                }
            }
            if (rightPeople.size() > 1) {
                for (int j = 0; j < rightPeople.size(); j++) {
                    for (int k = j + 1; k < rightPeople.size(); k++) {
                        CouplesState couplesState1 = couplesState.clone();
                        int[] arr1 = couplesState1.array;
                        arr1[rightPeople.get(j)] = 1;
                        arr1[rightPeople.get(k)] = 1;
                        if (isValidConfiguration(arr1)) {
                            int h = boatSide * 100 + rightPeople.get(j) * 10 + rightPeople.get(k);
                            acts.add(new Action(h));
                        }
                    }
                }
            }

        }
        return acts;
    }

    @Override
    public ArrayList<State> result(State s, Action a) {
        CouplesState couplesState = (CouplesState) s;
        CouplesState newState = couplesState.clone();
//        int[] arr = couplesState.array;
//        int boatSide = couplesState.boatSide;

        int code = a.actionCode;
        int sideCode = code / 100;
        int temp1 = code - sideCode * 100;
        int firstPersonIndex = temp1 / 10;
        int temp2 = temp1 - firstPersonIndex * 10;
        int secondPersonIndex = temp2;

        if (code == 888) { // moving toward right
            newState.boatSide = 2;
            for (int i = 0; i < 8; i++) {
                if (couplesState.array[i] == 1) {
                    newState.array[i] = 2;
                }
            }

        } else if (code == 999) { // moving toward left
            newState.boatSide = 1;
            for (int i = 0; i < 8; i++) {
                if (couplesState.array[i] == 1) {
                    newState.array[i] = 0;
                }
            }
        } else { //filling the boat
            if (secondPersonIndex != 9) {
                newState.array[firstPersonIndex] = 1;
                newState.array[secondPersonIndex] = 1;
            } else {
                newState.array[firstPersonIndex] = 1;
            }
        }
        ArrayList<State> singleState = new ArrayList<>();
        singleState.add(newState);
        return singleState;

    }

    @Override
    public boolean goalTest(State s) {
        boolean b = true;
        CouplesState couplesState = (CouplesState) s;
        for (int i = 0; i < 8; i++) {
            b = b && (couplesState.array[i] == 2);
        }
        return b;
    }

    @Override
    public int utility(Action a
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int heuristic(State s
    ) {
        CouplesState couplesState = (CouplesState) s;
        int a = 0;
        for (int i = 0; i < 8; i++) {
            if (couplesState.array[i] == 0) {
                a++;
            }
        }
        return a;
    }

    @Override
    public boolean isValidConfiguration(int[] array) {
        int a = 0;
        for (int i = 0; i < 8; i++) {
            if (array[i] == 1) {
                a++;
            }
        }
        if (a == 1) {
            return true;
        } else if (a == 2) {
            if (array[0] == 1) {
                if (array[1] == 1 || array[2] == 1 || array[4] == 1 || array[6] == 1) {
                    return true;
                } else {
                    return false;
                }
            }
            if (array[2] == 1) {
                if (array[3] == 1 || array[0] == 1 || array[4] == 1 || array[6] == 1) {
                    return true;
                } else {
                    return false;
                }
            }
            if (array[4] == 1) {
                if (array[5] == 1 || array[0] == 1 || array[2] == 1 || array[6] == 1) {
                    return true;
                } else {
                    return false;
                }
            }
            if (array[6] == 1) {
                if (array[7] == 1 || array[0] == 1 || array[2] == 1 || array[4] == 1) {
                    return true;
                } else {
                    return false;
                }
            }
            if (array[1] == 1) {
                if (array[0] == 1 || array[3] == 1 || array[5] == 1 || array[7] == 1) {
                    return true;
                } else {
                    return false;
                }
            }
            if (array[3] == 1) {
                if (array[2] == 1 || array[1] == 1 || array[5] == 1 || array[7] == 1) {
                    return true;
                } else {
                    return false;
                }
            }
            if (array[5] == 1) {
                if (array[4] == 1 || array[1] == 1 || array[3] == 1 || array[7] == 1) {
                    return true;
                } else {
                    return false;
                }
            }
            if (array[7] == 1) {
                if (array[6] == 1 || array[1] == 1 || array[3] == 1 || array[5] == 1) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

}

class CouplesState implements State {

    // 0 means he or she is on the left bank
    // 2 means he or she is on the right bank
    // 1 means he or she is on the boat
    int[] array = new int[8];
    int boatSide; // 1 if boat is on the left side and 2 if boat is on the right side

    public CouplesState() {
        for (int i = 0; i < 8; i++) {
            array[i] = 0;
        }
        boatSide = 1;
    }

    public CouplesState(int arr[], int boatSide) {
        for (int i = 0; i < 8; i++) {
            array[i] = arr[i];
        }
        this.boatSide = boatSide;
    }

//        array[1] = h1;
//        array[2] = w2;
//        array[3] = h2;
//        array[4] = w3;
//        array[5] = h3;
//        array[6] = w4;
//        array[7] = h4;
    @Override
    public CouplesState clone() {
        int[] arr = new int[8];
        int boatSide = this.boatSide;
        for (int i = 0; i < 8; i++) {
            arr[i] = array[i];
        }
        return new CouplesState(arr, boatSide);
    }

    @Override
    public boolean isEquals(State s) {
        if (s instanceof CouplesState) {
            CouplesState couplesState = (CouplesState) s;
            boolean b = (couplesState.boatSide == boatSide);
            for (int i = 0; i < 8; i++) {
                b = b && (array[i] == couplesState.array[i]);
            }
            return b;
        } else {
            return false;
        }

    }
}
