package com.example.edudeep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatActivity




class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        // Buttons and switches
        val changePasswordBtn = view.findViewById<Button>(R.id.changePasswordBtn)
        val notificationSwitch = view.findViewById<Switch>(R.id.notificationSwitch)
        val themeSwitch = view.findViewById<Switch>(R.id.themeSwitch)
        val languageBtn = view.findViewById<Button>(R.id.languageBtn)
        val privacyPolicyBtn = view.findViewById<Button>(R.id.privacyPolicyBtn)
        val logoutBtn = view.findViewById<Button>(R.id.logoutBtn)

        // Click listeners
        changePasswordBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Change Password Clicked", Toast.LENGTH_SHORT).show()
        }

        notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            val msg = if (isChecked) "Notifications ON" else "Notifications OFF"
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        }

        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Set Dark Mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Toast.makeText(requireContext(), "Dark Theme Enabled", Toast.LENGTH_SHORT).show()
            } else {
                // Set Light Mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Toast.makeText(requireContext(), "Light Theme Enabled", Toast.LENGTH_SHORT).show()
            }

            // Save the theme preference
            val preferences = requireContext().getSharedPreferences("Settings", AppCompatActivity.MODE_PRIVATE)
            preferences.edit().putBoolean("dark_theme", isChecked).apply()
        }


        languageBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Language Selection Clicked", Toast.LENGTH_SHORT).show()
        }

        privacyPolicyBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Privacy Policy Clicked", Toast.LENGTH_SHORT).show()
        }

        logoutBtn.setOnClickListener {
            // Sign out from Firebase
            FirebaseAuth.getInstance().signOut()

            // Show a toast confirming logout
            Toast.makeText(requireContext(), "Logged out", Toast.LENGTH_SHORT).show()

            // Redirect to the Login Activity (MainActivity)
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK // Clear activity stack
            startActivity(intent)

            // Optionally finish the current activity/fragment so the user cannot go back to the Settings screen
            requireActivity().finish()
        }


        return view
    }
}
