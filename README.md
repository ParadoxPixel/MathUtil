# MathUtil
Simple Java library to handle 2D and 3D coördinates and rotation

```java
//Create a circle position instance to generate the rotation matrix.
//Example uses 3 as step size with a precision of 2 meaning 120 pre-computed positions with a decimal precision of 2
CirclePos circlePos = new CirclePos(3, 2);

//Generate rotation matrix for rotating 90 degrees
Matrix r = circlePos(90);

//Point A with x=1, y=1
Pos2D a = new Pos2D(1, 1);

//Apply matrix
Pos2D b = a.applyMatrix(r);

//We now have Point A and Point B which is 90 degrees turned compared to A
```
