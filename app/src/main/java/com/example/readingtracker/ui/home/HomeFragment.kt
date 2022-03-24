package com.example.readingtracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.readingtracker.R
import com.example.readingtracker.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

  private lateinit var homeViewModel: HomeViewModel
private var _binding: FragmentHomeBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root

    homeViewModel.text.observe(viewLifecycleOwner, Observer {

    })
    (activity as AppCompatActivity?)?.supportActionBar?.title = "Почитай-ка"
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.timePicker.setIs24HourView(true)
        /*binding.timeHours.setOnClickListener {
            showHideTimePicker(true)
        }
        binding.timeSeparator.setOnClickListener {
            showHideTimePicker(true)
        }
        binding.timeMinutes.setOnClickListener {
            showHideTimePicker(true)
        }*/
        //тут задаем начальные значения часов и минут
        binding.timePicker.hour = homeViewModel.currentHours
        binding.timePicker.minute = homeViewModel.currentMinutes

        //тут надо получить кол-во минут из настроек
        binding.progressBar.max = 30
        binding.progressBar.progress = binding.timePicker.hour * 60 + binding.timePicker.minute

        binding.timePicker.setOnTimeChangedListener(TimePicker.OnTimeChangedListener { timePicker, hour, minute ->
            binding.cardView3.setVisibility(View.INVISIBLE)
            binding.timeHours.text = hour.toString()
            binding.timeMinutes.text = minute.toString()
            binding.progressBar.progress = hour * 60 + minute
            homeViewModel.currentHours = hour
            homeViewModel.currentMinutes = minute
            if ( hour * 60 + minute >=30)
                binding.cardView3.setVisibility(View.VISIBLE)
        })

        binding.button2.setOnClickListener {
            binding.cardView3.setVisibility(View.INVISIBLE)
        }

    }
    fun showHideTimePicker(result:Boolean){
        binding.timeHours.isVisible = !result
        binding.timeSeparator.isVisible = !result
        binding.timeMinutes.isVisible = !result
        binding.timePicker.isVisible = result
    }
}

