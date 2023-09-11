package com.ahmedadeltito.ui.home.postdetails.fragment

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ahmedadeltito.core.application.ApplicationModule
import com.ahmedadeltito.core.ui.BaseFragment
import com.ahmedadeltito.core.vm.BaseViewModel
import com.ahmedadeltito.datasource.di.DaggerDataSourceComponent
import com.ahmedadeltito.feature.comment.di.DaggerCommentFeatureComponent
import com.ahmedadeltito.feature.post.di.DaggerPostFeatureComponent
import com.ahmedadeltito.ui.home.PostContainerActivity
import com.ahmedadeltito.ui.home.R
import com.ahmedadeltito.ui.home.databinding.FragmentPostDetailsBinding
import com.ahmedadeltito.ui.home.postdetails.di.DaggerPostDetailsFragmentComponent
import com.ahmedadeltito.ui.home.postdetails.udf.PostDetailsUDF
import com.ahmedadeltito.ui.home.postdetails.viewmodel.PostDetailsViewModel
import com.ahmedadeltito.ui.home.postdetails.viewmodel.PostDetailsViewModelFactory
import com.ahmedadeltito.ui.home.postlist.udf.PostListUDF
import javax.inject.Inject

/**
 * Fragment for showing post details.
 */
class PostDetailsFragment : BaseFragment<
        PostDetailsUDF.ActionEvent,
        PostDetailsUDF.ViewState,
        PostDetailsUDF.SideEffect,
        FragmentPostDetailsBinding>() {

    companion object {
        const val POST_ID_KEY = "POST_ID_KEY"

        fun newInstance(args: Bundle? = null): PostDetailsFragment =
            PostDetailsFragment().apply {
                arguments = args
            }
    }

    @Inject
    lateinit var viewModelFactory: PostDetailsViewModelFactory

    override fun initializeViewModel(): BaseViewModel<PostDetailsUDF.ActionEvent, PostDetailsUDF.ViewState, PostDetailsUDF.SideEffect> =
        ViewModelProvider(this, viewModelFactory)[PostDetailsViewModel::class.java]

    override fun initializeViewBinding(): FragmentPostDetailsBinding =
        FragmentPostDetailsBinding.inflate(layoutInflater)

    override fun setupActivityComponent(activity: Activity) {
        DaggerPostDetailsFragmentComponent.factory().create(
            postFeatureComponent = DaggerPostFeatureComponent.factory().create(
                dataSourceComponent = DaggerDataSourceComponent.factory().create(
                    applicationModule = ApplicationModule(application = activity.application)
                )
            ),
            commentFeatureComponent = DaggerCommentFeatureComponent.factory().create(
                dataSourceComponent = DaggerDataSourceComponent.factory().create(
                    applicationModule = ApplicationModule(application = activity.application)
                )
            )
        ).inject(fragment = this@PostDetailsFragment)
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        if (activity is PostContainerActivity) {
            (activity as PostContainerActivity).setToolbar(toolbarTitle = getString(R.string.post_details))
        }
        arguments?.getString(POST_ID_KEY)?.let { postId ->
            emitActionEvent(
                actionEvent = PostDetailsUDF.ActionEvent.OnFetchPost(postId = postId)
            )
            emitActionEvent(
                actionEvent = PostDetailsUDF.ActionEvent.OnFetchCommentList(postId = postId)
            )
        }
    }

    override fun renderViewState(viewState: PostDetailsUDF.ViewState) {
        with(viewState) {
            viewBinding.postDetailsView.render { postDetailsRendering ->
                postDetailsRendering.toBuilder()
                    .state { postDetailsState ->
                        postDetailsState.copy(
                            postTitle = post.title ?: "Post Title is Empty",
                            postBody = post.body ?: "Post Body is Empty",
                            isLoading = isPostLoading
                        )
                    }
                    .build()
            }
            viewBinding.commentListView.render { postListRendering ->
                postListRendering.toBuilder()
                    .state { commentListState ->
                        commentListState.copy(
                            commentList = commentList,
                            isLoading = isCommentListLoading
                        )
                    }
                    .build()
            }
            handleNetworkException(throwable = throwable)
        }
    }

    override fun handleSideEffect(sideEffect: PostDetailsUDF.SideEffect) {
        // There is no side effects in this screen.
    }
}