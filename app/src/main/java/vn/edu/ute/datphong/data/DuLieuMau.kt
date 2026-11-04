package vn.edu.ute.datphong.data

import vn.edu.ute.datphong.model.LoaiPhong

object DuLieuMau {
    val dsLoaiPhong: List<LoaiPhong> = listOf(
        LoaiPhong("P139", "Phòng Đơn Tiêu Chuẩn", 450000L, 2),
        LoaiPhong("P201", "Phòng Đôi Cao Cấp", 800000L, 4),
        LoaiPhong("P305", "Phòng Gia Đình VIP", 1200000L, 4),
        LoaiPhong("P402", "Phòng Tổng Thống", 2500000L, 6)
    )
}