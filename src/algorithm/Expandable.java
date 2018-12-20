/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

/**
 *
 * @author hamid
 */

import java.util.ArrayList;
import java.util.Comparator;
import problems.Action;
import problems.State;

public class Expandable {
    State state;
    ArrayList<Action> actionSequence;

    int cost = 0;

    public int getCost(){ return cost; }
    public void setCost(int c) { cost = c; }

    public static Comparator<Expandable> costComparator = new Comparator<Expandable>(){
        @Override
        public int compare(Expandable c1, Expandable c2) {
            return (int) (c1.getCost() - c2.getCost());
        }
    };
}




