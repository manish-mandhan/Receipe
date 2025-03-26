package com.manish.mandhan.presentation.screens.shared

import androidx.lifecycle.ViewModel
import com.manish.mandhan.search.domain.model.DomainRecipeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(): ViewModel() {

    var domainRecipeModel: DomainRecipeModel? = null
}