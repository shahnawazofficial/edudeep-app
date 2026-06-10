package com.example.edudeep

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.tasks.await

object ChatService {
    private val db = FirebaseFirestore.getInstance()

    fun getUserChats(userId: String, onUpdate: (List<ChatItem>) -> Unit): ListenerRegistration {
        return db.collection("chat_groups")
            .whereArrayContains("participants", userId)
            .addSnapshotListener { snapshot, _ ->
                val chats = snapshot?.documents?.map {
                    ChatItem(
                        id = it.id,
                        name = it.getString("name") ?: "",
                        participantIds = it["participants"] as List<String>? ?: listOf(),
                        lastMessage = it.getString("lastMessage") ?: "",
                        timestamp = it.getLong("lastUpdated") ?: 0L
                    )
                } ?: emptyList()
                onUpdate(chats)
            }
    }

    fun listenForMessages(chatId: String, userId: String, onUpdate: (List<ChatMessage>) -> Unit): ListenerRegistration {
        return db.collection("chat_groups/$chatId/messages")
            .orderBy("timestamp")
            .addSnapshotListener { snapshot, _ ->
                val messages = snapshot?.documents?.map {
                    ChatMessage(
                        id = it.id,
                        text = it.getString("text") ?: "",
                        senderId = it.getString("senderId") ?: "",
                        timestamp = it.getLong("timestamp") ?: 0,
                        isSentByMe = it.getString("senderId") == userId
                    )
                } ?: emptyList()
                onUpdate(messages)
            }
    }

    suspend fun sendMessage(chatId: String, senderId: String, messageText: String) {
        val message = hashMapOf(
            "text" to messageText,
            "senderId" to senderId,
            "timestamp" to System.currentTimeMillis()
        )
        db.collection("chat_groups/$chatId/messages").add(message).await()
        db.collection("chat_groups").document(chatId).update(
            "lastMessage", messageText,
            "lastUpdated", System.currentTimeMillis()
        )
    }
}
