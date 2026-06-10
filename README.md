<h1 align="center">
  <br>
  <img src="app/src/main/ic_launcher-playstore.png" alt="EduDeep Logo" width="150">
  <br>
  EduDeep
  <br>
</h1>

<h4 align="center">A smart college management app built for students & teachers — powered by Firebase.</h4>

<p align="center">
  <img alt="Android" src="https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white"/>
  <img alt="Kotlin" src="https://img.shields.io/badge/Language-Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"/>
  <img alt="Firebase" src="https://img.shields.io/badge/Backend-Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black"/>
  <img alt="Min SDK" src="https://img.shields.io/badge/Min%20SDK-24-blue?style=for-the-badge"/>
  <img alt="Target SDK" src="https://img.shields.io/badge/Target%20SDK-35-blue?style=for-the-badge"/>
</p>

<p align="center">
  <a href="#-features">Features</a> •
  <a href="#-screenshots">Screenshots</a> •
  <a href="#-tech-stack">Tech Stack</a> •
  <a href="#-project-structure">Project Structure</a> •
  <a href="#-getting-started">Getting Started</a> •
  <a href="#-contributing">Contributing</a> •
  <a href="#-license">License</a>
</p>

---

## 📖 About

**EduDeep** is an Android application designed to bridge the communication gap between students and teachers in a college environment. It provides a centralized platform for academic resources, real-time messaging, announcements, notes, and much more — all in one sleek and intuitive app.

Whether you're a **student** tracking attendance and assignments, or a **teacher** sharing announcements and notes, EduDeep has you covered.

---

## ✨ Features

| Feature | Description |
|---|---|
| 🔐 **Role-Based Login** | Separate login flows for Students and Teachers |
| 📢 **Announcements** | View and post college/department announcements |
| 💬 **Real-Time Chat** | One-on-one and group messaging via Firebase |
| 🗂️ **Dashboard** | Quick access cards for all academic modules |
| 📝 **Notes** | Browse and manage academic notes |
| 📅 **Timetable** | View today's class schedule |
| 📊 **Attendance Tracking** | Monitor attendance records |
| 📋 **Assignments** | View and manage pending assignments |
| 🏆 **Results** | Check academic performance |
| 🎯 **Exams** | View upcoming and past exams |
| 🎉 **Events** | College and department event listings |
| 📁 **Projects** | Manage academic projects |
| ⚙️ **Settings** | User profile and app preferences |
| 👥 **Group Chat** | Create and manage group conversations |

---

## 📱 Screenshots

