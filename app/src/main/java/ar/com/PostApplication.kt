package ar.com

import android.app.Application
import android.content.SharedPreferences
import ar.com.postapp.common.RepositoryProvider

class PostApplication: Application() {
    lateinit var sharedPreferencesHelper: SharedPreferences

    companion object {
        lateinit var INSTANCE: PostApplication
            private set // It is used so that a value can’t be assigned from an external class.
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        sharedPreferencesHelper = getSharedPreferences("post_app_preferences", 0)

        RepositoryProvider.inject()
    }
}