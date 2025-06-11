## NumberSpiral

A quick little program for no particular reason. Takes an input of integer `n`, and produces a 2D array of ints `n` on a side, counting up from `1` to `n^2` starting in the top left and spiraling inwards clockwise. This program uses some equations for the coordinate values of the nth value in Ulam's Spiral, then offsets the coordinates to fit in the 2D array and records the value of the input squared minus the iteration of Ulam's Spiral.

I don't think this is the fastest way you could do this, but it avoids some tedious logic that I didn't really want to code. I've written plenty of if statements, I don't get much from doing more :D

### Usage
1. Compile using: `javac NumberSpiral.java`
2. Run with an integer launch argument denoting the dimensions of the 2d Array, i.e.: `java NumberSpiral 10`