import CRUD.MongoScala.{collection, insertAndCount}
import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala._
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala._
import PersonDB.Helpers._
import PersonDB.Person


import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}

import scala.util.{Failure, Success}

object PersonHelp extends App {


  val codecRegistry = fromRegistries(fromProviders(classOf[Person]), DEFAULT_CODEC_REGISTRY)

  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase("mydb1").withCodecRegistry(codecRegistry)
  val collection: MongoCollection[Person] = database.getCollection("test")




  val people: Seq[Person] = Seq(
    Person("Charles", "Babbage"),
    Person("George", "Boole"),
    Person("Gertrude", "Blanch"),
    Person("Grace", "Hopper"),
    Person("Ida", "Rhodes"),
    Person("Jean", "Bartik"),
    Person("John", "Backus"),
    Person("Lucy", "Sanders"),
    Person("Tim", "Berners Lee"),
    Person("Zaphod", "Hello")
  )

  collection.insertMany(people).printResults()

  collection.find().printResults()



  // addDocument(Person("Ahmed", "Fouad"))

//  collection.find().printResults()



  //collection.find().first().printHeadResult()


}