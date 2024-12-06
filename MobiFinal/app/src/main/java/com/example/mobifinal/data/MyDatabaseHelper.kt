package com.example.mobifinal.data  import android.content.ContentValues import android.content.Context import android.database.Cursor import android.database.sqlite.SQLiteDatabase import android.database.sqlite.SQLiteOpenHelper import com.example.mobifinal.Report import com.example.mobifinal.ui.screens.Student  class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "my_database.db"
        const val DATABASE_VERSION = 2
        const val TABLE_CLASSROOMS = "classrooms"
        const val TABLE_STUDENTS = "students"
        const val TABLE_USERS = "users"
        const val TABLE_REPORTS = "reports"

        const val COLUMN_CLASSROOM_ID = "id"
        const val COLUMN_CLASSROOM_NAME = "name"

        const val COLUMN_STUDENT_ID = "id"
        const val COLUMN_STUDENT_NAME = "name"
        const val COLUMN_CLASSROOM_ID_FK = "classroom_id"
        const val COLUMN_IS_PRESENT = "is_present"

        const val COLUMN_USER_ID = "id"
        const val COLUMN_USER_EMAIL = "email"
        const val COLUMN_USER_PASSWORD = "password"

        const val COLUMN_REPORT_ID = "id"
        const val COLUMN_REPORT_DATE = "date"
        const val COLUMN_REPORT_TIME = "time"
        const val COLUMN_REPORT_CLASSROOM = "classroom"
        const val COLUMN_REPORT_STUDENT_NAME = "student_name"
        const val COLUMN_REPORT_REASON = "reason"
        const val COLUMN_REPORT_PARENT_EMAIL = "parent_email"

        private const val SQL_CREATE_CLASSROOMS =
            "CREATE TABLE $TABLE_CLASSROOMS (" +
                    "$COLUMN_CLASSROOM_ID INTEGER PRIMARY KEY," +
                    "$COLUMN_CLASSROOM_NAME TEXT)"

        private const val SQL_CREATE_STUDENTS =
            "CREATE TABLE $TABLE_STUDENTS (" +
                    "$COLUMN_STUDENT_ID INTEGER PRIMARY KEY," +
                    "$COLUMN_STUDENT_NAME TEXT," +
                    "$COLUMN_CLASSROOM_ID_FK INTEGER," +
                    "$COLUMN_IS_PRESENT INTEGER," +
                    "FOREIGN KEY($COLUMN_CLASSROOM_ID_FK) REFERENCES $TABLE_CLASSROOMS($COLUMN_CLASSROOM_ID))"

        private const val SQL_CREATE_USERS =
            "CREATE TABLE $TABLE_USERS (" +
                    "$COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_USER_EMAIL TEXT UNIQUE," +
                    "$COLUMN_USER_PASSWORD TEXT)"

        private const val SQL_CREATE_REPORTS =
            "CREATE TABLE $TABLE_REPORTS (" +
                    "$COLUMN_REPORT_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_REPORT_DATE TEXT," +
                    "$COLUMN_REPORT_TIME TEXT," +
                    "$COLUMN_REPORT_CLASSROOM TEXT," +
                    "$COLUMN_REPORT_STUDENT_NAME TEXT," +
                    "$COLUMN_REPORT_REASON TEXT," +
                    "$COLUMN_REPORT_PARENT_EMAIL TEXT)"

        private const val SQL_DELETE_CLASSROOMS = "DROP TABLE IF EXISTS $TABLE_CLASSROOMS"
        private const val SQL_DELETE_STUDENTS = "DROP TABLE IF EXISTS $TABLE_STUDENTS"
        private const val SQL_DELETE_USERS = "DROP TABLE IF EXISTS $TABLE_USERS"
        private const val SQL_DELETE_REPORTS = "DROP TABLE IF EXISTS $TABLE_REPORTS"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_CLASSROOMS)
        db.execSQL(SQL_CREATE_STUDENTS)
        db.execSQL(SQL_CREATE_USERS)
        db.execSQL(SQL_CREATE_REPORTS)
        prepopulateData(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_STUDENTS)
        db.execSQL(SQL_DELETE_CLASSROOMS)
        db.execSQL(SQL_DELETE_USERS)
        db.execSQL(SQL_DELETE_REPORTS)
        onCreate(db)
    }

    private fun prepopulateData(db: SQLiteDatabase) {
        val classrooms = listOf("Room 001", "Room 002", "Room 003", "Room 004", "Room 005")
        classrooms.forEachIndexed { index, name ->
            val values = ContentValues().apply {
                put(COLUMN_CLASSROOM_ID, index + 1)
                put(COLUMN_CLASSROOM_NAME, name)
            }
            db.insert(TABLE_CLASSROOMS, null, values)
        }

        val students = listOf(
            Triple("Kelvin Doe", 1, 0),
            Triple("Evan Vanoostrum", 1, 0),
            Triple("Aidan Davis-Loforte", 1, 0),
            Triple("Jordan Kelsey", 1, 0),
            Triple("Joseph Effah", 2, 0),
            Triple("David Kristiansen", 2, 0),
            Triple("Eugene Agyei", 2, 0),
            Triple("Scott Henderson", 2, 0),
            Triple("Jordan Standing", 3, 0),
            Triple("Darren Andrews", 3, 0),
            Triple("Patrick Callaghan", 3, 0),
            Triple("Nana Konadu Sarpong", 3, 0),
            Triple("Steven Adewale", 4, 0),
            Triple("Micheal Ciarrocco", 4, 0),
            Triple("Nicole MacDonald", 4, 0),
            Triple("Jeffrey Rose", 4, 0),
            Triple("Ronald Taylor", 5, 0),
            Triple("Nathan Doe", 5, 0),
            Triple("Caleb Johnson", 5, 0),
            Triple("Keisha Brown", 5, 0)
        )

        students.forEach { (name, classroomId, isPresent) ->
            val values = ContentValues().apply {
                put(COLUMN_STUDENT_NAME, name)
                put(COLUMN_CLASSROOM_ID_FK, classroomId)
                put(COLUMN_IS_PRESENT, isPresent)
            }
            db.insert(TABLE_STUDENTS, null, values)
        }
    }

    fun createUser(email: String, password: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USER_EMAIL, email)
            put(COLUMN_USER_PASSWORD, password)
        }
        db.insert(TABLE_USERS, null, values)
    }

    fun getUser(email: String, password: String): Boolean {
        val db = readableDatabase
        val cursor: Cursor = db.query(
            TABLE_USERS,
            arrayOf(COLUMN_USER_ID),
            "$COLUMN_USER_EMAIL = ? AND $COLUMN_USER_PASSWORD = ?",
            arrayOf(email, password),
            null,
            null,
            null
        )
        val userExists = cursor.moveToFirst()
        cursor.close()
        return userExists
    }

    fun getClassrooms(): List<String> {
        val classrooms = mutableListOf<String>()
        val db = readableDatabase
        val cursor: Cursor = db.query(
            TABLE_CLASSROOMS,
            arrayOf(COLUMN_CLASSROOM_NAME),
            null,
            null,
            null,
            null,
            null
        )
        with(cursor) {
            while (moveToNext()) {
                val name = getString(getColumnIndexOrThrow(COLUMN_CLASSROOM_NAME))
                classrooms.add(name)
            }
            close()
        }
        return classrooms
    }

    fun saveReport(
        date: String,
        time: String,
        classroom: String,
        studentName: String,
        reason: String,
        parentEmail: String
    ) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_REPORT_DATE, date)
            put(COLUMN_REPORT_TIME, time)
            put(COLUMN_REPORT_CLASSROOM, classroom)
            put(COLUMN_REPORT_STUDENT_NAME, studentName)
            put(COLUMN_REPORT_REASON, reason)
            put(COLUMN_REPORT_PARENT_EMAIL, parentEmail)
        }
        db.insert(TABLE_REPORTS, null, values)
    }

    fun getReports(): List<Report> {
        val reports = mutableListOf<Report>()
        val db = readableDatabase
        val cursor = db.query(
            TABLE_REPORTS,
            null, null, null, null, null, null
        )
        with(cursor) {
            while (moveToNext()) {
                val report = Report(
                    id = getInt(getColumnIndexOrThrow(COLUMN_REPORT_ID)),
                    date = getString(getColumnIndexOrThrow(COLUMN_REPORT_DATE)),
                    time = getString(getColumnIndexOrThrow(COLUMN_REPORT_TIME)),
                    classroom = getString(getColumnIndexOrThrow(COLUMN_REPORT_CLASSROOM)),
                    studentName = getString(getColumnIndexOrThrow(COLUMN_REPORT_STUDENT_NAME)),
                    reason = getString(getColumnIndexOrThrow(COLUMN_REPORT_REASON)),
                    parentEmail = getString(getColumnIndexOrThrow(COLUMN_REPORT_PARENT_EMAIL)),
                    absentStudents = 0, // Placeholder value
                    presentStudents = 0, // Placeholder value
                    totalStudents = 0, // Placeholder value
                    className = "" // Placeholder value
                )
                reports.add(report)
            }
            close()
        }
        return reports
    }

    fun getStudentsByClassroom(classroomName: String): List<Student> {
        val students = mutableListOf<Student>()
        val db = readableDatabase

        val cursor: Cursor = db.query(
            TABLE_CLASSROOMS,
            arrayOf(COLUMN_CLASSROOM_ID),
            "$COLUMN_CLASSROOM_NAME = ?",
            arrayOf(classroomName),
            null,
            null,
            null
        )

        val classroomId = if (cursor.moveToFirst()) cursor.getInt(0) else -1
        cursor.close()

        if (classroomId != -1) {
            val studentCursor: Cursor = db.query(
                TABLE_STUDENTS,
                arrayOf(COLUMN_STUDENT_NAME, COLUMN_IS_PRESENT),
                "$COLUMN_CLASSROOM_ID_FK = ?",
                arrayOf(classroomId.toString()),
                null,
                null,
                null
            )
            with(studentCursor) {
                while (moveToNext()) {
                    val name = getString(getColumnIndexOrThrow(COLUMN_STUDENT_NAME))
                    val isPresent = getInt(getColumnIndexOrThrow(COLUMN_IS_PRESENT)) == 1
                    students.add(Student(name, isPresent, classroomName))
                }
                close()
            }
        }
        return students
    }
}