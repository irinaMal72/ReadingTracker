package com.example.readingtracker.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.readingtracker.R
import com.example.readingtracker.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

  private lateinit var dashboardViewModel: DashboardViewModel
private var _binding: FragmentDashboardBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    (activity as AppCompatActivity?)?.supportActionBar?.title = "Почитай-ка"
    dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

    _binding = FragmentDashboardBinding.inflate(inflater, container, false)
    val root: View = binding.root

    dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
    })

    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.timePickerGoal.setIs24HourView(true)
        //тут задаем начальные значения часов и минут
        binding.timePickerGoal.hour = 0
        binding.timePickerGoal.minute = 30

        binding.timePickerFrom.setIs24HourView(true)
        binding.timePickerFrom.hour = 23
        binding.timePickerFrom.minute = 0

        binding.timePickerTo.setIs24HourView(true)
        binding.timePickerTo.hour = 7
        binding.timePickerTo.minute = 0

    }

}