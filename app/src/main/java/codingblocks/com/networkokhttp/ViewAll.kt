package codingblocks.com.networkokhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.viewall.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewAll : AppCompatActivity() {
    var isScrolling=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewall)
        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofitClient.create(GithubService::class.java)
        val p = intent.getStringExtra("category")
        var counter=1
        val manager=GridLayoutManager(this,3,RecyclerView.VERTICAL,false)
        val list:ArrayList<GithubResponse> = arrayListOf()
        rview.layoutManager=manager
        rview.adapter=ViewallAdapter(this@ViewAll,list)
        fun fetchdata( count:Int){
            when (p) {
                "nowshowing"-> {

                    toolbar.title="Now Showing Movies"

                    service.nowShowing(count).enqueue(object : Callback<Github2> {
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

                                (rview.adapter as ViewallAdapter).notifyDataSetChanged()
                                prg.visibility=View.GONE

                            }
                        }
                    })
//            }
                }
                "popular"-> {
                    toolbar.title="Popular Movies"
                    service.popularMovies(count).enqueue(object : Callback<Github2> {
                        override fun onFailure(call: Call<Github2>, t: Throwable) {
//                tv.text="Loading failed!"
//                tv.text=tv.text.toString()+t.cause.toString()
                        }

                        override fun onResponse(
                            call: Call<Github2>,
                            response: Response<Github2>
                        ) {
                            runOnUiThread {
                                list.addAll(response.body()!!.results)

                                (rview.adapter as ViewallAdapter).notifyDataSetChanged()
                               prg.visibility=View.GONE
                            }
                        }
                    })
                }
                "upcoming"-> {
                    toolbar.title="Upcoming Movies"
                    service.upcoming(count).enqueue(object : Callback<Github2> {
                        override fun onFailure(call: Call<Github2>, t: Throwable) {
//                tv.text="Loading failed!"
//                tv.text=tv.text.toString()+t.cause.toString()
                        }

                        override fun onResponse(
                            call: Call<Github2>,
                            response: Response<Github2>
                        ) {
                            runOnUiThread {
                                list.addAll(response.body()!!.results)

                                (rview.adapter as ViewallAdapter).notifyDataSetChanged()
                                prg.visibility=View.GONE
                            }
                        }
                    })
                }
                "toprated"-> {
                    toolbar.title = "Top Rated Movies"
                    service.toprated(count).enqueue(object : Callback<Github2> {
                        override fun onFailure(call: Call<Github2>, t: Throwable) {
//                tv.text="Loading failed!"
//                tv.text=tv.text.toString()+t.cause.toString()

                        }

                        override fun onResponse(
                            call: Call<Github2>,
                            response: Response<Github2>
                        ) {
                            runOnUiThread {
                                list.addAll(response.body()!!.results)

                                (rview.adapter as ViewallAdapter).notifyDataSetChanged()
                                prg.visibility = View.GONE

                            }
                        }
                    })
                }
            }
//        }
        }

        fetchdata(1)
        rview.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentitems=manager.childCount
                val totalitems=manager.itemCount
                val scrolledoutitems= manager.findFirstVisibleItemPosition()
                if(isScrolling &&(currentitems+scrolledoutitems==totalitems)){
                    isScrolling=false
                    counter++
                    fetchdata(counter)
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState==AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling=true
                }
            }
        })

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}