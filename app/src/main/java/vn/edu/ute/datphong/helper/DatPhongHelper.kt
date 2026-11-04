package vn.edu.ute.datphong.helper

import vn.edu.ute.datphong.data.DuLieuMau
import vn.edu.ute.datphong.model.LoaiPhong
import vn.edu.ute.datphong.model.DonDatPhong

object DatPhongHelper {

    fun timLoaiPhong(ma: String): LoaiPhong? {
        val maCanTim = ma.trim()
        return DuLieuMau.dsLoaiPhong.find { it.ma.equals(maCanTim, ignoreCase = true) }
    }

    fun hopLeSoKhach(loai: LoaiPhong, soKhach: Int): Boolean {
        return soKhach >= 1 && soKhach <= (loai.sucChua + 2)
    }

    fun tinhTienPhong_23115053122139(loai: LoaiPhong, soDem: Int, soKhach: Int): Long {
        if (soDem < 1 || !hopLeSoKhach(loai, soKhach)) {
            return 0L
        }
        var tienPhong = loai.giaMotDem * soDem
        if (soDem >= 4) {
            tienPhong = (tienPhong * 85L) / 100L
        }
        val soKhachVuot = if (soKhach > loai.sucChua) (soKhach - loai.sucChua) else 0
        val phuThu = soKhachVuot.toLong() * 150000L * soDem
        return tienPhong + phuThu
    }
    fun taoMaDon(don: DonDatPhong): String {
        val parts = don.ngayNhan.trim().split("/")
        val ddMM = if (parts.size >= 2) "${parts[0]}${parts[1]}" else ""
        return "DP139-$ddMM-${don.maLoaiPhong.trim().uppercase()}"
    }

    fun locTheoSoKhach(ds: List<LoaiPhong>, soKhach: Int): List<LoaiPhong> {
        return ds.filter { it.sucChua >= soKhach }
            .sortedWith(compareByDescending<LoaiPhong> { it.sucChua }.thenBy { it.giaMotDem })
    }
}