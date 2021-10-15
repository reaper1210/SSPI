package com.petblowmachine.sspi.fragment

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import com.petblowmachine.sspi.R
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

class Profile : Fragment(),EasyPermissions.PermissionCallbacks {
    private lateinit var btnContactUs: ImageView
    private lateinit var btnFactSheetCardView1: ImageView
    private lateinit var lytFactSheetCardView1: RelativeLayout
    private lateinit var btnFactSheetCardView2: ImageView
    private lateinit var lytFactSheetCardView2: RelativeLayout
    private lateinit var btnFactSheetCardView3: ImageView
    private lateinit var lytFactSheetCardView3: RelativeLayout
    private lateinit var btnFactSheetCardView4: ImageView
    private lateinit var lytFactSheetCardView4: RelativeLayout
    private lateinit var btnGetDirections: ImageView
    private lateinit var imgTrustSeal: ImageView
    private val requestCode = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        btnFactSheetCardView1 = view.findViewById(R.id.btnFactSheetCardView1)
        lytFactSheetCardView1 = view.findViewById(R.id.lytFactSheetCardView1)
        lytFactSheetCardView1.visibility = View.GONE
        btnFactSheetCardView2 = view.findViewById(R.id.btnFactSheetCardView2)
        lytFactSheetCardView2 = view.findViewById(R.id.lytFactSheetCardView2)
        lytFactSheetCardView2.visibility = View.GONE
        btnFactSheetCardView3 = view.findViewById(R.id.btnFactSheetCardView3)
        lytFactSheetCardView3 = view.findViewById(R.id.lytFactSheetCardView3)
        lytFactSheetCardView3.visibility = View.GONE
        btnFactSheetCardView4 = view.findViewById(R.id.btnFactSheetCardView4)
        lytFactSheetCardView4 = view.findViewById(R.id.lytFactSheetCardView4)
        lytFactSheetCardView4.visibility = View.GONE

        btnContactUs = view.findViewById(R.id.btnContactUs)
        btnGetDirections = view.findViewById(R.id.btnGetDirections)
        imgTrustSeal = view.findViewById(R.id.imgTrustSeal)

        btnFactSheetCardView1.setOnClickListener {
            if(lytFactSheetCardView1.isVisible){
                lytFactSheetCardView1.visibility = View.GONE
                btnFactSheetCardView1.setImageResource(R.drawable.ic_arrow_right)
            }
            else{
                lytFactSheetCardView1.visibility = View.VISIBLE
                btnFactSheetCardView1.setImageResource(R.drawable.ic_arrow_down)
            }
        }
        btnFactSheetCardView2.setOnClickListener {
            if(lytFactSheetCardView2.isVisible){
                lytFactSheetCardView2.visibility = View.GONE
                btnFactSheetCardView2.setImageResource(R.drawable.ic_arrow_right)
            }
            else{
                lytFactSheetCardView2.visibility = View.VISIBLE
                btnFactSheetCardView2.setImageResource(R.drawable.ic_arrow_down)
            }
        }
        btnFactSheetCardView3.setOnClickListener {
            if(lytFactSheetCardView3.isVisible){
                lytFactSheetCardView3.visibility = View.GONE
                btnFactSheetCardView3.setImageResource(R.drawable.ic_arrow_right)
            }
            else{
                lytFactSheetCardView3.visibility = View.VISIBLE
                btnFactSheetCardView3.setImageResource(R.drawable.ic_arrow_down)
            }
        }
        btnFactSheetCardView4.setOnClickListener {
            if(lytFactSheetCardView4.isVisible){
                lytFactSheetCardView4.visibility = View.GONE
                btnFactSheetCardView4.setImageResource(R.drawable.ic_arrow_right)
            }
            else{
                lytFactSheetCardView4.visibility = View.VISIBLE
                btnFactSheetCardView4.setImageResource(R.drawable.ic_arrow_down)
            }
        }

        imgTrustSeal.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://trustseal.indiamart.com/members/petblowmachine"))
            startActivity(intent)
        }

        btnContactUs.setOnClickListener {
            if(hasPerms()){
                val callIntent = Intent(Intent.ACTION_DIAL)
                callIntent.data = Uri.parse("tel:" + 7738385381)
                startActivity(callIntent)
            }
            else{
                requestPerms()
            }
        }

        btnGetDirections.setOnClickListener {

            try{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.in/maps/dir//swami samarth pet industries vasai"))
                intent.`package` = "com.google.android.apps.maps"
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }catch(e:ActivityNotFoundException){
                Toast.makeText(activity as Context,"Google maps isn't installed", Toast.LENGTH_SHORT).show()
            }

        }

        return view
    }

    private fun hasPerms() =
        EasyPermissions.hasPermissions(requireContext(),android.Manifest.permission.CALL_PHONE)

    private fun requestPerms(){
        EasyPermissions.requestPermissions(
            this,
            "Need Permission to make a call",
            requestCode,
            android.Manifest.permission.CALL_PHONE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:" + 9307377878)
        startActivity(callIntent)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if(EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            AppSettingsDialog.Builder(requireActivity()).build().show()
        }
        else{
            requestPerms()
        }
    }
}