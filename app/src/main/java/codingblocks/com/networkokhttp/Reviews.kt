package codingblocks.com.networkokhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_reviews.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Reviews : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reviews)
        val id=intent.getIntExtra("ID",0)
        val type=intent.getStringExtra("type")

        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofitClient.create(GithubService::class.java)

        service.reviews(id,type).enqueue(object : Callback<ReviewArray>{
            override fun onFailure(call: Call<ReviewArray>, t: Throwable) {

            }

            override fun onResponse(call: Call<ReviewArray>, response: Response<ReviewArray>) {

                runOnUiThread {
                    if(response.body()!!.results.isEmpty()){
                        img.visibility=View.VISIBLE
                        noreview.visibility=View.VISIBLE
                    }
                    rview.layoutManager = LinearLayoutManager(this@Reviews, RecyclerView.VERTICAL,false)
                    rview.adapter = ReviewAdapter(this@Reviews, response.body()!!.results)
                        prg.visibility=View.GONE
                }
            }

        })

    }
}
