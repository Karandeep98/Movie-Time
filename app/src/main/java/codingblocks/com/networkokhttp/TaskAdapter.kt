package codingblocks.com.networkokhttp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.listdetails.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TaskAdapter(var tasks: ArrayList<Todo>,val context: Context) : BaseAdapter(

) {
    val retrofitClient = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val service = retrofitClient.create(GithubService::class.java)

//    var todoItemClick:TodoItemClickListner?= null
//    fun updateTasks(newTasks: ArrayList<Todo>) {
//        tasks.clear()
//        tasks.addAll(newTasks)
//        notifyDataSetChanged()
//    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater= LayoutInflater.from(context)
        val view=inflater.inflate(R.layout.listdetails,parent,false)
        val title=view.titletv
        val release=view.releasetv
        val overview=view.overviewtv
        val poster=view.img
//        val check=view.cb
        service.overview(tasks[position].id.toString().toInt()).enqueue(object : Callback<Overview> {
            override fun onFailure(call: Call<Overview>, t: Throwable) {
//                tv.text="Loading failed!"
//                tv.text=tv.text.toString()+t.cause.toString()
            }

            override fun onResponse(
                call: Call<Overview>,
                response: Response<Overview>
            ) {
//                listtext.text = tasks[position].id.toString()
                title.text=response.body()?.original_title
                release.text=response.body()?.release_date
                overview.text=response.body()?.overview
                Picasso.get().load("https://image.tmdb.org/t/p/w500"+response.body()?.poster_path).into(poster)

            }
        })
        return view

    }

    override fun getItem(position: Int): Todo = tasks[position]

    override fun getItemId(position: Int): Long = 0

    override fun getCount(): Int = tasks.size

}
