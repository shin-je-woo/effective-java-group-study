package chapter4.item24;

public class OutterClass {
   private int num = 1;

   void print(){
       InnerClass innerClass = new InnerClass();
       innerClass.printNum();
   }

    private class InnerClass{
       void printNum(){
           System.out.println(num);
       }

       void doSomething(){
           class LocalClass {
               private void printName(){
                   System.out.println("LocalClass");
               }
           }
           LocalClass localClass = new LocalClass();
           localClass.printName();
       }
    }

    public static void main(String[] args) {
        InnerClass innerClass = new OutterClass().new InnerClass();
        innerClass.printNum();

        OutterClass outterClass = new OutterClass();
        outterClass.print();

//        innerClass.doSomething();
    }
}
