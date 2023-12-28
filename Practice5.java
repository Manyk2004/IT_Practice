import java.util.ArrayList;
import java.util.Arrays;

public class Practice5 {
    public static void main(String[] args) {

        System.out.println(sameLetterPattern("ABAB", "CDCD")); // number 1
        System.out.println(sameLetterPattern("ABCBA", "BCDCB"));
        System.out.println(sameLetterPattern("FFGG", "CDCD"));
        System.out.println(sameLetterPattern("FFFF", "ABCD"));
        System.out.println();

        System.out.println(spiderVsFly("H3", "E2")); // number 2
        System.out.println(spiderVsFly("A4", "H4"));
        System.out.println(spiderVsFly("A2", "B3"));
        System.out.println();

        System.out.println(digitCount(4666)); // number 3
        System.out.println(digitCount(544));
        System.out.println(digitCount(121317));
        System.out.println(digitCount(0));
        System.out.println(digitCount(12345));
        System.out.println(digitCount(1289396387328L));
        System.out.println();

        String[] arr1 = {"cat", "create", "sat"}; // number 4
        String[] arr2 = {"trance", "recant"};
        String[] arr3 = {"dote", "dotes", "toes", "set", "dot", "dots", "sted"};
        System.out.println(totalPoints(arr1, "caster"));
        System.out.println(totalPoints(arr2, "recant"));
        System.out.println(totalPoints(arr3, "tossed"));
        System.out.println();

        int[] arr11 = {1, 2, 3, 4, 5}; // number 5
        int[] arr12 = {1, 2, 3, 7, 9};
        int[] arr13 = {10, 9, 7, 2, 8};
        int[] arr14 = {1, 6, 5, 4, 8, 2, 3, 7};
        System.out.println(sumsUp(arr11));
        System.out.println(sumsUp(arr12));
        System.out.println(sumsUp(arr13));
        System.out.println(sumsUp(arr14));
        System.out.println();

        String[] arr21 = {"95%", "83%", "90%", "87%", "88%", "93%"}; // number 6
        String[] arr22 = {"10%"};
        String[] arr23 = {"53%", "79%"};
        System.out.println(takeDownAverage(arr21));
        System.out.println(takeDownAverage(arr22));
        System.out.println(takeDownAverage(arr23));
        System.out.println();

        System.out.println(caesarCipher("encode", "hello world", 3)); // number 7
        System.out.println(caesarCipher("decode", "almost last task!", 4));
        System.out.println();

        System.out.println(setSetup(5, 3)); // number 8
        System.out.println(setSetup(7, 3));
        System.out.println();

        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra")); // number 9
        System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome"));
        System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing"));
        System.out.println();

        System.out.println(isNew(3)); // number 10
        System.out.println(isNew(30));
        System.out.println(isNew(321));
        System.out.println(isNew(123));
    }

    public static boolean sameLetterPattern(String s1, String s2){ // number 1
        if (s1.length() != s2.length())
            return false;
        ArrayList<Integer> difference = new ArrayList<>();
        for (int i = 0; i < s1.length() - 1; i++)
            difference.add(s1.charAt(i) - s1.charAt(i + 1));
        for (int i = 0; i < s2.length() - 1; i++)
            if (s2.charAt(i) - s2.charAt(i + 1) != difference.get(i))
                return false;
        return true;
    }

