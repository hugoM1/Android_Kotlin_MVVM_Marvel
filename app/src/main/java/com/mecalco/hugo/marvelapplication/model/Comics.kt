package com.mecalco.hugo.marvelapplication.model

/**
 * @author by hugo on 6/28/17.
 */

data class Comics constructor(var code: Int = 0,
                              var status: String? = null,
                              var copyright: String? = null,
                              var attributionText: String? = null,
                              var attributionHTML: String? = null,
                              var etag: String? = null,
                              var data: DataBean? = null){
    class DataBean {

        var offset: Int = 0
        var limit: Int = 0
        var total: Int = 0
        var count: Int = 0
        var results: List<ResultsBean>? = null

        class ResultsBean {

            var id: Int = 0
            var digitalId: Int = 0
            var title: String? = null
            var issueNumber: Float = Float.MAX_VALUE
            var variantDescription: String? = null
            var description: String? = null
            var modified: String? = null
            var isbn: String? = null
            var upc: String? = null
            var diamondCode: String? = null
            var ean: String? = null
            var issn: String? = null
            var format: String? = null
            var pageCount: Int = 0
            var resourceURI: String? = null
            var series: SeriesBean? = null
            var thumbnail: ThumbnailBean? = null
            var creators: CreatorsBean? = null
            var characters: CharactersBean? = null
            var stories: StoriesBean? = null
            var events: EventsBean? = null
            var textObjects: List<TextObjectsBean>? = null
            var urls: List<UrlsBean>? = null
            var variants: List<*>? = null
            var collections: List<*>? = null
            var collectedIssues: List<*>? = null
            var dates: List<DatesBean>? = null
            var prices: List<PricesBean>? = null
            var images: List<ImagesBean>? = null

            class SeriesBean {
                /**
                 * resourceURI : http://gateway.marvel.com/v1/public/series/22551
                 * name : The Mighty Captain Marvel (2016 - Present)
                 */

                var resourceURI: String? = null
                var name: String? = null
            }

            class ThumbnailBean {
                /**
                 * path : http://i.annihil.us/u/prod/marvel/i/mg/9/f0/594c2ad6839bf
                 * extension : jpg
                 */

                var path: String? = null
                var extension: String? = null
            }

            class CreatorsBean {
                /**
                 * available : 4
                 * collectionURI : http://gateway.marvel.com/v1/public/comics/61431/creators
                 * items : [{"resourceURI":"http://gateway.marvel.com/v1/public/creators/11841","name":"Sana Amanat","role":"editor"},{"resourceURI":"http://gateway.marvel.com/v1/public/creators/12982","name":"Vc Joe Caramagna","role":"letterer"},{"resourceURI":"http://gateway.marvel.com/v1/public/creators/12948","name":"Margaret Stohl","role":"writer"},{"resourceURI":"http://gateway.marvel.com/v1/public/creators/12832","name":"Elizabeth Torque","role":"inker (cover)"}]
                 * returned : 4
                 */

                var available: Int = 0
                var collectionURI: String? = null
                var returned: Int = 0
                var items: List<ItemsBean>? = null

                class ItemsBean {
                    /**
                     * resourceURI : http://gateway.marvel.com/v1/public/creators/11841
                     * name : Sana Amanat
                     * role : editor
                     */

                    var resourceURI: String? = null
                    var name: String? = null
                    var role: String? = null
                }
            }

            class CharactersBean {
                /**
                 * available : 4
                 * collectionURI : http://gateway.marvel.com/v1/public/comics/61431/characters
                 * items : [{"resourceURI":"http://gateway.marvel.com/v1/public/characters/1010370","name":"Alpha Flight"},{"resourceURI":"http://gateway.marvel.com/v1/public/characters/1010338","name":"Captain Marvel (Carol Danvers)"},{"resourceURI":"http://gateway.marvel.com/v1/public/characters/1010705","name":"Spectrum"},{"resourceURI":"http://gateway.marvel.com/v1/public/characters/1010803","name":"Ultimates"}]
                 * returned : 4
                 */

                var available: Int = 0
                var collectionURI: String? = null
                var returned: Int = 0
                var items: List<ItemsBeanX>? = null

                class ItemsBeanX {
                    /**
                     * resourceURI : http://gateway.marvel.com/v1/public/characters/1010370
                     * name : Alpha Flight
                     */

                    var resourceURI: String? = null
                    var name: String? = null
                }
            }

            class StoriesBean {
                /**
                 * available : 2
                 * collectionURI : http://gateway.marvel.com/v1/public/comics/61431/stories
                 * items : [{"resourceURI":"http://gateway.marvel.com/v1/public/stories/133291","name":"cover from Captain Marvel (2016) #6","type":"cover"},{"resourceURI":"http://gateway.marvel.com/v1/public/stories/133292","name":"story from Captain Marvel (2016) #6","type":"interiorStory"}]
                 * returned : 2
                 */

                var available: Int = 0
                var collectionURI: String? = null
                var returned: Int = 0
                var items: List<ItemsBeanXX>? = null

                class ItemsBeanXX {
                    /**
                     * resourceURI : http://gateway.marvel.com/v1/public/stories/133291
                     * name : cover from Captain Marvel (2016) #6
                     * type : cover
                     */

                    var resourceURI: String? = null
                    var name: String? = null
                    var type: String? = null
                }
            }

            class EventsBean {
                /**
                 * available : 0
                 * collectionURI : http://gateway.marvel.com/v1/public/comics/61431/events
                 * items : []
                 * returned : 0
                 */

                var available: Int = 0
                var collectionURI: String? = null
                var returned: Int = 0
                var items: List<*>? = null
            }

            class TextObjectsBean {
                /**
                 * type : issue_solicit_text
                 * language : en-us
                 * text : SECRET EMPIRE TIE-IN! Captain Marvel and her crew are up against the ropes as the full force of the Chitauri fleet bears down on Alpha Flight Space Station. Can Carol find a way to inspire her ranks — including the battalion of young cadets — to rise to the challenge of protecting the planet?
                 */

                var type: String? = null
                var language: String? = null
                var text: String? = null
            }

            class UrlsBean {
                /**
                 * type : detail
                 * url : http://marvel.com/comics/issue/61431/the_mighty_captain_marvel_2016_6?utm_campaign=apiRef&utm_source=fb9cf622de091ac20051e62a51c81149
                 */

                var type: String? = null
                var url: String? = null
            }

            class DatesBean {
                /**
                 * type : onsaleDate
                 * date : 2017-06-28T00:00:00-0400
                 */

                var type: String? = null
                var date: String? = null
            }

            class PricesBean {
                /**
                 * type : printPrice
                 * price : 3.99
                 */

                var type: String? = null
                var price: Double = 0.toDouble()
            }

            class ImagesBean {
                /**
                 * path : http://i.annihil.us/u/prod/marvel/i/mg/9/f0/594c2ad6839bf
                 * extension : jpg
                 */

                var path: String? = null
                var extension: String? = null
            }
        }
    }
}
