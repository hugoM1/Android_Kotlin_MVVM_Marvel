package com.mecalco.hugo.marvelapplication.viewmodel.view

import com.mecalco.hugo.marvelapplication.model.Characters
import com.mecalco.hugo.marvelapplication.model.Comics

/**
 * @author by hugo on 6/23/17.
 */
interface DetailActivityView : IView {

    fun loadHeroDetails(character: Characters.DataBean.ResultsBean)

    fun loadCharacterComics(comics: Comics)

}