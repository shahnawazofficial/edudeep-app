package com.example.edudeep

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var userTypeGroup: RadioGroup
    private lateinit var studentRadio: RadioButton
    private lateinit var teacherRadio: RadioButton
    private lateinit var phoneInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var showPasswordCheck: CheckBox
    private lateinit var loginButton: Button
    private lateinit var forgotPassword: TextView

    private lateinit var auth: FirebaseAuth
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Bind views
        userTypeGroup = findViewById(R.id.userTypeGroup)
        studentRadio = findViewById(R.id.studentRadio)
        teacherRadio = findViewById(R.id.teacherRadio)
        phoneInput = findViewById(R.id.phoneInput)
        passwordInput = findViewById(R.id.passwordInput)
        showPasswordCheck = findViewById(R.id.showPasswordCheck)
        loginButton = findViewById(R.id.loginButton)
        forgotPassword = findViewById(R.id.forgotPassword)

        // Show/Hide password logic
        showPasswordCheck.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                passwordInput.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                passwordInput.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            passwordInput.setSelection(passwordInput.text.length)
        }

        // Login button click
        loginButton.setOnClickListener {
            val phone = phoneInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            val userType = if (studentRadio.isChecked) "Student" else "Teacher"

            if (phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // **Dummy data for testing login**

            // If phone number is 8887554382 and password is password123, allow login
            if (phone == "8887554382" && password == "password123") {
                // Mocked UID from Firebase Authentication (in a real app, this will come from Firebase)
                val uid = "dummyUid123"

                // Set user role based on type (Student or Teacher)
                val role = if (userType == "Student") "Student" else "Teacher"
                EduDeepApp.currentUserId = uid
                EduDeepApp.currentUserRole = role

                Toast.makeText(this, "Welcome $role!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid phone or password", Toast.LENGTH_SHORT).show()
            }
        }

        // Forgot Password click
        forgotPassword.setOnClickListener {
            Toast.makeText(this, "Forgot Password clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
