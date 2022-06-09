package com.example.challengechapter6.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.challengechapter6.ui.login.ui.theme.ChallengeChapter6Theme

class LoginCompose : Fragment() {
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
        val (login, textLogin, background, usernameField, passwordField, button, noAccount, goRegister, doodle) = createRefs()
        var username by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        Text(
            modifier = Modifier
                .padding(top = 160.dp, start = 32.dp)
                .constrainAs(login) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            text = "Login",
            style = TextStyle(
                color = Color.Black,
                fontSize = 32.sp,
                fontFamily = montserratFamily,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            modifier = Modifier
                .padding(top = 8.dp, start = 32.dp)
                .constrainAs(textLogin) {
                    top.linkTo(login.bottom)
                    start.linkTo(parent.start)
                },
            text = "Login untuk melanjutkan",
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = montserratFamily,
                fontWeight = FontWeight.SemiBold
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
            .padding(start = 32.dp, end = 32.dp, top = 24.dp)
            .fillMaxWidth()
            .constrainAs(usernameField) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(textLogin.bottom)
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
                .padding(start = 32.dp, end = 32.dp, top = 18.dp)
                .fillMaxWidth()
                .constrainAs(passwordField) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(usernameField.bottom)
                })
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 32.dp)
                .constrainAs(button) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(passwordField.bottom)
                }, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.bukapalak))) {
            Text(text = "Login", style = TextStyle(
                color = colorResource(id = R.color.white),
                fontSize = 16.sp,
                fontFamily = montserratFamily,
                fontWeight = FontWeight.SemiBold,
            ), modifier = Modifier.padding(vertical = 12.dp))
        }
        Text(
            modifier = Modifier
                .padding(top = 16.dp, start = 100.dp)
                .constrainAs(noAccount) {
                    top.linkTo(button.bottom)
                    start.linkTo(parent.start)
                },
            text = "Belum punya akun?",
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
                .constrainAs(goRegister) {
                    top.linkTo(button.bottom)
                    start.linkTo(noAccount.end)
                },
            text = "Register",
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
    ChallengeChapter6Theme {
        Column() {
            Header()
        }
    }
}