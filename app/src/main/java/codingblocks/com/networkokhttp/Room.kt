package codingblocks.com.networkokhttp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.roomdetails.*


class Room : AppCompatActivity() {

   val db by lazy {
        Room.databaseBuilder(this,AppDatabase::class.java,"todo.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
             .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.roomdetails)
        var pos=intent.getIntExtra("favlist",0)
        if(pos!=1) {
            var check = db.tododao().checkrepeat(pos)
            Log.i("checkrepeat", check.toString())
            if (check.isNotEmpty()) {
                db.tododao().deletetask(Todo(id = pos))
                Toast.makeText(this, "Movie is removed from favourites!", Toast.LENGTH_LONG).show()
            } else {
                db.tododao().insert(Todo(id = pos))
                Toast.makeText(this, "Movie is added in favourites!", Toast.LENGTH_LONG).show()

            }
        }
//        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
//        lv.adapter = adapter
        var list=db.tododao().getalltask() as ArrayList<Todo>
        val adapter = TaskAdapter(list,this)
        lv.adapter = adapter
            lv.setOnItemClickListener { adapterView, view, i, l ->
                val detail= Intent(this,Details::class.java)
            detail.putExtra("ID",list[i].id)
                startActivity(detail)
                }

//            for (i in 0 until list.size) {
//                lv.text = tv.text.toString() + "\n" + list[i].id
//            }
        }

//        list = db.tododao().getalltask() as ArrayList<Todo>
//        val adapter=TaskAdapter(list,this)
//        lv.adapter=adapter
//        adapter.todoItemClick=object :TodoItemClickListner{
//            override fun onclick(task:Todo, position: Int) {
//                db.tododao().deletetask(task)
//                list=db.tododao().getalltask() as ArrayList<Todo>
//                adapter.updateTasks(list)
//            }
//            override fun oncheck(task: Todo, position: Int) {
//                db.tododao().updatecheck(Todo(task = list[position].task,status = !list[position].status))
//                list=db.tododao().getalltask() as ArrayList<Todo>
//                adapter.updateTasks(list)
//            }
//
//            override fun updateText(task: Todo,position: Int) {
//                db.tododao().updateTask(Todo(task=et.text.toString(),status = false))
//                list=db.tododao().getalltask() as ArrayList<Todo>
//                adapter.updateTasks(list)
//                et.setText("")
//
//            }
//        }
//        button.setOnClickListener {

//            adapter.updateTasks(list)
//            et.setText("")
//        }

    }

