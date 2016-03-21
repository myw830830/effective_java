
package affix.java8.interfaces;

interface Person {
   long getId();
   default String getName() { return "NN"; }
}
