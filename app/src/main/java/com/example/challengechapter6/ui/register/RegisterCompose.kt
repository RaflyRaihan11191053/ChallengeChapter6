package com.example.challengechapter6.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.challengechapter6.R
import com.example.challengechapter6.ui.register.ui.theme.ChallengeChapter6Theme

class RegisterCompose : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
            setContent {
                ChallengeChapter6Theme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        Column() {
                            Header()
                        }
                    }
                }
            }
        }
    }
}

private val montserratFamily = FontFamily(
    Font(R.font.montserratbold, FontWeight.Bold),
    Font(R.font.montserratregular, FontWeight.Normal),
    Font(R.font.montserratmedium, FontWeight.Medium),
    Font(R.font.montserratsemibold, FontWeight.SemiBold)
)
@Composable
fun Header() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (register, background, usernameField, emailField, passwordField, confirmPasswordField, button, haveAccount, goLogin, doodle) = createRefs()
        var username by remember {
            mutableStateOf("")
        }
        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        var confirmPassword by remember {
            mutableStateOf("")
        }
        Text(
            modifier = Modifier
                .padding(top = 160.dp, start = 32.dp)
                .constrainAs(register) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            text = "Register",
            style = TextStyle(
                color = Color.Black,
                fontSize = 32.sp,
                fontFamily = montserratFamily,
                fontWeight = FontWeight.Bold
            )
        )
        Image(painter = painterResource(id = R.drawable.ic_background), contentDescription = "",
            modifier = Modifier
                .constrainAs(background) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                })
        OutlinedTextField(value = username, onValueChange = {username = it},
            placeholder = { Text(
                text = "Username",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = montserratFamily,
                    fontWeight = FontWeight.Medium
                )
            )},
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp, top = 20.dp)
                .fillMaxWidth()
                .constrainAs(usernameField) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(register.bottom)
                })
        OutlinedTextField(value = email, onValueChange = {email = it},
            placeholder = { Text(
                text = "Email",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = montserratFamily,
                    fontWeight = FontWeight.Medium
                )
            )},
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp, top = 16.dp)
                .fillMaxWidth()
                .constrainAs(emailField) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(usernameField.bottom)
                })
        OutlinedTextField(value = password, onValueChange = {password = it},
            placeholder = { Text(
                text = "Password",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = montserratFamily,
                    fontWeight = FontWeight.Medium
                )
            )},
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp, top = 16.dp)
                .fillMaxWidth()
                .constrainAs(passwordField) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(emailField.bottom)
                })
        OutlinedTextField(value = confirmPassword, onValueChange = {confirmPassword = it},
            placeholder = { Text(
                text = "Konfirmasi Password",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = montserratFamily,
                    fontWeight = FontWeight.Medium
                )
            )},
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp, top = 16.dp)
                .fillMaxWidth()
                .constrainAs(confirmPasswordField) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(passwordField.bottom)
                })
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 32.dp)
                .constrainAs(button) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(confirmPasswordField.bottom)
                }, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.bukapalak))) {
            Text(text = "Register", style = TextStyle(
                color = colorResource(id = R.color.white),
                fontSize = 16.sp,
                fontFamily = montserratFamily,
                fontWeight = FontWeight.SemiBold,
            ), modifier = Modifier.padding(vertical = 12.dp))
        }
        Text(
            modifier = Modifier
                .padding(top = 16.dp, start = 100.dp)
                .constrainAs(haveAccount) {
                    top.linkTo(button.bottom)
                    start.linkTo(parent.start)
                },
            text = "Sudah punya akun?",
            style = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontFamily = montserratFamily,
                fontWeight = FontWeight.Medium
            )
        )
        Text(
            modifier = Modifier
                .padding(top = 16.dp, start = 4.dp)
                .constrainAs(goLogin) {
                    top.linkTo(button.bottom)
                    start.linkTo(haveAccount.end)
                },
            text = "Login",
            style = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontFamily = montserratFamily,
                fontWeight = FontWeight.Bold
            )
        )
        Image(painter = painterResource(id = R.drawable.ic_doodle), contentDescription = "",
            modifier = Modifier
                .constrainAs(doodle) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 830)
@Composable
fun DefaultPreview() {
    com.example.challengechapter6.ui.login.ui.theme.ChallengeChapter6Theme {
        Column() {
            Header()
        }
    }
}