package codingblocks.com.networkokhttp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.trailers.view.*

class MovieAdapter2( val context: Context, private val arrayList: ArrayList<Trailers>)
    : RecyclerView.Adapter<GithubViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        val inflater = LayoutInflater.from(context)
        return GithubViewHolder(inflater.inflate(R.layout.trailers, parent, false))
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val user = arrayList[position]
        holder.bind(user, position)
    }
}
  class GithubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: Trailers, position: Int) {

        with(itemView) {
            tv.text=user.name
            Picasso.get().load("https://img.youtube.com/vi/"+user.key+"/0.jpg").into(bt)
            bt.setOnClickListener {
                val j= Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+user.key))
                context.startActivity(j)
            }

        }

    }
}



