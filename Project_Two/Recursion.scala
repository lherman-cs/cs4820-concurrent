object Recursion {
  def pascal(c: Int, r: Int): Int = {
    if (r==1||r==2||c==0||c==r)
      1
    else
      pascal(c,r-1)+pascal(c-1,r-1)
  }
  
  def countChange(money: Int, coins: List[Int]): Int = {
    // implementation
    return 2		// stub -- remove this line
  }
  
  def balance(chars: List[Char]): Boolean = {
    // implementation (CSCI 6900 Only)
    return false	// stub -- remove this line
  }
  
 }
