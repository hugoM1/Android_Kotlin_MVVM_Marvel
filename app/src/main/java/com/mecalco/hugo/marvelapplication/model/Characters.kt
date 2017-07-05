package com.mecalco.hugo.marvelapplication.model

/**
 * @author by hugo on 5/24/17.
 */
data class Characters constructor(var code: Int = 0,
                                  var status: String? = null,
                                  var copyright: String? = null,
                                  var attributionText: String? = null,
                                  var attributionHTML: String? = null,
                                  var etag: String? = null,
                                  var data: DataBean? = null) {

    class DataBean {

        var offset: Int = 0
        var limit: Int = 0
        var total: Int = 0
        var count: Int = 0
        var results: List<ResultsBean>? = null

        class ResultsBean {

            var id: Int = 0
            var name: String? = null
            var description: String? = null
            var modified: String? = null
            var thumbnail: ThumbnailBean? = null
            var resourceURI: String? = null
            var comics: ComicsBean? = null
            var series: SeriesBean? = null
            var stories: StoriesBean? = null
            var events: EventsBean? = null
            var urls: List<UrlsBean>? = null

            class ThumbnailBean {
                /**
                 * path : http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784
                 * extension : jpg
                 */

                var path: String? = null
                var extension: String? = null
            }

            class ComicsBean {

                var available: Int = 0
                var collectionURI: String? = null
                var returned: Int = 0
                var items: List<ItemsBean>? = null

                class ItemsBean {
                    /**
                     * resourceURI : http://gateway.marvel.com/v1/public/comics/21366
                     * name : Avengers: The Initiative (2007) #14
                     */

                    var resourceURI: String? = null
                    var name: String? = null
                }
            }

            class SeriesBean {

                var available: Int = 0
                var collectionURI: String? = null
                var returned: Int = 0
                var items: List<ItemsBeanX>? = null

                class ItemsBeanX {
                    /**
                     * resourceURI : http://gateway.marvel.com/v1/public/series/1945
                     * name : Avengers: The Initiative (2007 - 2010)
                     */

                    var resourceURI: String? = null
                    var name: String? = null
                }
            }

            class StoriesBean {

                var available: Int = 0
                var collectionURI: String? = null
                var returned: Int = 0
                var items: List<ItemsBeanXX>? = null

                class ItemsBeanXX {
                    /**
                     * resourceURI : http://gateway.marvel.com/v1/public/stories/19947
                     * name : Cover #19947
                     * type : cover
                     */

                    var resourceURI: String? = null
                    var name: String? = null
                    var type: String? = null
                }
            }

            class EventsBean {
                /**
                 * available : 1
                 * collectionURI : http://gateway.marvel.com/v1/public/characters/1011334/events
                 * items : [{"resourceURI":"http://gateway.marvel.com/v1/public/events/269","name":"Secret Invasion"}]
                 * returned : 1
                 */

                var available: Int = 0
                var collectionURI: String? = null
                var returned: Int = 0
                var items: List<ItemsBeanXXX>? = null

                class ItemsBeanXXX {
                    /**
                     * resourceURI : http://gateway.marvel.com/v1/public/events/269
                     * name : Secret Invasion
                     */

                    var resourceURI: String? = null
                    var name: String? = null
                }
            }

            class UrlsBean {
                /**
                 * type : detail
                 * url : http://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_source=fb9cf622de091ac20051e62a51c81149
                 */

                var type: String? = null
                var url: String? = null
            }
        }

    }
}