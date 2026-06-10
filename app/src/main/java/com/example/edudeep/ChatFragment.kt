package com.example.edudeep

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edudeep.databinding.FragmentChatBinding
import com.google.firebase.firestore.FirebaseFirestore

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private lateinit var chatGroups: List<ChatGroup>
    private lateinit var adapter: ChatListAdapter

    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using ViewBinding
        binding = FragmentChatBinding.inflate(inflater, container, false)

        // Dummy data for now (replace with actual data fetching if needed)
        chatGroups = listOf(
            ChatGroup("1", "Math Group"),
            ChatGroup("2", "Science Group"),
            ChatGroup("3", "Project Team A")
        )

        // Initialize the RecyclerView Adapter
        adapter = ChatListAdapter(chatGroups) { selectedGroup ->
            // Pass groupId and groupName to ChatScreenActivity when a group is selected
            val intent = Intent(requireContext(), ChatScreenActivity::class.java)
            intent.putExtra("groupId", selectedGroup.id)  // Pass groupId
            intent.putExtra("groupName", selectedGroup.name)  // Pass groupName
            startActivity(intent)
        }

        // Set up the RecyclerView with the adapter and layout manager
        binding.chatRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.chatRecyclerView.adapter = adapter

        return binding.root
    }
}
