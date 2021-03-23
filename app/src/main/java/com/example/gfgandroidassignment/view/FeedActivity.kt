package com.example.gfgandroidassignment.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.gfgandroidassignment.R
import com.example.gfgandroidassignment.interfaces.ItemClickListener
import com.example.gfgandroidassignment.model.Item
import com.example.gfgandroidassignment.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.activity_feed.*


class FeedActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    var number: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        swipeRefreshLayout = findViewById(R.id.swipe)


        linearLayoutManager = LinearLayoutManager(this@FeedActivity)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.hasFixedSize()

        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            getServerData() // your code
            swipeRefreshLayout.setRefreshing(false)
        })
        getServerData()
    }


    private fun getServerData() {
        val feedViewModel = ViewModelProviders.of(this@FeedActivity).get(FeedViewModel::class.java)
        feedViewModel.getData()?.observe(this, Observer<List<Item>> { feedList ->
            recyclerView.adapter = FeedAdapter(
                this@FeedActivity,
                feedList as ArrayList<Item>,
                object :
                    ItemClickListener {
                    override fun onItemClick(pos: Int) {
                        Toast.makeText(applicationContext, "item $pos clicked", Toast.LENGTH_LONG)
                            .show()
                    }
                })
        })

    }

    fun getMonth(monthNum: String):String {
        when(monthNum) {
            "01" -> return "Jan"
            "02" -> return "Feb"
            "03" -> return "Mar"
            "04" -> return "Apr"
            "05" -> return "May"
            "06" -> return "Jun"
            "07" -> return "Jul"
            "08" -> return "Aug"
            "09" -> return "Sep"
            "10" -> return "Oct"
            "11" -> return "Nov"
            "12" -> return "Dec"
        }
        return ""
    }

    fun convertDate(date: String):String {
        val newDate:Array<String> = date.split(" ").toTypedArray()
        val formatDate:Array<String> = newDate[0].split("-").toTypedArray()
        val month:String = getMonth(formatDate[1])
        val format:String = month + " " + formatDate[2] + ", " + formatDate[0]
        val formatTime:Array<String> = newDate[1].split(":").toTypedArray()
        if (formatTime[0].toInt() > 12) {
            var after:String = formatTime[0].toInt().minus(12).toString()

            if (after.toInt() > 9) {
                after = after + ":" + formatTime[1] + " PM"
                return format + " " + after
            }
            else {
                after = "0" + after + ":" + formatTime[1] + " PM"
                return format + " " + after
            }
        }
        else {
            if (formatTime[0].toInt() > 9) {
                var before:String = formatTime[0] + ":" + formatTime[1] + " AM"
                return format + " " + before
            }
            else {
                var before:String = "0" + formatTime[0] + ":" + formatTime[1] + " AM"
                return format + " " + before
            }
        }
        return ""
    }

}