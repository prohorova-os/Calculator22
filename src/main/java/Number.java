//словарь арабских и римских цифр до 10 и десятки от 10 до 100
public enum Number {
    One("I", 1), Two("II", 2), Tree("III", 3), Four("IV", 4), Five("V", 5),
    Six("VI", 6), Seven("VII", 7), Eight("VIII", 8), Nine("IX", 9), Ten("X", 10),
    Twenty("XX", 20), Thirty("XXX", 30), Forty("XL", 40), Fifty("L", 50),
    Sixty("LX", 60), Seventy("LXX", 70), Eighty("LXXX", 80), Ninety("XC", 90),
    OneHundred("C", 100);

    private String rom;
    private Integer arab;
    //конструктор
    Number(String rom, Integer arab) {
        this.rom = rom;
        this.arab = arab;
    }
    public String getRom() {return rom;}
     public Integer getArab() {return arab;}
     //поиск элемента по входящему римскому числу
     public static Number toRom(String rom) {
         for (Number i: Number.values()) {
             if (i.getRom().equals(rom)) {
                return i;
             }
         }
         return null;
     }
     //поиск элемента по входящему арабскому числу
     public static Number toRom(Integer arab) {
         for (Number i: values()) {
             if (i.arab == arab) {
                    return i;
                 }
             }
         return null;
     }
}
