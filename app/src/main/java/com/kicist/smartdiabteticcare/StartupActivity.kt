package com.kicist.smartdiabteticcare

import android.os.Bundle
import com.google.android.material.imageview.ShapeableImageView
import android.widget.ImageView
import android.widget.LinearLayout
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.shapes.OvalShape
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
//import kotlinx.android.synthetic.main.activity_main.*
import com.kicist.smartdiabteticcare.databinding.ActivityStartupBinding

//import kotlinx.android.synthetic.main.activity_main.*
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
            // Handle Create an Account button click
        }

        binding.alreadyMemberLink.setOnClickListener {
            // Handle Already a Member link click
        }
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
            dot.setPadding(0, 0, 5, 0)
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