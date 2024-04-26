package com.example.lab7_8

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.lab7_8.databinding.ActivityMainBinding

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

        binding.button.setOnClickListener {
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

            val firstFragment = FirstFragment()
            val name = binding.editTextText.text.toString()

            val bundle = Bundle().apply {
                putString("name", name)
            }
//            if (fragmentManager.findFragmentById(binding.frame.id) != null) {
//                fragmentManager.popBackStack()
//            }
            firstFragment.arguments = bundle
            fragmentTransaction.replace(binding.frame.id, firstFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }


        binding.button2.setOnClickListener {
            val secondFragmentManager: FragmentManager = supportFragmentManager
            val secondFragmentTransaction: FragmentTransaction = secondFragmentManager.beginTransaction()

            val secondFragment = SecondFragment()

            val last_name = binding.editTextText2.text.toString()
            val bundle = Bundle().apply {
                putString("last_name", last_name)
            }
//            if (secondFragmentManager.findFragmentById(binding.frame.id) != null) {
//                secondFragmentManager.popBackStack()
//            }
            secondFragment.arguments = bundle
            secondFragmentTransaction.replace(binding.frame.id, secondFragment)
            secondFragmentTransaction.addToBackStack(null)
            secondFragmentTransaction.commit()
        }
    }
}