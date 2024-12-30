package com.dreamb.roomdatabaseex1

import android.content.Intent
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.room.Room
import com.dreamb.roomdatabaseex1.model.AppDatabase
import com.dreamb.roomdatabaseex1.model.User
import com.dreamb.roomdatabaseex1.ui.theme.RoomDatabaseEx1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoomDatabaseEx1Theme {
                MainScreen()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RoomDatabaseEx1Theme {
        MainScreen()
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val list by db.userDao().getAll()
        .collectAsStateWithLifecycle(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = {
                val intent = Intent(context, AddUserActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .wrapContentSize()
        ) {
            Text("등록")
        }

        list.forEach { user ->
            Button(
                onClick = {
                    val intent = Intent(context, UserInfoActivity::class.java)
                    intent.putExtra("UserName", user.name.toString())
                    intent.putExtra("UserNum", user.phoneNum.toString())
                    intent.putExtra("UserEmail", user.email.toString())

                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text(text = user.name.toString())
            }
        }
    }
}
