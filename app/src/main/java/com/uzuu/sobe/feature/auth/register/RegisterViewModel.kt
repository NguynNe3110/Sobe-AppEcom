package com.uzuu.sobe.feature.auth.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(): ViewModel() {

    // 2. Sử dụng StateFlow thay vì LiveData (Best practice hiện tại)
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()


    private val _navigateToConfirmEvent = Channel<Unit>()
    val navigateToConfirmEvent = _navigateToConfirmEvent.receiveAsFlow()

    // 3. Các hàm cập nhật state
    fun onPhoneChanged(phone: String) {
        _uiState.value = _uiState.value.copy(phone = phone)
    }

    fun onPasswordChanged(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun onAgreementChanged(isAgreed: Boolean) {
        _uiState.value = _uiState.value.copy(isAgreed = isAgreed)
    }

    fun onRegisterClick() {
        val currentState = _uiState.value
        if (!currentState.isAgreed) {
            _uiState.value = currentState.copy(errorMessage = "Vui lòng đồng ý điều khoản")
            return
        }

        _uiState.value = currentState.copy(isLoading = true)
        // TODO: Gọi UseCase đăng ký ở đây
        viewModelScope.launch {
            // val result = _registerUseCase(...)
            _navigateToConfirmEvent.send(Unit) // Bắn tín hiệu: "Thành công rồi, navigate thôi!"
            Log.d("DEBUG", "[viewmodel] in ỏnegisterclcik")

        }
    }

    // ... các hàm khác

    fun onGoogleSignUp() { /* Xử lý Google Sign-In */ }
    fun onFacebookSignUp() { /* Xử lý Facebook Login */ }
    fun onLoginClick() { /* Điều hướng sang màn hình Đăng nhập */ }

}
