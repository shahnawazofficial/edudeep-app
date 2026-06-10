package com.example.edudeep

import android.app.Application

class EduDeepApp : Application() {

    companion object {
        var currentUserRole: String = ""  // Current user role (e.g., "Teacher", "Student")
        var currentUserId: String = ""    // Current user ID (e.g., Firebase User ID)
    }

    override fun onCreate() {
        super.onCreate()
        // Initialize or fetch the user's role and ID here if needed, e.g., from Firebase
    }
}
