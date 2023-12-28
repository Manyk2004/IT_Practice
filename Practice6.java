import java.util.*;

public class Practice6 {
    public static void main(String[] args) {
        System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.",
                "sworn love lived"));
        System.out.println(hiddenAnagram("An old west action hero actor", "Clint Eastwood"));
        System.out.println(hiddenAnagram("Mr. Mojo Rising could be a song title", "Jim Morrison"));
        System.out.println(hiddenAnagram("Banana? margaritas", "ANAGRAM"));
        System.out.println(hiddenAnagram("D  e b90it->?$ (c)a r...d,,#~", "bad credit"));
        System.out.println(hiddenAnagram("Bright is the moon", "Bongo mirth"));
        System.out.println();

        System.out.println(collect("intercontinentalisationalism", 6));
        System.out.println(collect("strengths", 3));
        System.out.println(collect("pneumonoultramicroscopicsilicovolcanoconiosis", 15));
        System.out.println();

        System.out.println(nicoCipher("myworldevolvesinhers", "tesh"));
        System.out.println(nicoCipher("andiloveherso", "tesha"));
        System.out.println(nicoCipher("mubashirhassan", "crazy"));
        System.out.println(nicoCipher("edabitisamazing", "matt"));
        System.out.println(nicoCipher("iloveher", "612345"));
        System.out.println();

        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 5, 15}, 45)));
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 15, 3, 5}, 45)));
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, -1, 4, 5, 6, 10, 7}, 20)));
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10)));
        System.out.println(Arrays.toString(twoProduct(new int[]{100, 12, 4, 1, 2}, 15)));
        System.out.println();

        System.out.println(Arrays.toString(isExact(6)));
        System.out.println(Arrays.toString(isExact(24)));
        System.out.println(Arrays.toString(isExact(125)));
        System.out.println(Arrays.toString(isExact(720)));
        System.out.println(Arrays.toString(isExact(1024)));
        System.out.println(Arrays.toString(isExact(40320)));
        System.out.println();

        System.out.println(fractions("0.(6)"));
        System.out.println(fractions("1.(1)"));
        System.out.println(fractions("3.(142857)"));
        System.out.println(fractions("0.19(2367)"));
        System.out.println(fractions("0.1097(3)"));
        System.out.println();

        System.out.println(pilish_string("HOWINEEDADRINKALCOHOLICINNATUREAFTERTHEHEAVYLECTURESINVOLVINGQUANTUMMECHANICSANDALLTHESECRETSOFTHEUNIVERSE"));
        System.out.println(pilish_string("33314444"));
        System.out.println(pilish_string("TOP"));
        System.out.println(pilish_string("X"));
        System.out.println(pilish_string(""));
        System.out.println();

        System.out.println(generateNonconsecutive("3 + 5 * (2 - 6)"));
        System.out.println(generateNonconsecutive("6 – 18 / (-1 + 4)"));
        System.out.println();

        System.out.println(isValid("aabbcd"));
        System.out.println(isValid("aabbccddeefghi"));
        System.out.println(isValid("abcdefghhgfedecba"));
        System.out.println();

        System.out.println(findLCS("abcd", "bd"));
        System.out.println(findLCS("aggtab", "gxtxamb"));
        System.out.println();

    }
    public static String hiddenAnagram(String sentence, String target) {
        sentence = sentence.replaceAll("[^a-zA-Z]", "").toLowerCase();
        target = target.replaceAll("[^a-zA-Z]", "").toLowerCase();

        for (int i = 0; i <= sentence.length() - target.length(); i++) {
            String substring = sentence.substring(i, i + target.length());
            char[] substringChars = substring.toCharArray();
            char[] targetChars = target.toCharArray();
            Arrays.sort(substringChars);
            Arrays.sort(targetChars);

            if (Arrays.equals(substringChars, targetChars)) {
                return substring;
            }
        }
        return "Not found";
    }

    public static List<String> collect(String word, int n) {
        Set<String> substrings = new HashSet<>();

        if (word.length() < n) {
            return new ArrayList<>(substrings);
        }

        String substring = word.substring(0, n);
        substrings.add(substring);

        List<String> restSubstrings = collect(word.substring(n), n);
        substrings.addAll(restSubstrings);

        String[] sortedArray = substrings.toArray(new String[0]);
        Arrays.sort(sortedArray);

        return new ArrayList<>(Arrays.asList(sortedArray));
    }

    public static String nicoCipher(String message, String key) {
        int[] keyNumbers = new int[key.length()]; // Step 1
        for (int i = 0; i < key.length(); i++) {
            keyNumbers[i] = key.charAt(i) - 'a' + 1;
        }

        int numRows = (int) Math.ceil((double) message.length() / key.length()); // Step 2
        char[][] grid = new char[numRows][key.length()];
        int messageIndex = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < key.length(); j++) {
                if (messageIndex < message.length()) {
                    grid[i][j] = message.charAt(messageIndex);
                } else {
                    grid[i][j] = ' ';
                }
                messageIndex++;
            }
        }

        for (int i = 0; i < key.length(); i++) { // Step 3
            int minIndex = i;
            for (int j = i + 1; j < key.length(); j++) {
                if (keyNumbers[j] < keyNumbers[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                keyNumbers[i] = keyNumbers[i] ^ keyNumbers[minIndex];
                keyNumbers[minIndex] = keyNumbers[i] ^ keyNumbers[minIndex];
                keyNumbers[i] = keyNumbers[i] ^ keyNumbers[minIndex];

                for (int k = 0; k < numRows; k++) {
                    char temp = grid[k][i];
                    grid[k][i] = grid[k][minIndex];
                    grid[k][minIndex] = temp;
                }
            }
        }

        StringBuilder encodedMessage = new StringBuilder(); // Step 4
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < key.length(); j++) {
                encodedMessage.append(grid[i][j]);
            }
        }

        return "\"" + encodedMessage + "\"";
    }

    public static int[] twoProduct(int[] arr, int n) {
        Map<Integer, Integer> numToIndex = new HashMap<>();
        int[] result = new int[2];

        for (int i = 0; i < arr.length; i++) {
            int current = arr[i];

            if (n % current == 0) {
                int complement = n / current;
                if (numToIndex.containsKey(complement) && numToIndex.get(complement) < i) {
                    result[0] = complement;
                    result[1] = current;
                    return result;
                }
            }
            numToIndex.put(current, i);
        }

        return new int[0];
    }

    public static int findFactorial(int n, int current, int factorial) {
        if (factorial == n)
            return current;
        if (factorial > n)
            return -1;
        return findFactorial(n, current + 1, factorial * (current + 1));
    }

    public static int[] isExact(int n) {
        int result = findFactorial(n, 0, 1);
        if (result != -1) {
            return new int[]{n, result};
        }
        return new int[0];
    }

    public static String fractions(String decimal) {
        int decimalPointIndex = decimal.indexOf(".");
        String wholePart = decimal.substring(0, decimalPointIndex);
        String nonRepeatingPart = decimal.substring(decimalPointIndex + 1, decimal.indexOf("("));
        String repeatingPart = decimal.substring(decimal.indexOf("(") + 1, decimal.indexOf(")"));

        int nonRepeatingLength = nonRepeatingPart.length();
        int repeatingLength = repeatingPart.length();

        long denominator = (long) Math.pow(10, nonRepeatingLength + repeatingLength) - (long) Math.pow(10, nonRepeatingLength);
        long numerator = Long.parseLong(wholePart + nonRepeatingPart + repeatingPart) - Long.parseLong(wholePart + nonRepeatingPart);

        long gcd = findGCD(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;

        return numerator + "/" + denominator;
    }

    public static long findGCD(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return findGCD(b, a % b);
        }
    }

    public static String pilish_string(String txt) {
        String piDigits = "314159265358979";
        StringBuilder result = new StringBuilder();

        int piIndex = 0;
        int txtIndex = 0;

        while (piIndex < piDigits.length() && txtIndex < txt.length()) {
            int currentPiDigit = Character.getNumericValue(piDigits.charAt(piIndex));
            int wordLength = currentPiDigit;

            if (txtIndex + wordLength > txt.length()) {
                int remainingLength = txt.length() - txtIndex;
                result.append(txt.substring(txtIndex, txtIndex + remainingLength));
                txtIndex += remainingLength;
                while (wordLength > remainingLength) {
                    result.append(txt.charAt(txtIndex - 1));
                    wordLength--;
                }
            } else {
                result.append(txt.substring(txtIndex, txtIndex + wordLength));
                txtIndex += wordLength;
            }

            if (piIndex < piDigits.length() - 1 && txtIndex != txt.length()) {
                result.append(" ");
            }
            piIndex++;
        }

        return "\"" + result + "\"";
    }

    public static int generateNonconsecutive(String expression) {
        try {
            String cleanedExpression = expression.replaceAll(" ", "");
            String postfix = infixToPostfix(cleanedExpression);
            return evaluatePostfix(postfix);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid expression: " + e.getMessage());
        }
    }

    public static int evaluatePostfix(String postfix) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = postfix.split(" ");

        for (String token : tokens) {
            if (isNumeric(token)) {
                stack.push(Integer.parseInt(token));
            } else if (isOperator(token)) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Not enough operands for operator: " + token);
                }
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                int result = applyOperator(operand1, operand2, token);
                stack.push(result);
            } else {
                throw new IllegalArgumentException("Invalid token: " + token);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return stack.pop();
    }

    public static String infixToPostfix(String expression) {
        StringBuilder postfix = new StringBuilder();
        Stack<String> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                int j = i;
                while (j < expression.length() && (Character.isDigit(expression.charAt(j)) || expression.charAt(j) == '.')) {
                    j++;
                }
                postfix.append(expression.substring(i, j)).append(" ");
                i = j - 1;
            } else if (isOperator(String.valueOf(c))) {
                while (!operators.isEmpty() && hasHigherPrecedence(operators.peek(), String.valueOf(c)) && !operators.peek().equals("(")) {
                    postfix.append(operators.pop()).append(" ");
                }
                operators.push(String.valueOf(c));
            } else if (c == '(') {
                operators.push("(");
            } else if (c == ')') {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    postfix.append(operators.pop()).append(" ");
                }
                if (!operators.isEmpty() && operators.peek().equals("(")) {
                    operators.pop();
                } else {
                    throw new IllegalArgumentException("Unmatched closing parenthesis");
                }
            }
        }

        while (!operators.isEmpty()) {
            if (operators.peek().equals("(")) {
                throw new IllegalArgumentException("Unmatched opening parenthesis");
            }
            postfix.append(operators.pop()).append(" ");
        }

        return postfix.toString().trim();
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private static boolean hasHigherPrecedence(String op1, String op2) {
        if (op1.equals("(")) {
            return false;
        }
        if ((op1.equals("+") || op1.equals("-")) && (op2.equals("*") || op2.equals("/"))) {
            return false;
        }
        return true;
    }

    private static int applyOperator(int operand1, int operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new IllegalArgumentException("Division by zero");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    public static String isValid(String str) {
        Map<Character, Integer> charFrequency = new HashMap<>();

        for (char c : str.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        Map<Integer, Integer> frequencyFrequency = new HashMap<>();

        for (int freq : charFrequency.values()) {
            frequencyFrequency.put(freq, frequencyFrequency.getOrDefault(freq, 0) + 1);
        }

        if (frequencyFrequency.size() == 1 || (frequencyFrequency.size() == 2 && (frequencyFrequency.containsValue(1) &&
                (Math.abs(frequencyFrequency.keySet().toArray(new Integer[0])[0] - frequencyFrequency.keySet().toArray(new Integer[0])[1]) == 1 ||
                        frequencyFrequency.containsValue(1) && frequencyFrequency.get(1) == 1)))) {
            return "ДА";
        }
        return "НЕТ";
    }

    public static String findLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int index = dp[m][n];
        char[] lcs = new char[index];

        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs[index - 1] = s1.charAt(i - 1);
                i--;
                j--;
                index--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return new String(lcs);
    }
}
