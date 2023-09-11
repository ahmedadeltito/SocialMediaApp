package com.ahmedadeltito.core.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedadeltito.core.exceptions.NetworkException
import com.ahmedadeltito.core.udf.UiActionEvent
import com.ahmedadeltito.core.udf.UiSideEffect
import com.ahmedadeltito.core.udf.UiViewState
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * Base view model that handles most of the unidirectional data flow logic under the hood.
 * It is kind of only one layer of inheritance to abstract the logic and share it
 * allover the other view models.
 */
abstract class BaseViewModel<
        ActionEvent : UiActionEvent,
        ViewState : UiViewState,
        SideEffect : UiSideEffect,
        > : ViewModel() {

    private val initialState: ViewState by lazy { createInitialState() }

    abstract fun createInitialState(): ViewState

    private val currentViewState: ViewState
        get() = uiViewState.value

    private val _uiViewState: MutableStateFlow<ViewState> = MutableStateFlow(initialState)
    val uiViewState: StateFlow<ViewState> = _uiViewState.asStateFlow()

    private val _actionEvent: MutableSharedFlow<ActionEvent> = MutableSharedFlow()
    private val actionEvent: SharedFlow<ActionEvent>
        get() = _actionEvent

    private val _sideEffect: Channel<SideEffect> = Channel()
    val sideEffect: Flow<SideEffect> = _sideEffect.receiveAsFlow()

    init {
        viewModelScope.launch {
            actionEvent.collect { actionEvent ->
                handleActionEvents(actionEvent = actionEvent)
            }
        }
    }

    abstract fun handleActionEvents(actionEvent: ActionEvent)

    fun emitActionEvent(actionEvent: ActionEvent) {
        viewModelScope.launch { _actionEvent.emit(actionEvent) }
    }

    fun emitViewState(reduce: ViewState.() -> ViewState) {
        val newState = currentViewState.reduce()
        _uiViewState.value = newState
    }

    fun emitSideEffect(builder: () -> SideEffect) {
        viewModelScope.launch { _sideEffect.send(builder.invoke()) }
    }

    fun handleThrowable(throwable: Throwable): NetworkException = when (throwable) {
        is SocketTimeoutException -> NetworkException.NoNetworkException(error = throwable)
        is UnknownHostException -> NetworkException.ServerUnreachableException(error = throwable)
        else -> NetworkException.UnknownException(error = throwable)
    }
}