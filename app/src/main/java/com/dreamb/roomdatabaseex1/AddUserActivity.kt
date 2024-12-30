package com.dreamb.roomdatabaseex1

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.dreamb.roomdatabaseex1.model.AppDatabase
import com.dreamb.roomdatabaseex1.model.User
import com.dreamb.roomdatabaseex1.ui.theme.RoomDatabaseEx1Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddUserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoomDatabaseEx1Theme {
                AddUserScreen()
            }
        }
    }
}

@Composable
fun AddUserScreen() {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }

    var name by remember { mutableStateOf("") }
    var phoneNum by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = name,
            onValueChange = { name = it }, modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            label = { Text("이름") }
        )
        TextField(
            value = phoneNum,
            onValueChange = { phoneNum = it }, modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            label = { Text("전화번호") }
        )
        TextField(
            value = email,
            onValueChange = { email = it }, modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            label = { Text("이메일") }
        )
        val scope = rememberCoroutineScope()
        Button(
            onClick = {
                scope.launch(Dispatchers.IO) {
                    db.userDao().insertAll(
                        User(
                            name = name,
                            phoneNum = phoneNum,
                            email = email,
                        )
                    )
                    val activity = context as? Activity
                    activity?.finish()
                }
            },
            modifier = Modifier
                .wrapContentSize()
        ) {
            (Text("등록"))
        }
        Button(
            onClick = {
                val activity = context as? Activity
                activity?.finish()
            }, modifier = Modifier
                .wrapContentSize()
        ) {
            Text("닫기")
        }
    }
}