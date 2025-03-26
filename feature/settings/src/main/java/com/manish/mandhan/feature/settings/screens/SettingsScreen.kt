package com.manish.mandhan.feature.settings.screens

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manish.mandhan.feature.settings.R
import java.util.prefs.Preferences

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    toggleTheme: () -> Unit,
    currentTheme: State<String?>
) {


    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .then(modifier)
    ) {
        HeaderSection()
        Spacer(modifier = Modifier.height(48.dp))
        LanguageSection()
        Spacer(modifier = Modifier.height(12.dp))
        PreferencesSection(toggleTheme = toggleTheme, currentTheme = currentTheme)


    }

}

@Composable
fun HeaderSection(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {

        Text(
            fontSize = 24.sp,
            text = "Settings",
            fontFamily = FontFamily(Font(R.font.montserrat_bold))
        )
        Text(
            fontSize = 14.sp,
            text = "Manage preference and controls",
            fontFamily = FontFamily(Font(R.font.montserrat_medium))
        )
    }

}


@Composable
fun LanguageSection(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            fontSize = 14.sp,
            text = "General",
            fontFamily = FontFamily(Font(R.font.montserrat_bold))
        )
        Spacer(modifier = Modifier.height(12.dp))
        CardItem(
            imageId = R.drawable.languages,
            title = "Language",
            subTitle = "Choose your favorite language.",
        )
        Spacer(modifier = Modifier.height(12.dp))
        CardItem(
            imageId = com.manish.mandhan.common.R.drawable.gear,
            title = "Options",
            subTitle = "Configure tailored to your needs.",
        )

    }

}

@Composable
fun PreferencesSection(
    modifier: Modifier = Modifier,
    toggleTheme: () -> Unit,
    currentTheme: State<String?>
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Text(
            fontSize = 14.sp,
            text = "Personal Preferences",
            fontFamily = FontFamily(Font(R.font.montserrat_bold))
        )
        Spacer(modifier = Modifier.height(12.dp))
        CardItem(
            state = currentTheme,
            subTitle = "Click here to change theme.",
            imageChangingLogic = {
                if (currentTheme.value == "Dark") R.drawable.full_moon else R.drawable.sun
            },
            onCardClick = {
                toggleTheme()
            }
        )
    }
}


@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    state: State<String?>? = null,
    imageId: Int? = null,
    title: String? = null,
    subTitle: String,
    imageSizeInFloat: Float = 1f,
    onCardClick: () -> Unit = {},
    imageChangingLogic: () -> Int = { 0 },
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onCardClick()
                }
                .padding(horizontal = 16.dp, vertical = 12.dp)) {
            Box(modifier = Modifier.fillMaxWidth(.15f), contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier.fillMaxWidth(imageSizeInFloat),
                    painter = painterResource(imageId ?: imageChangingLogic()),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                state?.let {
                    Crossfade(targetState = state) {
                        if (it.value == "Dark"){
                            Text(
                                fontSize = 17.sp,
                                text = "Dark Theme",
                                fontFamily = FontFamily(Font(R.font.montserrat_semi_bold)),
                            )
                        } else {
                            Text(
                                fontSize = 17.sp,
                                text = "Light Theme",
                                fontFamily = FontFamily(Font(R.font.montserrat_semi_bold)),
                            )
                        }
                    }
                } ?: run {
                    Text(
                        fontSize = 17.sp,
                        text = title ?: "",
                        fontFamily = FontFamily(Font(R.font.montserrat_semi_bold)),
                        modifier = Modifier.animateContentSize()
                    )
                }
                Text(
                    fontSize = 13.sp,
                    text = subTitle,
                    fontFamily = FontFamily(Font(R.font.montserrat_medium))
                )
            }
        }
    }
}