package com.solom.yarasp.model

class Carrier (
    var code: Int = 0,
    var contacts: String = "",
    var url: String = "",
    var logoSvg: String = "",
    var title: String = "",
    var phone: String = "",
    var codes: Codes = Codes(),
    var address: String = "",
    var logo: String = "",
    var email: String = ""
)