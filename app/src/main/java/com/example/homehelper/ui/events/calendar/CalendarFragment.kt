package com.example.homehelper.ui.events.calendar

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homehelper.R
import com.example.homehelper.databinding.FragmentCalendarBinding
import com.example.homehelper.models.NewEvent
import com.example.homehelper.ui.events.list.ListViewModel
import com.example.homehelper.ui.events.list.RecyclerViewAdapter
import com.example.homehelper.util.Constants
import com.example.homehelper.viewmodels.ViewModelProviderFactory
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.domain.Event
import dagger.android.support.DaggerFragment
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CalendarFragment : DaggerFragment() {

    private var _binding: FragmentCalendarBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var adapter: RecyclerViewAdapter

    private lateinit var viewModel: ListViewModel

    private val simpleDateForMonth = SimpleDateFormat("MMMM - yyyy", Locale.getDefault())

    private val simpleDateForDay = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    private var events: List<NewEvent>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, providerFactory).get(ListViewModel::class.java)
        initCalendar()
        //set month
        binding.monthTV.text = simpleDateForMonth.format(Calendar.getInstance().time)
        subscribeObservers()
    }

    private fun initCalendar() {
        binding.compactcalendarView.setUseThreeLetterAbbreviation(true)

        binding.compactcalendarView.setListener(object : CompactCalendarView.CompactCalendarViewListener{

            override fun onDayClick(dateClicked: Date?) {
                if(dateClicked == null || events == null)
                    return
                val eventsList = ArrayList<NewEvent>()
                for (event in events!!) {
                    val startDate =  strToMillis(event.dateStart!!)
                    val endDate =  strToMillis(event.dateEnd!!)
                    if(dateClicked.time in startDate..endDate) {
                        eventsList.add(event)
                    }
                }
                if(eventsList.isNotEmpty())
                    showDialog(eventsList)
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date?) {
                if(firstDayOfNewMonth != null)
                    binding.monthTV.text = simpleDateForMonth.format(firstDayOfNewMonth)
            }
        })
    }

    private fun showDialog(eventsList: MutableList<NewEvent>) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.fragment_list)

        val recyclerView = dialog.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        //maybe call it after assigning the adapter
        adapter.setNews(eventsList)
        recyclerView.adapter = adapter
        dialog.show()
    }

    private fun subscribeObservers() {
        viewModel.observeNews().observe(viewLifecycleOwner) {
            if(it.isEmpty()){
                return@observe
            }

            if(it[0].images == listOf("-1")) Log.d(Constants.TAG, it[0].header)

            events = it

            setEvents()
        }
    }

    private fun setEvents() {
        if(events == null)
            return
        val eventsList = ArrayList<Event>(events!!.size)
        for(e in events!!) {
            var startDate = strToMillis(e.dateStart)
            val endDate = strToMillis(e.dateEnd!!)
            while(startDate <= endDate) {
                val event = Event(Color.RED, startDate)
                eventsList.add(event)
                //next day
                startDate += 86400000L
            }
        }
        binding.compactcalendarView.addEvents(eventsList)
    }

    private fun strToMillis(string: String?) : Long {
        return simpleDateForDay.parse(string!!)!!.time
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}