package com.uzuu.sobe.feature.auth.register.confirmRegister

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ConfirmRegisterViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(ConfirmRegisterUiState())
    val uiState = _uiState.asStateFlow()

    fun onClickComfirm() {

    }

    fun onRequestAgain() {

    }
}