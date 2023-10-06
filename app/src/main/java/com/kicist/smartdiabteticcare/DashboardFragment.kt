package com.kicist.smartdiabteticcare

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.kicist.smartdiabteticcare.databinding.FragmentDashboardBinding
import com.kicist.smartdiabteticcare.helpers.LinkHelper

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null;
    private val binding get() = _binding!!

    private fun navigateToMeasureFragment(){
        findNavController().navigate(R.id.action_navigation_home_to_navigation_measure)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        (activity as MainActivity).invertTitleViewBG()

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

        binding.measureBtn.setOnClickListener {navigateToMeasureFragment() }
        LinkHelper.makeTextClickable(binding.measureNowLink, ::navigateToMeasureFragment, true)

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
