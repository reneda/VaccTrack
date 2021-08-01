package com.example.vacctrack

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import java.util.*
import kotlin.collections.ArrayList

class First : AppCompatActivity() {

    private lateinit var searchButton: Button

    lateinit var pinCodeEdt: EditText

    lateinit var centersRV: RecyclerView

    lateinit var centerRVAdapter: CenterRVAdapter

    lateinit var centerList: List<CenterRVModal>

    lateinit var loadingPB: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        searchButton = findViewById(R.id.idBtnSearch)
        pinCodeEdt = findViewById(R.id.idEdtPinCode)
        centersRV = findViewById(R.id.centersRV)
        loadingPB = findViewById(R.id.idPBLoading)
        centerList = ArrayList<CenterRVModal>()


        searchButton.setOnClickListener {

            val pinCode = pinCodeEdt.text.toString()

            // on below line we are validating
            // our pin code as 6 digit or not.
            if (pinCode.length != 6) {

                // this method is called when users enter invalid pin code.
                Toast.makeText(this@First, "Please enter valid pin code", Toast.LENGTH_SHORT).show()
            } else {

                // if the pincode is correct.
                // first of all we are clearing our array list this
                // will clear the data in it if already present.
                (centerList as ArrayList<CenterRVModal>).clear()

                val c = Calendar.getInstance()

                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)
                val dpd = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                        loadingPB.setVisibility(View.VISIBLE)

                        val dateStr: String = """$dayOfMonth - ${monthOfYear + 1} - $year"""

                        getAppointments(pinCode, dateStr)
                    },
                    year,
                    month,
                    day
                )
                // calling a method to display
                // our datepicker dialog.
                dpd.show()
            }
        }
    }

    // below is the method for getting data from API.
    private fun getAppointments(pinCode: String, date: String) {
        val url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=" + pinCode + "&date=" + date
        val queue = Volley.newRequestQueue(this@First)

        // on below line we are creating a request
        // variable for making our json object request.
        val request =
            // as we are getting json object response and we are making a get request.
            JsonObjectRequest(
                Request.Method.GET, url, null, { response ->
                // this method is called when we get successful response from API.
                Log.e("TAG", "SUCCESS RESPONSE IS $response")

                loadingPB.setVisibility(View.GONE)
                try {

                    val centerArray = response.getJSONArray("centers")

                    // the zero length indicates that there is no data for the given pincode.
                    if (centerArray.length().equals(0)) {
                        Toast.makeText(this, "No Center Found", Toast.LENGTH_SHORT).show()
                    }
                    for (i in 0 until centerArray.length()) {

                        val centerObj = centerArray.getJSONObject(i)

                        // on below line we are getting data from our session
                        // object and we are storing that in a different variable.
                        val centerName: String = centerObj.getString("name")
                        val centerAddress: String = centerObj.getString("address")
                        val centerFromTime: String = centerObj.getString("from")
                        val centerToTime: String = centerObj.getString("to")
                        val fee_type: String = centerObj.getString("fee_type")

                        // on below line we are creating a variable for our session object
                        val sessionObj = centerObj.getJSONArray("sessions").getJSONObject(0)
                        val ageLimit: Int = sessionObj.getInt("min_age_limit")
                        val vaccineName: String = sessionObj.getString("vaccine")
                        val avaliableCapacity: Int = sessionObj.getInt("available_capacity")

                        // after extracting all the data we are passing this
                        // data to our modal class we have created
                        // a variable for it as center.
                        val center = CenterRVModal(
                            centerName,
                            centerAddress,
                            centerFromTime,
                            centerToTime,
                            fee_type,
                            ageLimit,
                            vaccineName,
                            avaliableCapacity
                        )
                        // after that we are passing this modal to our list on the below line.
                        centerList = centerList + center
                    }

                    // on the below line we are passing this list to our adapter class.
                    centerRVAdapter = CenterRVAdapter(centerList)

                    centersRV.layoutManager = LinearLayoutManager(this)

                    centersRV.adapter = centerRVAdapter

                    centerRVAdapter.notifyDataSetChanged()

                } catch (e: JSONException) {
                    // below line is for handling json exception.
                    e.printStackTrace();
                }
            },
                { error ->
                    // this method is called when we get any
                    // error while fetching data from our API
                    Log.e("TAG", "RESPONSE IS $error")
                    // in this case we are simply displaying a toast message.
                    Toast.makeText(this@First, "Fail to get response", Toast.LENGTH_SHORT).show()
                })
        // aading our request to our queue.
        queue.add(request)
    }
}