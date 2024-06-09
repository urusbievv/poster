package com.example.poster.utils

class DetectedClasses {
    companion object {
        val classes = arrayOf(
            "Поворот направо запрещен",
            "Уступите дорогу",
            "Главная дорога",
            "Неровная дорога",
            "Опасный поворот"
        )
        const val NOT_RECOGNIZED = "Не удалось распознать"
        const val MESSAGE_RECOGNIZED = "Наведите на знак ещё раз"
        fun getDescriptionForResult(result: String): String {
            return when (result) {
                "Поворот направо запрещен" -> "Знак 3.18.1 запрещает только тот маневр который изображен на знаке. Следовательно запрещен только поворот направо, а во всех остальных направлениях движение разрешено, в том числе и разворот. Действие знака не распространяется на маршрутные транспортные средства."
                "Уступите дорогу" -> "дорожный знак приоритета, предписывающий водителям уступить дорогу транспортным средствам, движущимся по пересекаемой дороге, а при наличии таблички 8.13 «Направление главной дороги» — по главной."
                "Главная дорога" -> "«Главная дорога» обозначается знаком 2.1, который, согласно ПДД, обозначает участки дорог с приоритетным правом движения. Следовательно, это знак приоритета. Он наделяет преимуществом транспорт, движущийся по отмеченному табличкой участку."
                "Неровная дорога" -> "Дорожный знак 1.16 «Неровная дорога» призывает водителя сбавить скорость при приближении к различным неровным участкам дорожного полотна. Используется в качестве временного знака, предшествующего проведению ремонтных работ. Указывает на наличие ям, выбоин, колеи по пути следования автомобиля."
                "Опасный поворот" -> "Знак 1.11.1 \"Опасный поворот направо\". Знак 1.11.1 предупреждает водителя о том, что он подъезжает к крутому (опасному) повороту или к повороту с плохой видимостью направо. Водителю нужно снизить скорость и выбрать наиболее подходящую траекторию для проезда этого поворота."
                else -> ""
            }
        }
    }
}
