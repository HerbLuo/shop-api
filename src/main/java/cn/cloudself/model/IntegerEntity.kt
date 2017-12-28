package cn.cloudself.model

import javax.persistence.*

/**
 * @author HerbLuo
 * @version 1.0.0.d
 *
 *
 * change logs:
 * 2017/5/16 HerbLuo 首次创建
 */
@Entity
class IntegerEntity : Number() {

    @get:Id
    var id: Int = 0

    @Transient
    fun getValue(): Int {
        return id
    }

    override fun toString(): String {
        return "" + id
    }

    override fun toInt(): Int {
        return id
    }

    override fun toLong(): Long {
        return id.toLong()
    }

    override fun toFloat(): Float {
        return id.toFloat()
    }

    override fun toDouble(): Double {
        return id.toDouble()
    }

    override fun toShort(): Short {
        return id.toShort()
    }

    override fun toChar(): Char {
        return id.toChar()
    }

    override fun toByte(): Byte {
        return id.toByte()
    }
}
