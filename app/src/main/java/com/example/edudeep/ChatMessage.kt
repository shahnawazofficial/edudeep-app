package com.example.edudeep

data class ChatMessage(
    val id: String = "",
    val text: String = "",
    val senderId: String = "",
    val timestamp: Long = 0,
    val isSentByMe: Boolean = false
)
