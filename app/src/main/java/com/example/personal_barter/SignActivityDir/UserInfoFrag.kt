package com.example.personal_barter.SignActivityDir

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.personal_barter.MainActivityDir.UserInfo
import com.example.personal_barter.R

class UserInfoFrag: Fragment() {
    private var page: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            page = it.getInt(ARG_TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_user_info, container, false)

        setViewEvent(view)
        return view
    }

    fun setViewEvent(view: View){
        when(page){
            3->{
                val textViewName = view.findViewById<TextView>(R.id.textView_frag_userInfo_name)
                val textViewId = view.findViewById<TextView>(R.id.textView_frag_userInfo_id)
                val textViewAddress = view.findViewById<TextView>(R.id.textView_frag_userInfo_address)
                val textViewGrade = view.findViewById<TextView>(R.id.textView_frag_userInfo_grade)

                textViewName.text = "이름: 홍길동"
                textViewId.text = "ID: " + UserInfo.getId()
                textViewAddress.text = "주소: 성동구"

                val buttonModify = view.findViewById<Button>(R.id.button_frag_userInfo_modify)
                buttonModify.setOnClickListener {
                    val dialog = Dialog(view.context)
                    dialog.setContentView(R.layout.dialog_search)
                    dialog.show()

                    val buttonOk = dialog.findViewById<Button>(R.id.button_dialog_search)
                    buttonOk.text = "확인"
                    dialog.findViewById<EditText>(R.id.editText_dialog_search).setHint("비밀번호")
                    buttonOk.setOnClickListener {
                        if(dialog.findViewById<EditText>(R.id.editText_dialog_search).text.toString() == UserInfo.getPw()){
                            dialog.dismiss()
                            dialog.setContentView(R.layout.dialog_user_info_modify)
                            dialog.show()
                            val editTextName = dialog.findViewById<EditText>(R.id.editText_dialog_name_modify)
                            val editTextAddress = dialog.findViewById<EditText>(R.id.editText_dialog_address_modify)
                            val editTextPw = dialog.findViewById<EditText>(R.id.editText_dialog_pw_modify)

                            editTextName.setHint(textViewName.text.toString().substring(4))
                            editTextAddress.setHint(textViewAddress.text.toString().substring(4))
                            editTextPw.setHint(UserInfo.getPw().map { '*' }.joinToString(""))

                            val buttonModify = dialog.findViewById<Button>(R.id.button_dialog_modify)
                            buttonModify.setOnClickListener {
                                if(editTextName.text.isNotEmpty()){
                                    textViewName.text = "이름: " + editTextName.text.toString()
                                }
                                if(editTextAddress.text.isNotEmpty()){
                                    textViewAddress.text = "주소: " + editTextAddress.text.toString()
                                }
                                if(editTextPw.text.isNotEmpty()){
                                    UserInfo.setPw(editTextPw.text.toString())
                                }
                                dialog.dismiss()
                            }
                        }else{
                            Toast.makeText(view.context, "비밀번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
    companion object{
        const val ARG_TYPE  = "UserInfo_Page"
        fun newInstance(position: Int): Fragment{
            val frag = UserInfoFrag()
            val args = Bundle()

            args.putInt("UserInfo_Page", position)
            frag.arguments = args

            return frag
        }
    }
}