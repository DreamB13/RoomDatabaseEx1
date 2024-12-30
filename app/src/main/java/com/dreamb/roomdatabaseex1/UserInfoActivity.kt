package com.dreamb.roomdatabaseex1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.room.ColumnInfo
import com.dreamb.roomdatabaseex1.model.AppDatabase
import com.dreamb.roomdatabaseex1.model.User
import com.dreamb.roomdatabaseex1.ui.theme.RoomDatabaseEx1Theme

class UserInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("UserName")?: "Unknown"
        val phoneNum = intent.getStringExtra("UserNum")?: "Unknown"
        val email = intent.getStringExtra("UserEmail")?: "Unknown"
        enableEdgeToEdge()
        setContent {
            RoomDatabaseEx1Theme {
                @Composable
                fun UserInfoSreen() {
                    val context = LocalContext.current
                    val db = remember { AppDatabase.getDatabase(context) }

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(name, fontSize = 30.sp)
                        Text(phoneNum, fontSize = 30.sp)
                        Text(email, fontSize = 30.sp)

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
                UserInfoSreen()
            }
        }
    }
}