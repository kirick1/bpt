import java.util.ArrayList;

public class FSMTable {
    public enum States { Q0, Q1, Q2, Q3, ERROR }
    public enum Events { DIGIT, LETTER, LAST, ERROR }
    public class Transition {
        States start;
        States finish;
        Events event;
        Transition(States startState, Events event, States finishEvent) {
            this.start = startState;
            this.finish = finishEvent;
            this.event = event;
        }
    }
    private ArrayList<Transition> transitions = new ArrayList<>();
    FSMTable() {
        // Q0
        this.transitions.add(new Transition(States.Q0, Events.DIGIT, States.Q1));
        this.transitions.add(new Transition(States.Q0, Events.LETTER, States.ERROR));
        this.transitions.add(new Transition(States.Q0, Events.LAST, States.ERROR));
        // Q1
        this.transitions.add(new Transition(States.Q1, Events.DIGIT, States.Q1));
        this.transitions.add(new Transition(States.Q1, Events.LETTER, States.Q2));
        this.transitions.add(new Transition(States.Q1, Events.LAST, States.ERROR));
        // Q2
        this.transitions.add(new Transition(States.Q2, Events.DIGIT, States.Q1));
        this.transitions.add(new Transition(States.Q2, Events.LETTER, States.ERROR));
        this.transitions.add(new Transition(States.Q2, Events.LAST, States.Q3));
    }
    private Events getEvent(char c, int stringLength, int index) {
        if(Character.isDigit(c)) {
            if(index == stringLength - 1) return Events.LAST;
            else return Events.DIGIT;
        } else if(c == 'E') return Events.LETTER;
        return Events.ERROR;
    }
    private States nextState(States startState, Events event) {
        for (Transition currentTransition : this.transitions) {
            if (currentTransition.start == startState && currentTransition.event == event) return currentTransition.finish;
        }
        return States.ERROR;
    }
    boolean fsm(String string) {
        States state = States.Q0;
        int stringLength = string.length();
        for(int i = 0; i < stringLength; i++) {
            char c = string.charAt(i);
            Events currentEvent = this.getEvent(c, stringLength, i);
            if(currentEvent == Events.ERROR) return false;
            else state = this.nextState(state, currentEvent);
            if(state == States.Q3) return true;
            if(state == States.ERROR) return false;
        }
        return false;
    }
    public static void main(String argv[]) {
        String stringTrue = "111E11E2";
        String stringFalse = "111E11EEE2";
        FSMTable fsm = new FSMTable();
        System.out.println(fsm.fsm(stringTrue));
        System.out.println(fsm.fsm(stringFalse));
    }
}
