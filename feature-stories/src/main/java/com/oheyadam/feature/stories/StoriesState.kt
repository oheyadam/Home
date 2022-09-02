package com.oheyadam.feature.stories

import com.oheyadam.core.common.ui.state.Message
import com.oheyadam.core.model.Story

data class StoriesState(
  val isLoading: Boolean = false,
  val messages: List<Message> = emptyList(),
  val stories: List<Story> = emptyList()
)
