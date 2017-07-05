package com.mecalco.hugo.marvelapplication.api

import com.mecalco.hugo.marvelapplication.model.Characters
import com.mecalco.hugo.marvelapplication.model.Comics
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author by hugo on 5/24/17.
 */
interface MarvelApi {

    //https://gateway.marvel.com/v1/public/characters?ts=1&apikey=fb9cf622de091ac20051e62a51c81149&hash=b68e7aeef8e1843eb3b2958aa737c743
    @GET("characters?orderBy=-modified&ts=1&hash=b68e7aeef8e1843eb3b2958aa737c743")
    fun getCharactersList(
            @Query("apikey") apiKey: String,
            @Query("limit") limit: Int): Observable<Characters>

    //https://gateway.marvel.com/v1/public/characters/1009148?ts=1&apikey=fb9cf622de091ac20051e62a51c81149&hash=b68e7aeef8e1843eb3b2958aa737c743
    @GET("characters/{characterId}?ts=1&hash=b68e7aeef8e1843eb3b2958aa737c743")
    fun getCharacterDetail(
            @Path("characterId") characterId: Int,
            @Query("apikey") apiKey: String): Flowable<Characters>

    //https://gateway.marvel.com/v1/public/characters/1010338/comics?ts=1&apikey=fb9cf622de091ac20051e62a51c81149&hash=b68e7aeef8e1843eb3b2958aa737c743
    @GET("characters/{characterId}/comics?ts=1&hash=b68e7aeef8e1843eb3b2958aa737c743")
    fun getComicsByCharacter(
            @Path("characterId") characterId: Int,
            @Query("apikey") apiKey: String): Flowable<Comics>

}