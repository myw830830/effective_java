
package affix.java8.interfaces;

interface Generated {
      default String getName() { return getClass().getName() + "_" + hashCode(); }
}
