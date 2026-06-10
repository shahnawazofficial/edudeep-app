package com.example.edudeep.models

data class Message(
    val text: String,
    val isSent: Boolean // true if sent by user, false if received
)
