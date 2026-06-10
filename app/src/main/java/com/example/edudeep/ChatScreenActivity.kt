package com.example.edudeep

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edudeep.databinding.ActivityChatScreenBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class ChatScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatScreenBinding
    private lateinit var chatAdapter: ChatAdapter
    private val messages = mutableListOf<ChatMessage>()

    private val db = FirebaseFirestore.getInstance()
    private var listener: ListenerRegistration? = null

    private lateinit var groupId: String
    private lateinit var groupName: String
    private val userId = EduDeepApp.currentUserId

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        groupId = intent.getStringExtra("groupId") ?: ""
        groupName = intent.getStringExtra("groupName") ?: "Group Chat"
        title = groupName

        chatAdapter = ChatAdapter(messages)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ChatScreenActivity)
            adapter = chatAdapter
        }

        binding.sendButton.setOnClickListener {
            val text = binding.messageInput.text.toString().trim()
            if (text.isNotEmpty()) {
                sendMessage(text)
                binding.messageInput.setText("")
            }
        }

        listenForMessages()
    }

    private fun sendMessage(text: String) {
        val message = hashMapOf(
            "text" to text,
            "senderId" to userId,
            "timestamp" to System.currentTimeMillis()
        )
        db.collection("chat_groups")
            .document(groupId)
            .collection("messages")
            .add(message)
            .addOnSuccessListener {
                db.collection("chat_groups").document(groupId).update(
                    mapOf(
                        "lastMessage" to text,
                        "lastUpdated" to System.currentTimeMillis()
                    )
                )
            }
    }

    private fun listenForMessages() {
        listener = db.collection("chat_groups")
            .document(groupId)
            .collection("messages")
            .orderBy("timestamp")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Toast.makeText(this, "Error loading messages", Toast.LENGTH_SHORT).show()
                    return@addSnapshotListener
                }

                messages.clear()
                for (doc in snapshot!!) {
                    val msg = ChatMessage(
                        id = doc.id,
                        text = doc.getString("text") ?: "",
                        senderId = doc.getString("senderId") ?: "",
                        timestamp = doc.getLong("timestamp") ?: 0L,
                        isSentByMe = doc.getString("senderId") == userId
                    )
                    messages.add(msg)
                }
                chatAdapter.notifyDataSetChanged()
                binding.recyclerView.scrollToPosition(messages.size - 1)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        listener?.remove()
    }
}
