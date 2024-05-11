# Generic Addition

Generics in programming is a concept that allows you to
use the same function to handle parameters of different types
(assuming that the logical operation is the same).

For example, we can use '+' in the same way to achieve addition for `int`s and `double`s:
```java
int a = 2;
int b = 3;
int c = a + b;   // 5

double x = 2.1;
double y = 3.2;
double z = x + y;   //
```

But if we want to abstract this to a method and take the sum of all items in an ArrayList:

```java
public static void main(String[] args) {

    // Sum of ints.
    ArrayList<Integer> ints = new ArrayList<>(Arrays.asList(
        1, 2, 3, 4, 5
    ));

    double intSum = sum(ints);
    System.out.println(intSum);   // 15.0


    // Sum of doubles using the same method.
    ArrayList<Double> doubles = new ArrayList<>(Arrays.asList(
        1.5, 2.3, 4.2
    ));

    double doubleSum = sum(doubles);
    System.out.println(doubleSum);   // 8.0

}
```

Without Generics we would have to do this:

```java
public static double sum(ArrayList<Integer> numbers) {
    double sum = 0;

    for (int number : numbers) {
        sum += number.doubleValue();
    }

    return sum;
}

// Seperate method that does the same thing for Type Safety.
public static double sum(ArrayList<Double> numbers) {
    double sum = 0;

    for (double number : numbers) {
        sum += number.doubleValue();
    }

    return sum;
}
```

Generics allow us to pass in values of any type:

```java
public static <T> void addItemToList(ArrayList<T> collection, T newItem) {
    collection.add(newItem);
}
```

`T` is a temporary type that can be anything, but it must be the same:
- `addItemToList(ArrayList<Integer>, Integer)` is fine.
- `addItemToList(ArrayList<Double>, Double)` is fine.
- `addItemToList(ArrayList<Integer>, Double)` is NOT.


But this isn't enough to shrink our `sum()` method down to one method because
`<T>` can accept values of *any* type, but not all types utilise '+'.

We need to restrict what `<T>` can be.

```java
public static <T extends Number> double sum(ArrayList<T> numbers) {
    double sum = 0;

    for (T number : numbers) {
        sum += number.doubleValue();
    }

    return sum;
}
```

`<T>` means we can use any type.
`<T extends Number>` means we can use any type that extends `Number`.

Because the `Number` class contains the addition logic, this is enough for us to perform the
summing logic.


We can also use the Wildcard Generic syntax:
```java
public static double sum(ArrayList<? extends Number> numbers) {
    double sum = 0;

    for (Number number : numbers) {
        sum += number.doubleValue();
    }

    return sum;
}
```

---

These two syntaxes behave mostly the same but have two key differences.

Wildcards cannot match two types:
```java
public static <T> void addItemsToList(ArrayList<T> collection, ArrayList<T> newItems) {
    for (T item : newItems) {
        collection.add(item);
    }
}
```

But if we try to use a Wildcard:
```java
public static void addItemToList(ArrayList<?> collection, ArrayList<?> newItems) {
    // Doesn't work because the type of one collection might not be the same
    // as the type of the other.

    // We also can't use `?` on its own as a type.
}
```

Using multiple Wildcards at the same time like this is almost equivalent to:
```java
public static <T, U> void addItemsToList(ArrayList<T> collection, ArrayList<U> newItems) {
    for (U item : newItems) {
        collection.add(item);   // method add(T) not applicable for argument type U
    }
}
```
(except you aren't able to do `U item : newItems` because you don't have a reference to the type with Wildcards).


The other key difference is that wildcards allow you to take a class or its *parents* instead of
a class and its children:
- `public static void foo(ArrayList<? extends Number> numbers)` is fine.
- `public static <T extends Number> void foo(ArrayList<T> numbers)` is fine.

- `public static void foo(ArrayList<? super Number> numbers)` is fine.
- `public static <T super Number> void foo(ArrayList<T> numbers)` is NOT.

But I don't know why this is useful.