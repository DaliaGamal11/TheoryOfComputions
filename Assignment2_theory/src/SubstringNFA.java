import java.util.HashSet;
import java.util.Set;

public class SubstringNFA {
    private Set<Integer> currentState;

    public SubstringNFA() {
        currentState = new HashSet<>();
        currentState.add(0);
    }

    private void transition(char input) {
        Set<Integer> nextState = new HashSet<>();
        for (int state : currentState) {
            if (state == 0 && input == '1') {
                nextState.add(1);
            }
            if (state == 0 && input == '0') {
                nextState.add(0);
            }
            if (state == 1 && input == '0') {
                nextState.add(2);
            }
            if (state == 1 && input == '1') {
                nextState.add(1);
            }
            if (state == 2 && input == '1') {
                nextState.add(3);
            }
            if (state == 2 && input == '0') {
                nextState.add(0);
            }
            if (state == 3 && (input == '0' || input == '1')) {
                nextState.add(3);
            }
            if (state == 0 && input == '0') {
                nextState.add(4);
            }
            if (state == 4 && input == '1') {
                nextState.add(5);
            }
            if (state == 5 && input == '0') {
                nextState.add(6);
            }
            if (state == 5 && input == '1') {
                nextState.add(3);
            }
            if (state == 6 && (input == '0' || input == '1')) {
                nextState.add(6);
            }
        }
        currentState = nextState;
    }

    public boolean acceptsString(String input) {
        // Reset currentState before processing the input string
        currentState.clear();
        currentState.add(0);

        for (char c : input.toCharArray()) {
            transition(c);
            if (currentState.contains(3) || currentState.contains(6)) {
                return true;
            }
        }
        return false;
    }
}