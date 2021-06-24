/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme

class DogDetailPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = intent.extras!!.getString("name")
        val type = intent.extras!!.getString("type")
        val detail = intent.extras!!.getString("detail")
        val age = intent.extras!!.getString("age")
        val gender = intent.extras!!.getInt("gender")
        val imageID = intent.extras!!.getInt("imageID")

        setContent {
            MyTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = name!!)
                            },
                            navigationIcon = {
                                IconButton(
                                    onClick = {
                                        finish()
                                    }
                                ) {
                                    Icon(imageVector = Icons.Filled.ArrowBack, null)
                                }
                            },
                            backgroundColor = Color.Blue,
                            contentColor = Color.White,
                            elevation = 12.dp
                        )
                    }
                ) {
                    Surface(
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        showDogDetail(name!!, type!!, detail!!, age!!, gender, imageID)
                    }
                }
            }
        }
    }
}

@Composable
fun showDogDetail(name: String, type: String, detail: String, age: String, gender: Int, imageID: Int) {
    Card(
        backgroundColor = Color.White,
        border = BorderStroke(1.dp, Color.Gray),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(vertical = 8.dp, horizontal = 8.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = imageID),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                Box {
                    Text(
                        text = name,
                        color = Color.Black,
                        fontSize = 30.sp,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                    )
                }

                Box {
                    Surface(Modifier.padding(start = 8.dp, top = 8.dp), color = Color.Green) {
                        Text(
                            text = type,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .wrapContentSize(Alignment.CenterStart)
                        )
                    }
                }
            }

            Text(
                text = if (gender == 1) "male" else "female",
                color = Color.Black,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp)
            )

            Text(
                text = age,
                color = Color.Black,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
            )

            Text(
                text = detail,
                color = Color.Black,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp)
            )
        }
    }
}
