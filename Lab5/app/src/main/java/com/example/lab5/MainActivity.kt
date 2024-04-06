package com.example.lab5

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.toastButton.setOnClickListener {
            Toast.makeText(applicationContext, "Toast!", Toast.LENGTH_LONG).show()
        }
        binding.toggleText.setText(binding.toggleButton.text)
        binding.toggleButton.setOnClickListener {
            binding.toggleText.setText(binding.toggleButton.text)
        }
        binding.editTextText.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                Toast.makeText(applicationContext, "Focus on edit text", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(applicationContext, "Focus is gone", Toast.LENGTH_LONG).show()
            }

        }
        val textWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Toast.makeText(applicationContext, "before text changed", Toast.LENGTH_SHORT).show()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.editTextStateText.setText("Text was changed")
            }

            override fun afterTextChanged(s: Editable?) {
                Toast.makeText(applicationContext, "after text changed", Toast.LENGTH_SHORT).show()
            }

        }
        binding.editTextText.addTextChangedListener(textWatcher)
    }
}