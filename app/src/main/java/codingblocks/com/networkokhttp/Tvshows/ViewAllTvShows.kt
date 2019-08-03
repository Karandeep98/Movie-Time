package codingblocks.com.networkokhttp.Tvshows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import codingblocks.com.networkokhttp.R
import kotlinx.android.synthetic.main.activity_view_all_tv_shows.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewAllTvShows : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all_tv_shows)
    var isScrolling=false

        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/tv/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofitClient.create(TVLinks::class.java)
        val p = intent.getStringExtra("category")
        var counter=1
        val manager= GridLayoutManager(this,3, RecyclerView.VERTICAL,false)
        val list:ArrayList<OverviewTv> = arrayListOf()
        rview.layoutManager=manager
        rview.adapter=ViewallTvShowsAdapter(this@ViewAllTvShows,list)
        fun fetchdata( count:Int){
            when (p) {
                "airingtoday"-> {

                    toolbar.title="Airing Today"

                    service.airingtoday(count).enqueue(object : Callback<OverviewTvarray> {
                        override fun onFailure(call: Call<OverviewTvarray>, t: Throwable) {
//                prg.visibility = View.GONE
//                tv.text="Loading failed!"
//                tv.text=tv.text.toString()+t.cause?.toString()
//                Snackbar.make(root, "No Internet Connection", Snackbar.LENGTH_INDEFINITE).show()

                        }
                        override fun onResponse(
                            call: Call<OverviewTvarray>,
                            response: Response<OverviewTvarray>
                        ) {

                            runOnUiThread {
                                list.addAll(response.body()!!.results.filter {
                                    it.original_language!="ja"&&it.backdrop_path!=null
                                })

                                (rview.adapter as ViewallTvShowsAdapter).notifyDataSetChanged()
                                prg.visibility= View.GONE

                            }
                        }
                    })
//            }
                }
                "onair"-> {
                    toolbar.title="On Air"
                    service.onair(count).enqueue(object : Callback<OverviewTvarray> {
                        override fun onFailure(call: Call<OverviewTvarray>, t: Throwable) {
//                tv.text="Loading failed!"
//                tv.text=tv.text.toString()+t.cause.toString()
                        }

                        override fun onResponse(
                            call: Call<OverviewTvarray>,
                            response: Response<OverviewTvarray>
                        ) {
                            runOnUiThread {
                                list.addAll(response.body()!!.results.filter {
                                    it.original_language!="ja"&&it.backdrop_path!=null
                                })

                                (rview.adapter as ViewallTvShowsAdapter).notifyDataSetChanged()
                                prg.visibility=View.GONE
                            }
                        }
                    })
                }
                "populartvshows"-> {
                    toolbar.title="Popular TV Shows"
                    service.popular(count).enqueue(object : Callback<OverviewTvarray> {
                        override fun onFailure(call: Call<OverviewTvarray>, t: Throwable) {
//                tv.text="Loading failed!"
//                tv.text=tv.text.toString()+t.cause.toString()
                        }

                        override fun onResponse(
                            call: Call<OverviewTvarray>,
                            response: Response<OverviewTvarray>
                        ) {
                            runOnUiThread {
                                list.addAll(response.body()!!.results.filter {
                                    it.original_language!="ja"&&it.backdrop_path!=null
                                })

                                (rview.adapter as ViewallTvShowsAdapter).notifyDataSetChanged()
                                prg.visibility=View.GONE
                            }
                        }
                    })
                }
                "topratedtvshows"-> {
                    toolbar.title = "Top Rated TV Shows"
                    service.toprated(count).enqueue(object : Callback<OverviewTvarray> {
                        override fun onFailure(call: Call<OverviewTvarray>, t: Throwable) {
//                tv.text="Loading failed!"
//                tv.text=tv.text.toString()+t.cause.toString()

                        }

                        override fun onResponse(
                            call: Call<OverviewTvarray>,
                            response: Response<OverviewTvarray>
                        ) {
                            runOnUiThread {
                                list.addAll(response.body()!!.results.filter {
                                    it.original_language!=null&&it.backdrop_path!=null
                                })

                                (rview.adapter as ViewallTvShowsAdapter).notifyDataSetChanged()
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
                if(newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling=true
                }
            }
        })

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
