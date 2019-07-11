package codingblocks.com.networkokhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Details : AppCompatActivity() {

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


        val service = retrofitClient.create(GithubService::class.java)

        service.overview(pos).enqueue(object : Callback<Overview> {
            override fun onFailure(call: Call<Overview>, t: Throwable) {
                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause.toString()
            }

            override fun onResponse(
                call: Call<Overview>,
                response: Response<Overview>
            ) {
                runOnUiThread {
                    toolbar.title=response.body()!!.original_title
//                    Picasso.get().load("https://image.tmdb.org/t/p/w500"+response.body()!!.backdrop_path).into(toolbarimage)
                    tv.text="\n\n⭐ "+ response.body()!!.vote_average+"/10\n\nPlot:  "+ response.body()!!.overview+"\n" +
                           "\nRelease Date: "+response.body()!!.release_date+"\n\nRuntime: "+
                    response.body()!!.runtime/60 +" hrs " +response.body()!!.runtime%60+" mins"+"\n\nGenres: "

                for(i in 0 until response.body()!!.genres.size ){
                    tv.text= tv.text.toString() +response.body()!!.genres[i].name+", "
                }
                  tv.text=tv.text.substring(0,tv.text.length-2) //to remove the extra comma
                }
            }
        })
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

                }
            }
        })


    }


}
