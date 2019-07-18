package codingblocks.com.networkokhttp

import androidx.room.*
//Dao= Data access objects

    @Dao
    interface TodoDao {
        @Insert
        fun insert(todo: Todo)

        @Query("Select * from Todo")
        fun getalltask():List<Todo>

        @Query("Select * from Todo where id=:id")
        fun checkrepeat(id:Int):List<Todo>


        @Delete
        fun deletetask(todo: Todo)


    }

