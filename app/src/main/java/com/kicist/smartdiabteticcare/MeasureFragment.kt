package com.kicist.smartdiabteticcare

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.Transition
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.target.ImageViewTarget
import com.bumptech.glide.request.target.Target
import com.kicist.smartdiabteticcare.databinding.FragmentMeasureBinding


class MeasureFragment : Fragment() {
    private var _binding: FragmentMeasureBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).invertTitleViewBG(false)
        _binding = FragmentMeasureBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.measureFragmentLayout
            .setOnClickListener {
            (activity as MainActivity).measureBGL()
        }

        binding.measureBtnF
            .setOnClickListener {
                (activity as MainActivity).measureBGL()
            }

        Glide.with(this)
            .asGif()
            .load(R.drawable.anim_measure)
            .apply(
                RequestOptions()
                    .fitCenter()
                    .override(Target.SIZE_ORIGINAL))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .into(binding.measureImg)

        val measureBtn = binding.measureBtnF

        Glide.with(this)
            .asGif()
            .load(R.drawable.anim_fingerprint)
            .override(80, 80)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .into(
                object : CustomTarget<GifDrawable>() {
                    override fun onResourceReady(
                        resource: GifDrawable,
                        transition: com.bumptech.glide.request.transition.Transition<in GifDrawable>?
                    ) {
                        resource.start()  // Start the GIF animation
                        measureBtn.setCompoundDrawablesWithIntrinsicBounds(resource,
                            null, null, null)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        // Handle clearing the drawable
                        measureBtn.setCompoundDrawablesWithIntrinsicBounds(placeholder,
                            null, null, null)
                    }
                }
            )

    }
}