package com.example.edudeep

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edudeep.adapters.MessageAdapter
import com.example.edudeep.databinding.ActivityChatBinding
import com.example.edudeep.models.Message

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var adapter: MessageAdapter
    private val messages = mutableListOf<Message>()  // Ensure this is MutableList<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get chat name from the Intent or set a default value
        val chatName = intent.getStringExtra("chatName") ?: "Unknown" // Ensure non-null chat name
        binding.chatUserName.text = chatName

        // Initialize the RecyclerView Adapter
        adapter = MessageAdapter(messages)  // Passing mutableList
        binding.messageRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.messageRecyclerView.adapter = adapter

        // Dummy message to show something in the chat initially
        messages.add(Message("Hello! How can I help you?", isSent = false))
        adapter.notifyItemInserted(messages.size - 1)

        // Send button click listener
        binding.sendButton.setOnClickListener {
            val msg = binding.messageInput.text.toString().trim()
            if (msg.isNotEmpty()) {
                // Add the new message to the list and notify the adapter
                messages.add(Message(msg, isSent = true))
                adapter.notifyItemInserted(messages.size - 1)

                // Clear the message input field
                binding.messageInput.setText("")

                // Scroll to the last item
                binding.messageRecyclerView.scrollToPosition(messages.size - 1)
            }
        }
    }
}
