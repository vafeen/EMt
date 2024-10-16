package com.example.emtask.data.network.response

data class Offer(
    val id: String?,
    val title: String,
    val link: String,
    val button: Button? = null
)