package com.oheyadam.home.core.design.component

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.oheyadam.core.design.R

@Composable fun TopAppBar(
  modifier: Modifier = Modifier,
  showNavigationIcon: Boolean = false,
  onNavigationActionClick: () -> Unit = {},
  @StringRes titleResId: Int,
  onSettingsActionClick: () -> Unit,
  scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
) {
  MediumTopAppBar(
    modifier = modifier,
    title = {
      Text(text = stringResource(titleResId), maxLines = 1, overflow = TextOverflow.Ellipsis)
    },
    navigationIcon = {
      if (showNavigationIcon) {
        IconButton(onClick = onNavigationActionClick) {
          Icon(
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = stringResource(R.string.content_description_action_up)
          )
        }
      }
    },
    actions = {
      IconButton(onClick = onSettingsActionClick) {
        Icon(
          imageVector = Icons.Outlined.Settings,
          contentDescription = stringResource(R.string.content_description_action_settings)
        )
      }
    },
    scrollBehavior = scrollBehavior
  )
}

@Preview
@Composable
fun TopAppBarPreview() {
  TopAppBar(
    showNavigationIcon = true,
    titleResId = R.string.title_home, onSettingsActionClick = { },
    scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
  )
}
