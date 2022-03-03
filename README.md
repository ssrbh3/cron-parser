# Cron Parser

## Limitations

Could not do in interest of time.

- Application Test coverage is not full, could be improved
- Application strictly works with 6 expressions.
- Application does not handle special time strings such as "@yearly"
- Application does not handle case where month has less than 31 days.

## Instructions to run the program

- Make sure Java(8+) is installed on machine.
- Execute following command``` gradle clean build ``` that should generate an executable jar in ``` /build/libs```
  folder.
- Execute following command (from ``` /build/libs``` level ) to run the app with desired input

```
java -jar cron-parser-0.0.1-SNAPSHOT.jar "*/10 0 1-5,10-15,* * 1-5 /usr/bin/find"
```

Sample output for above request -

```
minute        0 10 20 30 40 50
hour          0
day of month  1 2 3 4 5 10 11 12 13 14 15 6 7 8 9 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find
```

## Tests

The tests are present in `test/` directory covering few basic cases in interest of time. Some sample basic outputs are
kept in `test/resources` folder that the test runs against.
