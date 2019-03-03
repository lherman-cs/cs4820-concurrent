//import Recursion._

object Main {
  
  def main(args: Array[String]) {
    println("step 2: print function")
    myPrint()
    println()
    
    println("step 3: recursive algorithm")
    // args(0) is number of lines of Pascal triangle to print
    //printPascal(args(0).toInt)	
    printPascal(10)	
    /*
    // args(1) is the amount of money for which to calculate the number of combinations for change
    printCountChange(args(1).toInt, List(1, 5, 10))	
    
    
    // arg2 and arg3 are parenthesized strings to be validated
    printBalance(args(2))
    printBalance(args(3))
    println()
        
    */
  }  
  
  def myPrint(){
    val lines = 5
    // println("in myPrint")
    for (i <- 1 to lines){
      for(j <- 1 to (lines-i))
        print(" ")
      for (j<- 1 to (i*2-1))
        print("*")
      println()
    }
  }
  
 
  def printPascal(row: Int): Unit = {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }
 /* 
  def printCountChange(money:Int, coins:List[Int]): Unit = {
    println("Number of ways to give change for " + money + " is: " + countChange(money, coins))
  }
  */

  /*
  def printBalance(expression:String): Unit = {
    if (balance(expression.toList)) {
      println("expression: " + expression + " is balanced")
    } else {
      println("expression: " + expression + " is not balanced")
      }
  }
  */
}

