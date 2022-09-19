import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); //ввод арифметической операции в консоль
        while (true) {
            System.out.print("Введите арифметическую операцию\n" +
                    "(арифметическая операция может быть из чисел:\n" +
                    "- только из арабских чисел от 1 до 10,\n" +
                    "- только из римских чисел от I до X;\n" +
                    "включать только операции +, -, *, /;\n" +
                    "результат арифметической операции, состоящей из римских цифр, должен быть > 0;\n" +
                    "для ввода арифметической операции нажмите enter): ");
            String arifmOperat = in.nextLine();
            if(arifmOperat.isEmpty()) { //проверка, не пустая ли строка
                System.out.println("Ошибка: Введенная строка - пустая.");
            } else {
                arifmOperat = arifmOperat.replaceAll("\\s+",""); //подготовка строки к разложению на операторы и операнд, удаляем пробелы и скрытые символы из строки
                arifmOperat = arifmOperat.toUpperCase(); //переводим в верхний регистр
                boolean bol = validationArifmOperat(arifmOperat); //подготавливаемся к вычислению значения, проверка строчки, является ли она арифметическим выражением
                if (bol) {
                    ArrayList<String> arrOpers = arrayOperand(arifmOperat); //разложение строки на операнды и оператор:
                    ArrayList<Integer> arrNums = new ArrayList<>(); //преобразование массива строк в список чисел: 0, 1 - числовые значения операндов, 2 - хранит значение 0 - если цифры арабские, 1 - если римские
                    arrNums = convertingArrStrToArrNumbers(arrOpers);
                    Integer result = 1000;
                    if((arrNums.get(0) > 10) | (arrNums.get(1)  > 10)) {
                        System.out.println("Ошибка: Числа не должны быть > 10.");
                    } else { //вычисляем результат
                        switch (arrOpers.get(2)) {
                            case "+":
                                result = arrNums.get(0) + arrNums.get(1);
                                break;
                            case "-":
                                result = arrNums.get(0) - arrNums.get(1);
                                break;
                            case "*":
                                result = arrNums.get(0) * arrNums.get(1);
                                break;
                            case "/":
                                result = arrNums.get(0) / arrNums.get(1);
                                break;
                        }
                        if ((result != 1000) && (arrNums.get(2) != 1)) { //печатаем если (числа до 10) и (не римское число)
                            System.out.println("\n" + arifmOperat + "=" + result);
                        } else if ((result >= 1) && (arrNums.get(2) == 1)) { //или если результат >1 и (римское число)
                            RomanNumbers resultRoman = new RomanNumbers(result);
                            resultRoman.displayInfo(arifmOperat);
                        }
                    }
                }
            }
            System.out.println("\n----------------------------------------");
        }
    }
     static Boolean validationArifmOperat(String str){ //проверка строчки, является ли она арифметическим выражением
         //правильные
         boolean result1 = str.matches("^(\\s*)\\d{1,2}(\\s*)(\\+|\\-|\\*|\\/)(\\s*)\\d{1,2}(\\s*)$");
         boolean result2 = str.matches("^(\\s*)(I|II|III|IV|V|VI|VII|VIII|IX|X)(\\s*)(\\+|\\-|\\*|\\/)(\\s*)(I|II|III|IV|V|VI|VII|VIII|IX|X)(\\s*)$");
         //Ошибка: Используются одновременно разные системы счисления
         boolean result3 = str.matches("^(\\s*)(\\-*)(\\s*)((I|II|III|IV|V|VI|VII|VIII|IX|X)|[IVXLCDM]{2,100})(\\s*)(\\+|\\-|\\*|\\/)(\\s*)(\\-*)(\\s*)\\d{1,2}(\\s*)$");
         boolean result4 = str.matches("^(\\s*)(\\-*)(\\s*)\\d{1,2}(\\s*)(\\+|\\-|\\*|\\/)(\\s*)(\\-*)(\\s*)((I|II|III|IV|V|VI|VII|VIII|IX|X)|[IVXLCDM]{2,100})(\\s*)$");
         //Ошибка: Используется недопустимый оператор.
         boolean result5 = str.matches("^(\\s*)\\d{1,2}(\\s*)([^\\+\\-\\*\\/])(\\s*)\\d{1,2}(\\s*)$");
         boolean result6 = str.matches("^(\\s*)((I|II|III|IV|V|VI|VII|VIII|IX|X)|\\d{1,2})(\\s*)([^\\+\\-\\*\\/])(\\s*)((I|II|III|IV|V|VI|VII|VIII|IX|X)|\\d{1,2})(\\s*)$");
         //Ошибка: Числа должны быть целыми
         boolean result7 = str.matches("^(\\s*)\\d{1,2}(\\.)\\d{0,16}(\\s*)(\\+|\\-|\\*|\\/)(\\s*)\\d{1,2}(\\s*)$");
         boolean result8 = str.matches("^(\\s*)\\d{1,2}(\\s*)(\\+|\\-|\\*|\\/)(\\s*)\\d{1,2}(\\.)\\d{0,16}(\\s*)$");
         //Ошибка: Числа не должны быть отрицательными
         boolean result9 = str.matches("^(\\s*)((\\-))(\\s*)\\d{1,2}(\\s*)(\\+|\\-|\\*|\\/)(\\s*)((\\-)*)(\\s*)\\d{1,2}(\\s*)$");
         boolean result10 = str.matches("^(\\s*)((\\-))(\\s*)(I|II|III|IV|V|VI|VII|VIII|IX|X)(\\s*)(\\+|\\-|\\*|\\/)(\\s*)((\\-)*)(\\s*)(I|II|III|IV|V|VI|VII|VIII|IX|X)(\\s*)$");
         boolean result11 = str.matches("^(\\s*)((\\-)*)(\\s*)\\d{1,2}(\\s*)(\\+|\\-|\\*|\\/)(\\s*)((\\-))(\\s*)\\d{1,2}(\\s*)$");
         boolean result12 = str.matches("^(\\s*)((\\-)*)(\\s*)(I|II|III|IV|V|VI|VII|VIII|IX|X)(\\s*)(\\+|\\-|\\*|\\/)(\\s*)((\\-))(\\s*)(I|II|III|IV|V|VI|VII|VIII|IX|X)(\\s*)$");
         //Ошибка: Римские числа должны быть из диапазона от I до X
         boolean result13 = str.matches("^(\\s*)[M*C*M*D*C*D*L*C*D*C*C*X*C*C*L*L*X*X*L*X*I*X*VI*I*I*V*I*I*V*I*V*I*V*I*I*I*I*I*I*]*(\\s*)(\\+|\\-|\\*|\\/)(\\s*)[M*C*M*D*C*D*L*C*D*C*C*X*C*C*L*L*X*X*L*X*I*X*VI*I*I*V*I*I*V*I*V*I*V*I*I*I*I*I*I*]*(\\s*)$");
         //Ошибка: Формат арифметической операции не удовлетворяет заданию, должно быть два операнда и один оператор
         boolean result14 = str.matches("^(\\s*)\\d{1,2}(\\s*)(\\+|\\-|\\*|\\/)(\\s*)\\d{1,2}(\\s*)((\\s*)(\\+|\\-|\\*|\\/)(\\s*)\\d{1,2}(\\s*)){1,100}$");
         boolean result15 = str.matches("^(\\s*)(I|II|III|IV|V|VI|VII|VIII|IX|X)(\\s*)(\\+|\\-|\\*|\\/)(\\s*)(I|II|III|IV|V|VI|VII|VIII|IX|X)(\\s*)((\\s*)(\\+|\\-|\\*|\\/)(\\s*)(I|II|III|IV|V|VI|VII|VIII|IX|X)(\\s*)){1,100}$");

         try {
             if(result1|result2) {
                 return true;
             } else if(result3|result4) {
                 throw new Exception("Ошибка: Используются одновременно разные системы счисления.");
             } else if(result5|result6) {
                 throw new Exception("Ошибка: Используется недопустимый оператор.");
             } else if(result7|result8) {
                 throw new Exception("Ошибка: Числа должны быть целыми.");
             } else if(result9|result10|result11|result12){
                 throw new Exception("Ошибка: Числа не должны быть отрицательными.");
             } else if(result13){
                 throw new Exception("Ошибка: Римские числа должны быть из диапазона от I до X.");
             } else if(result14|result15){
                 throw new Exception("Ошибка: Формат арифметической операции не удовлетворяет заданию, должно быть два операнда и один оператор.");
             } else{
                 throw new Exception("Ошибка: Строка не является арифметической операцией, удовлетворяющей условиям.");
             }
         } catch (Exception ex) {
             System.out.println(ex.getMessage());
         }
         return false;
     }
     public static ArrayList<String> arrayOperand(String str){ //разложение строки в массив строк
         ArrayList<String> results = new ArrayList<>(); //формируем массив строк из значений и операнда
         String[] res = new String[2];
         if(str.indexOf("+") != -1){ //разбиваем строчку
             res = str.split("\\+");
             results.add(res[0]);
             results.add(res[1]);
             results.add("+");
         } else if(str.indexOf("-") != -1){
             res = str.split("-");
             results.add(res[0]);
             results.add(res[1]);
             results.add("-");
         } else if(str.indexOf("*") != -1){
             res = str.split("\\*");
             results.add(res[0]);
             results.add(res[1]);
             results.add("*");
         } else if(str.indexOf("/") != -1){
             res = str.split("/");
             results.add(res[0]);
             results.add(res[1]);
             results.add("/");
         }
         return results;
     }
     public static ArrayList<Integer> convertingArrStrToArrNumbers(ArrayList<String> arrValues){ //преобразование массива строк в массив чисел
     Boolean bol = true;
     ArrayList<Integer> results = new ArrayList<>();
     try { //проверяем первое число, если оно арабское число, то преобразуем преобразованием строки в число
        results.add(Integer.valueOf(arrValues.get(0)));
     } catch (NumberFormatException e) {
        bol = false;
     }
     try {
         if(bol){ //если число
             results.add(Integer.valueOf(arrValues.get(1)));
             results.add(0); //передаем 0, т.к. число арабское, нужно для вывода результата на экран
         }
         else { //если это римские цифры, преобразуем значения римских цифр
             String operandStrOne = new String(arrValues.get(0));
             String operandStrTwo = new String(arrValues.get(1));
             Number operandOne = Number.toRom(operandStrOne);
             results.add(operandOne.getArab());
             Number operandTwo = Number.toRom(operandStrTwo);
             results.add(operandTwo.getArab());
             results.add(1); //передаем 1, т.к. число римское, нужно для вывода результата на экран
             if((results.get(0) < (results.get(1) + 1)) && (arrValues.get(2) == "-")) { //проверяем, чтобы разница значений римских цифр была больше 0
                 System.out.println("Ошибка: Разница римских цифр должна быть >= 1.");
             } else if(((results.get(0) / results.get(1)) == 0) && (arrValues.get(2) == "/")) {
                 System.out.println("Ошибка: Результат частного римских цифр должен быть >= 0.");
             }
         }
         return results;
     } catch(Exception ex){ //вывод ошибки
        System.out.println(ex.getMessage());
     }
     return results;
     }
}