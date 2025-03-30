package org.example

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MessageAdapter
    private val messages = mutableListOf<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = MessageAdapter(messages) { message ->
            openEditMessageActivity(message)
        }
        recyclerView.adapter = adapter

        fetchMessages()
    }

    private fun fetchMessages() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        apiService.createPost(Message(0, "Sample Title")) // Пример вызова, чтобы получить данные
        // Здесь вы можете добавить код для получения списка сообщений
    }

    private fun openEditMessageActivity(message: Message) {
        val intent = Intent(this, EditMessageActivity::class.java)
        intent.putExtra("message", message)
        startActivity(intent)
    }
}
