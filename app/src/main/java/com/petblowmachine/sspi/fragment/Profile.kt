package com.petblowmachine.sspi.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.petblowmachine.sspi.R
import com.skyhope.expandcollapsecardview.ExpandCollapseCard
import com.skyhope.expandcollapsecardview.ExpandCollapseListener
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.security.Permission
import java.util.jar.Manifest

class Profile : Fragment(),ExpandCollapseListener,EasyPermissions.PermissionCallbacks {
    private lateinit var btnContactUs:ImageView
    private lateinit var btnGetDirections:ImageView
    private val requestCode = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val cardView1 = view.findViewById<ExpandCollapseCard>(R.id.cardView1)
        val cardView2 = view.findViewById<ExpandCollapseCard>(R.id.cardView2)
        val cardView3 = view.findViewById<ExpandCollapseCard>(R.id.cardView3)
        val cardView4 = view.findViewById<ExpandCollapseCard>(R.id.cardView4)
        btnContactUs = view.findViewById(R.id.btnContactUs)
        btnGetDirections = view.findViewById(R.id.btnGetDirections)

        cardView1.initListener(this)
        cardView2.initListener(this)
        cardView3.initListener(this)
        cardView4.initListener(this)


        btnContactUs.setOnClickListener {
            if(hasPerms()){
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:" + 9373482927) //change the number
                startActivity(callIntent)
            }
            else{
                requestPerms()
            }
        }

        btnGetDirections.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.in/maps/dir//swami samarth pet industries vasai"))
            intent.`package` = "com.google.android.apps.maps"
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        return view
    }
    override fun onExpandCollapseListener(p0: Boolean) {

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