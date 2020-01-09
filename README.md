# map sdk

        https://developers.google.com/maps/documentation/android-sdk/v3-client-migration
        maps sdk v3 still needs google play service

        // master-v3
        implementation 'com.google.android.gms:play-services-basement:15.0.1'
        implementation 'com.google.android.gms:play-services-base:15.0.1'
        implementation 'com.google.android.gms:play-services-gcm:15.0.1'
        implementation 'com.google.android.gms:play-services-location:15.0.1'

        // maps-v2
        implementation 'com.google.android.gms:play-services-location:17.0.0'
        implementation 'com.google.android.gms:play-services-maps:16.1.0'


- coolpad android 5.1

        - google service framework

                - minium android 4.4+
                https://www.apkmirror.com/apk/google-inc/google-services-framework/google-services-framework-4-4-2-1289630-release/google-services-framework-4-4-2-1289630-android-apk-download/  

        - Google Play Store is missing

                - latest version, no response if click login button
                https://www.apkmirror.com/apk/google-inc/google-play-store/google-play-store-18-3-13-release/google-play-store-18-3-13-all-0-pr-288027794-android-apk-download/

        - Google Play services is missing, UI has 'install' button 

                - minium 5.0+ not work, google play service has stopped
                https://www.apkmirror.com/apk/google-inc/google-play-services/google-play-services-20-1-03-release/google-play-services-20-1-03-020400-286399165-android-apk-download/

                - Min: Android 4.1(lowest version can be found), not work, google play service has stopped
                https://www.apkmirror.com/apk/google-inc/google-play-services/google-play-services-20-1-03-release/google-play-services-20-1-03-000300-286399165-android-apk-download/ 

                exception monitored from studio
                java.lang.SecurityException: Permission Denial: Component com.google.android.gms/.chimera.container.SharedModuleProvider requests FLAG_SINGLE_USER, but app does not hold android.permission.INTERACT_ACROSS_USERS

                - google play info (check google play xxx status, and update from pre-installed app store, in my case the store is offline)
                https://play-services-info.en.uptodown.com/android

                uninstall google play service first then click Play Store in this app to redirect to a app store to download
                in this coolpad phone it checks the pre-installed coolpad app store, but click install got 404 from http://www.coolmart.net.cn/components/

                - install wandoujia
                Google Play 服务(v7.8.99) (2015-09-02), install directly
                same exception, java.lang.SecurityException: caller uid 10119 lacks any of android.permission.INTERACT_ACROSS_USERS
                play info shows version is too old 

                GO谷歌安装器
                uninstall google play service and use this app to install -> 您的网络出现问题...

                GG服务框架安装器 -> 安装没有响应没有进度

                谷歌安装器 -> 您的设备尚未root，无法安装

                su谷歌安装器 -> 安装成功，open googlemap -> 您必须先更新goole play服务，然后才能运行googlemap 
                -> check info from studio -> Google Play services out of date.  Requires 11925000 but found 10084246
                -> click update from play stpre -> 提示谷歌服务已停止 -> uninstall existing and install play store from su谷歌安装器 
                -> open play store -> 谷歌服务已停止 -> Google Play services out of date.  Requires 11925000 but found 10084246
                
- huawei 4.2 (菜单按键 not work, cannot shutdown some app)

        - su谷歌安装器
        cannot find it from wandoujia / pp assist, apk from wandoujia web does not work

        - google play service
        install from wandoujia

        - play store
        https://www.apkmirror.com/apk/google-inc/google-play-store/google-play-store-18-3-13-release/google-play-store-18-3-13-all-0-pr-288027794-android-apk-download/

        - open googlemap
        you have to update google play service first
        Google Play services out of date.  Requires 11925000 but found 4325038

        - update play store
        login google account (need clear-internet) -> ...
        


