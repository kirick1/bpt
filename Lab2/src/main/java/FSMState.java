public class FSMState {
    abstract class State {
        public abstract State transition(char c, int stringLength, int index);
    }
    class Q0 extends State {
        public State transition(char c, int stringLength, int index) {
            if(Character.isDigit(c)) return new Q1();
            return new ERROR();
        }
    }
    class Q1 extends State {
        public State transition(char c, int stringLength, int index) {
            if(Character.isDigit(c)) return new Q1();
            else if(c == 'E') return new Q2();
            return new ERROR();
        }
    }
    class Q2 extends State {
        public State transition(char c, int stringLength, int index) {
            if(Character.isDigit(c)) {
                if(index == stringLength - 1) return new Q3();
                else return new Q1();
            }
            return new ERROR();
        }
    }
    class Q3 extends State {
        public State transition(char c, int stringLength, int index) { return null; }
    }
    class ERROR extends State {
        public State transition(char c, int stringLength, int index) { return null; }
    }
    private State currentState;
    FSMState() { this.currentState = new Q0(); }
    boolean fsm(String string) {
        State state = this.currentState;
        int stringLength = string.length();
        for(int i = 0; i < stringLength; i++) {
            char c = string.charAt(i);
            if(state.transition(c, stringLength, i) instanceof ERROR) return false;
            if(state.transition(c, stringLength, i) instanceof Q3) return true;
            state = state.transition(c, stringLength, i);
        }
        return false;
    }
    public static void main(String argv[]) {
        String stringTrue = "111E11E2";
        String stringFalse = "111E11EEE2";
        FSMState fsm = new FSMState();
        System.out.println(fsm.fsm(stringTrue));
        System.out.println(fsm.fsm(stringFalse));
    }
}
