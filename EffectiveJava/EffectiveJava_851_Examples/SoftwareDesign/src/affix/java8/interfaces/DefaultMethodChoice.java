
package affix.java8.interfaces;

class Friend implements Person{
    
    private long id;
    
    public Friend(long id){
        this.id = id;
    }

    @Override
    public long getId() {
       return id;
    }
    
}

class NamedBuddy implements Generated{
    
    private String name;
    
    public NamedBuddy(String name){
        this.name = name;
    }

    @Override
    public String getName() {
       return name;
    }
    
}

class AnonymousBuddy implements Generated{
    ;
}

class Student implements Person, Generated {
    
    private String name;
    private long id;
    
    public Student(String name, long id){
        this.name = name;
        this.id = id;
    }
    
   @Override
   public long getId() { return id; }
   
   @Override
   public String getName() { return name; }
   
//   @Override
//   public String getName() { return Person.super.getName(); }
}

public class DefaultMethodChoice {
    
    public static void main(String[] args){
    
        Person p1 = new Friend(12345);
        System.out.println("Person p1 " + p1.getId() + "  " + p1.getName());
        
        Person p2 = new Student("Kalle", 242424);
        System.out.println("Person p2 " + p2.getId() + "  " + p2.getName());

        Generated p3 = new NamedBuddy("Emma");
        System.out.println("Generated p3 " + p3.getName());
        
        Generated p4 = new AnonymousBuddy();
        System.out.println("Generated p4 " + p4.getName());
    }
}