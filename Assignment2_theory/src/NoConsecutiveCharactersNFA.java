public class NoConsecutiveCharactersNFA {
    private final int[][] transitionTable = {
            {1, 2},
            {-1, 0},
            {0, -1}
    };

    private final int[] acceptingStates = {0, 1, 2}; // All states are accepting states

    public boolean isAcceptingState(int state) {
        for (int acceptingState : acceptingStates) {
            if (state == acceptingState) {
                return true;
            }
        }
        return false;
    }

    public boolean isAccepted(String input) {
        int currentState = 0;
        for (char c : input.toCharArray()) {
            int charIndex = (c == '0') ? 0 : (c == '1') ? 1 : -1; // Determine the character index
            if (charIndex == -1) return false; // Return false for invalid characters
            currentState = transitionTable[currentState][charIndex]; // Transition to the next state
            if (currentState == -1) {
                return false;
            }
        }

        return isAcceptingState(currentState);
    }
}