1.This program uses [Google Guava 21.0](https://google.github.io/guava/releases/21.0/api/docs/) library (Java 8).

2.Input file format: "-i" -negative infinity, "+i" -positive infinity, "," -the separator between lower endpoint and upper endpoint of interval, "U" -the separator between intervals.Each set from a new line.
For example:
```sh
-i,2.5U7,8
0,1U2,8U9,12
-1,3U7,11
-1,4U7,8.6
```
 ### Usage
 Task 1
 ```sh
double IntersectionOfIntervals (new MultiplicitiesLoader(String filePath), double x).findNumber();
```
Task 2
```sh
HashSet<Range<Double>> IntersectionOfIntervals (new MultiplicitiesLoader(String filePath)).setOfIntervals();
```
