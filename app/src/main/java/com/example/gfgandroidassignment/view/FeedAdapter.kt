package com.example.gfgandroidassignment.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gfgandroidassignment.R
import com.example.gfgandroidassignment.interfaces.ItemClickListener
import com.example.gfgandroidassignment.model.Item
import com.jakewharton.rxbinding.view.RxView
import kotlinx.android.synthetic.main.item_feed_large.view.*
import kotlinx.android.synthetic.main.item_feed_small.view.*

class FeedAdapter(
    var context: FeedActivity,
    var feedList: ArrayList<Item>,
    private val itemClick: ItemClickListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    companion object {
        var mItemClickListener : ItemClickListener? = null
    }
    var LAYOUT_LARGE:Int = 0
    var LAYOUT_SMALL:Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == LAYOUT_LARGE) {
            val view = LayoutInflater.from(context).inflate(R.layout.item_feed_large, parent, false)
            return FeedHolderLarge(view)
        }
        else {
            val view = LayoutInflater.from(context).inflate(R.layout.item_feed_small, parent, false)
            return FeedHolderSmall(view)
        }
    }

    override fun getItemCount(): Int {
        return feedList.size
    }


    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return LAYOUT_LARGE
        }
        else {
            return LAYOUT_SMALL
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == LAYOUT_LARGE) {
            (holder as FeedHolderLarge).bindItems(feedList.get(position), context)
            holder.tvName?.text = feedList[position].title
            holder.tvDate?.text = ((context).convertDate(feedList[position].pubDate))
        }
        else {
            (holder as FeedHolderSmall).bindItems(feedList.get(position), context)
            holder.tvFname?.text = feedList[position].title
            mItemClickListener = itemClick
            holder.tvFname?.text = feedList[position].title
            holder.tvDate2?.text = ((context).convertDate(feedList[position].pubDate))
            RxView.clicks(holder.mView).subscribe {
                mItemClickListener!!.onItemClick(position)
            }
        }
    }

    class FeedHolderLarge(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.tvTitle
        val tvDate = view.tvDate
        val context: Context? = null

        fun bindItems(item: Item, context: FeedActivity) {
            tvName.text=item.title
//            tvDate.text = ((context).convertDate(item.pubDate))

//            Log.d("TAG", "FeedHolderLarge tvDate.text:: " + tvDate.text)

        }


    }

    class FeedHolderSmall(view: View) : RecyclerView.ViewHolder(view) {
        val tvFname = view.tvTitle2
        val tvDate2 = view.tvDate2

        val mView:View = view.parentRelative

        fun bindItems(item: Item, context: FeedActivity) {
            tvFname.text=item.title
//            tvDate2.text = ((context).convertDate(item.pubDate))
//            Log.d("TAG", "FeedHolderSmall tvDate2.text:: " + tvDate2.text)


        }
    }

}