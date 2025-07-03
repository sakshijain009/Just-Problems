🧩 Problem Statement – FizzBuzz with 4 Threads
You are given a number n, and asked to print the numbers from 1 to n, but with the following rules:

If the number is divisible by 3, print "Fizz"

If divisible by 5, print "Buzz"

If divisible by both 3 and 5, print "FizzBuzz"

Otherwise, print the number

But here's the twist:
You need to use four threads, each doing only one of the following:

Thread Name	Role
fizz	Prints "Fizz" for multiples of 3
buzz	Prints "Buzz" for multiples of 5
fizzbuzz	Prints "FizzBuzz" for multiples of both 3 and 5
number	Prints the number itself if none of the above
