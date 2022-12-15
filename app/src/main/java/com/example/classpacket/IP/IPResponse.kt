package com.example.classpacket.IP

import com.google.gson.annotations.SerializedName

class IPResponse {
    @SerializedName("IP")
    var IP: IP? = null
    @SerializedName("hostname")
    var hostname: hostname? = null
    @SerializedName("continent")
    var continent: continent? = null
    @SerializedName("country")
    var country: country? = null
    @SerializedName("region")
    var region: region? = null
    @SerializedName("city")
    var city: city? = null
    @SerializedName("longtitude")
    var longtitude: longtitude? = null
    @SerializedName("latitude")
    var latitude: latitude? = null
    @SerializedName("name")
    var name: String? = null
}

class IPData {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("main")
    var main: String? = null
    @SerializedName("description")
    var description: String? = null
}

class Sys{
    @SerializedName("continent")
    var continent: String? = null
    @SerializedName("country")
    var country: String? = null
    @SerializedName("region")
    var region: String? = null
    @SerializedName("city")
    var city: String? = null

}

