package com.manish.mandhan.receipe.vm

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manish.mandhan.receipe.App
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(private val app: Application) : ViewModel() {


    val Context.datastore by preferencesDataStore(name = "local")

    private val _currentTheme = MutableStateFlow<String?>(null)

    val currentTheme = _currentTheme.asStateFlow()

    var shouldShowSplashScreen by mutableStateOf(true)

    init {
        Log.d("VIEW_MODEL_LOG", "view model deployed")
        app.datastore.data.onEach {
            _currentTheme.value = it[stringPreferencesKey("theme")] ?: "Light"

            shouldShowSplashScreen = false
        }.launchIn(viewModelScope)
    }


    fun changeTheme(value: String) {
        viewModelScope.launch {
            app.datastore.edit {
                it[stringPreferencesKey("theme")] = value
            }
        }
    }


}

