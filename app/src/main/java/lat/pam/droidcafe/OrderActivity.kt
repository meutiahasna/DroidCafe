package lat.pam.droidcafe

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinner: Spinner = findViewById(R.id.city_spinner)
        if (spinner != null) {
            spinner.onItemSelectedListener = this
        }

        val adapter = ArrayAdapter.createFromResource(
            this, R.array.city_array, android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        if (spinner != null) {
            spinner.adapter = adapter
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val citySpinner = parent?.getItemAtPosition(position).toString()
        displayToast(citySpinner)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
    fun onRadioButtonClicked(view: View) {
        val checked = (view as RadioButton).isChecked
        when (view.getId()) {
            R.id.sameday -> if (checked)
                displayToast(getString(R.string.same_day_messenger_service))
            R.id.nextday -> if (checked)
                displayToast(getString(R.string.next_day_ground_delivery))
            R.id.pickup -> if (checked)
                displayToast(getString(R.string.pick_up))
            else -> {}
        }
    }
    private fun displayToast(message: String) {
        Toast.makeText(applicationContext, message,Toast.LENGTH_SHORT).show()
    }
}