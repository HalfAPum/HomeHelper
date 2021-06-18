package com.example.homehelper.ui.register

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.homehelper.R
import com.example.homehelper.databinding.ActivityRegisterBinding
import com.example.homehelper.util.Constants
import com.example.homehelper.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class RegisterActivity : DaggerAppCompatActivity(), View.OnClickListener{

    private lateinit var binding: ActivityRegisterBinding

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private lateinit var viewModel: RegisterViewModel

    private var isChecked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this, providerFactory).get(RegisterViewModel::class.java)
        binding.registerButton.setOnClickListener(this)
        binding.appartment.setOnEditorActionListener(
                object : TextView.OnEditorActionListener {
                    override fun onEditorAction(
                        v: TextView?,
                        actionId: Int,
                        event: KeyEvent?
                    ): Boolean {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                            actionId == EditorInfo.IME_ACTION_DONE ||
                            event != null &&
                            event.action == KeyEvent.ACTION_DOWN &&
                            event.keyCode == KeyEvent.KEYCODE_ENTER) {
                            if (event == null || !event.isShiftPressed) {
                                // the user is done typing.
                                    if(binding.appartment.text.isEmpty()) {
                                        showWrongApartmentMsg(View.VISIBLE)
                                        hideKeyboard(this@RegisterActivity)
                                    }
                                        else {
                                        viewModel.getHouseId(binding.appartment.text.toString())
                                        subscribeHouseIdObserver()
                                    }
                                    return true; // consume.
                            }
                        }
                        return false; // pass on to other listeners.
                    }
                }
            )
    }

    override fun onClick(v: View?) {
        if(v == null)
            return

        if(v.id != R.id.register_button)
            return

        if(binding.email.text.isEmpty() ||
            binding.password.text.isEmpty() ||
            binding.name.text.isEmpty() ||
            binding.secondName.text.isEmpty() ||
            binding.patronymic.text.isEmpty() ||
            binding.appartment.text.isEmpty() ||
            binding.spinner.selectedItem.toString().isEmpty()){
            makeToast("Не всі поля заповнені")
            return
        }

        if(!binding.email.text.toString().contains('@')){
            makeToast("Невіврно введено email")
            return
        }


        viewModel.register(
            binding.email.text.toString(),
            binding.password.text.toString(),
            binding.name.text.toString(),
            binding.secondName.text.toString(),
            binding.patronymic.text.toString(),
            binding.appartment.text.toString(),
            binding.spinner.selectedItem.toString().toInt()
            )
        subscribeResisterObserver()

    }

    private fun makeToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun subscribeResisterObserver() {
        viewModel.registerObserver.observe(this) {
            if(it.email == "err")
                return@observe
            finish()
        }
    }

    private fun subscribeHouseIdObserver() {
        viewModel.houseIdObserver.observe(this) {
            Log.d(Constants.TAG, "Auth message result: ${it.house_id}")
            hideKeyboard(this)
            if (it.house_id <= 0) {
                showWrongApartmentMsg(View.VISIBLE)
                return@observe
            }
            showWrongApartmentMsg(View.GONE)
            viewModel.loadPorches(it.house_id)
            subscribePorchesObserver()
        }
    }

    private fun showWrongApartmentMsg(code: Int) {
        binding.errorMsg.visibility = code
    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun subscribePorchesObserver() {
        viewModel.porchesObserver.observe(this){
            Log.d(Constants.TAG, "Auth message result: ${it[0].id}")
            if(it[0].id == -1) return@observe
            if(it.isEmpty())
                return@observe

            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, it.map { return@map it.id.toString() })
            binding.spinner.adapter = adapter
        }
    }

}