package com.manish.mandhan.profile.presentation.screens.profile_root

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.manish.mandhan.profile.presentation.R

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, onFavoriteCardClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .background(MaterialTheme.colorScheme.primary)
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HeadSection()
        Spacer(modifier = Modifier.height(8.dp))
        DashboardSection(
            onFavoriteCardClick = onFavoriteCardClick
        )
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
            fontSize = 24.sp,
            text = "Hello Manish,",
            fontFamily = FontFamily(Font(com.manish.mandhan.common.R.font.montserrat_bold))
        )
        Text(
            fontSize = 14.sp,
            text = "Good After Noon.",
            fontFamily = FontFamily(Font(com.manish.mandhan.common.R.font.montserrat_medium))
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
fun DashboardSection(modifier: Modifier = Modifier, onFavoriteCardClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        Text(
            fontSize = 14.sp,
            text = "Dashboard",
            fontFamily = FontFamily(Font(com.manish.mandhan.common.R.font.montserrat_bold))
        )
        Spacer(modifier = Modifier.height(12.dp))
        CardItem(
            imageId = com.manish.mandhan.common.R.drawable.start_img_bg_less,
            title = "Favorite Recipes",
            subTitle = "Explore your Favorite Recipes.",
            modifier = Modifier.clickable {
                onFavoriteCardClick()
            }

        )
        Spacer(modifier = Modifier.height(12.dp))
        CardItem(
            imageId = com.manish.mandhan.common.R.drawable.goal,
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
            fontSize = 14.sp,
            text = "Personalization",
            fontFamily = FontFamily(Font(com.manish.mandhan.common.R.font.montserrat_bold))
        )
        Spacer(modifier = Modifier.height(12.dp))
        CardItem(
            imageId = com.manish.mandhan.common.R.drawable.heart,
            title = "Your Interests",
            subTitle = "Explore your Favorite Recipes.",
        )
        Spacer(modifier = Modifier.height(12.dp))

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
        modifier = modifier
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
                    fontSize = 17.sp,
                    text = title,
                    fontFamily = FontFamily(Font(com.manish.mandhan.common.R.font.montserrat_semi_bold))
                )
                Text(
                    fontSize = 13.sp,
                    text = subTitle,
                    fontFamily = FontFamily(Font(com.manish.mandhan.common.R.font.montserrat_medium))
                )
            }
        }
    }
}