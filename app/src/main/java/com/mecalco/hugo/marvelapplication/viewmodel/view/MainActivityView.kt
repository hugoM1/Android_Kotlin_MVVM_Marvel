package com.mecalco.hugo.marvelapplication.viewmodel.view

import com.mecalco.hugo.marvelapplication.model.Characters

/**
 * @author by hugo on 5/24/17.
 */
interface MainActivityView : IView {

    fun loadHeroes(characters: Characters)

}