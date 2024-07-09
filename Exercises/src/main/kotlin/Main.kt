//1- implement a function from int to string that return if the number is even or odd
fun parityFunction (x:Int) : String {
    return when(x % 2){
        0 -> "even"
        else -> "odd"
    }
}

val parity = { x: Int ->
     when(x % 2){
        0 -> "even"
        else -> "odd"
    }
} //lambda format

/* 2- Implement a neg function that accepts a predicate on strings (i.e., a function from strings to Booleans) and returns
* another predicate on strings, namely, one that does the exact opposite
* */
val neg: ((String) -> Boolean) -> ((String) -> Boolean)= {f -> {x -> !f(x)}} //f is the input predicate, x is the string in input to the predicate

/* 3- Make neg work for generic predicates, and write tests to check it*/
fun <T> genericNeg (f : ((x: T) -> Boolean)) : ((T) -> Boolean) {return {x -> !f(x)}}

/* 4- Create a function to get the n-th Fibonacci number*/
fun fibonacci (n:Int) :Int {
    return when(n){
            0 -> n
            1 -> n
            else -> fibonacci(n-1) + fibonacci(n-2)
    }
}

/* 5-
* Define a set of geometric shapes and methods for calculating their perimeter and area
* - Define a sealed Shape
* - Define concrete types Rectangle, Circle, and Square; these product types should exhibit the typical geometric
* properties you would expect to characterise the corresponding shapes
* - Define two methods perimeter(shape: Shape): Double and area(shape: Shape): Double for computing perimeter and area
* */

sealed class Shape
class Rectangle(val b:Double, val h: Double) : Shape()
class Square (val l:Double) : Shape()
class Circle (val r:Double): Shape()

fun Shape.getPerimeter() = when (this) {
    is Rectangle -> (this.b + this.h) * 2
    is Square -> this.l * 4
    is Circle -> 2 * this.r * 3.14
}

fun Shape.getArea() = when (this) {
    is Rectangle -> this.b * this.h
    is Square -> this.l * this.l
    is Circle -> 2 * this.r * this.r * 3.14
}


fun main(){
    println("ex1")
    println(parityFunction(10)) //even
    println(parity(3)) //odd

    println("ex2")
    val empty: (String) -> Boolean = { it == "" }
    val notEmpty = neg(empty)
    println( notEmpty("foo") )// true
    println(notEmpty("")) // false
    println(notEmpty("foo") && !notEmpty("")) //true

    println("ex3 - with strings")
    val empty2: (String) -> Boolean = { it == "" }
    val notEmpty2 = genericNeg(empty2)
    println(notEmpty2("foo") )// true
    println(notEmpty2("")) // false
    println(notEmpty2("foo") && !notEmpty2("")) //true
    println("ex3 - with int")
    val notEmpty3 = genericNeg<Int>{ it % 2 == 0}
    println(notEmpty3(3) )// true
    println(notEmpty3(2)) // false
    println(notEmpty3(3) && !notEmpty3(2)) //true

    println("ex4")
    for (x in 0 ..4) {
        println("fibonacci(${x}) ${fibonacci(x)}")
    }

    println("ex5")
    val rect = Rectangle(4.0, 5.0)
    val square = Square(4.0)
    val circle = Square(2.0)

    println("rectangle perimeter = ${rect.getPerimeter()} ")
    println("square perimeter = ${square.getPerimeter()}")
    println("circle perimeter = ${circle.getPerimeter()}")

    println("rectangle area = ${rect.getArea()} ")
    println("square area = ${square.getArea()}")
    println("circle area = ${circle.getArea()}")
}
