package com.example.edudeep

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.edudeep.databinding.ActivityDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    // Pair of dashboard item titles and descriptions
    private val dashboardItems = listOf(
        "Announcements" to "View latest announcements",
        "Fee Statement" to "Check your fee details",
        "Attendance" to "Track attendance record",
        "Assignments" to "Check pending tasks",
        "Results" to "View your performance",
        "Exams" to "Upcoming & past exams",
        "Events" to "College & department events",
        "Notes" to "Browse your notes",
        "Projects" to "Your academic projects",
        "Timetable" to "See today's classes",
        "Teacher on Leave" to "Absent faculty today",
        "Backlog" to "Subjects to clear",
        "Registration" to "Subject/course registration"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "EduDeep"

        // Setup RecyclerView Grid
        val adapter = DashboardAdapter(dashboardItems) { title ->
            when (title) {
                "Announcements" -> startActivity(Intent(this, AnnouncementActivity::class.java))
                "Fee Statement" -> showToast("Fee Statement Clicked")
                "Attendance" -> showToast("Attendance Clicked")
                "Assignments" -> showToast("Assignments Clicked")
                "Results" -> showToast("Results Clicked")
                "Exams" -> showToast("Exams Clicked")
                "Events" -> showToast("Events Clicked")
                "Notes" -> startActivity(Intent(this, NotesActivity::class.java))
                "Projects" -> showToast("Projects Clicked")
                "Timetable" -> showToast("Timetable Clicked")
                "Teacher on Leave" -> showToast("Teacher on Leave Clicked")
                "Backlog" -> showToast("Backlog Clicked")
                "Registration" -> showToast("Registration Clicked")
                else -> showToast("Unknown item")
            }
        }
        binding.dashboardRecycler.layoutManager = GridLayoutManager(this, 2)
        binding.dashboardRecycler.adapter = adapter

        // Bottom Navigation setup
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val bottomNav: BottomNavigationView = binding.bottomNav
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Already on Dashboard, do nothing or reload if needed
                    true
                }
                R.id.nav_chat -> {
                    startActivity(Intent(this, ChatActivity::class.java))
                    true
                }
                R.id.nav_settings -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // Optional: Highlight current tab
        bottomNav.selectedItemId = R.id.nav_home
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
