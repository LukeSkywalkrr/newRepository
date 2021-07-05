package com.androiddevs.mvvmnewsapp.models

data class Session(
    val address: String,
    val available_capacity: Int,
    val available_capacity_dose1: Int,
    val available_capacity_dose2: Int,
    val block_name: String,
    val center_id: Int,
    val date: String,
    val district_name: String,
    val fee: String,
    val fee_type: String,
    val from: String,
    val lat: Double,
    val long: Double,
    val min_age_limit: Int,
    val name: String,
    val pincode: Int,
    val session_id: String,
    val slots: List<String>,
    val state_name: String,
    val to: String,
    val vaccine: String
)