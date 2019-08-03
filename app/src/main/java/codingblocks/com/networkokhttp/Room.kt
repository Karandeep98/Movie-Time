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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import codingblocks.com.networkokhttp.Tvshows.TaskAdapterTvshows
import kotlinx.android.synthetic.main.roomdetails.*


class Room : AppCompatActivity() {

   val db by lazy {
        Room.databaseBuilder(this,AppDatabase::class.java,"todo.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
             .build()
    }

    val db2 by lazy {
        Room.databaseBuilder(this,AppDatabase2::class.java,"todo2.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.roomdetails)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val type = intent.getStringExtra("type")
//        var pos=intent.getIntExtra("favlist",0)
//        if(pos!=1) {
//            var check = db.tododao().checkrepeat(pos)
//            Log.i("checkrepeat", check.toString())
//            if (check.isNotEmpty()) {
//                db.tododao().deletetask(Todo(id = pos))
//                Toast.makeText(this, "Movie is removed from favourites!", Toast.LENGTH_LONG).show()
//                            }
//            else {
//                db.tododao().insert(Todo(id = pos))
//                Toast.makeText(this, "Movie is added in favourites!", Toast.LENGTH_LONG).show()
//            }
//        }
        if (type == "movie") {
        val list = db.tododao().getalltask() as ArrayList<Todo>

        Log.i("favlist", list.toString())
        if (list.isEmpty()) {
            emptyimg.visibility = View.VISIBLE
            tvempty.visibility = View.VISIBLE
        }

        rview.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)
        rview.adapter = TaskAdapter(this, list)
    }
        else if(type=="tv"){
                val list2=db2.tododao().getalltask() as ArrayList<Todo>
            if (list2.isEmpty()) {
                emptyimg.visibility = View.VISIBLE
                tvempty.visibility = View.VISIBLE
            }

            rview.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)
            rview.adapter = TaskAdapterTvshows(this, list2)
            }
    }
        }


