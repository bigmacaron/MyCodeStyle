package kr.kro.fatcats.mycodestyle.model.enums

enum class ExcuseCategory(val s : String) {
    ALL("전체"),
    MISSED_APPOINTMENT("가지못한(할) 핑계"),
    LATE_APPOINTMENT("늦는 핑계"),
    NO_RESPONSE("답장 못한 핑계"),
    FUNNY("재밌는 핑계")
}