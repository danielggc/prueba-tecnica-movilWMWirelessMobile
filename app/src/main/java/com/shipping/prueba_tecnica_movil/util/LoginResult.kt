package com.example.loginpage.util

enum class LoginResult(val value: Int) {
    EMPTY_PASSWORD(0),
    EMPTY_USERNAME(1),
    LONG_PASSWORD(2),
    SHORT_USERNAME(3),
    LOGIN_ERROR(4),
    INVALID_EMAIL( 3),
    SUCCESSFUL(6),
    OK(7),
    PASSWORD_CONFIRMATION_ERROR(8),
}