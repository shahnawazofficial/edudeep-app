package com.example.edudeep

data class ChatItem(
    val id: String,
    val name: String,
    val participantIds: List<String>,
    val lastMessage: String,
    val timestamp: Long
)
