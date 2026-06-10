package com.example.edudeep

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.edudeep.databinding.ActivityCreateGroupBinding
import com.example.edudeep.models.ChatUser // Ensure correct import
import com.google.firebase.firestore.FirebaseFirestore

class CreateGroupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateGroupBinding
    private val db = FirebaseFirestore.getInstance()
    private val selectedStudentIds = mutableListOf<String>()
    private val sectionStudents = mutableListOf<ChatUser>() // List of ChatUser objects

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateGroupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loadStudentsButton.setOnClickListener {
            val section = binding.sectionInput.text.toString().trim()
            if (section.isNotEmpty()) {
                loadStudentsFromSection(section)
            } else {
                Toast.makeText(this, "Enter section", Toast.LENGTH_SHORT).show()
            }
        }

        binding.createGroupButton.setOnClickListener {
            val groupName = binding.groupNameInput.text.toString().trim()
            val subject = binding.subjectInput.text.toString().trim()
            val section = binding.sectionInput.text.toString().trim()

            if (groupName.isEmpty() || subject.isEmpty() || section.isEmpty() || selectedStudentIds.isEmpty()) {
                Toast.makeText(this, "Fill all fields and select students", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val group = hashMapOf(
                "name" to groupName,
                "subject" to subject,
                "section" to section,
                "createdBy" to EduDeepApp.currentUserId,
                "participants" to selectedStudentIds,
                "lastMessage" to "",
                "lastUpdated" to System.currentTimeMillis()
            )

            db.collection("chat_groups")
                .add(group)
                .addOnSuccessListener {
                    Toast.makeText(this, "Group created!", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to create group", Toast.LENGTH_SHORT).show()
                }
        }

        binding.studentListView.setOnItemClickListener { _, _, position, _ ->
            val user = sectionStudents[position]
            if (selectedStudentIds.contains(user.uid)) {
                selectedStudentIds.remove(user.uid)
            } else {
                selectedStudentIds.add(user.uid)
            }
        }
    }

    private fun loadStudentsFromSection(section: String) {
        db.collection("users")
            .whereEqualTo("section", section)
            .whereEqualTo("role", "Student")
            .get()
            .addOnSuccessListener { result ->
                sectionStudents.clear()
                selectedStudentIds.clear()
                for (doc in result) {
                    val user = ChatUser(
                        name = doc.getString("name") ?: "Unnamed",
                        lastMessage = "", // Default empty value
                        uid = doc.id,
                        imageResId = R.drawable.baseline_account_circle_24 // Use a placeholder image
                    )
                    sectionStudents.add(user)
                }

                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_multiple_choice,
                    sectionStudents.map { it.name }
                )
                binding.studentListView.choiceMode = android.widget.ListView.CHOICE_MODE_MULTIPLE
                binding.studentListView.adapter = adapter
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error loading students", Toast.LENGTH_SHORT).show()
            }
    }
}
