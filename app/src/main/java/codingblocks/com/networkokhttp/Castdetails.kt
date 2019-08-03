package codingblocks.com.networkokhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import codingblocks.com.networkokhttp.Tvshows.TvcastAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_castdetails.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Castdetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_castdetails)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val pos = intent.getIntExtra("castID",0)
        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val service = retrofitClient.create(GithubService::class.java)

        service.castinfo(pos).enqueue(object : Callback<Castinfo> {
            override fun onFailure(call: Call<Castinfo>, t: Throwable) {
                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause.toString()
            }

            override fun onResponse(
                call: Call<Castinfo>,
                response: Response<Castinfo>
            ) {
                runOnUiThread {
                    toolbar.title=response.body()?.name
                    tvname.text=response.body()!!.name
                    Picasso.get().load("https://image.tmdb.org/t/p/w500"+response.body()?.profile_path).into(img)
                    if(response.body()?.birthday!=null) {
                        val age = 2019 - response.body()?.birthday?.substring(0, 4)!!.toInt()
                        tv.text="\n"+age.toString()
                        tv.visibility=View.VISIBLE
                    }
            if(response.body()?.place_of_birth!=null) {
                tv2.text = "\n" + response.body()?.place_of_birth
                tv2.visibility=View.VISIBLE
            }
                    readmore.setOnClickListener {
                        readmore.visibility=View.GONE
                        tv3.visibility=View.VISIBLE
                        tv4.visibility=View.VISIBLE
                    }
                      tv3.text= response.body()?.biography
                    prg.visibility=View.GONE
                }
            }
        })
        service.moviecast(pos).enqueue(object : Callback<Moviecastarray> {
            override fun onFailure(call: Call<Moviecastarray>, t: Throwable) {
                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause.toString()
            }

            override fun onResponse(
                call: Call<Moviecastarray>,
                response: Response<Moviecastarray>
            ) {
                runOnUiThread {
                    //                    tv.text=response.body().toString()
                    rview.layoutManager = LinearLayoutManager(this@Castdetails,LinearLayoutManager.HORIZONTAL,false)
                    rview.adapter = Moviecastadapter(this@Castdetails, response.body()!!.cast.filter {
                        it.poster_path!=null
                    } as ArrayList<Moviecast>)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)

                }
            }
        })
        service.tvcast(pos).enqueue(object : Callback<TVcastArray> {
            override fun onFailure(call: Call<TVcastArray>, t: Throwable) {
                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause.toString()
            }

            override fun onResponse(
                call: Call<TVcastArray>,
                response: Response<TVcastArray>
            ) {
                runOnUiThread {
                    //                    tv.text=response.body().toString()
                    rview2.layoutManager = LinearLayoutManager(this@Castdetails,LinearLayoutManager.HORIZONTAL,false)
                    rview2.adapter = TvcastAdapter(this@Castdetails, response.body()!!.cast.filter {
                        it.poster_path!=null
                    } as ArrayList<Tvcast>)

                    if(response.body()?.cast!!.isEmpty()){
                        tvcast.visibility=View.GONE
                    }
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)

                }
            }
        })
    }
}
