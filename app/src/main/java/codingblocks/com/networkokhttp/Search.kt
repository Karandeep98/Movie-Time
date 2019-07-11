package codingblocks.com.networkokhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Search : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val text=intent.getStringExtra("searchtext")
        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val service = retrofitClient.create(GithubService::class.java)
        service.search(text).enqueue(object : Callback<Searcharray> {
            override fun onFailure(call: Call<Searcharray>, t: Throwable) {
                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause.toString()
            }

            override fun onResponse(
                call: Call<Searcharray>,
                response: Response<Searcharray>
            ) {
                runOnUiThread {
                    //                    tv.text=response.body().toString()
                    rview.layoutManager = LinearLayoutManager(this@Search, LinearLayoutManager.VERTICAL,false)
                    rview.adapter = SearchAdapter(this@Search, response.body()!!.results)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)

                }
            }
        })
    }
}
