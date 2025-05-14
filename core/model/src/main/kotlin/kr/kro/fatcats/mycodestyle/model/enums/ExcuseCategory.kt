package kr.kro.fatcats.mycodestyle.model.enums

enum class ExcuseCategory(val s: String) {
    ALL("전체"),
    MISSED_APPOINTMENT("약속 취소 핑계"),
    LATE_APPOINTMENT("지각 핑계"),
    NO_RESPONSE("답장 누락 핑계"),
    FUNNY("웃긴 핑계")
}