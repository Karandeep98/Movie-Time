package codingblocks.com.networkokhttp

interface TodoItemClickListner {
    fun onclick(task:Todo,position:Int)
    fun oncheck(task:Todo,position: Int)
    fun updateText(task: Todo,position: Int)
}
