import java.io.*;
import java.util.*;


public class Main {
    public static String checkNoSubstringBA(String input) {
        // DFA Transition Table
        int[][] transitionTable = {
                {0, 1}, // State 1: Haven't encountered 'b' after 'a' yet; On 'a', transition to State 1; On 'b', transition to State 2
                {2, 1}, // State 2: Have encountered 'b' after 'a'; On 'a', transition to State 3 (dead state); On 'b', stay in State 2
                {2, 2}  // State 3: Dead state for strings containing 'ba'; Stay in State 3 on any input
        };

        // Start state
        int currentState = 0;

        // Accepting states
        int[] acceptingStates = {0, 1};

        for (char c : input.toCharArray()) {
            int charIndex = (c == 'a') ? 0 : 1;
            currentState = transitionTable[currentState ][charIndex];
        }

        for (int state : acceptingStates) {
            if (currentState == state) {
                return "True";
            }
        }

        return "False";
    }
    public static String checkEven0FollowedBy1(String input) {
        // DFA Transition Table
        int[][] transitionTable = {
                // State 0: Initial state
                // On '0', transition to State 1; On '1', stay in State 0
                {1, 0},
                // State 1: Odd number of '0's encountered
                // On '0', stay in State 1; On '1', transition to State 0
                {1, 0}
        };

        // Start state
        int currentState = 0;

        // Process each character in the input string
        for (char c : input.toCharArray()) {
            int charIndex = (c == '0') ? 0 : (c == '1') ? 1 : -1; // Determine the character index
            if (charIndex == -1) return "False"; // Return false for invalid characters
            currentState = transitionTable[currentState][charIndex]; // Transition to the next state
        }

        // Check if the final state is an accepting state
        if (currentState == 0) {
            return "True";
        } else {
            return "False";
        }
    }

    public static String acceptOddX(String input) {
        // DFA Transition Table
        int[][] transitionTable = {
                // State 1: Initial state
                // On 'x', transition to State 2; On 'y', stay in State 1
                {1, 0},
                // State 2: Odd number of 'x's encountered
                // On 'x', transition to State 1; On 'y', stay in State 2
                {0, 1}
        };

        // Start state
        int currentState = 0;

        // Process each character in the input string
        for (char c : input.toCharArray()) {
            int charIndex = (c == 'x') ? 0 : (c == 'y') ? 1 : -1; // Determine the character index
            if (charIndex == -1) return "False"; // Return false for invalid characters
            currentState = transitionTable[currentState ][charIndex]; // Transition to the next state
        }

        // Check if the final state is an accepting state
        return (currentState == 1) ? "True" : "False";
    }

