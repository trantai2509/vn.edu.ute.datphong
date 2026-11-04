package vn.edu.ute.datphong.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import vn.edu.ute.datphong.R
import vn.edu.ute.datphong.helper.DatPhongHelper
import vn.edu.ute.datphong.helper.dinhDangTien
import vn.edu.ute.datphong.model.DonDatPhong

class XacNhanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xac_nhan)

        val txtHoTen: TextView = findViewById(R.id.txtHoTen)
        val txtSdt: TextView = findViewById(R.id.txtSdt)
        val txtEmail: TextView = findViewById(R.id.txtEmail)
        val txtLoaiPhong: TextView = findViewById(R.id.txtLoaiPhong)
        val txtNgayNhan: TextView = findViewById(R.id.txtNgayNhan)
        val txtSoDem: TextView = findViewById(R.id.txtSoDem)
        val txtSoKhach: TextView = findViewById(R.id.txtSoKhach)
        val txtTongTien: TextView = findViewById(R.id.txtTongTien)
        val btnSua: MaterialButton = findViewById(R.id.btnSua)

        @Suppress("DEPRECATION")
        val don = intent.getSerializableExtra("don_dat_phong") as? DonDatPhong
        val tongTien = intent.getLongExtra("tong_tien", 0L)

        if (don != null) {
            val loai = DatPhongHelper.timLoaiPhong(don.maLoaiPhong)
            txtHoTen.text = "Họ tên: ${don.hoTen}"
            txtSdt.text = "SĐT: ${don.sdt}"
            txtEmail.text = "Email: ${don.email}"
            txtLoaiPhong.text = "Loại phòng: ${loai?.ten ?: don.maLoaiPhong}"
            txtNgayNhan.text = "Ngày nhận: ${don.ngayNhan}"
            txtSoDem.text = "Số đêm: ${don.soDem}"
            txtSoKhach.text = "Số khách: ${don.soKhach}"
            txtTongTien.text = "Tổng tiền: ${tongTien.dinhDangTien()}"
        }

        btnSua.setOnClickListener {
            finish()
        }
    }
}