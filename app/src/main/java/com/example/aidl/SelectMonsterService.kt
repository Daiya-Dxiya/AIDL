package com.example.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlin.random.Random


class SelectMonsterService : Service() {
    private val TAG: String = this::class.java.name
    private val level1Monsters = arrayOf(
        "アンジャナフ",
        "ジュラトドス",
        "トビカガチ",
        "トビカガチ亜種",
        "パオウルムー",
        "パオウルムー亜種",
        "バフバロ",
        "プケプケ",
        "プケプケ亜種",
        "ブラントドス",
        "ボルボロス",
        "ラドバルキン",
        "リオレイア",
        "リオレイア亜種"
    )
    private val level2Monsters = arrayOf(
        "アンジャナフ亜種",
        "凍て刺すレイギエナ",
        "ヴォルガノス",
        "ウラガンキン",
        "オドガロン",
        "オドガロン亜種",
        "傷ついたイャンガルルガ",
        "ジンオウガ",
        "ディアブロス",
        "ディアブロス亜種",
        "ティガレックス",
        "ティガレックス亜種",
        "ディノバルド",
        "ディノバルド亜種",
        "ナルガクルガ",
        "ブラキディオス",
        "ベリオロス",
        "リオレウス",
        "リオレウス亜種",
        "レイギエナ"
    )
    private val level3Monsters = arrayOf(
        "イヴェルカーナ",
        "怒り喰らうイビルジョー",
        "キリン",
        "クシャルダオラ",
        "紅蓮滾るバゼルギウス",
        "死を纏うヴァルハザク",
        "テオ・テスカトル",
        "ナナ・テスカトリ",
        "ネロミェール",
        "リオレウス希少種",
        "リオレイア希少種",
        "ラージャン"
    )

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind")
        return binder
    }

    private val binder = object : ISelectMonsterService.Stub() {
        override fun select(levels: Int): String {
            var targetMonsters:Array<String> = emptyArray()
            if(levels and 1 !== 0)targetMonsters += level1Monsters
            if(levels and 2 !== 0)targetMonsters += level2Monsters
            if(levels and 4 !== 0)targetMonsters += level3Monsters
            Log.d(TAG,targetMonsters[0])
            return targetMonsters[Random.nextInt(0, targetMonsters.size)]
        }
    }
}
