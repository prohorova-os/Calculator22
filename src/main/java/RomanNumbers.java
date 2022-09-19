import java.util.ArrayList;
//класс римских чисел: перевод арабского числа в римское для вывода результата на экран
public class RomanNumbers {
     private StringBuffer val;
     //конструктор перевода арабского числа в римское для вывода результата на экран
     RomanNumbers(Integer number) {
         StringBuffer result = new StringBuffer("");
         Integer n1 = number / 10; //находим десятки
         Integer n2 = number - n1 * 10; //находим единицы
         if ((n1 > 0) && (n2 != 0)) { //если есть десятки и единицы, то преобразуем десятки и единицы в римское число
             ArrayList<Integer> arrNum = new ArrayList<>();
             arrNum.add(n1 * 10);
             arrNum.add(number - n1 * 10);
             for (int i = 0; i < 2; i++) {
                 //перевод арабского числа в римское через использование enum
                 Number partNumber = Number.toRom(arrNum.get(i));
                 result.append(partNumber.getRom());
             }
         } else { //если нет десятков
         //перевод арабского числа в римское через использование enum
             Number partNumber = Number.toRom(number);
             result.append(partNumber.getRom());
         }
         this.val = result;
     }
     //вывод римского числа на экран
     public void displayInfo(String arifmOperat) {
        System.out.println("\n" + arifmOperat + "=" + val);
     }
}
