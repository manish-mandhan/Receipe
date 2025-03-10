package com.manish.mandhan.feature.profile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manish.mandhan.feature.profile.R

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .verticalScroll(state = rememberScrollState())
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HeadSection()
        Spacer(modifier = Modifier.height(8.dp))
        DashboardSection()
        Spacer(modifier = Modifier.height(12.dp))
        PersonalizationSection()

    }
}

@Composable
fun HeadSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .padding(start = 12.dp)
    ) {
        Text(
            fontSize = 30.sp,
            text = "Hello Manish,",
            fontFamily = FontFamily(Font(R.font.montserrat_bold))
        )
        Text(
            fontSize = 16.sp,
            text = "Good After Noon.",
            fontFamily = FontFamily(Font(R.font.montserrat_medium))
        )
        Image(
            modifier = Modifier
                .align(Alignment.End)
                .fillMaxWidth(.7f),
            imageVector = ImageVector.vectorResource(R.drawable._8493600_6001178),
            contentDescription = null
        )

    }
}

@Composable
fun DashboardSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        Text(
            fontSize = 16.sp,
            text = "Dashboard",
            fontFamily = FontFamily(Font(R.font.montserrat_bold))
        )
        Spacer(modifier = Modifier.height(12.dp))
        CardItem(
            imageId = R.drawable.start_image,
            title = "Favorite Recipes",
            subTitle = "Explore your Favorite Recipes."

        )
        Spacer(modifier = Modifier.height(12.dp))
        CardItem(
            imageId = R.drawable.target_566958,
            title = "Daily Challenges",
            subTitle = "Explore exciting daily challenges.",
            imageSizeInFloat = .8f
        )
    }
}

@Composable
fun PersonalizationSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        Text(
            fontSize = 16.sp,
            text = "Personalization",
            fontFamily = FontFamily(Font(R.font.montserrat_bold))
        )
        Spacer(modifier = Modifier.height(12.dp))
        CardItem(
            imageId = R.drawable.heart_new_image,
            title = "Your Interests",
            subTitle = "Explore your Favorite Recipes.",
            imageSizeInFloat = .95f

        )
        Spacer(modifier = Modifier.height(12.dp))
//        CardItem(
//            imageId = R.drawable.heart_new_image,
//            title = "Daily Challenges",
//            subTitle = "Explore exciting daily challenges.",
//            imageSizeInFloat = .8f
//        )
    }
}


@Preview(showBackground = true)
@Composable
private fun CardPrev() {

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