    public static String spiderVsFly(String spiderCord, String flyCord){ // number 2
        int spiderSymbol = spiderCord.charAt(0) - 65;
        int spiderNumber = Integer.parseInt(spiderCord.substring(1, 2));
        int flySymbol = flyCord.charAt(0) - 65;
        int flyNumber = Integer.parseInt(flyCord.substring(1, 2));
        if (flyNumber == 0)
            flySymbol = 0;

        double straightWay = 1;
        double[] ways = new double[5];
        for (int i = 0; i < 5; i++)
            ways[i] = i * Math.sqrt(2 - Math.sqrt(2));

        StringBuilder strWay1 = new StringBuilder(spiderCord);
        double intWay1 = 0;
        int nowSpiderSymbol1 = spiderSymbol;
        int nowSpiderNumber1 = spiderNumber;
        while (nowSpiderSymbol1 != flySymbol || nowSpiderNumber1 != flyNumber) {
            if (nowSpiderNumber1 == 0) {
                nowSpiderSymbol1 = flySymbol;
                nowSpiderNumber1 = 1;
            } else if (nowSpiderSymbol1 == spiderSymbol) {
                nowSpiderNumber1 -= 1;
                if (nowSpiderNumber1 == 0) nowSpiderSymbol1 = 0;
            } else {
                nowSpiderNumber1 += 1;
            }
            strWay1.append("-").append((char) (nowSpiderSymbol1 + 65)).append(nowSpiderNumber1);
            intWay1 += straightWay;
        }

        StringBuilder strWay2 = new StringBuilder(spiderCord);
        double intWay2 = 0;
        int nowSpiderSymbol2 = spiderSymbol;
        int nowSpiderNumber2 = spiderNumber;
        if (nowSpiderNumber2 > flyNumber) {
            while (nowSpiderNumber2 != flyNumber) {
                nowSpiderNumber2 -= 1;
                strWay2.append("-").append((char) (nowSpiderSymbol2 + 65)).append(nowSpiderNumber2);
                intWay2 += straightWay;
            }
            while (nowSpiderSymbol2 != flySymbol) {
                int clock = flySymbol - nowSpiderSymbol2;
                if (clock < 0)
                    clock += 8;
                if (clock <= 4)
                    nowSpiderSymbol2 = (nowSpiderSymbol2 + 1) % 8;
                else
                    nowSpiderSymbol2 = (nowSpiderSymbol2 + 7) % 8;
                strWay2.append("-").append((char) (nowSpiderSymbol2 + 65)).append(nowSpiderNumber2);
                intWay2 += ways[nowSpiderNumber2];
            }
        }
        else {
            while (nowSpiderSymbol2 != flySymbol) {
                int clock = flySymbol - nowSpiderSymbol2;
                if (clock < 0)
                    clock += 8;
                if (clock <= 4)
                    nowSpiderSymbol2 = (nowSpiderSymbol2 + 1) % 8;
                else
                    nowSpiderSymbol2 = (nowSpiderSymbol2 + 7) % 8;
                strWay2.append("-").append((char) (nowSpiderSymbol2 + 65)).append(nowSpiderNumber2);
                intWay2 += ways[nowSpiderNumber2];
            }
            while (nowSpiderNumber2 != flyNumber) {
                nowSpiderNumber2 += 1;
                strWay2.append("-").append((char) (nowSpiderSymbol2 + 65)).append(nowSpiderNumber2);
                intWay2 += straightWay;
            }
        }
        if (intWay1 <= intWay2) {
            return strWay1.toString();
        }
        return strWay2.toString();
    }

    public static int digitCount(long num){ // number 3
        if (num < 10 && num >= 0)
            return 1;
        else if (num < 0)
            return 0;
        return 1 + digitCount(num / 10);
    }

    public static int totalPoints(String[] mass, String s){ // number 4
        ArrayList<String> uniqueWords = new ArrayList<>();
        check:
        for (String word: mass) {
            for (String uniqueWord : uniqueWords)
                if (uniqueWord.equals(word))
                    continue check;
            uniqueWords.add(word);
        }
        int sum = 0;
        wordCheck:
        for (String word: uniqueWords) {
            if (word.length() > 2)
                for (char c: word.toCharArray()) {
                    int symbolCount1 = word.length() - word.replace(String.valueOf(c), "").length();
                    int symbolCount2 = s.length() - s.replace(String.valueOf(c), "").length();
                    if (symbolCount1 > symbolCount2)
                        continue wordCheck;
                }
            sum += word.length() - 2;
            if (word.length() == 6)
                sum += 50;
        }
        return sum;
    }

    public static String sumsUp(int[] arr){ // number 5
        ArrayList<String> answer = new ArrayList<>();
        ArrayList<Integer> numsBefore = new ArrayList<>();
        check:
        for (int num: arr) {
            for(int i = 0; i < numsBefore.size(); i++) {
                int numBefore = numsBefore.get(i);
                if (num + numBefore == 8) {
                    answer.add(Arrays.toString(new int[] {Math.min(num, numBefore), Math.max(num, numBefore)}));
                    numsBefore.remove(i);
                    continue check;
                }
            }
            numsBefore.add(num);
        }
        return Arrays.toString(answer.toArray());
    }

    public static int takeDownAverage(String[] marks){ // number 6
        int sum = 0;
        for (int i = 0; i < marks.length; i++) {
            int num = Integer.parseInt(marks[i].substring(0, marks[i].length() - 1));
            sum += num;
        }
        int newMark = ((sum / marks.length) - 5) * (marks.length + 1);
        return newMark - sum;
    }

