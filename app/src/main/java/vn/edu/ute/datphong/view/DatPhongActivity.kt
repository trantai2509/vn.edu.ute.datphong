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
}