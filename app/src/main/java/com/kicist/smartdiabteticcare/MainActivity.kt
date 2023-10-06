package com.kicist.smartdiabteticcare

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.kicist.smartdiabteticcare.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    public fun invertTitleViewBG(state:Boolean = false){
        var color = R.color.white
        var img = R.drawable.ic_account

        if(state){
            color = R.color.colorAccent
            img = R.drawable.ic_account_white
        }

        binding.titleView.setBackgroundColor(ContextCompat.getColor(this, color))
        binding.accountImg.setImageResource(img)
    }

    public fun measureBGL(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Loading")
//        val inflater = layoutInflater
//        val dialogLayout = inflater.inflate(R.layout.loader_layout, null)

//        builder.setView(dialogLayout)
        builder.setCancelable(false)  // Optional: prevent the dialog from being dismissed by back button or touch outside
        val alertDialog = builder.create()
        alertDialog.setCanceledOnTouchOutside(true)
        alertDialog.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView;
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        binding.accountImg.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }


        navView.setupWithNavController(navHostFragment.navController)
    }
}