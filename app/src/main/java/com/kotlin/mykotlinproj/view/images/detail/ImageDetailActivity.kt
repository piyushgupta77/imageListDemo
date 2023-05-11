package com.kotlin.mykotlinproj.view.images.detail

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlin.mykotlinproj.Constants
import com.kotlin.mykotlinproj.R
import com.kotlin.mykotlinproj.data.model.images.UnsplashPhoto
import com.kotlin.mykotlinproj.databinding.ActivityImageDetailBinding
import com.kotlin.mykotlinproj.viewmodel.ImageDetailViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.android.synthetic.main.activity_image_detail.*

@AndroidEntryPoint
class ImageDetailActivity : AppCompatActivity() {
    private val REQUEST_CODE = 1001
    private val TAG = ImageDetailActivity::class.java.canonicalName
    private var selectedImage: UnsplashPhoto? = null
    private lateinit var viewBinding: ActivityImageDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()

        selectedImage = intent.extras?.getParcelable(Constants.SELECTED_IMAGE)
        if (selectedImage != null) {
            Picasso.get().load(selectedImage?.urls?.small)
                .into(findViewById<ImageView>(R.id.img_photo_display))
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
            finish()
        }

        findViewById<Button>(R.id.btn_save).setOnClickListener {
            var isPermission = false
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                isPermission = isStoragePermissionGranted()
            }
            viewBinding.viewmodel?.handleUserSaveClick(
                selectedImage!!,
                isPermission
            )
        }

        viewBinding.viewmodel?.isDownloading?.observe(this, Observer {
            if (it == true) {
                Toast.makeText(this, "Downloading image", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initViewModel() {
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_detail)
        viewBinding.viewmodel = ViewModelProvider(this).get(ImageDetailViewModel::class.java)
        viewBinding.lifecycleOwner = this
    }

    private fun isStoragePermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED
            ) {
                Log.v(TAG, "Permission is granted")
                true
            } else {
                Log.v(TAG, "Permission is revoked")

                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    //TODO show user a dialog and redirect them to settings activity
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_CODE
                    )
                }
                false
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted")
            true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0])
            viewBinding.viewmodel?.userPermissionSuccess(selectedImage!!)
        }
    }
}