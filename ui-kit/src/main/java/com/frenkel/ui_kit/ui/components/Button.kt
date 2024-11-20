package com.frenkel.ui_kit.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.frenkel.ui_kit.ui.theme.FTheme
import com.frenkel.ui_kit.ui.theme.*
import androidx.compose.material3.Button as MaterialButton

enum class ButtonStyle(
    val backgroundColor: Color,
    val pressedBackgroundColor: Color,
    val disabledBackgroundColor: Color,
    val borderWidth: Dp? = null,
    val borderColor: Color? = null,
    val borderPressedColor: Color? = null,
    val borderDisabledColor: Color? = null,
) {
    PRIMARY(
        backgroundColor = Primary600,
        pressedBackgroundColor = Color(0xFF0F3339),
        disabledBackgroundColor = Primary300
    ),

    SECONDARY(
        backgroundColor = Primary100,
        pressedBackgroundColor = Primary200,
        disabledBackgroundColor = Primary100
    ),

    TERTIARY(
        backgroundColor = Color.Transparent,
        pressedBackgroundColor = Color.Transparent,
        disabledBackgroundColor = Color.Transparent,
        borderWidth = 1.dp,
        borderColor = Greyscale200,
        borderPressedColor = Primary200,
        borderDisabledColor = Greyscale200
    )
}

enum class ButtonSize(
    val buttonHeight: Dp,
    val cornerRadius: Dp,
    val fontSize: TextUnit
) {
    LARGE(
        buttonHeight = 56.dp,
        cornerRadius = 12.dp,
        fontSize = LargeFontSize
    ),

    MEDIUM(
        buttonHeight = 48.dp,
        cornerRadius = 10.dp,
        fontSize = MediumFontSize
    ),

    SMALL(
        buttonHeight = 32.dp,
        cornerRadius = 8.dp,
        fontSize = SmallFontSize
    )
}

enum class ButtonState {
    DEFAULT, PRESSED, DISABLED
}


@Composable
fun Button(
    text: String,
    onClick: () -> Unit,
    buttonStyle: ButtonStyle = ButtonStyle.PRIMARY,
    buttonSize: ButtonSize = ButtonSize.LARGE,
    enabled: Boolean = true,
    modifier: Modifier = Modifier) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val buttonState = when {
        !enabled -> ButtonState.DISABLED
        isPressed -> ButtonState.PRESSED
        else -> ButtonState.DEFAULT
    }

    MaterialButton(
        modifier = modifier
            .height(buttonSize.buttonHeight),
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isPressed) buttonStyle.pressedBackgroundColor else buttonStyle.backgroundColor,
            disabledContainerColor = buttonStyle.disabledBackgroundColor
        ),
        border = if (buttonStyle.borderWidth != null) {
            BorderStroke(
                buttonStyle.borderWidth,
                when {
                    !enabled -> buttonStyle.borderDisabledColor
                    isPressed -> buttonStyle.borderPressedColor
                    else -> buttonStyle.borderColor
                } ?: Color.Transparent
            )
        } else {
            null
        },
        shape = RoundedCornerShape(buttonSize.cornerRadius)
    ) {
        Text(
            text = text,
            color = getTextColor(buttonStyle, buttonState),
            fontSize = buttonSize.fontSize,
            fontFamily = BodyLargeBold.fontFamily,
            fontWeight = BodyLargeBold.fontWeight,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun ButtonPreview() {
    FTheme {
        Button(
            text = "Preview",
            onClick = {},
            buttonStyle = ButtonStyle.PRIMARY,
            buttonSize = ButtonSize.LARGE,
            enabled = false
        )
    }
}

private fun getTextColor(
    buttonStyle: ButtonStyle,
    buttonState: ButtonState
): Color {
    return when (buttonStyle) {
        ButtonStyle.PRIMARY -> White
        ButtonStyle.SECONDARY, ButtonStyle.TERTIARY ->
            if (buttonState == ButtonState.DISABLED) Primary300 else Primary600
    }
}