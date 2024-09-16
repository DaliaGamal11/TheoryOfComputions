public class EqualOccurrencesNFA {
    // States
    private static final int q0 = 0;
    private static final int q1 = 1;
    private static final int q2 = 2;
    private static final int q3 = 3; // Additional state
    private static final int q4 = 4; // Additional state
    private static final int q5 = 5; // Additional state
    private static final int q6 = 6; // Additional state
    private static final int q7 = 7; // Additional state

    // Alphabet
    private static final char[] alphabet = {'0', '1'};
    // Transition Function
    // Transition Function
    private static final int[][] transition = {
            {q1, -1},   // From q0
            {-1, q2},   // From q1
            {q3, q2},   // From q2
            {q1, q4},   // From q3 (Transition to q1 on '0')
            {q5, q4},   // From q4 (Transition to q5 on '1' after '100')
            {q5, q6},   // From q5 (Additional transition from q5 on '0')
            {q7, q4},   // From q6 (Transition to q7 on '1' after '1001')
            {q7, q7},   // From q7 (Accepting state)
            {q7, q4},   // Additional transition from q4 on '1'
            {q7, q7},   // Additional transition from q7 on '0'
            {q7, q4},   // Additional transition from q7 on '1' after '1001'
            {q7, q7}    // Additional transition from q7 on '0' after '1001'
    };



    // Start State
    private static final int startState = q0;

    // Accepting States
    private static final int[] acceptingStates = {q0, q1, q2, q3, q4, q5, q6, q7};

    public static boolean accepts(String input) {
        int currentState = startState;
        for (char symbol : input.toCharArray()) {
            int symbolIndex = getSymbolIndex(symbol);
            if (symbolIndex == -1) {
                return false; // Invalid input symbol
            }
            currentState = transition[currentState][symbolIndex];
            if (currentState == -1) {
                return false; // Invalid transition
            }
        }
        // Check if the current state is an accepting state
        return isAcceptingState(currentState);
    }

    private static int getSymbolIndex(char symbol) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == symbol) {
                return i;
            }
        }
        return -1; // Symbol not found in the alphabet
    }

    private static boolean isAcceptingState(int state) {
        for (int acceptingState : acceptingStates) {
            if (state == acceptingState) {
                return true;
            }
        }
        return false;
    }
}
