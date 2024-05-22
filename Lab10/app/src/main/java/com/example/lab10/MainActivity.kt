package com.example.lab10

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val textView: TextView = findViewById(R.id.text)
        val button: Button = findViewById(R.id.postButton)

        val retrofit = RetrofitClient.getInstance()

        val apiService = retrofit.create(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val postId = 1
                val post = apiService.getPost(postId)
                withContext(Dispatchers.Main) {
                    textView.text = post.title
                }
                savePostToFile(post)
            }
            catch (e: Exception) {
                Log.d("Get Error", e.toString())
            }
        }

        button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val post = apiService.postPost(Post(0, 1, "test post title", "test post body"))
                    withContext(Dispatchers.Main) {
                        Toast.makeText(applicationContext, post.title, Toast.LENGTH_SHORT).show()
                    }
                }
                catch (e: Exception) {
                    Log.d("Post Error", e.toString())
                }
            }
        }
    }

    private suspend fun savePostToFile(post: Post) {
        withContext(Dispatchers.IO) {
            try {
                val filename = "post_${post.id}.txt"
                val fileContents = "Title: ${post.title}\nBody: ${post.body}"
                openFileOutput(filename, Context.MODE_PRIVATE).use {
                    it.write(fileContents.toByteArray())
                }
            } catch (e: IOException) {
                Log.d("File Save Error", e.toString())
            }
        }
    }
}