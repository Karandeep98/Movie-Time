package codingblocks.com.networkokhttp

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.review.view.*

class ReviewAdapter(val context: Context, private val arrayList: ArrayList<Reviewdetails>)
    : RecyclerView.Adapter<ReviewAdapter.GithubViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        val inflater = LayoutInflater.from(context)
        return GithubViewHolder(inflater.inflate(R.layout.review, parent, false))

    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val user = arrayList[position]
        holder.bind(user, position)
    }

    inner class GithubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currentuser: Reviewdetails? =null
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
//        }

        fun bind(user: Reviewdetails, position: Int) {
            this.currentuser = user
            with(itemView) {
                nametv.text = "By-"+user.author
                contenttv.text=user.content
//                titletv.text=user.content.substring()
                readmore.setOnClickListener {
                    contenttv.maxLines=15
                    readmore.visibility=View.GONE
                    nametv.visibility=View.VISIBLE
                }
                }
            }

        }
    }


