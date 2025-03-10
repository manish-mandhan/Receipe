package com.manish.mandhan.feature.settings.screens

import android.preference.PreferenceActivity.Header
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manish.mandhan.feature.settings.R

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .then(modifier)
    ) {
        HeaderSection()
//        Spacer(modifier = Modifier.height(48.dp))
        GeneralSection()


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
            fontSize = 30.sp,
            text = "Settings",
            fontFamily = FontFamily(Font(R.font.montserrat_bold))
        )
        Text(
            fontSize = 16.sp,
            text = "Manage preference and controls",
            fontFamily = FontFamily(Font(R.font.montserrat_medium))
        )
    }

}

@Composable
fun GeneralSection(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

//        CardItem(
//            imageId = R.drawable.settings,
//            title = "Preference"
//        )
//        Text(
//            fontSize = 16.sp,
//            text = "General",
//            fontFamily = FontFamily(Font(R.font.montserrat_bold))
//        )
//        Spacer(modifier = Modifier.height(12.dp))

    }

}

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    imageId: Int,
    title: String, subTitle: String,
    imageSizeInFloat: Float = 1f
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {

        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Box(modifier = Modifier.fillMaxWidth(.15f), contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier.fillMaxWidth(imageSizeInFloat),
                    painter = painterResource(imageId),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    fontSize = 18.sp,
                    text = title,
                    fontFamily = FontFamily(Font(R.font.montserrat_semi_bold))
                )
                Text(
                    fontSize = 14.sp,
                    text = subTitle,
                    fontFamily = FontFamily(Font(R.font.montserrat_medium))
                )
            }
        }
    }
}