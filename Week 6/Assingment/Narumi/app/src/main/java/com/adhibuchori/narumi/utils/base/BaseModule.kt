package com.adhibuchori.narumi.utils.base

import org.koin.core.module.Module

interface BaseModule {
    fun getModules(): List<Module>
}