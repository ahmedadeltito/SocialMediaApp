package com.ahmedadeltito.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmedadeltito.core.extensions.getFragmentTag
import com.ahmedadeltito.ui.home.coordinator.PostCoordinator
import com.ahmedadeltito.ui.home.databinding.ActivityPostContainerBinding
import com.ahmedadeltito.ui.home.di.DaggerPostContainerActivityComponent
import com.ahmedadeltito.ui.home.postlist.fragment.PostListFragment
import javax.inject.Inject

/**
 * Container that holds all the fragment or screens related to post feature.
 */
class PostContainerActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityPostContainerBinding

    @Inject
    lateinit var coordinator: PostCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPostContainerBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.activityPostToolbar)

        DaggerPostContainerActivityComponent.factory().create().inject(activity = this)

        if (savedInstanceState == null) {
            coordinator.startPostList(activity = this)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        if (getFragmentTag()?.equals(PostListFragment::class.java.simpleName) == true) {
            this@PostContainerActivity.finish()
        } else {
            super.onBackPressed()
        }
    }

    fun setToolbar(toolbarTitle: String = "") {
        supportActionBar?.apply {
            title = toolbarTitle
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }
}