# Calculator

 калькулятор реализован через два класса и одно перечисления enum:
 1) class Calculator - получение строки арифметической операции через консоль,
       удаление пробелов и невидимых символов; перевод строки в верхний регистр;
       проверка, не пустая ли строка; проверка, является ли строка арифметическим выражением:
       вывод исключений, если строка не подходит под заявленные условия; разложение строки в список строк;
       преобразование массива список в список чисел; вычисление результата; вывод результата на экран,
       если он удовлетворяет условиям, или вывод исключения;
 2) class RomanNumbers - класс для перевода арабского числа в римское и вывод на экран; 
 3) enum Number - словарь арабских и римских цифр до 10 и десятки от 10 до 100, включающий функции
       поиск элемента по входящему римскому числу, поиск элемента по входящему арабскому числу.