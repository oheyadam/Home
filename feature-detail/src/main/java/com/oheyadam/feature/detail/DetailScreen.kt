package com.oheyadam.feature.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Label
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import com.oheyadam.core.design.R
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(viewModel: BreedViewModel = viewModel()) {
  Box(modifier = Modifier.fillMaxSize()) {
    val state = viewModel.state
    ProgressView(
      isLoading = state.isLoading,
      modifier = Modifier.align(TopCenter)
    )
    Content(
      state = state,
      modifier = Modifier.fillMaxSize()
    )
    Snackbar(
      textResId = state.errorResId,
      onActionClick = { viewModel.getDogDetails() },
      onSnackbarShown = { viewModel.errorMessageShown() },
      modifier = Modifier.align(BottomCenter)
    )
  }
}

@Composable
private fun Content(
  state: BreedState,
  modifier: Modifier = Modifier,
) {
  LazyColumn(modifier = modifier) {
    item(key = state.thumbnailUrl) {
      Thumbnail(thumbnailUrl = state.thumbnailUrl)
    }
    item(key = state.name) {
      DetailsCard(state.name, state.temperament)
    }
  }
}

@Composable
private fun DetailsCard(
  name: String?,
  temperament: String?,
  modifier: Modifier = Modifier,
) {
  DefaultSpacer()
  Card(
    modifier = modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .padding(horizontal = dimensionResource(R.dimen.spacing_default))
      .shadow(elevation = dimensionResource(R.dimen.spacing_medium))
  ) {
    Column {
      Name(name)
      Temperament(temperament)
    }
  }
  DefaultSpacer()
}

@Composable
private fun Thumbnail(
  thumbnailUrl: String?,
  modifier: Modifier = Modifier,
) {
  if (thumbnailUrl == null) return
  SubcomposeAsyncImage(
    model = thumbnailUrl,
    contentDescription = stringResource(R.string.content_description_thumbnail),
    loading = { CircularProgressIndicator() },
    modifier = modifier
      .fillMaxWidth()
      .heightIn(min = dimensionResource(R.dimen.image_height))
  )
}

@Composable
private fun DefaultSpacer() {
  Spacer(modifier = Modifier.size(dimensionResource(R.dimen.spacing_default)))
}

@Composable
private fun Name(
  name: String?,
  modifier: Modifier = Modifier,
) {
  if (name == null) return
  Description(
    modifier = modifier,
    text = name,
    icon = {
      Icon(
        imageVector = Icons.Outlined.Label,
        contentDescription = stringResource(R.string.content_description_name)
      )
    })
}

@Composable
private fun Temperament(
  temperament: String?,
  modifier: Modifier = Modifier,
) {
  if (temperament == null) return
  Description(
    modifier = modifier,
    text = temperament,
    icon = {
      Icon(
        imageVector = Icons.Outlined.Info,
        contentDescription = stringResource(R.string.content_description_temperament)
      )
    })
  DefaultSpacer()
}

@Composable
private fun Description(
  text: String,
  icon: @Composable (() -> Unit),
  modifier: Modifier = Modifier,
) {
  Row(
    verticalAlignment = CenterVertically,
    modifier = modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .padding(
        start = dimensionResource(R.dimen.spacing_default),
        end = dimensionResource(R.dimen.spacing_default),
        top = dimensionResource(R.dimen.spacing_default)
      )
  ) {
    icon()
    Spacer(modifier = Modifier.size(dimensionResource(R.dimen.spacing_medium)))
    Text(text = text)
  }
}

@Composable
private fun Snackbar(
  textResId: Int?,
  onActionClick: () -> Unit,
  onSnackbarShown: () -> Unit,
  modifier: Modifier = Modifier,
) {
  if (textResId == null) return
  val hostState = remember { SnackbarHostState() }
  val scope = rememberCoroutineScope()
  val message = stringResource(textResId)
  val actionLabel = stringResource(R.string.action_refresh)
  SnackbarHost(hostState = hostState, modifier)
  LaunchedEffect(
    key1 = hostState,
    block = {
      scope.launch {
        val result = hostState.showSnackbar(
          message = message,
          actionLabel = actionLabel,
          duration = SnackbarDuration.Indefinite
        )
        onSnackbarShown()
        if (result == SnackbarResult.ActionPerformed) {
          onActionClick()
        }
      }
    }
  )
}

@Composable
private fun ProgressView(
  isLoading: Boolean,
  modifier: Modifier = Modifier,
) {
  if (isLoading) {
    LinearProgressIndicator(modifier = modifier)
  }
}
