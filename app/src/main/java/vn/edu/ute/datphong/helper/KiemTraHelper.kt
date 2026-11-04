package vn.edu.ute.datphong.helper

object KiemTraHelper {

    fun kiemTraHoTen(hoTen: String): Boolean {
        // \p{L} khớp mọi chữ cái Unicode (bao gồm tiếng Việt có dấu)
        val regex = Regex("^[\\p{L}]+( [\\p{L}]+)+$")
        return regex.matches(hoTen)
    }

    fun kiemTraSdt(sdt: String): Boolean {
        // Đúng 10 số, bắt đầu bằng 07 hoặc 08 (*)
        val regex = Regex("^0[78]\\d{8}$")
        return regex.matches(sdt)
    }

    fun kiemTraEmail(email: String): Boolean {
        // Trước @ bắt đầu bằng chữ cái, tiếp theo chữ/số/chấm/gạch dưới, tổng dài 3-30 ký tự -> theo sau cần 2-29 ký tự
        // Sau @ là ute.udn.vn (*)
        val regex = Regex("^[a-zA-Z][a-zA-Z0-9._]{2,29}@ute\\.udn\\.vn$")
        return regex.matches(email)
    }

    fun kiemTraNgay(ngay: String): Boolean {
        // dd/MM/yyyy: ngày 01-31, tháng 01-12, năm 2026-2029
        val regex = Regex("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(202[6-9])$")
        return regex.matches(ngay)
    }
}