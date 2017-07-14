package com.mecalco.hugo.marvelapplication.viewmodel.view

import com.mecalco.hugo.marvelapplication.model.Comics

/**
 * @author by hugo on 7/13/17.
 */
interface ComicDetailActivityView : IView{

    fun loadComicDetails(comics: Comics)

}