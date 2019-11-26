import java.util.*;
import java.util.function.*;

class Point {
  double x;
  double y;
  Point (double x, double y) {this.x = x; this.y = y;}

  double distance(Point other) {
    double delta_x = this.x - other.x;
    double delta_y = this.y - other.y;
    return Math.sqrt(delta_x * delta_x + delta_y * delta_y);
  }
}


class PSet {
  Predicate<Point> ps;    // representation of a point set
  private PSet(Predicate<Point> ps) {this.ps = ps;}

  boolean in(Point p) {
    return ps.test(p);
  }

  static PSet disk(Point center, double radius) {
    return new PSet(point -> center.distance(point) <= radius);
  }

  PSet intersect(PSet set) {
    return new PSet(point -> ps.test(point) && set.ps.test(point));
  }

  static PSet rect(Point corner1, Point corner2) {
      return new PSet(point ->
              corner1.distance(point) <= corner1.distance(corner2) &&
              corner2.distance(point) <= corner2.distance(corner1));
  }

  PSet union(PSet set) {
      return new PSet(point -> ps.test(point) || set.ps.test(point));
  }

  PSet complement() {
      return new PSet(point -> !ps.test(point));
  }

  PSet reflectx() { 
      return new PSet(point -> ps.test(new Point(-point.x, point.y)));
  }
}
  

class Example {
  public static void main(String argv[]) {
    double x = Double.parseDouble(argv[0]);
    double y = Double.parseDouble(argv[1]);
    Point p = new Point(x,y);
    PSet myregion = PSet.disk(new Point(0,0),2).intersect(PSet.disk(new Point(0,2),2));
    System.out.println("Point at" + x + ", " + y);
    System.out.println("Point in Disk at 0,0, radius 2");
    System.out.println(myregion.in(p));
    System.out.println("Point in Complement");
    System.out.println(myregion.complement().in(p));

    PSet rect = PSet.rect(new Point(3, 3), new Point(4, 18));
    System.out.println("Point in rect from 3,3 to 4,18");
    System.out.println(rect.in(p));
    System.out.println("Point in Complement");
    System.out.println(rect.complement().in(p));
    System.out.println("Point in ReflectX");
    System.out.println(rect.reflectx().in(p));

    System.out.println("Point in Union of rect and disk");
    System.out.println(rect.union(myregion).in(p));

  }
}
