package com.kicist.smartdiabteticcare

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
import com.kicist.smartdiabteticcare.databinding.ActivityStartupBinding
import com.kicist.smartdiabteticcare.helpers.LinkHelper

class StartupActivity : AppCompatActivity() {
    private val imageSwitcherData = arrayOf(
        mapOf(
            "img" to R.drawable.startup_activity_slider_img_1,
            "tagline" to R.string.start_activity_swicther_tag_1
        ),
        mapOf(
            "img" to R.drawable.startup_activity_slider_img_2,
            "tagline" to R.string.start_activity_swicther_tag_2
        ),
        mapOf(
            "img" to R.drawable.startup_activity_slider_img_3,
            "tagline" to R.string.start_activity_swicther_tag_3
        ),
        mapOf(
            "img" to R.drawable.startup_activity_slider_img_4,
            "tagline" to R.string.start_activity_swicther_tag_4
        ),
        mapOf(
            "img" to R.drawable.startup_activity_slider_img_5,
            "tagline" to R.string.start_activity_swicther_tag_5
        ),
    )

    private var currentIndex = 0
    private lateinit var binding: ActivityStartupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageSwitcher.post {
            updateImageNText(
                imageSwitcherData[currentIndex]["img"],
                imageSwitcherData[currentIndex]["tagline"]
            )

            setupDots()
        }

        binding.imageSwitcher.setOnClickListener {
            currentIndex = (currentIndex + 1) % imageSwitcherData.size

            updateImageNText(
                imageSwitcherData[currentIndex]["img"],
                imageSwitcherData[currentIndex]["tagline"])

            updateDots()
        }

        binding.createAccountButton.setOnClickListener {
            startActivity(Intent(applicationContext, SignupActivity::class.java))
        }

        LinkHelper.makeTextClickable(binding.alreadyMemberLink, fun () {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }, false)
    }

    private fun updateImageNText(image: Int?, text: Int?){
        val shapeableImageView = binding.imageSwitcher
        shapeableImageView.setImageResource(image as Int)

        // Set the circular appearance
        val shapeAppearanceModel = ShapeAppearanceModel()
            .toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, shapeableImageView.width / 2.0f)
            .build()

        shapeableImageView.shapeAppearanceModel = shapeAppearanceModel
        binding.imageSwitcherTextView.text = getString(text as Int)
    }

    private fun setupDots() {
        for (i in imageSwitcherData.indices) {
            val dot = ImageView(this)
            dot.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dot_inactive))
            dot.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
            )
            dot.setPadding(0, 0, 10, 0)
            binding.dots.addView(dot)
        }

        updateDots()
    }

    private fun updateDots() {
        val dots = binding.dots

        for (i in 0 until dots.childCount) {
            val dot = dots.getChildAt(i) as ImageView
            if (i == currentIndex) {
                dot.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dot_active))
            } else {
                dot.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dot_inactive))
            }
        }
    }
}