    public static String StartAndEnd(String input) {
        // DFA Transition Table
        int[][] transitionTable = {
                // State 1: Initial state
                // On 'a', transition to State 2; On 'b', transition to State 3
                {1, 2},
                // State 2: String ends with 'a'
                // On 'a', stay in State 2; On 'b', transition to State 4
                {1, 3},
                // State 3: String ends with 'b'
                // On 'a', transition to State 4; On 'b', stay in State 3
                {3, 2},
                // State 4: Accepted state (string starts and ends with the same character)
                // On any input, stay in State 4
                {3, 3}
        };
        // If the input string has only one character, return "True"
        if (input.length() == 1) {
            return "True";
        }

        // Check if all characters in the string are the same
        char firstChar = input.charAt(0);
        boolean allSame = true;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) != firstChar) {
                allSame = false;
                break;
            }
        }

        // If all characters are the same, return "True"
        if (allSame) {
            return "True";
        }



        // Start state
        int currentState = 0;

        // Process each character in the input string
        for (char c : input.toCharArray()) {
            int charIndex = (c == 'a') ? 0 : (c == 'b') ? 1 : -1; // Determine the character index
            if (charIndex == -1) return "False"; // Return false for invalid characters
            currentState = transitionTable[currentState ][charIndex]; // Transition to the next state
        }

        // Check if the final state is an accepting state
        return (currentState == 3 && input.charAt(0) == input.charAt(input.length() - 1)) ? "True" : "False";
    }
    public static String divisibleByFour(String input) {
        int[][] transitionTable = {
                // State 0: Initial state
                // On '0', transition to State 0 if last bit was '0'; transition to State 1 if last bit was '1'
                // On '1', transition to State 2 if last bit was '0'; transition to State 0 if last bit was '1'
                {0, 1},
                // State 1: Second bit '0'
                // On '0', transition to State 2
                // On '1', transition to State 0
                {2, 0},
                // State 2: Second bit '1'
                // On '0', transition to State 1
                // On '1', transition to State 2
                {1, 2}
        };

        int currentState = 0;
        for (char c : input.toCharArray()) {
            int charIndex = (c == '0') ? 0 : (c == '1') ? 1 : -1; // Determine the character index
            if (charIndex == -1) return "False"; // Return false for invalid characters
            currentState = transitionTable[currentState][charIndex];
        }

        // Check if the final state is an accepting state (State 2)
        return (currentState == 2) ? "False" : "True";
    }
    public static String acceptsAllStrings(String input) {
        // Define DFA states
        final int Q0 = 0;
        final int Q1 = 1;
        final int Q2 = 2;
        final int Q3 = 3; // Accepting state

        // Split input into individual strings
        String[] inputStrings = input.split("\n");

        // Apply DFA to each input string
        StringBuilder resultBuilder = new StringBuilder();
        for (String inputString : inputStrings) {
            int currentState = Q0;
            for (char c : inputString.toCharArray()) {
                switch (currentState) {
                    case Q0:
                        if (c == '0') currentState = Q0;
                        else if (c == '1') currentState = Q1;
                        break;
                    case Q1:
                        if (c == '0') currentState = Q0;
                        else if (c == '1') currentState = Q2;
                        break;
                    case Q2:
                        if (c == '0') currentState = Q0;
                        else if (c == '1') currentState = Q3;
                        break;
                    case Q3:
                        currentState = Q3;
                        break;
                    default:
                        currentState = -1; // Invalid state
                        break;
                }
            }
            // Check if the final state is Q0 or Q1 and append the result to the result builder
            resultBuilder.append((currentState == Q0 || currentState == Q1) ? "True" : "False");
            if (inputString != inputStrings[inputStrings.length - 1]) { // Add newline character if not the last input string
                resultBuilder.append("\n");
            }
        }

        // Return the results
        return resultBuilder.toString();
    }
    public static void main(String[] args) {

        String inputPath = "input.txt";
        String outputPath = "output.txt";

        try (Scanner sc = new Scanner(new File(inputPath));
             FileWriter fw = new FileWriter(outputPath)) {

            int problem = 0;
            String line;

            while (sc.hasNextLine()) {
                line = sc.nextLine().trim(); // Trim to remove leading/trailing whitespace

                if (line.isEmpty()) continue; // Skip empty lines

                if (problem < 1) {
                    try {
                        problem = Integer.parseInt(line);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid problem number: " + line);
                        problem = 0;
                    }
                    fw.write(problem + "\n");
                } else if (line.equals("end")||line.equals("End")) {
                    fw.write("x\n");
                    problem = 0;
                } else {
                    switch (problem) {
                        case 1:
                            fw.write(checkNoSubstringBA(line) + "\n");
                            break;
                        case 2:
                            fw.write(checkEven0FollowedBy1(line) + "\n");
                            break;
                        case 3:
                            fw.write(acceptOddX(line)+"\n");
                            break;
                        case 4:
                            fw.write(StartAndEnd(line)+"\n");
                            break;
                        case 5:
                            fw.write(divisibleByFour(line)+"\n");
                            break;
                        case 6:
                            fw.write(acceptsAllStrings(line)+"\n");
                            break;
                        case 7:
                            fw.write(EqualOccurrencesNFA.accepts(line) ? "True\n" : "False\n");
                            break;
                        case 8:
                            SubstringNFA nfa = new SubstringNFA();
                            fw.write(nfa.acceptsString(line) ? "True\n" : "False\n");
                            break;
                        case 9:
                            NoConsecutiveCharactersNFA nfa2 = new NoConsecutiveCharactersNFA();
                            fw.write(nfa2.isAccepted(line) ? "True\n" : "False\n");
                            break;
                        case 10:
                            ZeroFollowedByOneNFA nfa3 = new ZeroFollowedByOneNFA();
                            fw.write(nfa3.isAccepted(line) ? "True\n" : "False\n");
                            break;


                        default:
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error writing to output file: " + e.getMessage());
        }
    }


}
