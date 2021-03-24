package com.example.gfgandroidassignment.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
            (holder as FeedHolderLarge).bindItems(feedList.get(position))
            mItemClickListener = itemClick
            holder.title?.text = feedList[position].title
            holder.date?.text = ((context).convertDate(feedList[position].pubDate))
            holder.time?.text = ((context).convertTime(feedList[position].pubDate))

            // Image path is not in correct format. Showing blank screen
            Log.d("FeedAdapter", "Large article image url ::  " + feedList[position].enclosure.link)


            // TODO Please comment below line in order to check the image position
            Glide.with(context).load(feedList[position].enclosure.link).into(holder.image)

            RxView.clicks(holder.mView).subscribe {
                mItemClickListener!!.onItemClick(position)
            }
        }
        else {
            (holder as FeedHolderSmall).bindItems(feedList.get(position))
            mItemClickListener = itemClick
            holder.title?.text = feedList[position].title
            holder.date?.text = ((context).convertDate(feedList[position].pubDate))
            holder.time?.text = ((context).convertTime(feedList[position].pubDate))

            // Image path is not in correct format. Showing blank screen
            Log.d("FeedAdapter", "Regular articles image url ::  " + feedList[position].thumbnail)

            // TODO Please comment below line in order to check the image position
            Glide.with(context).load(feedList[position].thumbnail).into(holder.image)

            RxView.clicks(holder.mView).subscribe {
                mItemClickListener!!.onItemClick(position)
            }
        }
    }

    class FeedHolderLarge(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.tvTitle
        val date = view.tvDate
        val time = view.tvTime
        val image = view.ivImage
        val mView:View = view.parentLayout
        fun bindItems(item: Item) {
            title.text=item.title
        }
    }

    class FeedHolderSmall(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.tvTitle2
        val date = view.tvDate2
        val time = view.tvTime2
        val image = view.ivImage2
        val mView:View = view.parentRelative

        fun bindItems(item: Item) {
            title.text=item.title
        }
    }

}