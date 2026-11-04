package vn.edu.ute.datphong.view

import android.app.Activity
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
import vn.edu.ute.datphong.helper.dinhDangTien
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import vn.edu.ute.datphong.model.DonDatPhong
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
    private lateinit var btnChonPhong: MaterialButton
    private lateinit var btnDatPhong: MaterialButton

    private var loaiPhongDangChon: LoaiPhong = DuLieuMau.dsLoaiPhong[0]

    private val chonPhongLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val maMoi = result.data?.getStringExtra("ma_loai_phong")
            if (maMoi != null) {
                DatPhongHelper.timLoaiPhong(maMoi)?.let {
                    loaiPhongDangChon = it
                    hienThiLoaiPhong()
                    capNhatTamTinh()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dat_phong)

        val maLuu = savedInstanceState?.getString("MA_LOAI_PHONG_LUU")
        if (maLuu != null) {
            DatPhongHelper.timLoaiPhong(maLuu)?.let {
                loaiPhongDangChon = it
            }
        }

        anhXaView()
        hienThiLoaiPhong()
        capNhatTamTinh()

        edtSoDem.doAfterTextChanged { capNhatTamTinh() }
        edtSoKhach.doAfterTextChanged { capNhatTamTinh() }

        btnChonPhong.setOnClickListener {
            val soKhach = edtSoKhach.text?.toString()?.trim()?.toIntOrNull() ?: 1
            val intent = Intent(this, ChonPhongActivity::class.java)
            intent.putExtra("so_khach", soKhach)
            chonPhongLauncher.launch(intent)
        }

        btnDatPhong.setOnClickListener {
            xuLyDatPhong()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("MA_LOAI_PHONG_LUU", loaiPhongDangChon.ma)
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
        btnChonPhong = findViewById(R.id.btnChonPhong)
        btnDatPhong = findViewById(R.id.btnDatPhong)
    }

    private fun hienThiLoaiPhong() {
        txtLoaiPhong.text = "${loaiPhongDangChon.ten} - Giá: ${loaiPhongDangChon.giaMotDem.dinhDangTien()}"
    }

    private fun capNhatTamTinh() {
        val soDem = edtSoDem.text?.toString()?.trim()?.toIntOrNull() ?: 0
        val soKhach = edtSoKhach.text?.toString()?.trim()?.toIntOrNull() ?: 0
        val tien = DatPhongHelper.tinhTienPhong_23115053122139(loaiPhongDangChon, soDem, soKhach)
        txtTamTinh.text = if (tien == 0L) "Tạm tính: 0" else "Tạm tính: ${tien.dinhDangTien()}"
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
            tilHoTen.error = "Họ tên không hợp lệ"
            hopLe = false
        } else tilHoTen.error = null

        if (!KiemTraHelper.kiemTraSdt(sdt)) {
            tilSdt.error = "SĐT phải 10 số, bắt đầu 07 hoặc 08"
            hopLe = false
        } else tilSdt.error = null

        if (!KiemTraHelper.kiemTraEmail(email)) {
            tilEmail.error = "Email phải đúng định dạng @ute.udn.vn"
            hopLe = false
        } else tilEmail.error = null

        if (!KiemTraHelper.kiemTraNgay(ngayNhan)) {
            tilNgayNhan.error = "Ngày nhận dạng dd/MM/yyyy (2026-2029)"
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

        val don = DonDatPhong(
            hoTen = hoTen,
            sdt = sdt,
            email = email,
            maLoaiPhong = loaiPhongDangChon.ma,
            ngayNhan = ngayNhan,
            soDem = soDem!!,
            soKhach = soKhach!!
        )
        val tongTien = DatPhongHelper.tinhTienPhong_23115053122139(loaiPhongDangChon, soDem, soKhach)
        val intent = Intent(this, XacNhanActivity::class.java)
        intent.putExtra("don_dat_phong", don)
        intent.putExtra("tong_tien", tongTien)
        startActivity(intent)
    }
}