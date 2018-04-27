public class FSMSwitch {
    FSMSwitch() {}
    boolean fsm(String string) {
        FSMTable.States state = FSMTable.States.Q0;
        int stringLength = string.length();
        for(int i = 0; i < stringLength && state != FSMTable.States.ERROR; i++) {
            char c = string.charAt(i);
            switch(state) {
                case Q0:
                    if(Character.isDigit(c)) state = FSMTable.States.Q1;
                    else state = FSMTable.States.ERROR;
                    break;
                case Q1:
                    if(Character.isDigit(c)) state = FSMTable.States.Q1;
                    else if(c == 'E') state = FSMTable.States.Q2;
                    else state = FSMTable.States.ERROR;
                    break;
                case Q2:
                    if(Character.isDigit(c)) {
                        if (i == stringLength - 1) return true;
                        else state = FSMTable.States.Q1;
                    } else state = FSMTable.States.ERROR;
                    break;
            }
        }
        return false;
    }
    public static void main(String argv[]) {
        String stringTrue = "111E11E2";
        String stringFalse = "111E11EEE2";
        FSMSwitch fsm = new FSMSwitch();
        System.out.println(fsm.fsm(stringTrue));
        System.out.println(fsm.fsm(stringFalse));
    }
}