    public static String caesarCipher(String mode, String mess, int shift){ // number 7
        char[] message = mess.toUpperCase().toCharArray();
        if (!mode.equals("decode") && !mode.equals("encode"))
            return "Incorrect mode";
        for (int i = 0; i < message.length; i++) {
            if ((message[i] > 65 && message[i] <= 90 && mode.equals("encode")) ||
                    (message[i] >= 65 && message[i] < 90 && mode.equals("decode"))){
                message[i] += shift;
            }
        }
        return new String(message);
    }

    public static int setSetup(int n, int k){ // number 8
        if (k == 1)
            return n;
        return (n - k + 1) * setSetup(n, k - 1);
    }

    public static String timeDifference(String city1, String time, String city2){ // number 9
        int time1 = timeZone(city1);
        int time2 = timeZone(city2);
        int deltaTime = time2 - time1;
        int deltaHour = deltaTime / 60;
        int deltaMinutes = deltaTime % 60;

        String[] date = time.split(" ");
        int month = monthIndex(date[0]);
        int day = Integer.parseInt(date[1].replace(",", ""));
        int year = Integer.parseInt(date[2]);
        String[] hour_minutes = date[3].split(":");
        int hour = Integer.parseInt(hour_minutes[0]);
        int minute = Integer.parseInt(hour_minutes[1]);

        int newYear = year;
        int newMonth = month;
        int newDay = day;
        int newHour = hour + deltaHour;
        int newMinute = minute + deltaMinutes;

        if (newMinute < 0) {
            newMinute += 60;
            newHour -= 1;
        }
        else if (newMinute >= 60) {
            newMinute -= 60;
            newHour += 1;
        }
        if (newHour < 0) {
            newHour += 24;
            newDay -= 1;
        }
        else if (newHour >= 24) {
            newHour -= 24;
            newDay += 1;
        }
        if (newDay == 0) {
            newMonth -= 1;
            if (newMonth == 0) {
                newMonth = 12;
                newYear -= 1;
            }
            newDay = dayInMonth(newMonth, newYear);
        }
        else if (newDay > dayInMonth(newMonth, newYear)) {
            newMonth += 1;
            if (newMonth == 13) {
                newMonth = 1;
                newYear += 1;
            }
            newDay = 1;
        }

        String strMinute;
        if (newMinute < 10)
            strMinute = "0" + newMinute;
        else
            strMinute = Integer.toString(newMinute);
        String strHour;
        if (newHour < 10)
            strHour = "0" + newHour;
        else
            strHour = Integer.toString(newHour);
        return newYear + "-" + newMonth + "-" + newDay + " " + strHour + ":" + strMinute;
    }
    public static int timeZone(String city) {
        int time = 0;
        switch (city) {
            case "Los Angeles" -> time = -8 * 60;
            case "New York" -> time = -5 * 60;
            case "Caracas" -> time = -(4 * 60 + 30);
            case "Buenos Aires" -> time = -3 * 60;
            case "London" -> time = 0;
            case "Rome" -> time = 60;
            case "Moscow" -> time = 3 * 60;
            case "Tehran" -> time = 3 * 60 + 30;
            case "New Delhi" -> time = 5 * 60 + 30;
            case "Beijing" -> time = 8 * 60;
            case "Canberra" -> time = 10 * 60;
        }
        return time;
    }
    public static int monthIndex(String month) {
        int index = 0;
        switch (month) {
            case "January" -> index = 1;
            case "February" -> index = 2;
            case "March" -> index = 3;
            case "April" -> index = 4;
            case "May" -> index = 5;
            case "June" -> index = 6;
            case "July" -> index = 7;
            case "August" -> index = 8;
            case "September" -> index = 9;
            case "October" -> index = 10;
            case "November" -> index = 11;
            case "December" -> index = 12;
        }
        return index;
    }
    public static int dayInMonth(int monthIndex, int year) {
        int days = 0;
        switch (monthIndex) {
            case 1, 3, 5, 7, 8, 10, 12 -> days = 31;
            case 4, 6, 9, 11 -> days = 30;
            case 2 -> {
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                    days = 29;
                }
                else {
                    days = 28;
                }
            }
        }
        return days;
    }

    public static boolean isNew(int num){ // number 10
        if (num / 10 <= 1)
            return true;
        char[] number = String.valueOf(num).toCharArray();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < number.length; i++)
            if (number[i] != '0')
                s.append(number[i]);
        if (s.length() <= 1)
            return true;
        char[] nums = s.toString().toCharArray();
        for (int j = 0; j < s.length() - 1; j++)
            if (nums[j] > nums[j + 1])
                return false;
        return true;
    }
}
