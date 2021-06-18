package com.example.homehelper.ui.chat.householder

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homehelper.R
import com.example.homehelper.databinding.FragmentHouseHolderBinding
import com.example.homehelper.databinding.FragmentListBinding
import com.example.homehelper.models.NewEvent
import com.example.homehelper.models.chat.Letter
import com.example.homehelper.ui.events.list.ListViewModel
import com.example.homehelper.util.Constants
import com.example.homehelper.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HouseHolderFragment : DaggerFragment() {

    private lateinit var viewModel: HouseHolderViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory


    val adapter: RecyclerViewAdapter = RecyclerViewAdapter()

    private var _binding: FragmentHouseHolderBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHouseHolderBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, providerFactory).get(HouseHolderViewModel::class.java)
        initRecyclerView()
        binding.send.setOnClickListener {
            viewModel.sendLetter(binding.textView3.text.toString())
            binding.textView3.setText("")
            adapter.setLetter(Letter(-1, binding.textView3.text.toString(), null, null))
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter

        viewModel.lettersLiveData.observe(viewLifecycleOwner) {
            Log.d(Constants.TAG, "$it")
            if(it.isEmpty()){
                return@observe
            }
            adapter.setLetters(it)
            adapter.setContext(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showDialog(letter: Letter) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.list_dialog)

        val status :String
            when(letter.reply_type) {
            null ->{
                status = "Відсутня"
            }
            false -> {
                status = "Відхилено"
                dialog.findViewById<TextView>(R.id.status).setTextColor(Color.RED)
            }
            true -> {
                status = "Підтверджено"
                dialog.findViewById<TextView>(R.id.status).setTextColor(Color.GREEN)
            }
        }

        dialog.findViewById<TextView>(R.id.status).text = status


        dialog.findViewById<TextView>(R.id.text).text = "Запитання:\n" + letter.answer_text + "\nВідповідь:\n" + letter.reply_text

        dialog.show()
    }
}