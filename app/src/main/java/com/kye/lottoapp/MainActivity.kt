package com.kye.lottoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val result_num = arrayListOf<Int>()
    var lottNO = mutableListOf<Int>()
    var controllerFlag: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 45까지 배열
        for (i in 1..45) {
            lottNO.add(i)
        }

        lottNO.shuffle()  // 섞어 놓기

        // TextView 객체 배열
        val lottNoArr = arrayListOf(no1, no2, no3, no4, no5, no6)
        var idx = 0
        msg.text = ""


        // 추첨 시작 버튼을 클릭하면 수행
        button.setOnClickListener {
            if (controllerFlag) { // 번호 추출 중이 아닐때만 수행
                controllerFlag = false // 추첨 진행 중

                if (idx < 6) {
                    displayCount(idx)
                    idx++
                }
            }
        }


        // 일괄번호추첨 버튼 클릭
        button2.setOnClickListener {
            if (controllerFlag) { // 번호 추출 중이 아닐때만 수행
                idx = 0
                controllerFlag = false  // 추첨 진행 중
                lottNO.shuffle()  // 섞어 놓기

                lottNoArr.forEach {

                    if (idx < 6) {
                        displayAllCount(idx)
                        idx++
                    }
                }
            }
        }


        // 재시작 버튼 클릭 시  초기화 후 추첨버튼을 클릭하라는 메시지 출력
        button3.setOnClickListener {

            // 모든 구분자 초기화
            idx = 0

            controllerFlag = true

            lottNoArr.forEach {
                it.setBackgroundResource(R.drawable.lott_ball)
                it.text = "7"
            }

            lottNO.shuffle()  // 섞어 놓기

            msg.text = " 모두 초기화 되었습니다. \n 다시 추첨할 수 있습니다. !!!"

        }
    }

    fun displayCount(idx: Int) {

        // TextView 객체 배열
        val lottNoArr = arrayListOf(no1, no2, no3, no4, no5, no6)

        result_num.add(lottNO[idx]) // 번호 추출
        //println("lottNO == " + lottNO.toString())

        // Timer를 위한 설정 (익명 클래스로 생성해서 사용)
        val countTimer = object : CountDownTimer(1000, 500) {

            // 3초 중 0.5초마다 숫자를 화면에 순서대로 보여주는 척 한다.
            override fun onTick(millisUntilFinished: Long) {

                controllerFlag = false
                msg.text = " ${idx + 1}번째 번호를 추첨 중입니다. \n 끝날 때 까지 기다려 주세요"

            }

            // 타이머가 끝나면 호출
            override fun onFinish() {

                lottNoArr[idx].text = "${result_num[idx]}"
                //println("=========> ${result_num[idx]}")

                // 번호에 따른 볼의 색을 지정
                when (result_num[idx]) {
                    1, 2, 3, 4, 5, 6, 7, 8, 9, 10 -> lottNoArr[idx].setBackgroundResource(R.drawable.lott_ball_1_10)
                    11, 12, 13, 14, 15, 16, 17, 18, 19, 20 -> lottNoArr[idx].setBackgroundResource(R.drawable.lott_ball_11_20)
                    21, 22, 23, 24, 25, 26, 27, 28, 29, 30 -> lottNoArr[idx].setBackgroundResource(R.drawable.lott_ball_21_30)
                    31, 32, 33, 34, 35, 36, 37, 38, 39, 40 -> lottNoArr[idx].setBackgroundResource(R.drawable.lott_ball_31_40)
                    41, 42, 43, 44, 45 -> lottNoArr[idx].setBackgroundResource(R.drawable.lott_ball_41_45)
                }

                if (idx == 5) {
                    msg.text = "번호 추첨이 모두 끝났습니다. \n 다시 시작하시려면 재시작 버튼을 터치해 주세요"
                } else {
                    msg.text = " ${idx + 1}번째 번호 추첨이 끝났습니다. \n 다시 개별번호추첨 버튼을 터치해 주세요"
                }

                controllerFlag = true
            }
        }

        countTimer.start()

    }

    fun displayAllCount(idx: Int) {

        // TextView 객체 배열
        val lottNoArr = arrayListOf(no1, no2, no3, no4, no5, no6)

        result_num.add(lottNO[idx]) // 번호 추출
        //println("lottNO == " + lottNO.toString())

        // Timer를 위한 설정 (익명 클래스로 생성해서 사용)
        val countTimer = object : CountDownTimer(2000, 1000) {

            // 3초 중 0.5초마다 숫자를 화면에 순서대로 보여주는 척 한다.
            override fun onTick(millisUntilFinished: Long) {

                controllerFlag = false

                for (i in 1..45) {
                    msg.text = "일괄 번호 추첨이 진행중입니다. \n 잠시만 기다려 주세요 "
                }

            }

            // 타이머가 끝나면 호출
            override fun onFinish() {

                lottNoArr[idx].text = "${result_num[idx]}"
                //println("=========> ${result_num[idx]}")

                // 번호에 따른 볼의 색을 지정
                when (result_num[idx]) {
                    1, 2, 3, 4, 5, 6, 7, 8, 9, 10 -> lottNoArr[idx].setBackgroundResource(R.drawable.lott_ball_1_10)
                    11, 12, 13, 14, 15, 16, 17, 18, 19, 20 -> lottNoArr[idx].setBackgroundResource(R.drawable.lott_ball_11_20)
                    21, 22, 23, 24, 25, 26, 27, 28, 29, 30 -> lottNoArr[idx].setBackgroundResource(R.drawable.lott_ball_21_30)
                    31, 32, 33, 34, 35, 36, 37, 38, 39, 40 -> lottNoArr[idx].setBackgroundResource(R.drawable.lott_ball_31_40)
                    41, 42, 43, 44, 45 -> lottNoArr[idx].setBackgroundResource(R.drawable.lott_ball_41_45)
                }

                if (idx == 5) {
                    msg.text = "일괄 번호 추첨이 모두 끝났습니다. \n 다시 시작하시려면 재시작 버튼을 터치해 주세요"
                }

                controllerFlag = true
            }
        }

        countTimer.start()

    }
}
