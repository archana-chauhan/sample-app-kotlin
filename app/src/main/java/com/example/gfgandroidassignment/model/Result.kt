package com.example.gfgandroidassignment.model

data class Result(
    val feed: Feed,
    val items: List<Item>,
    val status: String
)

data class Feed(
    val author: String,
    val description: String,
    val image: String,
    val link: String,
    val title: String,
    val url: String
)

data class Item(
    val author: String,
    val categories: List<String>,
    val content: String,
    val description: String,
    val enclosure: Enclosure,
    val guid: String,
    val link: String,
    val pubDate: String,
    val thumbnail: String,
    val title: String
)

data class Enclosure(
    val link: String,
    val thumbnail: String,
    val type: String
)
