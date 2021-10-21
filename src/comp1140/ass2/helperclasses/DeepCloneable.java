package comp1140.ass2.helperclasses;

/**
 * Interface to reflect the fact that the particular class can be deep cloned
 *
 * @author Ziling Ouyang
 */
public interface DeepCloneable<T> {

    /**
     * Creates a deep clone of an object
     */
    T deepClone();
}
