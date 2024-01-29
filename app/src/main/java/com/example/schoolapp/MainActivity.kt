package com.example.schoolapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.schoolapp.databinding.ActivityMainBinding
import com.example.schoolapp.fragments.HistoryFragment
import com.example.schoolapp.fragments.RecipeListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(RecipeListFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.recipeListFragment2 -> replaceFragment(RecipeListFragment())
                R.id.historyFragment2 -> replaceFragment(HistoryFragment())

                else -> {

                }

            }
            true
        }

    }

fun replaceFragment(fragment: Fragment) {
    val fragmentManager = supportFragmentManager

    if (fragmentManager.backStackEntryCount > 0) {
        val first = fragmentManager.getBackStackEntryAt(0)
        fragmentManager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    val fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.frame_layout, fragment)
    fragmentTransaction.commit()
}
}
