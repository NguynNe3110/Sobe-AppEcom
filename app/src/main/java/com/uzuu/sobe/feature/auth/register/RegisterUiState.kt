package com.uzuu.sobe.feature.auth.register

data class RegisterUiState(
    val phone: String = "",
    val password: String = "",
    val isAgreed: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)