> *Screenshots coming soon — build the app and run on a device/emulator to preview.*

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| **Language** | [Kotlin](https://kotlinlang.org/) |
| **UI Toolkit** | Android Views (XML Layouts) |
| **Build System** | Gradle with Kotlin DSL (`.gradle.kts`) |
| **Authentication** | Firebase Authentication |
| **Database** | Cloud Firestore (Firebase) |
| **File Storage** | Firebase Storage |
| **Real-time Messaging** | Firestore + Firebase Cloud Messaging |
| **Image Loading** | [Glide](https://github.com/bumptech/glide) |
| **Profile Images** | [CircleImageView](https://github.com/hdodenhof/CircleImageView) |
| **Architecture** | MVVM with LiveData & ViewModel |
| **Navigation** | Jetpack Navigation Component |
| **Async** | Kotlin Coroutines |
| **View Binding** | Android ViewBinding |

---

## 📁 Project Structure

```
edudeep2/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/edudeep/
│   │       │   ├── activities/
│   │       │   │   ├── SplashActivity.kt        # App entry point
│   │       │   │   ├── MainActivity.kt          # Login screen
│   │       │   │   ├── DashboardActivity.kt     # Main dashboard (grid cards)
│   │       │   │   ├── ChatActivity.kt          # Chat module entry
│   │       │   │   ├── ChatListActivity.kt      # List of all chats
│   │       │   │   ├── ChatScreenActivity.kt    # Individual chat screen
│   │       │   │   ├── CreateGroupActivity.kt   # Create group chat
│   │       │   │   ├── AnnouncementActivity.kt  # View announcements
│   │       │   │   ├── NotesActivity.kt         # Browse notes
│   │       │   │   └── SettingsActivity.kt      # App settings
│   │       │   ├── adapters/
│   │       │   │   ├── DashboardAdapter.kt      # Dashboard grid adapter
│   │       │   │   ├── ChatAdapter.kt           # Chat messages adapter
│   │       │   │   ├── ChatListAdapter.kt       # Chat list adapter
│   │       │   │   └── MessageAdapter.kt        # Message bubbles adapter
│   │       │   ├── fragments/
│   │       │   │   ├── HomeFragment.kt
│   │       │   │   ├── ChatFragment.kt
│   │       │   │   └── SettingsFragment.kt
│   │       │   ├── models/
│   │       │   │   ├── ChatMessage.kt
│   │       │   │   ├── ChatUser.kt
│   │       │   │   ├── ChatGroup.kt
│   │       │   │   ├── ChatItem.kt
│   │       │   │   ├── Message.kt
│   │       │   │   └── DashboardItem.kt
│   │       │   ├── services/
│   │       │   │   └── ChatService.kt           # Firebase messaging service
│   │       │   └── EduDeepApp.kt               # Application class
│   │       ├── res/
│   │       │   ├── layout/                      # XML UI layouts
│   │       │   ├── drawable/                    # Icons & vector assets
│   │       │   ├── menu/                        # Navigation menus
│   │       │   ├── values/                      # Colors, strings, styles
│   │       │   └── xml/                         # Backup & data extraction rules
│   │       └── AndroidManifest.xml
│   ├── google-services.json                     # Firebase configuration
│   └── build.gradle.kts
├── gradle/
│   └── libs.versions.toml                       # Version catalog
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

- **Android Studio** Hedgehog (2023.1.1) or newer
- **JDK 11** or higher
- **Android SDK** with API Level 24+
- A **Firebase Project** (for authentication, Firestore, and storage)

### Clone the Repository

```bash
git clone https://github.com/shahnawazofficial/edudeep-app.git
cd edudeep-app
```

### Firebase Setup

1. Go to [Firebase Console](https://console.firebase.google.com/) and create a new project (or use the existing one).
2. Register your Android app with the package name: `com.example.edudeep`
3. Download the `google-services.json` file and place it in the `app/` directory.
4. Enable the following Firebase services:
   - **Authentication** → Email/Password (or Phone)
   - **Cloud Firestore** → Create a database in test/production mode
   - **Firebase Storage** → For profile pictures and file uploads

### Build & Run

1. Open the project in **Android Studio**.
2. Let Gradle sync complete.
3. Connect a device or start an emulator (API 24+).
4. Click **▶ Run** or press `Shift + F10`.

> **Note:** Make sure your `google-services.json` is present in the `app/` directory before building.

---

## 🔑 Permissions

The app uses the following Android permissions:

| Permission | Reason |
|---|---|
| `INTERNET` | Firebase & network communication |
| `ACCESS_NETWORK_STATE` | Check connectivity before requests |
| `READ_EXTERNAL_STORAGE` | Access files for upload |
| `WRITE_EXTERNAL_STORAGE` | Save downloaded files |
| `BLUETOOTH` / `BLUETOOTH_ADMIN` | Future Bluetooth-based features |
| `USE_FINGERPRINT` | Biometric authentication (planned) |

---

## 🗺️ Roadmap

- [x] Role-based login (Student / Teacher)
- [x] Dashboard with grid navigation cards
- [x] Real-time 1-on-1 chat
- [x] Group chat creation
- [x] Announcements module
- [x] Notes module
- [x] Settings & profile management
- [ ] Push notifications via FCM
- [ ] Attendance module (full implementation)
- [ ] Assignment submission
- [ ] Fee statement integration
- [ ] Timetable view (full implementation)
- [ ] Biometric / fingerprint login
- [ ] Dark mode support
- [ ] Results & grade tracking

---

## 🤝 Contributing

Contributions are welcome! To contribute:

1. **Fork** this repository
2. Create a feature branch: `git checkout -b feature/your-feature-name`
3. Commit your changes: `git commit -m 'feat: add your feature'`
4. Push to the branch: `git push origin feature/your-feature-name`
5. Open a **Pull Request**

Please make sure your code follows the existing code style and passes all tests.

---

## 👤 Author

**Shahnawaz**

- GitHub: [@shahnawazofficial](https://github.com/shahnawazofficial)

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2026 Shahnawaz

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

---

<p align="center">
  Made with ❤️ using Kotlin & Firebase
</p>
