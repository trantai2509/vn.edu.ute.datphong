package vn.edu.ute.datphong.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import vn.edu.ute.datphong.R
import vn.edu.ute.datphong.data.DuLieuMau
import vn.edu.ute.datphong.model.LoaiPhong
import vn.edu.ute.datphong.helper.DatPhongHelper
import androidx.core.widget.doAfterTextChanged
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import vn.edu.ute.datphong.helper.KiemTraHelper

class DatPhongActivity : AppCompatActivity() {

    private lateinit var tilHoTen: TextInputLayout
    private lateinit var tilSdt: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilNgayNhan: TextInputLayout
    private lateinit var tilSoDem: TextInputLayout
    private lateinit var tilSoKhach: TextInputLayout

    private lateinit var edtHoTen: TextInputEditText
    private lateinit var edtSdt: TextInputEditText
    private lateinit var edtEmail: TextInputEditText
    private lateinit var edtNgayNhan: TextInputEditText
    private lateinit var edtSoDem: TextInputEditText
    private lateinit var edtSoKhach: TextInputEditText

    private lateinit var txtLoaiPhong: TextView
    private lateinit var txtTamTinh: TextView
    private lateinit var btnDatPhong: MaterialButton

    private var loaiPhongDangChon: LoaiPhong = DuLieuMau.dsLoaiPhong[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dat_phong)
        anhXaView()
        hienThiLoaiPhong()
        edtSoDem.doAfterTextChanged { capNhatTamTinh() }
        edtSoKhach.doAfterTextChanged { capNhatTamTinh() }
        btnDatPhong.setOnClickListener { xuLyDatPhong() }
    }

    private fun anhXaView() {
        tilHoTen = findViewById(R.id.tilHoTen)
        tilSdt = findViewById(R.id.tilSdt)
        tilEmail = findViewById(R.id.tilEmail)
        tilNgayNhan = findViewById(R.id.tilNgayNhan)
        tilSoDem = findViewById(R.id.tilSoDem)
        tilSoKhach = findViewById(R.id.tilSoKhach)

        edtHoTen = findViewById(R.id.edtHoTen)
        edtSdt = findViewById(R.id.edtSdt)
        edtEmail = findViewById(R.id.edtEmail)
        edtNgayNhan = findViewById(R.id.edtNgayNhan)
        edtSoDem = findViewById(R.id.edtSoDem)
        edtSoKhach = findViewById(R.id.edtSoKhach)

        txtLoaiPhong = findViewById(R.id.txtLoaiPhong)
        txtTamTinh = findViewById(R.id.txtTamTinh)
        btnDatPhong = findViewById(R.id.btnDatPhong)
    }

    private fun hienThiLoaiPhong() {
        txtLoaiPhong.text = "${loaiPhongDangChon.ten} - Giá: ${loaiPhongDangChon.giaMotDem}"
    }

    private fun capNhatTamTinh() {
        val soDem = edtSoDem.text?.toString()?.trim()?.toIntOrNull() ?: 0
        val soKhach = edtSoKhach.text?.toString()?.trim()?.toIntOrNull() ?: 0
        val tien = DatPhongHelper.tinhTienPhong_23115053122139(loaiPhongDangChon, soDem, soKhach)
        txtTamTinh.text = "Tạm tính: $tien"
    }

    private fun xuLyDatPhong() {
        val hoTen = edtHoTen.text?.toString()?.trim() ?: ""
        val sdt = edtSdt.text?.toString()?.trim() ?: ""
        val email = edtEmail.text?.toString()?.trim() ?: ""
        val ngayNhan = edtNgayNhan.text?.toString()?.trim() ?: ""
        val soDemStr = edtSoDem.text?.toString()?.trim() ?: ""
        val soKhachStr = edtSoKhach.text?.toString()?.trim() ?: ""

        var hopLe = true

        if (!KiemTraHelper.kiemTraHoTen(hoTen)) {
            tilHoTen.error = "Họ tên không hợp lệ (ít nhất 2 từ)"
            hopLe = false
        } else tilHoTen.error = null

        if (!KiemTraHelper.kiemTraSdt(sdt)) {
            tilSdt.error = "SĐT phải 10 số, bắt đầu 07 hoặc 08"
            hopLe = false
        } else tilSdt.error = null

        if (!KiemTraHelper.kiemTraEmail(email)) {
            tilEmail.error = "Email phải có đuôi @ute.udn.vn"
            hopLe = false
        } else tilEmail.error = null

        if (!KiemTraHelper.kiemTraNgay(ngayNhan)) {
            tilNgayNhan.error = "Ngày định dạng dd/MM/yyyy (2026-2029)"
            hopLe = false
        } else tilNgayNhan.error = null

        val soDem = soDemStr.toIntOrNull()
        if (soDem == null || soDem !in 1..19) {
            tilSoDem.error = "Số đêm từ 1 đến 19"
            hopLe = false
        } else tilSoDem.error = null

        val soKhach = soKhachStr.toIntOrNull()
        if (soKhach == null || !DatPhongHelper.hopLeSoKhach(loaiPhongDangChon, soKhach)) {
            tilSoKhach.error = "Số khách từ 1 đến ${loaiPhongDangChon.sucChua + 2}"
            hopLe = false
        } else tilSoKhach.error = null

        if (!hopLe) {
            Snackbar.make(btnDatPhong, "Vui lòng kiểm tra lại thông tin", Snackbar.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "Đã ghi nhận đơn đặt phòng của $hoTen", Toast.LENGTH_SHORT).show()
    }
}