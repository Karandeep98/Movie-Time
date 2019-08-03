package codingblocks.com.networkokhttp.Tvshows

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import codingblocks.com.networkokhttp.*
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_tvmain.*
import kotlinx.android.synthetic.main.navigation_tv.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
                l.putExtra("type", "movie")
                startActivity(l)
            }
            R.id.favtv->{
                val l = Intent(this, Room::class.java)
                l.putExtra("type", "tv")
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


    private var doubleBackPressed = false
    override fun onBackPressed() {

//        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (doubleBackPressed) {
//            super.onBackPressed()
            finishAffinity()
        }
        else {
            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(GravityCompat.START)
            }
            else{
                doubleBackPressed = true
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
                GlobalScope.launch {
                    delay(2000)
                    doubleBackPressed = false
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        val searchitem=menu?.findItem(R.id.menu_search)
        val searchview=searchitem?.actionView as androidx.appcompat.widget.SearchView
        searchview.queryHint="Search Movies,TV Shows,Actors"
        searchview.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val a= Intent(this@TVMainActivity,Search::class.java)
                a.putExtra("searchtext",p0)
                startActivity(a)
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
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
