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

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.FillBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.model.DogInfo
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dogInfoList = getDogDataFunction()

        setContent {
            MyTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Puppy Adoption")
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
                        dogList(
                            dogInfoList = dogInfoList,
                            goDogInfoPage = {
                                Intent(this, DogDetailPage::class.java).apply {
                                    putExtra("name", it.name)
                                    putExtra("type", it.type)
                                    putExtra("detail", it.detail)
                                    putExtra("age", it.age)
                                    putExtra("gender", it.gender)
                                    putExtra("imageID", it.image)
                                }.run {
                                    startActivity(this)
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    private fun getDogDataFunction(): List<DogInfo> {
        val dog = DogInfo(
            "elvin",
            "Chihuahua",
            "Chihuahuas are the smallest breed recognized by some kennel clubs. There are two varieties of Chihuahua – the Smooth Coat (smooth-haired) and the Long Coat (long-haired). The Kennel Club considers the two to be distinct breeds; mating between the two are not eligible for KC registration.",
            "6 months",
            1,
            R.drawable.chihuahua
        )
        val dog1 = DogInfo(
            "adam",
            "Dachshund",
            "The name dachshund is of German origin and literally means \"badger dog,\" from Dachs (\"European badger\") and Hund (\"hound, dog\"). ",
            "3 months",
            1,
            R.drawable.dachshund
        )
        val dog2 = DogInfo(
            "jimmy",
            "Labrador",
            "Labradors are a medium-large breed, with males typically weighing 29–36 kg (65–80 lb) and females 25–32 kg (55–70 lb).[10] The majority of the characteristics of this breed, with the exception of colour, are the result of breeding to produce a working retriever.",
            "1 months",
            2,
            R.drawable.labrador
        )
        val dog3 = DogInfo(
            "haha",
            "Husky",
            "Siberian husky, breed of working dog raised in Siberia by the Chukchi people, who valued it as a sled dog, companion, and guard. It was brought to Alaska in 1909 for sled-dog races and soon became established as a consistent winner. A graceful dog with erect ears and a dense, soft coat, the Siberian husky stands 20 to 24 inches (51 to 61 cm) and weighs 35 to 60 pounds (16 to 27 kg). It is usually gray, tan, or black and white, and it may have head markings resembling a cap, mask, or spectacles. The breed, kept pure for hundreds of years in Siberia, is noted for intelligence and a gentle temperament.",
            "2 months",
            1,
            R.drawable.husky
        )

        val dogInfoList: MutableList<DogInfo> = arrayListOf()
        dogInfoList.apply {
            this.add(dog)
            this.add(dog1)
            this.add(dog2)
            this.add(dog3)
            this.add(dog)
            this.add(dog1)
            this.add(dog2)
            this.add(dog3)
        }
        return dogInfoList
    }
}

@Composable
fun dogInfoCardView(dogInfo: DogInfo, goDogInfoPage: (info: DogInfo) -> Unit) {
    Card(
        backgroundColor = Color.White,
        border = BorderStroke(1.dp, Color.Gray),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { goDogInfoPage(dogInfo) })
            .padding(vertical = 8.dp, horizontal = 8.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = dogInfo.image),
                contentDescription = null,
                contentScale = FillBounds,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
            )

            Text(
                text = dogInfo.name,
                color = Color.Black,
                fontSize = 24.sp,
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(start = 8.dp, top = 8.dp)
            )

            Surface(Modifier.padding(start = 8.dp, top = 8.dp), color = Color.Green) {
                Text(
                    text = dogInfo.type,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 4.dp, end = 4.dp)
                )
            }

            Text(
                text = if (dogInfo.gender == 1) "male" else "female",
                color = Color.Black,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp)
            )

            Text(
                text = dogInfo.age,
                color = Color.Black,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun dogList(dogInfoList: List<DogInfo>, goDogInfoPage: (info: DogInfo) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 150.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        dogInfoList.forEach {
            item {
                dogInfoCardView(dogInfo = it, goDogInfoPage)
            }
        }
    }
}
