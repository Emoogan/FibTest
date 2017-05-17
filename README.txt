The algorithm implemented to calculate the nth Fibonacci is the matrix power method, with the naive multiplication.

There are always tradeoffs between algorithms, the main goal with this app is reliability, then speed then storage, even when using large numbers.
There are several reasons this matrix power method is better suited:
1. Faster than the recursive or iterative method (but slower than the matrix doubling method)
2. Easier to implement than the matrix doubling method
3. Less storage than dynamic programming method


Limited by the resources on the device; it can be expected to perform reasonably well up to the millionth number, 
after which the time taken for the calculation is very long.

These websites gave good metrics, such as the O values and time taken for each algorithm.
http://pages.cs.wisc.edu/~mhock/SSL/fibcalc.pdf
https://www.nayuki.io/page/fast-fibonacci-algorithms