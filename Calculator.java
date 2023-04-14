import java.util.Scanner;

class Calculator {
    static Scanner sc = new Scanner(System.in);
    static int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    static String[] rim = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    public static void main(String[] args) {
        System.out.println( Main.calc(sc.nextLine()));
    }

    static class Main {
        public static String calc(String input) {
            String[] chisl = input.split(" ");
            String num1 = chisl[0];
            char op = chisl[1].charAt(0);
            String num2 = chisl[2];
            if (!vmassiv(num1) || !vmassiv(num2)) {
                throw new RuntimeException("Не входят в диапазон");
            }

            if (!proverkachisl(num1, num2)) {
                throw new RuntimeException("Числа разной стилистики");
            }
            int xx = perevodtoarabic(num1);
            int yy = perevodtoarabic(num2);

            int result = proverkaOP(op, xx, yy);
            String stringresult;
            if (araborrim(num1)) {
                stringresult = arabtorim(result);
            } else {
                stringresult = Integer.toString(result);
            }
            return stringresult;

        }
    }

    public static boolean araborrim(String num) {
        boolean result = false;
        for (int i = 0; i < rim.length; i++) {
            if (rim[i].equals(num)) {
                result = true;
                break;
            } else {
                result = false;
            }
        }
        return result;
    }

    public static boolean vmassiv(String num) {
        for (int i = 0; i < rim.length; i++) {
            if (rim[i].equals(num)) {
                return true;
            }
            for (int ii = 0; ii < numbers.length; ii++) {
                if (numbers[ii] == perevodtoarabic(num)) {
                    return true;
                }
            }
        }

        return false;
    }


    public static int proverkaOP(char op, int x, int y) {
        int result = 0;
        switch (op) {
            case '+' -> {
                result = x + y;
            }
            case '-' -> {
                result = x - y;
            }
            case '*' -> {
                result = x * y;
            }
            case '/' -> {
                result = x / y;
            }
            default -> throw new RuntimeException("Неверная операция");
        }
        return result;
    }

    public static boolean proverkachisl(String num1, String num2) {
        boolean flag1 = false;
        boolean flag2 = false;
        for (int i = 0; i < rim.length; i++) {
            if (rim[i].equals(num1))
                flag1 = true;
        }
        for (int i = 0; i < rim.length; i++) {
            if (rim[i].equals(num2)) {
                flag2 = true;
            }
        }

        if (flag1 == flag2) {
            return true;
        } else {
            return false;
        }

    }

    static int perevodtoarabic(String num) {
        for (int i = 0; i < rim.length; i++) {
            if (rim[i].equals(num)) {
                return rimtoarab(num);
            }
        }
        return Integer.parseInt(num);

    }


    static int rimtoarab(String num) {
        return switch (num) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> -1;
        };
    }

    static String arabtorim(int num) {
        if(num <= 0 || num > 100){
            throw new RuntimeException("Нет отрицательных чисел");
        }
        int[] values = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanLiterals = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        String roman = "";
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                roman += romanLiterals[i];
            }
        }
        return roman;
    }
}
