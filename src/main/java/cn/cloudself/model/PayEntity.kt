package cn.cloudself.model

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
data class PayEntity(
        var id: Int = 0,
        var price: Double? = null,
        var thirdPayUrl: String? = null,
        private val callbackUrl: String? = null
)
