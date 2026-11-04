package vn.edu.ute.datphong.helper

fun Long.dinhDangTien(): String {
    val laSoAm = this < 0
    val chuoiSo = kotlin.math.abs(this).toString()
    val ketQua = StringBuilder()
    var dem = 0
    for (i in chuoiSo.length - 1 downTo 0) {
        ketQua.append(chuoiSo[i])
        dem++
        if (dem % 3 == 0 && i != 0) {
            ketQua.append('.')
        }
    }
    val hienThi = ketQua.reverse().toString()
    return if (laSoAm) "-$hienThi đ" else "$hienThi đ"
}