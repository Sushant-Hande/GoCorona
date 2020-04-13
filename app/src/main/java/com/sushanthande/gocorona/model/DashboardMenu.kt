package com.sushanthande.gocorona.model

/**
 *Created by Sushant Hande on 25-03-2020
 */
class DashboardMenu {

    var id: Int = 0

    var name: String? = null

    var image: Int? = null

    enum class Menu(val id: Int) {
        INDIA_UPDATES(1),
        WORLD_UPDATES(2),
        SYMPTOMS(3),
        PRECAUTIONS(4),
    }
}