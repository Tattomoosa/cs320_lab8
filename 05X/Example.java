import java.util.*;
import java.util.stream.*;

class Example {

  public static void main(String argv[]) {
    int a[] =
      Arrays.stream(argv)
      .mapToInt(s -> Integer.parseInt(s))
      .toArray();

    System.out.println("part a:");
    // replace this expression starting Arrays.stream(a)
    System.out.println(
            Arrays.stream(a)
                .max()
                .orElse(0));

    System.out.println("part b:");
    // fill in here with expresssion starting Arrays.stream(a)
    Arrays.stream(a)
        .filter(x -> x - 1 > 0 && (x - 1) % 3 == 0)
        .forEach(x -> System.out.println(x));

    System.out.println("part c:");
    // fill in here with expresssion starting Arrays.stream(a)
    Arrays.stream(a)
        .dropWhile(x -> x != 0)
        // .skip(1)
        .dropWhile(x -> x == 0)
        .takeWhile(x -> x != 0)
        .forEach(x -> System.out.println(x));

    System.out.println("part d:");
    // fill in here with expresssion starting Arrays.stream(a)
    Arrays.stream(a)
        .forEach(x -> IntStream.range(0,x).forEach(n -> System.out.println(x)));

    System.out.println("part e:");
    System.out.println(Arrays.stream(a).reduce((h, t) -> t).orElse(0));
  }
}
