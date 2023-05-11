package com.kotlin.mykotlinproj.view.images.list

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.mykotlinproj.Constants
import com.kotlin.mykotlinproj.R
import com.kotlin.mykotlinproj.data.model.images.UnsplashPhoto
import com.kotlin.mykotlinproj.databinding.ActivityImageSearchBinding
import com.kotlin.mykotlinproj.view.images.detail.ImageDetailActivity
import com.kotlin.mykotlinproj.viewmodel.ImageListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageSearchActivity : AppCompatActivity() {
    private var rv_image_list: RecyclerView? = null
    private val TAG = ImageSearchActivity::class.java.canonicalName
    private lateinit var viewBinding: ActivityImageSearchBinding
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handler = Handler()
        initViewModel()
        initUi()
        observeTextChange()
        observeImageSearchResult()

        //TODO remove added for testing
        viewBinding.viewmodel?.searchPhotos("cricket")
    }

    private fun initViewModel() {
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_search)
        viewBinding.viewmodel = ViewModelProvider(this).get(ImageListViewModel::class.java)
        viewBinding.lifecycleOwner = this
    }

    private fun observeTextChange() {
        findViewById<EditText>(R.id.et_search).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                handler.removeCallbacksAndMessages(null)
                handler.postDelayed({
                    Log.d(TAG, "text entered $p0")
                    viewBinding.viewmodel?.searchPhotos(p0.toString())
                }, Constants.DEBOUNCE_TEXT_CHANGE_DURATION)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun initUi() {
        rv_image_list = findViewById<RecyclerView>(R.id.rv_image_list)
        rv_image_list?.adapter = ImageListAdapter(object : ImageListAdapter.OnItemClickListener {
            override fun onItemClick(item: UnsplashPhoto?) {
                //TODO user router pattern via View model for decoupling
                val intent = Intent(this@ImageSearchActivity, ImageDetailActivity::class.java)
                intent.putExtra(Constants.SELECTED_IMAGE, item)
                startActivity(intent)
            }
        })
        rv_image_list?.layoutManager = LinearLayoutManager(this)
        rv_image_list?.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    private fun observeImageSearchResult() {
        viewBinding.viewmodel?.imageSearchResultItems?.observe(this, Observer {
            (rv_image_list?.adapter as ImageListAdapter).setImageList(it)
        })

        viewBinding.viewmodel?.error?.observe(this, Observer {
            Toast.makeText(this, "Unable to load new images", Toast.LENGTH_LONG).show()
        })

        viewBinding.viewmodel?.newSearch?.observe(this, Observer {
            if (it == true) {
                (rv_image_list?.adapter as ImageListAdapter).clear()
            }
        })
    }
}