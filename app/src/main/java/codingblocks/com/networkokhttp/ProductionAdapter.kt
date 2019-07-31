package codingblocks.com.networkokhttp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.production.view.*

class ProductionAdapter(val context: Context, private val arrayList: ArrayList<ProductionCompanies>)
    : RecyclerView.Adapter<ProductionAdapter.GithubViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        val inflater = LayoutInflater.from(context)
        return GithubViewHolder(inflater.inflate(R.layout.production, parent, false))

    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val user = arrayList[position]
        holder.bind(user, position)
    }

    inner class GithubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currentuser: ProductionCompanies? = null
//        init{
//            itemView.setOnClickListener {
//                //        Log.i("tagcharacter",currentuser?.character)
////        Log.i("tagid",currentuser!!.id.toString())
//
//                val l=Intent(context,Castdetails::class.java)
////        Log.i("tagl",l.toString())
//                l.putExtra("castID",currentuser!!.id)
//
//                context.startActivity(l)
//            }

        fun bind(user: ProductionCompanies, position: Int) {
            this.currentuser = user
            with(itemView) {
                name.text = user.name

                Log.i("proname",user.name)
                Log.i("proname2",name.text.toString())
                Picasso.get().load("https://image.tmdb.org/t/p/original" + user.logo_path).into(img)
            }

        }
    }
}


