package com.example.lab9

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.lab9.Database.AppDatabase
import com.example.lab9.Entities.Place
import com.example.lab9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "memorable_places"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
        val recyclerView: RecyclerView = binding.placesRecycler
        recyclerView.layoutManager = LinearLayoutManager(this)

        binding.button2.setOnClickListener {
            val places: List<Place> = db.placeDao().getAll()
            val placeAdapter: PlaceAdapter = PlaceAdapter(places) { place ->
                db.placeDao().delete(place)
            }
            recyclerView.adapter = placeAdapter
        }

        binding.button.setOnClickListener {
            db.placeDao().insert(Place(0, binding.nameEdit.text.toString(), binding.descriptionEdit.text.toString(), binding.latitudeEdit.text.toString().toFloat(), binding.longitudeEdit.text.toString().toFloat()))
        }


    }
}