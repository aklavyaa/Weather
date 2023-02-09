package com.app.weather

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.app.weather.data.Response


class MainActivity : ComponentActivity() {
    lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = (application as WeatherApplication).repository
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository))[MainViewModel::class.java]


        setContent {
            Surface(
                color = colorResource(id = R.color.purple_700),
            ) {
                var temp by remember {
                    mutableStateOf("")
                }
                var place by remember {
                    mutableStateOf("")
                }
                var humidity by remember {
                    mutableStateOf("")
                }
                var visibility by remember {
                    mutableStateOf("")
                }
                var speed by remember {
                    mutableStateOf("")
                }
                var description by remember {
                    mutableStateOf("")
                }
                mainViewModel.weather.observe(this, Observer {
                    Log.e("tag", "onCreate: "+it )

                    when(it){
                        is Response.Loading->{
                        }
                        is Response.Success->{
                            temp = it.data?.main?.temp.toString()
                            place =  it.data?.name.toString()
                            humidity = it.data?.main?.humidity.toString()
                            visibility =  it.data?.visibility.toString()
                            speed = it.data?.wind?.speed.toString()
                            description = it.data?.weather?.get(0)?.description?:""

                        }
                        is Response.Error->{
                            Toast.makeText(this,it.err.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                })


                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ){
                    MainView(place,temp,humidity,speed,visibility,description)

                }

            }
        }
    }
}

@Composable
//fun MainView(temp:String,minTemp:String,maxTemp:String,humidity:String,speed:String) {
fun MainView(place:String,temp:String,humidity:String
             ,speed:String,visibility:String,description:String) {


    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(26.dp)
    ) {
        Text(
            text = place,
            color = White,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                
                Text(
                    text = "$temp \u2103",
                    color = White,
                    fontSize = 28.sp

                )
                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    painter = painterResource(id = R.drawable.temperature),
                    contentDescription = "temp",
                    modifier = Modifier.size(28.dp),
                    tint = White

                )

            }

            Column() {
                Row(
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.humidity),
                        contentDescription = "humid",
                        tint = White,
                        modifier = Modifier.size(17.dp),


                        )
                    Spacer(modifier = Modifier.width(8.dp))


                    Text(
                        text = "$humidity",
                        color = White,
                        fontSize = 17.sp



                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.speed),
                        contentDescription = "speed",
                        tint = White,
                        modifier = Modifier.size(17.dp),


                        )
                    Spacer(modifier = Modifier.width(8.dp))


                    Text(
                        text = "$speed",
                        color = White,
                        fontSize = 17.sp


                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.visibility),
                        contentDescription = "visibility",
                        tint = White,
                        modifier = Modifier.size(17.dp),


                        )

                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "$visibility",
                        color = White,
                        fontSize = 17.sp

                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "$description",
            color = White,
            fontSize = 16.sp

        )



    }
}




