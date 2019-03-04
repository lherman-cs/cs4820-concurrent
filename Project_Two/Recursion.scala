import scala.collection.mutable.MutableList
import scala.collection.immutable.List

object Recursion {
  def pascal(c: Int, r: Int): Int = {
    if (r == 0) {
      if(c == 0) 1
      else 0
    }
    else
      pascal(c,r-1)+pascal(c-1,r-1)
  }
  
  def countChange(money: Int, coins: List[Int]): Int = {
    var combinations = MutableList.fill(money + 1)(0)
    combinations(0) = 1
    countChange(money, coins, combinations)
    combinations(money)
  }
  
  private def countChange(money: Int, coins: List[Int], combinations: MutableList[Int]): Unit = {
    if(coins.isEmpty) return

    var curChange = coins.head
    for(solution <- curChange to money) {
      combinations(solution) += combinations(solution - curChange)
    }
    countChange(money, coins.tail, combinations)
  }

  def balance(chars: List[Char]): Boolean = {
    return balance(chars, 0)
  }

  private def balance(chars: List[Char], opens: Int): Boolean = {
    if(chars.isEmpty) return opens == 0

    chars.head match {
      case '(' => balance(chars.tail, opens + 1)
      case ')' => 
        if(opens == 0) false
        else balance(chars.tail, opens - 1)
      case _ => balance(chars.tail, opens)
    }
  }
 }
