package com.ahmedadeltito.core.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.ahmedadeltito.core.exceptions.NetworkException
import com.ahmedadeltito.core.udf.UiActionEvent
import com.ahmedadeltito.core.udf.UiSideEffect
import com.ahmedadeltito.core.udf.UiViewState
import com.ahmedadeltito.core.vm.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Base fragment that handles most of the unidirectional data flow logic under the hood.
 * It is kind of only one layer of inheritance to abstract the logic and share it allover
 * the other fragments.
 */
abstract class BaseFragment<
        ActionEvent : UiActionEvent,
        ViewState : UiViewState,
        SideEffect : UiSideEffect,
        View : ViewBinding,
        > : Fragment() {

    private lateinit var viewModel: BaseViewModel<ActionEvent, ViewState, SideEffect>

    protected abstract fun initializeViewModel(): BaseViewModel<ActionEvent, ViewState, SideEffect>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View? {
        viewBinding = initializeViewBinding()
        return viewBinding.root
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            setupActivityComponent(activity = it)
            viewModel = initializeViewModel()
            lifecycleScope.launch {
                repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                    viewModel.uiViewState.collect { viewState ->
                        renderViewState(viewState = viewState)
                    }
                }
            }
            lifecycleScope.launch {
                repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                    viewModel.sideEffect.collect { sideEffect ->
                        handleSideEffect(sideEffect = sideEffect)
                    }
                }
            }
            onActivityReady(savedInstanceState = savedInstanceState)
        }
    }

    protected lateinit var viewBinding: View
    protected abstract fun initializeViewBinding(): View

    protected abstract fun setupActivityComponent(activity: Activity)

    protected abstract fun onActivityReady(savedInstanceState: Bundle?)

    protected fun emitActionEvent(actionEvent: ActionEvent) {
        viewModel.emitActionEvent(actionEvent = actionEvent)
    }

    protected abstract fun renderViewState(viewState: ViewState)

    protected abstract fun handleSideEffect(sideEffect: SideEffect)

    protected fun handleNetworkException(throwable: Throwable?) {
        if (throwable is NetworkException) {
            when (throwable) {
                is NetworkException.NotFoundException ->
                    Log.e("NotFoundException", throwable.notFound)

                is NetworkException.NoNetworkException ->
                    Log.e("NoNetworkException", "${throwable.message}")

                is NetworkException.ServerUnreachableException ->
                    Log.e("ServerUnreachable", " ${throwable.message}")

                is NetworkException.UnknownException ->
                    Log.e("UnknownException", "${throwable.message}")
            }
            return
        }
    }
}