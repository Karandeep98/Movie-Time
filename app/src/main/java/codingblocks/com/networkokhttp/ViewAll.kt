package codingblocks.com.networkokhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.viewall.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewAll : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewall)


        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofitClient.create(GithubService::class.java)
        var p = intent.getStringExtra("category")
        when (p) {
            "nowshowing"-> {
                var list:ArrayList<GithubResponse> = arrayListOf()
        for (i in 1..5) {
            toolbar.title="Now Showing Movies"
                service.nowShowing(i).enqueue(object : Callback<Github2> {
                    override fun onFailure(call: Call<Github2>, t: Throwable) {
//                prg.visibility = View.GONE
//                tv.text="Loading failed!"
//                tv.text=tv.text.toString()+t.cause?.toString()
//                Snackbar.make(root, "No Internet Connection", Snackbar.LENGTH_INDEFINITE).show()

                    }

                    override fun onResponse(
                        call: Call<Github2>,
                        response: Response<Github2>
                    ) {

                        runOnUiThread {
                            list.addAll(response.body()!!.results)

                            rview.layoutManager = GridLayoutManager(this@ViewAll, 3, GridLayoutManager.VERTICAL, false)
//                            rview.adapter = ViewallAdapter(this@ViewAll, response.body()!!.results)
                            rview.adapter=ViewallAdapter(this@ViewAll,list)
                prg.visibility=View.GONE
                        }
                    }
                })
            }
        }
            "popular"-> {
                toolbar.title="Popular Movies"
                service.popularMovies().enqueue(object : Callback<Github2> {
                    override fun onFailure(call: Call<Github2>, t: Throwable) {
//                tv.text="Loading failed!"
//                tv.text=tv.text.toString()+t.cause.toString()
                    }

                    override fun onResponse(
                        call: Call<Github2>,
                        response: Response<Github2>
                    ) {
                        runOnUiThread {
                            rview.layoutManager = GridLayoutManager(this@ViewAll, 3, GridLayoutManager.VERTICAL, false)
                            rview.adapter = ViewallAdapter(this@ViewAll, response.body()!!.results)
                            prg.visibility=View.GONE
                        }
                    }
                })
            }
            "upcoming"-> {
                toolbar.title="Upcoming Movies"
                service.upcoming().enqueue(object : Callback<Github2> {
                    override fun onFailure(call: Call<Github2>, t: Throwable) {
//                tv.text="Loading failed!"
//                tv.text=tv.text.toString()+t.cause.toString()
                    }

                    override fun onResponse(
                        call: Call<Github2>,
                        response: Response<Github2>
                    ) {
                        runOnUiThread {
                            rview.layoutManager = GridLayoutManager(this@ViewAll, 3, GridLayoutManager.VERTICAL, false)
                            rview.adapter = ViewallAdapter(this@ViewAll, response.body()!!.results)
                            prg.visibility=View.GONE
                        }
                    }
                })
            }
            "toprated"-> {
                toolbar.title="Top Rated Movies"
                service.toprated().enqueue(object : Callback<Github2> {
                    override fun onFailure(call: Call<Github2>, t: Throwable) {
//                tv.text="Loading failed!"
//                tv.text=tv.text.toString()+t.cause.toString()
                    }

                    override fun onResponse(
                        call: Call<Github2>,
                        response: Response<Github2>
                    ) {
                        runOnUiThread {
                            rview.layoutManager = GridLayoutManager(this@ViewAll, 3, GridLayoutManager.VERTICAL, false)
                            rview.adapter = ViewallAdapter(this@ViewAll, response.body()!!.results)
                            prg.visibility=View.GONE

                        }
                    }
                })
            }
//        }
        }
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}