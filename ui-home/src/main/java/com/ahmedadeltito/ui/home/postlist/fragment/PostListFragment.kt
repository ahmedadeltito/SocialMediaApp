package com.ahmedadeltito.ui.home.postlist.fragment

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ahmedadeltito.core.application.ApplicationModule
import com.ahmedadeltito.core.ui.BaseFragment
import com.ahmedadeltito.core.vm.BaseViewModel
import com.ahmedadeltito.datasource.di.DaggerDataSourceComponent
import com.ahmedadeltito.feature.post.di.DaggerPostFeatureComponent
import com.ahmedadeltito.ui.home.PostContainerActivity
import com.ahmedadeltito.ui.home.R
import com.ahmedadeltito.ui.home.coordinator.PostCoordinator
import com.ahmedadeltito.ui.home.databinding.FragmentPostListBinding
import com.ahmedadeltito.ui.home.postlist.di.DaggerPostListFragmentComponent
import com.ahmedadeltito.ui.home.postlist.udf.PostListUDF
import com.ahmedadeltito.ui.home.postlist.viewmodel.PostListViewModel
import com.ahmedadeltito.ui.home.postlist.viewmodel.PostListViewModelFactory
import javax.inject.Inject

/**
 * Fragment for showing post list.
 */
class PostListFragment : BaseFragment<
        PostListUDF.ActionEvent,
        PostListUDF.ViewState,
        PostListUDF.SideEffect,
        FragmentPostListBinding>() {

    companion object {
        fun newInstance(args: Bundle? = null): PostListFragment = PostListFragment().apply {
            arguments = args
        }
    }

    @Inject
    lateinit var viewModelFactory: PostListViewModelFactory

    @Inject
    lateinit var coordinator: PostCoordinator

    override fun initializeViewModel(): BaseViewModel<
            PostListUDF.ActionEvent,
            PostListUDF.ViewState,
            PostListUDF.SideEffect> =
        ViewModelProvider(this, viewModelFactory)[PostListViewModel::class.java]

    override fun initializeViewBinding(): FragmentPostListBinding =
        FragmentPostListBinding.inflate(layoutInflater)

    override fun setupActivityComponent(activity: Activity) {
        DaggerPostListFragmentComponent.factory().create(
            postFeatureComponent = DaggerPostFeatureComponent.factory().create(
                dataSourceComponent = DaggerDataSourceComponent.factory().create(
                    applicationModule = ApplicationModule(application = activity.application)
                )
            )
        ).inject(fragment = this@PostListFragment)
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        if (activity is PostContainerActivity) {
            (activity as PostContainerActivity).setToolbar(toolbarTitle = getString(R.string.post_list))
        }
        fetchProductList()
    }

    override fun renderViewState(viewState: PostListUDF.ViewState) {
        with(viewState) {
            viewBinding.postListView.render { postListRendering ->
                postListRendering.toBuilder()
                    .state { postListState ->
                        postListState.copy(postList = postList, isLoading = isLoading)
                    }
                    .onPostItemSelected { postId ->
                        emitActionEvent(
                            actionEvent = PostListUDF.ActionEvent.OnPostItemClicked(postId = postId)
                        )
                    }
                    .onSwipeToRefresh {
                        fetchProductList()
                    }
                    .build()
            }
            handleNetworkException(throwable = throwable)
        }
    }

    override fun handleSideEffect(sideEffect: PostListUDF.SideEffect) {
        when (sideEffect) {
            is PostListUDF.SideEffect.NavigateToPostDetails ->
                if (activity != null && activity is AppCompatActivity) {
                    coordinator.startPostDetails(
                        activity = activity as AppCompatActivity,
                        postId = sideEffect.postId
                    )
                }
        }
    }

    private fun fetchProductList() {
        emitActionEvent(actionEvent = PostListUDF.ActionEvent.OnFetchPostList)
    }
}