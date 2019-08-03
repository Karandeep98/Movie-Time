package codingblocks.com.networkokhttp.Tvshows

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import codingblocks.com.networkokhttp.*
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_tvmain.*
import kotlinx.android.synthetic.main.navigation_tv.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TVMainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val retrofitClient = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/tv/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofitClient.create(TVLinks::class.java)
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.movies -> {
                val l = Intent(this, MainActivity::class.java)
                startActivity(l)
            }
            R.id.fav -> {
                val l = Intent(this, Room::class.java)
//                l.putExtra("favlist", 1)
                startActivity(l)
            }
            R.id.about -> {
                val l = Intent(this, About::class.java)
                startActivity(l)
            }
            R.id.tvshows->{
//                val l = Intent(this, TVMainActivity::class.java)
//                startActivity(l)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_tv)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        service.airingtoday(1).enqueue(object : Callback<OverviewTvarray> {
            override fun onFailure(call: Call<OverviewTvarray>, t: Throwable) {
//                prg.visibility= View.GONE
//                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause?.toString()
                Snackbar.make(root,"No Internet Connection", Snackbar.LENGTH_INDEFINITE).show()

            }

            override fun onResponse(
                call: Call<OverviewTvarray>,
                response: Response<OverviewTvarray>
            ) {
                runOnUiThread {
                    //                    tv.text=response.body().toString()
                    rview.layoutManager = LinearLayoutManager(this@TVMainActivity, LinearLayoutManager.HORIZONTAL,false)
                    rview.adapter = TvAdapter(this@TVMainActivity, response.body()!!.results.filter {
                        it.original_language=="en"&& it.backdrop_path!=null
                    } as ArrayList<OverviewTv>)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)
                    val snapHelper = PagerSnapHelper()
                    snapHelper.attachToRecyclerView(rview)
                    prg.visibility= View.GONE
                    scrollview.visibility=View.VISIBLE
                }
            }
        })
        service.onair(1).enqueue(object : Callback<OverviewTvarray> {
            override fun onFailure(call: Call<OverviewTvarray>, t: Throwable) {
//                prg.visibility= View.GONE
//                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause?.toString()
                Snackbar.make(root,"No Internet Connection", Snackbar.LENGTH_INDEFINITE).show()

            }

            override fun onResponse(
                call: Call<OverviewTvarray>,
                response: Response<OverviewTvarray>
            ) {
                runOnUiThread {
                    //                    tv.text=response.body().toString()
                    rview2.layoutManager = LinearLayoutManager(this@TVMainActivity, LinearLayoutManager.HORIZONTAL,false)
                    rview2.adapter = TvAdapter2(this@TVMainActivity, response.body()!!.results.filter {
                        it.original_language=="en"
                    } as ArrayList<OverviewTv>)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)
//                    val snapHelper = PagerSnapHelper()
//                    snapHelper.attachToRecyclerView(rview)
                    prg.visibility= View.GONE
//                    scrollview.visibility= View.VISIBLE
                }
            }
        })
        service.popular(1).enqueue(object : Callback<OverviewTvarray> {
            override fun onFailure(call: Call<OverviewTvarray>, t: Throwable) {
//                prg.visibility= View.GONE
//                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause?.toString()
                Snackbar.make(root,"No Internet Connection", Snackbar.LENGTH_INDEFINITE).show()

            }

            override fun onResponse(
                call: Call<OverviewTvarray>,
                response: Response<OverviewTvarray>
            ) {
                runOnUiThread {
                    //                    tv.text=response.body().toString()
                    rview3.layoutManager = LinearLayoutManager(this@TVMainActivity, LinearLayoutManager.HORIZONTAL,false)
                    rview3.adapter = TvAdapter(this@TVMainActivity, response.body()!!.results.filter {
                        it.original_language=="en"&& it.backdrop_path!=null
                    } as ArrayList<OverviewTv>)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)
                    val snapHelper = PagerSnapHelper()
                    snapHelper.attachToRecyclerView(rview3)
                    prg.visibility= View.GONE
//                    scrollview.visibility= View.VISIBLE
                }
            }
        })
        service.toprated(1).enqueue(object : Callback<OverviewTvarray> {
            override fun onFailure(call: Call<OverviewTvarray>, t: Throwable) {
//                prg.visibility= View.GONE
//                tv.text="Loading failed!"
                tv.text=tv.text.toString()+t.cause?.toString()
                Snackbar.make(root,"No Internet Connection", Snackbar.LENGTH_INDEFINITE).show()

            }

            override fun onResponse(
                call: Call<OverviewTvarray>,
                response: Response<OverviewTvarray>
            ) {
                runOnUiThread {
                    //                    tv.text=response.body().toString()
                    rview4.layoutManager = LinearLayoutManager(this@TVMainActivity, LinearLayoutManager.HORIZONTAL,false)
                    rview4.adapter = TvAdapter2(this@TVMainActivity, response.body()!!.results.filter {
                        it.original_language=="en"&& it.backdrop_path!=null
                    } as ArrayList<OverviewTv>)
//                    Picasso.get().load(response.body()?.Poster.toString()).into(image)
//                    val snapHelper = PagerSnapHelper()
//                    snapHelper.attachToRecyclerView(rview)
                    prg.visibility= View.GONE
//                    scrollview.visibility= View.VISIBLE
                }
            }
        })
        viewAllAiringToday.setOnClickListener {
            val a=Intent(this,ViewAllTvShows::class.java)
            a.putExtra("category","airingtoday")
            startActivity(a)
        }
        viewAllOnAir.setOnClickListener {
            val a=Intent(this,ViewAllTvShows::class.java)
            a.putExtra("category","onair")
            startActivity(a)
        }

        viewAllpopulartvshows.setOnClickListener {
            val a=Intent(this,ViewAllTvShows::class.java)
            a.putExtra("category","populartvshows")
            startActivity(a)
        }
        viewAlltopratedtvshows.setOnClickListener {
            val a=Intent(this,ViewAllTvShows::class.java)
            a.putExtra("category","topratedtvshows")
            startActivity(a)
        }
    }
}
