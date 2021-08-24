package com.petblowmachine.sspi.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.petblowmachine.sspi.FcmNotificationSender
import com.petblowmachine.sspi.R
import com.petblowmachine.sspi.adapter.MachineDetailsAdapter
import com.petblowmachine.sspi.modal.Applic
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MachineDetailsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MachineDetailsAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var txtMachineName:TextView
    private lateinit var imgMachineImage:ImageView
    private lateinit var bottomSheetLayout: FrameLayout
    private lateinit var btnTop: Button
    private lateinit var edTxtOne: EditText
    private lateinit var edTxtTwo: EditText
    private lateinit var btnRequestPrice: Button
    private lateinit var db:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_machine_details)

        db = FirebaseFirestore.getInstance()

        txtMachineName = findViewById(R.id.machineDetailsMachineName)
        imgMachineImage = findViewById(R.id.machineDetailsImage)
        recyclerView = findViewById(R.id.machineDetailsRecyclerView)
        bottomSheetLayout = findViewById(R.id.machineDetailsBottomFrameLayout)
        btnTop = findViewById(R.id.btnTopMachineDetailsRequestPrice)
        btnRequestPrice = findViewById(R.id.btnRequestPrice)
        edTxtOne = findViewById(R.id.edTxtOneMachineDetails)
        edTxtTwo = findViewById(R.id.edTxtTwoMachineDetails)

        BottomSheetBehavior.from(bottomSheetLayout).apply{

            peekHeight= TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 64f, resources.displayMetrics)
                .toInt()
            this.state = BottomSheetBehavior.STATE_COLLAPSED

            this.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback(){
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if(newState==BottomSheetBehavior.STATE_COLLAPSED){
                        btnTop.visibility = View.VISIBLE
                    }else{
                        btnTop.visibility = View.INVISIBLE
                    }
                }
                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            })

        }

        txtMachineName.text = Applic.machineName
        Glide.with(this).load(Applic.machineImg).error(R.drawable.no_img).into(imgMachineImage)

        db.collection("categories").document(Applic.categoryName).collection("Machines")
            .document(Applic.machineName).collection("details").document("details").get()
            .addOnCompleteListener {
                if(it.isSuccessful){
                    val map: Map<String,String> = (it.result?.data as Map<String, String>?)!!

                    val keySet: Set<String> = map.keys

                    val listOfKeys = ArrayList(keySet)

                    val values: Collection<String> = map.values

                    val listOfValues = ArrayList(values)

                    adapter = MachineDetailsAdapter(this,listOfKeys,listOfValues)
                    layoutManager = LinearLayoutManager(this)
                    val dividerItemDecoration = DividerItemDecoration(recyclerView.context,layoutManager.orientation)
                    recyclerView.addItemDecoration(dividerItemDecoration)
                    recyclerView.layoutManager = layoutManager
                    recyclerView.adapter = adapter
                }
            }

        btnTop.setOnClickListener {
            BottomSheetBehavior.from(bottomSheetLayout).state = BottomSheetBehavior.STATE_EXPANDED
        }

        btnRequestPrice.setOnClickListener{
            val bottomSheet = BottomSheetBehavior.from(bottomSheetLayout)
            val name = edTxtOne.text.toString()
            val phoneNumber = edTxtTwo.text.toString()
            if(name.isNotBlank()&&phoneNumber.length==10){
                val requestData = hashMapOf(
                    "name" to name,
                    "phone_number" to phoneNumber,
                    "machineName" to Applic.machineName,
                    "time" to FieldValue.serverTimestamp()
                )
                db.collection("admin").document("admin").collection("requests").document()
                    .set(requestData)
                    .addOnCompleteListener { dataEntered ->
                        if(dataEntered.isSuccessful){
                            var adminToken = ""
                            db.collection("admin").document("admin").get()
                                .addOnCompleteListener { getToken ->
                                    if(getToken.isSuccessful){
                                        val map = getToken.result?.data
                                        adminToken = map?.get("token").toString()
                                        val notificationSender = FcmNotificationSender(
                                            adminToken,
                                            "New Price Request",
                                            "$name has requested price for ${Applic.machineName}",
                                            this)
                                        notificationSender.sendNotifications()
                                    }
                                    else{
                                        Toast.makeText(this,"Something went wrong on the server side",Toast.LENGTH_LONG).show()
                                    }
                                }
                            bottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
                            disableRequestButton()
                            Toast.makeText(this,"Your request has been sent to the seller",Toast.LENGTH_LONG).show()
                            edTxtOne.text.clear()
                            edTxtTwo.text.clear()
                            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            imm.hideSoftInputFromWindow(edTxtOne.windowToken,0)
                            imm.hideSoftInputFromWindow(edTxtTwo.windowToken,0)
                        }
                        else{
                            Toast.makeText(this,"Something went wrong on the server side",Toast.LENGTH_LONG).show()
                        }
                    }
            }
            else{
                Toast.makeText(this,"Invalid Details",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun disableRequestButton() {
        btnTop.isClickable = false
        btnTop.setOnClickListener {
            Toast.makeText(this,"Wait few seconds after a Request",Toast.LENGTH_LONG).show()
        }
        Handler(Looper.getMainLooper()).postDelayed({
            btnTop.isClickable = true
            btnTop.setOnClickListener {
                BottomSheetBehavior.from(bottomSheetLayout).state = BottomSheetBehavior.STATE_EXPANDED
            }
        },10000)
    }

}