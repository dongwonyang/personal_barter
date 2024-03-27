package com.example.personal_barter.SaleDir

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.personal_barter.MainActivityDir.UserInfo
import com.example.personal_barter.R
import com.example.personal_barter.SignActivityDir.SignInActivity
import com.example.personal_barter.SignActivityDir.UserInfoActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import android.Manifest
import android.content.pm.PackageManager
import android.provider.MediaStore.Audio.Media
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider

class SaleActivity : AppCompatActivity() {
    val imagePicture:ImageView by lazy { findViewById(R.id.imageView_sale_picture) }
    val REQ_GALLERY = 1001
    val REQ_CAMEAR = 1002
    val REQ_CAMERA_PERMISSION = 1112
    var photoUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sale)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomnavigation_sale)
        bottomNavigationView.selectedItemId = R.id.navigation_sale

        val toolbar = findViewById<Toolbar>(R.id.toolbar_sale)
        setSupportActionBar(toolbar)

    }
    
    fun onImageViewClicked(view: View){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_picture)
        dialog.show()
        dialog.findViewById<Button>(R.id.button_dialog_camera).setOnClickListener {
            selectCamera()
            dialog.dismiss()
        }

        dialog.findViewById<Button>(R.id.button_dialog_gallery).setOnClickListener {
            selectGallery()
            dialog.dismiss()
        }
        dialog.findViewById<Button>(R.id.button_dialog_picture_cancel).setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun selectCamera(){
        var permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        if(permission == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA),
                REQ_CAMERA_PERMISSION
            )
        }else{
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(cameraIntent.resolveActivity(packageManager) != null){
                val photoFile: File? = createImageFile()
                photoFile?.let{
                    photoUri = FileProvider.getUriForFile(this, applicationContext.packageName+".provider", it)
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                    startActivityForResult(cameraIntent, REQ_CAMEAR)
                }
            }
        }

    }
    private fun createImageFile(): File?{
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        val imageFIleName = "JPEG_${timeStamp}_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(imageFIleName, ".jpg", storageDir)
    }
    private fun selectGallery(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQ_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            when(requestCode){
                REQ_CAMEAR->{
                    imagePicture.setImageURI(photoUri)
                }
                REQ_GALLERY->{
                    val uri: Uri? = data?.data
                    imagePicture.setImageURI(uri)
                }
            }
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.toolbar_search -> {
                val dialog = Dialog(this)
                dialog.setContentView(R.layout.dialog_search)
                dialog.show()
                dialog.findViewById<Button>(R.id.button_dialog_search).setOnClickListener {
                    Toast.makeText(this, "검색 이벤트 실행", Toast.LENGTH_SHORT).show()
                }
                return true
            }
            R.id.toolbar_info -> {
                if (!UserInfo.isSingIn()) {
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, UserInfoActivity::class.java)
                    startActivity(intent)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

