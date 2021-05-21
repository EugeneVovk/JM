package calculator;

import java.util.*;

public class Test1 {

    public static final String RED = "\033[0;31m";
    public static final String RESET = "\033[0m";

    public static void main(String[] args) {

        //  получаем вводные данные от пользователя и записываем в строку
        Scanner scanner = new Scanner(System.in);
        String smb = scanner.nextLine();
        if (smb.length() < 5) {
            error();
            System.out.println("Данные передаются в одну строку, через пробел, например: 1 + 2 или VI / III");
        }
        smb = smb.toUpperCase();
        //  делим строку на эл-ты и записываем их в массив
        String[] numbers = smb.split(" ");

        //  массивы цифр для поиска соответствий
        String[] roman = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] arabic = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        //  определяем цифры
        boolean rome_a = false;
        boolean rome_b = false;
        boolean arab_a = false;
        boolean arab_b = false;
        //  выбираем из массива оператор и переводим его в чар
        char c = numbers[1].charAt(0);
        //  a и b - числа введённые пользотелем
        int a = 0;
        int b = 0;
        int res;
        //  поиск по массивам цифр, ищем "ключ-значение"
        for (int i = 1; i <= roman.length; i++) {
            //  сравниваем, находим соответствия
            if (numbers[0].equals(roman[i - 1])) {
                a = i;
                rome_a = true;
            }
            if (numbers[2].equals(roman[i - 1])) {
                b = i;
                rome_b = true;
            }
        }
        for (int i = 1; i <= arabic.length; i++) {
            if (numbers[0].equals(arabic[i - 1])) {
                a = i;
                arab_a = true;
            }
            if (numbers[2].equals(arabic[i - 1])) {
                b = i;
                arab_b = true;
            }
        }
        //  с какими цифрами имем дело?
        if (rome_a && rome_b) {
            res = operations(c, a, b);
            System.out.println(romeConverter(res));
        } else if (arab_a && arab_b) {
            System.out.println(operations(c, a, b));
        } else {
            error();
            System.out.println("Калькулятор принимает на вход только " +
                    "арабские целые числа от 1 до 10 включительно или римские от I до X, не более.");
        }
    }

    public static void error() {
        System.out.print(RED + "ERROR: " + RESET);
    }

    public static int operations(char symbol, int number1, int number2) {
        int res = 0;
        switch (symbol) {
            case '+' -> res = number1 + number2;
            case '-' -> res = number1 - number2;
            case '*' -> res = number1 * number2;
            case '/' -> res = number1 / number2;
            default -> {
                error();
                System.out.println("Калькулятор умеет выполнять операции " +
                        "сложения, вычитания, умножения и деления с двумя числами.");
            }
        }
        return res;
    }

    public static String romeConverter(int res) {

        if (res <= 0)
            return RED + "Недопустимое значение в римских цифрах" + RESET;

        StringBuilder str = new StringBuilder();
        while (res == 100) {
            str = new StringBuilder("C");
            res -= 100;
        }
        while (res >= 90) {
            str.append("XC");
            res -= 90;
        }
        while (res >= 50) {
            str.append("L");
            res -= 50;
        }
        while (res >= 40) {
            str.append("XL");
            res -= 40;
        }
        while (res >= 10) {
            str.append("X");
            res -= 10;
        }
        while (res >= 9) {
            str.append("IX");
            res -= 9;
        }
        while (res >= 5) {
            str.append("V");
            res -= 5;
        }
        while (res >= 4) {
            str.append("IV");
            res -= 4;
        }
        while (res >= 1) {
            str.append("I");
            res -= 1;
        }
        return str.toString();
    }
}
