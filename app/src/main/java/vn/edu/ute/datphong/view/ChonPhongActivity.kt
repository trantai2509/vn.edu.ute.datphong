package vn.edu.ute.datphong.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import vn.edu.ute.datphong.R
import vn.edu.ute.datphong.data.DuLieuMau
import vn.edu.ute.datphong.helper.DatPhongHelper
import vn.edu.ute.datphong.helper.dinhDangTien

class ChonPhongActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chon_phong)

        val rgLoaiPhong: RadioGroup = findViewById(R.id.rgLoaiPhong)
        val btnChonXong: MaterialButton = findViewById(R.id.btnChonXong)

        val soKhach = intent.getIntExtra("so_khach", 1)
        val dsPhuHop = DatPhongHelper.locTheoSoKhach(DuLieuMau.dsLoaiPhong, soKhach)

        for (phong in dsPhuHop) {
            val rb = RadioButton(this)
            rb.id = View.generateViewId()
            rb.text = "${phong.ten} - Sức chứa: ${phong.sucChua} - Giá: ${phong.giaMotDem.dinhDangTien()}"
            rb.tag = phong.ma
            rgLoaiPhong.addView(rb)
        }

        btnChonXong.setOnClickListener {
            val checkedId = rgLoaiPhong.checkedRadioButtonId
            if (checkedId == -1) {
                Toast.makeText(this, "Vui lòng chọn loại phòng", Toast.LENGTH_SHORT).show()
            } else {
                val rbChon = findViewById<RadioButton>(checkedId)
                val maPhong = rbChon.tag as String
                val data = Intent()
                data.putExtra("ma_loai_phong", maPhong)
                setResult(Activity.RESULT_OK, data)
                finish()
            }
        }
    }
}