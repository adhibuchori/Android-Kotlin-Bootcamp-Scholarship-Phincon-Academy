# Diary App
Developed by : [Mochammad Adhi Buchori](www.linkedin.com/in/mochammad-adhi-b-2049a1136)

<div align="center">
  <img src="https://drive.google.com/uc?id=1HONj0O6r6lEL6zDzHcST-nDBYYunENVA" alt="App Preview of Diary App">
  <p>Figure 1. App Preview of Diary App.</p>
</div>

## Project Brief
Diary App is developed to help users efficiently record their daily activities, organize notes by date, and display notes in an accessible and interactive format. Built using Kotlin, this app aims to provide an easy-to-use interface for users to document their day-to-day activities.

## Main Feature
- **Daily Note Input:** Users can enter daily log details such as the title and description of the activity.
- **Note Storage:** Users can perform CRUD (Create, Read, Update, Delete) operations on notes using Room Database, which allows efficient local data storage and retrieval.

## Tech Stack

### Programming Language
- **Kotlin:** The primary programming language used for developing the application.

### Android Components
- **AlertDialog:** For prompting users for confirmation on actions such as deleting or updating a note, and to display important information.
- **RecyclerView:** To display a list of notes in an interactive and scrollable format, allowing users to view and interact with their diary entries.

### Architecture Components

#### Room Database

- **Entity:** Define the diary entry structure.
- **DAO (Data Access Object):** Define methods for CRUD operations.
- **Database:** The Room database class for local data storage.

#### ViewModel
Manage UI-related data in a lifecycle-conscious way, storing and processing the diary entries.

#### Repository
- A layer for managing data operations, abstracting the data sources from the rest of the app.

### Additional Libraries:
- **Kotlin Coroutines:** For managing background threads to ensure smooth and responsive UI interactions.
- **Android Jetpack Libraries:** Including LiveData and Lifecycle for reactive programming and lifecycle-aware components.

## App Download Link
You can download the Waste Wizard application from Google Drive [link here](https://drive.google.com/file/d/1HMtD4Wcqq_OKf2lxQkI_FZhg_nsmv-a8/view?usp=drive_link).
