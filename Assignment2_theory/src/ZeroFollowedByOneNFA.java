public class ZeroFollowedByOneNFA {
    private final int[][][] transitions;
    private final int[] acceptingStates;

    public ZeroFollowedByOneNFA() {
        transitions = new int[][][]{
                {{1}, {0}}, // State 0: If '0', go to state 1. If '1', stay in state 0.
                {{-1}, {0}}, // State 1: If '0', go to invalid state. If '1', go back to state 0.
        };
        acceptingStates = new int[]{0}; // Only state 0 is an accepting state
    }

    public boolean isAccepted(String input) {
        int currentState = 0;
        for (char c : input.toCharArray()) {
            int inputIndex = (c == '0') ? 0 : (c == '1') ? 1 : -1;
            if (inputIndex == -1) return false; // Invalid character
            currentState = transitions[currentState][inputIndex][0];
            if (currentState == -1) return false; // Invalid transition
        }
        return currentState == 0; // Accept if in state 0
    }
}