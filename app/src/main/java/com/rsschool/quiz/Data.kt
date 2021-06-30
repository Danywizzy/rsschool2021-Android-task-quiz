package com.rsschool.quiz

data class Question(
    val text: String,
    val answers: List<String>,
    val correct: Int
)

val questions = listOf(
    Question(
        text = "В 2021 Международный день пива празднуется ...",
        answers = listOf("15 марта", "4 сентября", "26 декабря", "6 августа", "2 мая"),
        correct = 3
    ),
    Question(
        text = "Рекордное время употребления 1 литра пива составляет?",
        answers = listOf("1.3с", "4.2с", "2.7с", "3.4с", "6.1с"),
        correct = 0
    ),
    Question(
        text = "Как называется наука, изучающая пиво?",
        answers = listOf("Бирология", "Зитология", "Пивоведение", "Пивология", "Хмелелогия"),
        correct = 1
    ),
    Question(
        text = "В каком году пиво начали разливать в металлические банки?",
        answers = listOf("1885", "1916", "1857", "1950", "1935"),
        correct = 4
    ),
    Question(
        text = "Самое крепкое пиво Snake venom имеет крепость...%",
        answers = listOf("16,2", "65", "32", "67.5", "48"),
        correct = 3
    )
)