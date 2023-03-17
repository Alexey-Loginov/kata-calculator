import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Operation operation = Operation.SUBTRACTION;
        String input;
        String [] strings;
        int arg1 = 0;
        int arg2 = 0;
        int output = 0;
        boolean isRome = false;

        while(true){
            input = scanner.nextLine();
            strings = input.toUpperCase().split(" ");

            if (strings.length != 3) {
                System.out.println("throws Exception //т.к. строка не является математической операцией");
                return;
            }

            try {
                arg1 = Integer.parseInt(strings[0]);
                isRome = false;
            } catch (Exception e) {
                try {
                    arg1 = Rome.valueOf(strings[0]).getInteger();
                    isRome = true;
                } catch (Exception e1) {
                    System.out.println("throws Exception //т.к. первый операнд не является целым числом");
                    return;
                }
            }

            if (arg1 < 1 || arg1 > 10) {
                System.out.println("throws Exception //т.к. первый операнд не входит в диапазон от 1 до 10 включительно");
                return;
            }

            try {
                arg2 = Integer.parseInt(strings[2]);
                if (isRome) {
                    System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
                    return;
                }
                isRome = false;
            } catch (Exception e) {
                try {
                    arg2 = Rome.valueOf(strings[2]).getInteger();
                    if (!isRome) {
                        System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
                        return;
                    }
                } catch (Exception e1) {
                    System.out.println("throws Exception //т.к. второй операнд не является целым числом");
                    return;
                }
            }

            if (arg2 < 1 || arg2 > 10) {
                System.out.println("throws Exception //т.к. второй операнд не входит в диапазон от 1 до 10 включительно");
                return;
            }

            switch (strings[1]) {
                case "+":
                    operation = Operation.ADDITION;
                    output = operationAddition(arg1, arg2);
                    break;
                case "-":
                    operation = Operation.SUBTRACTION;
                    output = operationSubtraction(arg1, arg2);
                    break;
                case "*":
                    operation = Operation.MULTIPLICATION;
                    output = operationMultiplication(arg1, arg2);
                    break;
                case "/":
                    operation = Operation.DIVISION;
                    output = operationDivision(arg1, arg2);
                    break;
                default:
                    System.out.println("throws Exception //т.к. строка не является математической операцией");
                    return;
            }

            if (isRome) {
                if (output < 1) {
                    System.out.println("throws Exception //т.к. для римских чисел результат меньше 1");
                    return;
                } else {
                    System.out.println(getRomeString(output));
                }
            } else {
                System.out.println(output);
            }
        }
    }

    static int operationAddition(int arg1, int arg2) {
        return (int) (arg1 + arg2);
    }

    static int operationSubtraction(int arg1, int arg2) {
        return (int) (arg1 - arg2);
    }

    static int operationMultiplication(int arg1, int arg2) {
        return (int) (arg1 * arg2);
    }

    static int operationDivision(int arg1, int arg2) {
        return (int) (arg1 / arg2);
    }

    static String getRomeString(int num) {
        String string;
        int num10 = num / 10;

        switch (num10) {
            case 1:
                string = "X";
                break;
            case 2:
                string = "XX";
                break;
            case 3:
                string = "XXX";
                break;
            case 4:
                string = "XL";
                break;
            case 5:
                string = "L";
                break;
            case 6:
                string = "LX";
                break;
            case 7:
                string = "LXX";
                break;
            case 8:
                string = "LXXX";
                break;
            case 9:
                string = "XC";
                break;
            case 10:
                string = "C";
                break;
            default:
                string = "";
                break;
        }

        int num1 = num % 10;

        switch (num1) {
            case 1:
                string += "I";
                break;
            case 2:
                string += "II";
                break;
            case 3:
                string += "III";
                break;
            case 4:
                string += "IV";
                break;
            case 5:
                string += "V";
                break;
            case 6:
                string += "VI";
                break;
            case 7:
                string += "VII";
                break;
            case 8:
                string += "VIII";
                break;
            case 9:
                string += "IX";
                break;
            default:
                string += "";
                break;
        }

        return string;
    }
}

enum Operation {
    ADDITION("+"), SUBTRACTION("-"), MULTIPLICATION("*"), DIVISION("/");
    private String string;
    Operation(String string) {
        this.string = string;
    }
}

enum Rome {
    I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10);
    private int number;
    Rome(int num) {
        this.number = num;
    }
    public int getInteger() {
        return number;
    }
}
