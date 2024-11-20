package com.frenkel.ui_kit.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.frenkel.ui_kit.R

val Satoshi = FontFamily(
    Font(
        resId = R.font.satoshi_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.satoshi_medium,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.satoshi_bold,
        weight = FontWeight.Bold
    ),
)

val HeadingH1 = TextStyle(
    fontFamily = Satoshi,
    fontWeight = FontWeight.Bold,
    fontSize = 48.sp
)

val HeadingH2 = TextStyle(
    fontFamily = Satoshi,
    fontWeight = FontWeight.Bold,
    fontSize = 40.sp
)

val HeadingH3 = TextStyle(
    fontFamily = Satoshi,
    fontWeight = FontWeight.Bold,
    fontSize = 32.sp
)

val HeadingH4 = TextStyle(
    fontFamily = Satoshi,
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp
)

val HeadingH5 = TextStyle(
    fontFamily = Satoshi,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp
)

val HeadingH6 = TextStyle(
    fontFamily = Satoshi,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp
)


val BodyXLargeBold = TextStyle(
    fontFamily = Satoshi,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp
)

val BodyXLargeMedium = TextStyle(
    fontFamily = Satoshi,
    fontWeight = FontWeight.Medium,
    fontSize = 18.sp
)

val BodyXLargeRegular = TextStyle(
    fontFamily = Satoshi,
    fontWeight = FontWeight.Normal,
    fontSize = 18.sp
)


val BodyLargeBold = TextStyle(
    fontFamily = Satoshi,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp
)

val BodyLargeMedium = TextStyle(
    fontFamily = Satoshi,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp
)

val BodyLargeRegular = TextStyle(
    fontFamily = Satoshi,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
)


val BodyMediumBold = TextStyle(
    fontFamily = Satoshi,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp
)

val BodyMediumMedium = TextStyle(
    fontFamily = Satoshi,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp
)

val BodyMediumRegular = TextStyle(
    fontFamily = Satoshi,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp
)

val BodySmallBold = TextStyle(
    fontFamily = Satoshi,
    fontWeight = FontWeight.Bold,
    fontSize = 12.sp
)

val BodySmallMedium = TextStyle(
    fontFamily = Satoshi,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp
)

val BodySmallRegular = TextStyle(
    fontFamily = Satoshi,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
)


val Typography = Typography(
    displayLarge = BodyLargeMedium,
    displayMedium = BodyMediumMedium,
    displaySmall = BodySmallMedium,
    headlineLarge = HeadingH1,
    headlineMedium = HeadingH2,
    headlineSmall = HeadingH3,
    titleLarge = BodyXLargeBold,
    titleMedium = BodyLargeBold,
    titleSmall = BodyMediumBold,
    bodyLarge = BodyLargeRegular,
    bodyMedium = BodyMediumRegular,
    bodySmall = BodySmallRegular,
    labelLarge = BodyLargeRegular,
    labelMedium = BodyMediumRegular,
    labelSmall = BodySmallRegular,
)