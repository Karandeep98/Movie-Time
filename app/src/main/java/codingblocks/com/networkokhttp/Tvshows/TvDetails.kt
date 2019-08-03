package codingblocks.com.networkokhttp.Tvshows

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import codingblocks.com.networkokhttp.*
import codingblocks.com.networkokhttp.ProductionCompanies
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tv_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TvDetails : AppCompatActivity() {
    val db2 by lazy {
        Room.databaseBuilder(this,AppDatabase2::class.java,"todo2.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_details)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val pos = intent.getIntExtra("ID", 55)
        Log.i("pos",pos.toString())
        val check = db2.tododao().checkrepeat(pos)
//        favbt.isChecked = check.isNotEmpty()
        if(check.isNotEmpty()) {
            favfill.visibility = View.VISIBLE
            favborder.visibility=View.GONE
        }
        else if (check.isEmpty()){
            favborder.visibility = View.VISIBLE
            favfill.visibility=View.GONE
        }
        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/tv/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofitClient.create(TVLinks::class.java)
        service.overview(pos).enqueue(object : Callback<Basicdetails> {
            override fun onFailure(call: Call<Basicdetails>, t: Throwable) {
//                tv.text="Loading failed!"
//                tv.text=tv.text.toString()+t.cause.toString()
                Snackbar.make(root,"No Internet Connection", Snackbar.LENGTH_INDEFINITE).show()
                prg.visibility= View.GONE
            }

            override fun onResponse(
                call: Call<Basicdetails>,
                response: Response<Basicdetails>
            ) {
                runOnUiThread {
                    favborder.setOnClickListener {
                        db2.tododao().insert(Todo(id = pos))
                        Toast.makeText(this@TvDetails, "TV Show is added in favourites!", Toast.LENGTH_LONG).show()
                        favfill.visibility=View.VISIBLE
                        favborder.visibility=View.GONE
                    }
                    favfill.setOnClickListener {
                        db2.tododao().deletetask(Todo(id = pos))
                        Toast.makeText(this@TvDetails, "TV Show is removed from favourites!", Toast.LENGTH_LONG).show()
                        favborder.visibility=View.VISIBLE
                        favfill.visibility=View.GONE
                    }
//                    if(response.body()?.production_companies!!.isEmpty()){
//                        tvproduction.visibility=View.GONE
//                    }
//                    rviewproduction.layoutManager = LinearLayoutManager(this@TvDetails, LinearLayoutManager.HORIZONTAL,false)
//                    rviewproduction.adapter = ProductionAdapter(this@TvDetails, response.body()!!.production_companies.filter {
//                        it.logo_path!=null
//                    } as ArrayList<ProductionCompanies>)


//                    prg.setProgress(false)
                    toolbar.title=response.body()!!.name
                    tvtitle.text=response.body()!!.name
                    Picasso.get().load("https://image.tmdb.org/t/p/original"+response.body()?.backdrop_path).fit().centerCrop().into(img)
//                    if(response.body()?.backdrop_path==null){
//                        Picasso.get().load("https://image.tmdb.org/t/p/w500"+response.body()?.poster_path).fit().centerCrop().into(img)
//
//                    }

                    if(response.body()?.vote_average?.toInt()!=0) {
                        tv.text = "\n\n⭐ " + response.body()!!.vote_average + "/10"
                    }
                    tv.text=tv.text.toString()+"\n\nPlot:  "+ response.body()!!.overview+"\n" +
                            "\nFirst Air Date: "+response.body()!!.first_air_date
//                    if(response.body()?.runtime!=0){
////                        Log.i("movieruntime",response.body()?.runtime)
//                        if(response.body()!!.runtime/60!=0){
//                            tv.text=tv.text.toString()+"\n\nRuntime: "+response.body()!!.runtime/60 +" hrs " +response.body()!!.runtime%60+" mins"
//                        }
//                        else
//                            tv.text=tv.text.toString()+"\n\nRuntime: "+response.body()!!.runtime%60+" mins"
//                    }

                }
                tv.text=tv.text.toString() +"\n\nGenres: "
                prg.visibility = View.GONE
                scrollview.visibility=View.VISIBLE
//                    prg.setProgress(100,false)
                for(i in 0 until response.body()!!.genres.size ){
                    tv.text= tv.text.toString() +response.body()!!.genres[i].name+", "
                }
                tv.text=tv.text.substring(0,tv.text.length-2) //to remove the extra comma


                //list of production companies

                share.setOnClickListener {
                    //                        val j=Intent(Intent.ACTION_VIEW, Uri.parse("http://www.imdb.com/title/${response.body()?.imdb_id}"))
//                        startActivity(j)
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "text/plain"
                    intent.putExtra(Intent.EXTRA_TEXT, response.body()?.homepage)
                    startActivity(Intent.createChooser(intent, "Share with"))
                }
//                    favbt.setOnClickListener {
//                        adapter.updateTasks(favlist)
//                        val l=Intent(this@Details,Room::class.java)
//                        l.putExtra("favlist",response.body()!!.id)
//                        startActivity(l)
//                        if(favbt.isChecked){
//                favborder.setOnClickListener {
//                    db.tododao().insert(Todo(id = pos))
//                    Toast.makeText(this@Details, "Movie is added in favourites!", Toast.LENGTH_LONG).show()
//                    favfill.visibility=View.VISIBLE
//                    favborder.visibility=View.GONE
//                }
//                favfill.setOnClickListener {
//                    db.tododao().deletetask(Todo(id = pos))
//                    Toast.makeText(this@Details, "Movie is removed from favourites!", Toast.LENGTH_LONG).show()
//                    favborder.visibility=View.VISIBLE
//                    favfill.visibility=View.GONE
//                }
//                        tv.text=tv.text.toString()+favbt.isChecked.toString()
//                    }

            }
        }
        )
        service.videos(pos).enqueue(object : Callback<Trailerarray> {
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
                    rview3.layoutManager = LinearLayoutManager(this@TvDetails, LinearLayoutManager.HORIZONTAL,false)
                    rview3.adapter = MovieAdapter2(this@TvDetails, response.body()!!.results)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)
                    if(response.body()!!.results.isEmpty())
                        tvtrailer.text=""

                }
            }
        })
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
                    rview.layoutManager = LinearLayoutManager(this@TvDetails, LinearLayoutManager.HORIZONTAL,false)
                    rview.adapter = MovieAdapter(this@TvDetails, response.body()!!.cast)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)

                }
            }
        })
        service.similartvshows(pos).enqueue(object : Callback<SimilarArray> {
            override fun onFailure(call: Call<SimilarArray>, t: Throwable) {
                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause.toString()
            }

            override fun onResponse(
                call: Call<SimilarArray>,
                response: Response<SimilarArray>
            ) {
                runOnUiThread {
                    //                    toolbarimage
                    //                    tv.text=response.body().toString()
                    rview2.layoutManager = LinearLayoutManager(this@TvDetails,LinearLayoutManager.HORIZONTAL,false)
                    rview2.adapter = SimilarTvshowsAdapter(this@TvDetails, response.body()!!.results)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)
                    if(response.body()!!.results.isEmpty())
                        tvsimilar.text=""
                }
            }
        })
        swipeRefresh.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            //            refreshData() // your code
            val i =Intent(this,TvDetails::class.java)
            i.putExtra("ID",pos)
            finish()
            startActivity(i)
            swipeRefresh.isRefreshing = false
        })
        reviews.setOnClickListener {
            val k=Intent(this,Reviews::class.java)
            k.putExtra("ID",pos)
            k.putExtra("type","tv")
            startActivity(k)
        }
    }
}
