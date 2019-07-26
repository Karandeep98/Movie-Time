package codingblocks.com.networkokhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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
//                tv.text="Loading failed!"
//                tv.text=tv.text.toString()+t.cause.toString()
                Snackbar.make(root,"No Internet Connection", Snackbar.LENGTH_INDEFINITE).show()

            }

            override fun onResponse(
                call: Call<Searcharray>,
                response: Response<Searcharray>
            ) {
                runOnUiThread {
                    //                    tv.text=response.body().toString()
                    if(response.body()!!.results.isEmpty()){
                        noresult.visibility=View.VISIBLE
                    }
                    rview.layoutManager = LinearLayoutManager(this@Search, RecyclerView.VERTICAL,false)
                    rview.adapter = SearchAdapter(this@Search, response.body()!!.results)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)
                    prg.visibility=View.GONE

                }
            }
        })
    }
}
