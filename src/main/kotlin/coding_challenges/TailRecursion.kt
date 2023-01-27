package coding_challenges

/*
* fac n = go n 1
* go 1 a = a
* go na = go (n-1) * (a*n)
* */

fun main() {
    println(fac(5))
    print(tailFac(5))
}

/*
* -> factorial(5)
  -> factorial(4)
    -> factorial(3)
      -> factorial(2)
        -> factorial(1)
          -> factorial(0)
          <- 1
        <- 1 = 1 * 1
      <- 2 = 2 * 1
    <- 6 = 3 * 2
  <- 24 = 4 * 6
<- 120 = 5 * 24
* */

fun fac(n: Int): Int{
    return if (n == 0) 1
    else fac(n - 1) * n
}


/*
* -> fact(5, 1)
  -> fact(4, 5)
    -> fact(3, 20)
      -> fact(2, 60)
        -> fact(1, 120)
          -> fact(0, 120)
          <- 120 // the returned value is obtained directly in the last recursive call
* */

fun tailFac(n: Int, a: Int = 1): Int{
    return if (n == 0) a
    else tailFac(n-1, a * n)
}
