package codingblocks.com.networkokhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
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
                    val age = 2019-response.body()?.birthday?.substring(0,4)!!.toInt()
                    tv.text="\n\nAge: "+age+"\nBirthPlace: "+response.body()?.place_of_birth+
                            "\nBiography\n"+response.body()?.biography
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
                    rview.adapter = Moviecastadapter(this@Castdetails, response.body()!!.cast)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)

                }
            }
        })

    }
}
