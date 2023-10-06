package com.kicist.smartdiabteticcare

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.kicist.smartdiabteticcare.databinding.FragmentInsightsBinding

class InsightsFragment : Fragment() {
    private var _binding: FragmentInsightsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInsightsBinding.inflate(inflater, container, false)
        (activity as MainActivity).invertTitleViewBG(true)

        binding.intevalSelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> updateChartData(3) // 3 days
                    1 -> updateChartData(7) // 7 days
                    2 -> updateChartData(30) // monthly
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                updateChartData(3)
            }
        }
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun updateChartData(days: Int) {
        val barChart: BarChart = binding.bglChart
        val yourXAxisValues: List<String>
        val yourDataList: List<Int>

        when (days) {
            3 -> {
                yourXAxisValues = listOf("Day 1", "Day 2", "Day 3")
                yourDataList = listOf(90, 88, 92)  // Sample glucose values for 3 days
            }
            7 -> {
                yourXAxisValues = listOf("Day 1", "Day 2", "Day 3", "Day 4", "Day 5", "Day 6", "Day 7")
                yourDataList = listOf(90, 88, 92, 93, 89, 90, 88)  // Sample glucose values for 7 days
            }
            else -> {
                yourXAxisValues = (1..30).map { "Day $it" }
                yourDataList = List(30) { (80..100).random() }  // Sample glucose values for 30 days
            }
        }

        // Now update the chart with the data above
        val yVals = ArrayList<BarEntry>()
        for (i in yourDataList.indices) {
            yVals.add(BarEntry(i.toFloat(), yourDataList[i].toFloat()))
        }

        val dataSet = BarDataSet(yVals, "Glucose Level")
        dataSet.color = ContextCompat.getColor(requireContext(), R.color.colorAccent)

        barChart.data = BarData(dataSet)

        // Configure the X axis
        val xAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(yourXAxisValues)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.setDrawGridLines(false)

        // Configure the left Y axis
        val leftAxis = barChart.axisLeft
//        leftAxis.setLabelCount(6, true)
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)

        // hide the right Y axis:
        val rightAxis = barChart.axisRight
        rightAxis.setDrawLabels(false)
        rightAxis.setDrawGridLines(false)

        barChart.description.isEnabled = false
        barChart.invalidate()
    }

}