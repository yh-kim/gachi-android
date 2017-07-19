package com.pickth.gachi.base

import android.app.Application
import com.kakao.auth.*

/**
 * Created by yonghoon on 2017-07-11.
 * Mail   : yonghoon.kim@pickth.com
 */
class BaseApplication: Application() {


    companion object {
        @Volatile private var instance: BaseApplication? = null

        private class KakaoSDKAdapter: KakaoAdapter() {
            override fun getApplicationConfig(): IApplicationConfig {
                return IApplicationConfig {
                    BaseApplication.instance
                }
            }

            override fun getSessionConfig(): ISessionConfig {
                return object:ISessionConfig {
                    /**
                     * Kakao SDK에서 사용하는 WebBiew에서 email 값을 저장할지 여부
                     */
                    override fun isSaveFormData(): Boolean = true

                    /**
                     * 로그인 시 인증받을 타입을 지정한다. 지정하지 않을 시 가능한 모든 옵션이 지정
                     *
                     * 1. KAKAO_TALK
                     * 2. KAKAO_STORY
                     * 3. KAKAO_ACCOUNT : 웹뷰 DIalog를 통해 카카오 계정 연결
                     * 4. KAKAO_TALK_EXCLUDE_NATIVE_LOGIN : 카카오톡으로만 로그인을 유도하고 싶으면서 계정이 없을때 계정생성을 위한 버튼도 같이 제공을 하고 싶다면 지정.KAKAO_TALK과 중복 지정불가.
                     * 5. KAKAO_LOGIN_ALL : 모든 로그인 방식 사용
                     */
                    override fun getAuthTypes(): Array<AuthType> {
                        return arrayOf(AuthType.valueOf(0))
                    }

                    override fun isSecureMode(): Boolean = false

                    override fun getApprovalType(): ApprovalType = ApprovalType.INDIVIDUAL

                    override fun isUsingWebviewTimer(): Boolean = false

                }
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        KakaoSDK.init(KakaoSDKAdapter())
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }
}