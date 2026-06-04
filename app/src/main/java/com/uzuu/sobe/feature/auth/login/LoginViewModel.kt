package com.uzuu.sobe.feature.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uzuu.sobe.feature.auth.register.RegisterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    // 2. Sử dụng StateFlow thay vì LiveData (Best practice hiện tại)
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

// thieest laajp them uiEvnet shared flow hoạc channel dẻ emit bên kia hứng

    // 3. Các hàm cập nhật state
    fun onPhoneChanged(phone: String) {
        _uiState.value = _uiState.value.copy(phone = phone)
    }

    fun onPasswordChanged(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }


    fun onGoogleLogin() { /* Xử lý Google Sign-In */ }
    fun onFacebookLogin() { /* Xử lý Facebook Login */ }

    fun onNavigateToHome() { /*nút đăng nhập */ }
    fun onNavigateToRegister() { }
    fun onNavigateToForgetPassword() { }
}
