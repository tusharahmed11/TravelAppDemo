package com.example.travelapp.ui.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.feature.register.RegisterViewModel
import com.example.travelapp.widget.AppCircleImageButton
import com.example.travelapp.widget.AppPrimaryButton
import com.example.travelapp.widget.AppSocialIconButton
import com.example.travelapp.widget.AppSpacer
import com.example.travelapp.widget.AppTextField
import com.example.travelapp.widget.FacebookIcon
import com.example.travelapp.widget.InstagramIcon
import com.example.travelapp.widget.TwitterIcon
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignUpScreen(viewModel: RegisterViewModel = koinViewModel()) {

    val uiState = viewModel.uiState.collectAsState()
    var email = viewModel.email.collectAsState()
    var name = viewModel.name.collectAsState()
    var confirmPassword = viewModel.confirmPassword.collectAsState()
    var password = viewModel.password.collectAsState()
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {
        AppSpacer(12.dp)

        AppCircleImageButton(
            onClick = { /* Handle back navigation */ },
            imageVector = Icons.Default.ArrowBackIosNew,
            contentDescription = "Back button"
        )

        AppSpacer(28.dp)

        Text(
            text = "Sign up now",
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1B1E28),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        AppSpacer(10.dp)

        Text(
            text = "Please fill the details and create account",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF7D848D),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        AppSpacer(36.dp)

        AppTextField(
            value = name.value,
            onValueChange = { viewModel.onNameChange(it) },
            placeholder = "Enter your name",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        AppSpacer(16.dp)

        AppTextField(
            value = email.value,
            onValueChange = { viewModel.onEmailChange(it) },
            placeholder = "Enter your email",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        AppSpacer(16.dp)

        AppTextField(
            value = password.value,
            onValueChange = { viewModel.onPasswordChange(it) },
            placeholder = "Enter your password",
            isPassword = true,
            isPasswordVisible = isPasswordVisible,
            onPasswordVisibilityToggle = { isPasswordVisible = !isPasswordVisible },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )

        AppSpacer(16.dp)

        AppTextField(
            value = confirmPassword.value,
            onValueChange = { viewModel.onConfirmPasswordChange(it) },
            placeholder = "Confirm your password",
            isPassword = true,
            isPasswordVisible = isPasswordVisible,
            onPasswordVisibilityToggle = { isPasswordVisible = !isPasswordVisible },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )

        AppSpacer(36.dp)
        AnimatedVisibility(uiState.value.isLoading) {
            CircularProgressIndicator()
        }
        AppPrimaryButton(
            text = "Sign Up",
            onClick = { viewModel.register()},
            modifier = Modifier.fillMaxWidth()
        )

        uiState.value.errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        }

        AppSpacer(36.dp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Already have an account? ",
                color = Color(0xFF707B81),
                fontSize = 14.sp
            )
            Text(
                text = "Sign in",
                color = Color(0xFFFF7029),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable { /* Handle sign up */ }
            )
        }

        AppSpacer(60.dp)

        Text(
            text = "Or connect",
            color = Color(0xFF707B81),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        AppSpacer(20.dp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppSocialIconButton(
                backgroundColor = Color(0xFF1877F2),
                onClick = { /* Handle Facebook login */ }
            ) {
                FacebookIcon()
            }

            AppSpacer(20.dp)

            AppSocialIconButton(
                backgroundBrush = Brush.linearGradient(
                    listOf(
                        Color(0xFF833AB4),
                        Color(0xFFFD1D1D),
                        Color(0xFFFCB045)
                    )
                ),
                onClick = { /* Handle Instagram login */ }
            ) {
                InstagramIcon()
            }

            AppSpacer(20.dp)

            AppSocialIconButton(
                backgroundColor = Color(0xFF03A9F4),
                onClick = { /* Handle Twitter login */ }
            ) {
                TwitterIcon()
            }
        }

        AppSpacer(32.dp)
    }
}
