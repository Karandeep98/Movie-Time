package codingblocks.com.networkokhttp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_favourites.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class TaskAdapter( val context: Context, private val arrayList: ArrayList<Todo>)
    : RecyclerView.Adapter<TaskAdapter.GithubViewHolder>() {
    val retrofitClient = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val service = retrofitClient.create(GithubService::class.java)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        val inflater = LayoutInflater.from(context)
        return GithubViewHolder(inflater.inflate(R.layout.activity_favourites, parent, false))

    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val user = arrayList[position]
        holder.bind(user, position)
    }

    inner class GithubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currentuser: Todo? = null

        init {
            itemView.setOnClickListener {
                //            Toast.makeText(context, currentuser!!.title + "clicked!",Toast.LENGTH_LONG ).show()
                val detail= Intent(context,Details::class.java)
                detail.putExtra("ID", currentuser?.id)
                context.startActivity(detail)

            }

        }

        fun bind(user: Todo, position: Int) {
            this.currentuser = user
            service.overview(arrayList[position].id.toString().toInt()).enqueue(object : Callback<Overview> {
                override fun onFailure(call: Call<Overview>, t: Throwable) {
//                tv.text="Loading failed!"
//                tv.text=tv.text.toString()+t.cause.toString()
                }
                override fun onResponse(
                    call: Call<Overview>,
                    response: Response<Overview>
                ) {
                    with(itemView) {
                        //                listtext.text = tasks[position].id.toString()
                        titletv.text = response.body()?.title
//                        releasetv.text = response.body()?.release_date
//                        overviewtv.text = response.body()?.overview
                        Picasso.get().load("https://image.tmdb.org/t/p/w500" + response.body()?.poster_path)
                            .into(img)
                    }
                }
            })
        }
    }}
