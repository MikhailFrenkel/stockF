package com.frenkel.stockf.features.common.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.frenkel.stockf.R
import com.frenkel.ui_kit.ui.theme.*

@Composable
fun SearchField(
    text: String,
    placeholder: String,
    onTextChanged: (String) -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        placeholder = {
            Text(
                text = placeholder,
                color = if (isSystemInDarkTheme()) Greyscale500 else Greyscale400,
                style = BodyLargeRegular
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = if (isSystemInDarkTheme()) Greyscale500 else Greyscale400
            )
        },
        trailingIcon = {
            if (text.isNotBlank()) {
                IconButton(
                    onClick = onClear
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(R.string.clear),
                        tint = if (isSystemInDarkTheme()) Greyscale50 else Greyscale900
                    )
                }
            }
        },
        onValueChange = onTextChanged,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors().copy(
            unfocusedContainerColor = if (isSystemInDarkTheme()) Greyscale800 else Greyscale50,
            focusedContainerColor = if (isSystemInDarkTheme()) Greyscale800 else Greyscale50,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = if (isSystemInDarkTheme()) Primary500 else Primary600,
        )
    )
}