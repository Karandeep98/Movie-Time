package codingblocks.com.networkokhttp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.edit
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.room.Room
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout



class Details : AppCompatActivity() {
    val db by lazy {
        Room.databaseBuilder(this,AppDatabase::class.java,"todo.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var pos = intent.getIntExtra("ID", 55)
        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var check = db.tododao().checkrepeat(pos)
//        favbt.isChecked = check.isNotEmpty()
        if(check.isNotEmpty()) {
            favfill.visibility = View.VISIBLE
            favborder.visibility=View.GONE
        }
        else if (check.isEmpty()){
            favborder.visibility = View.VISIBLE
            favfill.visibility=View.GONE
        }


        val service = retrofitClient.create(GithubService::class.java)
//        val db by lazy {
//            androidx.room.Room.databaseBuilder(this,AppDatabase::class.java,"todo.db")
//                .allowMainThreadQueries()
//                .fallbackToDestructiveMigration()
//                .build()
//        }
//        var favlist=db.tododao().getalltask() as ArrayList<Todo>
//        val adapter=TaskAdapter(favlist,this)
//        lv.adapter=adapter

//        var favlist= arrayListOf<Int>()
        service.overview(pos).enqueue(object : Callback<Overview> {
            override fun onFailure(call: Call<Overview>, t: Throwable) {
//                tv.text="Loading failed!"
//                tv.text=tv.text.toString()+t.cause.toString()
                Snackbar.make(root,"No Internet Connection", Snackbar.LENGTH_INDEFINITE).show()
prg.visibility=View.GONE
            }

            override fun onResponse(
                call: Call<Overview>,
                response: Response<Overview>
            ) {
                runOnUiThread {

//                    prg.setProgress(false)
                    toolbar.title=response.body()!!.title
                    tvtitle.text=response.body()!!.title
                    Picasso.get().load("https://image.tmdb.org/t/p/original"+response.body()?.backdrop_path).into(img)
                    if(response.body()?.backdrop_path==null){
                        Picasso.get().load("https://image.tmdb.org/t/p/w500"+response.body()?.poster_path).into(img)

                    }
//                    if(response.body()?.backdrop_path==null){
//                        Picasso.get().load("https://image.tmdb.org/t/p/original"+response.body()?.poster_path).into(img)
//
//                    }
                    if(response.body()?.vote_average?.toInt()!=0) {
                        tv.text = "\n\n⭐ " + response.body()!!.vote_average + "/10"
                    }
                    tv.text=tv.text.toString()+"\n\nPlot:  "+ response.body()!!.overview+"\n" +
                           "\nRelease Date: "+response.body()!!.release_date
                    if(response.body()?.runtime!=0){
//                        Log.i("movieruntime",response.body()?.runtime)
                        if(response.body()!!.runtime/60!=0){
                        tv.text=tv.text.toString()+"\n\nRuntime: "+response.body()!!.runtime/60 +" hrs " +response.body()!!.runtime%60+" mins"
                                    }
                        else
                        tv.text=tv.text.toString()+"\n\nRuntime: "+response.body()!!.runtime%60+" mins"
                    }
                    }
                   tv.text=tv.text.toString() +"\n\nGenres: "
                    prg.visibility = View.GONE
                scrollview.visibility=View.VISIBLE
//                    prg.setProgress(100,false)
                for(i in 0 until response.body()!!.genres.size ){
                    tv.text= tv.text.toString() +response.body()!!.genres[i].name+", "
                }
                  tv.text=tv.text.substring(0,tv.text.length-2) //to remove the extra comma

                    share.setOnClickListener {
//                        val j=Intent(Intent.ACTION_VIEW, Uri.parse("http://www.imdb.com/title/${response.body()?.imdb_id}"))
//                        startActivity(j)
                        val intent = Intent(Intent.ACTION_SEND)
                        intent.type = "text/plain"
                        intent.putExtra(Intent.EXTRA_TEXT, "http://www.imdb.com/title/${response.body()?.imdb_id}")
                        startActivity(Intent.createChooser(intent, "Share with"))
                    }
//                    favbt.setOnClickListener {
//                        adapter.updateTasks(favlist)
//                        val l=Intent(this@Details,Room::class.java)
//                        l.putExtra("favlist",response.body()!!.id)
//                        startActivity(l)
//                        if(favbt.isChecked){
                    favborder.setOnClickListener {
                            db.tododao().insert(Todo(id = pos))
                            Toast.makeText(this@Details, "Movie is added in favourites!", Toast.LENGTH_LONG).show()
                            favfill.visibility=View.VISIBLE
                            favborder.visibility=View.GONE
                        }
                        favfill.setOnClickListener {
                            db.tododao().deletetask(Todo(id = pos))
                            Toast.makeText(this@Details, "Movie is removed from favourites!", Toast.LENGTH_LONG).show()
                        favborder.visibility=View.VISIBLE
                            favfill.visibility=View.GONE
                        }
//                        tv.text=tv.text.toString()+favbt.isChecked.toString()
//                    }

                }
            }
        )

        service.trailers(pos).enqueue(object : Callback<Trailerarray> {
            override fun onFailure(call: Call<Trailerarray>, t: Throwable) {
                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause.toString()
            }

            override fun onResponse(
                call: Call<Trailerarray>,
                response: Response<Trailerarray>
            ) {
                runOnUiThread {
                    //                    tv.text=response.body().toString()
                    rview3.layoutManager = LinearLayoutManager(this@Details, LinearLayoutManager.HORIZONTAL,false)
                    rview3.adapter = MovieAdapter2(this@Details, response.body()!!.results)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)
        if(response.body()!!.results.isEmpty())
                tvtrailer.text=""

                }
            }
        })
//        service.movieinfo().enqueue(object : Callback<MovieInfo> {
//            override fun onFailure(call: Call<MovieInfo>, t: Throwable) {
//                tv.text="Loading failed!"
//                tv.text=tv.text.toString()+t.cause.toString()
//            }
//
//            override fun onResponse(
//                call: Call<MovieInfo>,
//                response: Response<MovieInfo>
//            ) {
//                runOnUiThread {
//                   tvheader.text=response.body()!!.original_title+"\n"+ response.body()!!.overview+"\n" +
//                           "Release Date "+response.body()!!.release_date+"\nRuntime "+response.body()!!.runtime+"mins"
//                }
//            }
//        })
        service.cast(pos).enqueue(object : Callback<Cast> {
            override fun onFailure(call: Call<Cast>, t: Throwable) {
                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause.toString()
            }

            override fun onResponse(
                call: Call<Cast>,
                response: Response<Cast>
            ) {
                runOnUiThread {
                    //                    tv.text=response.body().toString()
                    rview.layoutManager = LinearLayoutManager(this@Details, LinearLayoutManager.HORIZONTAL,false)
                    rview.adapter = MovieAdapter(this@Details, response.body()!!.cast)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)

                }
            }
        })
        service.similarmovies(pos).enqueue(object : Callback<Github2> {
            override fun onFailure(call: Call<Github2>, t: Throwable) {
                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause.toString()
            }

            override fun onResponse(
                call: Call<Github2>,
                response: Response<Github2>
            ) {
                runOnUiThread {
//                    toolbarimage
                    //                    tv.text=response.body().toString()
                    rview2.layoutManager = LinearLayoutManager(this@Details,LinearLayoutManager.HORIZONTAL,false)
                    rview2.adapter = GithubAdapter2(this@Details, response.body()!!.results)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)
                    if(response.body()!!.results.isEmpty())
                        tvsimilar.text=""
                }
            }
        })
        swipeRefresh.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
//            refreshData() // your code
            val i =Intent(this,Details::class.java)
            i.putExtra("ID",pos)
            finish()
            startActivity(i)
            swipeRefresh.isRefreshing = false
        })


    }


}
