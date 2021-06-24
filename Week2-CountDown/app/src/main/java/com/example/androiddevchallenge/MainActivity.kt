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
import android.os.CountDownTimer
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.ui.theme.MyTheme

var changeColor by mutableStateOf(true)
var isReset by mutableStateOf(false)

class MainActivity : AppCompatActivity() {

    private lateinit var timer: CountDownTimer

    val model by viewModels<CountDownTimerModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.timer = object : CountDownTimer(3 * 60 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                model.updateTimes(millisUntilFinished)
                changeColor = !changeColor
            }

            override fun onFinish() {
                model.updateTimes(0)
            }
        }

        model.times.observe(this) { times ->
            setContent {
                MyTheme {
                    Surface(
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.instant_noodles),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .height(300.dp)
                                    .fillMaxWidth()
                            )

                            Text(
                                text = "${if ((times / 1000 / 60) == 0L) "00" else if ((times / 1000 / 60) < 10) "0${(times / 1000 / 60)}" else (times / 1000 / 60)} : " +
                                    "${if ((times / 1000 % 60) == 0L) "00" else if ((times / 1000 % 60) < 10) "0${(times / 1000 % 60)}" else (times / 1000 % 60)}",
                                color = if (changeColor) Color.Red else Color.Blue,
                                fontSize = 30.sp,
                                modifier = Modifier.padding(16.dp)
                            )

                            Button(
                                onClick = {
                                    if (isReset) {
                                        timer.cancel()
                                        model.updateTimes(0)
                                    } else {
                                        timer.start()
                                    }

                                    isReset = !isReset
                                },
                                modifier = Modifier.padding(top = 16.dp)
                            ) {
                                val text = if (isReset) "reset" else "start"
                                Text(text = text)
                            }
                        }
                    }
                }
            }
        }
    }
}

class CountDownTimerModel : ViewModel() {
    private val _times = MutableLiveData(0L)

    val times: LiveData<Long> = _times

    fun updateTimes(newTimes: Long) {
        _times.value = newTimes
    }
}
