package com.target.dealbrowserpoc.dealbrowser.api.models

/**
 * Created by Juan Mendez on 2/13/18.

 *  "_id": "548917fa8dd740cdeaef2df5",
 *  aisle": "c19",
 *  "description": "description",
 *  "guid": "2a792523-a8ad-440c-a003-1a09a7b74309",
 *  "image": "http://lorempixel.com/400/400/",
 *  "index": 4,
 *  "price": "$121.77",
 *  "salePrice": null,
 *  "title": "title"
 */
data class Deal(var _id:String, var aisle:String, var description:String,
                var guid:String, var image:String, var index:Int, var price:String,
                var salesPrice:String?=null, var title:String )