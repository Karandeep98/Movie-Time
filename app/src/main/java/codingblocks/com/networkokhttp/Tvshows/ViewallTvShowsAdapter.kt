package codingblocks.com.networkokhttp.Tvshows

import codingblocks.com.networkokhttp.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.edit
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_github.view.*

class ViewallTvShowsAdapter( val context: Context, private val arrayList: ArrayList<OverviewTv>)
    : RecyclerView.Adapter<ViewallTvShowsAdapter.GithubViewHolder>() {
    //    val prefs= PreferenceManager.getDefaultSharedPreferences(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        val inflater = LayoutInflater.from(context)
        return GithubViewHolder(inflater.inflate(R.layout.activity_favourites, parent, false))

    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val user = arrayList[position]
        holder.bind(user, position)
    }

    inner class GithubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currentuser: OverviewTv? = null

        init {
            itemView.setOnClickListener {
                //            Toast.makeText(context, currentuser!!.title + "clicked!",Toast.LENGTH_LONG ).show()
                val detail= Intent(context,TvDetails::class.java)
                detail.putExtra("ID", currentuser!!.id)
                context.startActivity(detail)

            }

        }

        fun bind(user: OverviewTv, position: Int) {

            this.currentuser = user
            with(itemView) {
                titletv.text = user.name
//                ratingtv.text = "⭐ " + user.vote_average.toString() + "/10"
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.poster_path).fit().centerCrop().into(img)
            }
        }
    }}




