package codingblocks.com.networkokhttp
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import codingblocks.com.networkokhttp.Tvshows.TvDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.searchlayout.view.*

class SearchAdapter(val context: Context, private val arrayList: ArrayList<Searchdetails>)
    : RecyclerView.Adapter<SearchAdapter.GithubViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        val inflater = LayoutInflater.from(context)
        return GithubViewHolder(inflater.inflate(R.layout.searchlayout, parent, false))

    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val user = arrayList[position]
        holder.bind(user, position)
    }

    inner class GithubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currentuser: Searchdetails? =null
        init{
            itemView.setOnClickListener {
              if(currentuser?.media_type=="movie") {

                  val l = Intent(context, Details::class.java)
                  l.putExtra("ID", currentuser!!.id)
                  context.startActivity(l)
              }
               else if(currentuser?.media_type=="tv") {

                    val l = Intent(context, TvDetails::class.java)
                    l.putExtra("ID", currentuser!!.id)
                    context.startActivity(l)
                }
                if(currentuser?.media_type=="person") {

                    val l = Intent(context, Castdetails::class.java)
                    l.putExtra("castID", currentuser!!.id)
                    context.startActivity(l)
                }
            }
        }

        fun bind(user: Searchdetails, position: Int) {
            this.currentuser = user
            with(itemView) {
                if(user.media_type=="movie") {
                    titletv.text = user.title
                    releasetv.text = "Release Date: " + user.release_date
                    overviewtv.text = "\n" + user.overview
                    mediatype.text="Movie"
                    Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.poster_path).into(img)
                }
                else if(user.media_type=="tv"){
                    titletv.text = user.name
                    releasetv.text = "First Air Date: " + user.first_air_date
                    overviewtv.text = "\n" + user.overview
                    mediatype.text="TV Show"
                    Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.poster_path).into(img)

                }
                else if(user.media_type=="person"){
                    titletv.text = user.name
                    mediatype.text="Person"
                    Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.profile_path).into(img)

                }

            }

        }
    }}


