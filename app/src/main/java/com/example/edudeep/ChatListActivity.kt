package com.example.edudeep

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edudeep.databinding.ActivityChatListBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ChatListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatListBinding
    private val db = FirebaseFirestore.getInstance()
    private lateinit var adapter: ChatListAdapter
    private val chatGroups = mutableListOf<ChatGroup>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up RecyclerView
        adapter = ChatListAdapter(chatGroups) { group ->
            val intent = Intent(this, ChatScreenActivity::class.java)
            intent.putExtra("groupId", group.id)
            intent.putExtra("groupName", group.name)
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Show FAB for Teacher only
        if (EduDeepApp.currentUserRole == "Teacher") {
            binding.fabCreateGroup.visibility = View.VISIBLE
            binding.fabCreateGroup.setOnClickListener {
                startActivity(Intent(this, CreateGroupActivity::class.java))
            }
        } else {
            binding.fabCreateGroup.visibility = View.GONE
        }

        // Load chat groups
        loadChatGroups()
    }

    private fun loadChatGroups() {
        val userId = EduDeepApp.currentUserId

        db.collection("chat_groups")
            .whereArrayContains("participants", userId)
            .get()
            .addOnSuccessListener { result ->
                chatGroups.clear()
                for (doc in result) {
                    val group = ChatGroup(
                        id = doc.id,
                        name = doc.getString("name") ?: "Unnamed Group",
                        section = doc.getString("section") ?: "",
                        subject = doc.getString("subject") ?: ""
                    )
                    chatGroups.add(group)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to load groups", Toast.LENGTH_SHORT).show()
            }
    }

    private fun fetchUserRole() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val db = FirebaseFirestore.getInstance()

        if (firebaseUser != null) {
            val userRoleRef = db.collection("users").document(firebaseUser.uid)
            userRoleRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val role = document.getString("role") ?: "Student"
                        EduDeepApp.currentUserRole = role
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "Error getting user role: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
