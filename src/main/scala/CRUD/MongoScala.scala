package CRUD
import org.mongodb.scala._
import CRUD.Helpers._

import org.mongodb.scala.{Completed, Document, MongoClient, MongoCollection, MongoDatabase, Observable, Observer}

object MongoScala extends App {

  val mongoClient: MongoClient = MongoClient()

  val database: MongoDatabase = mongoClient.getDatabase("mydb")


  val collection: MongoCollection[Document] = database.getCollection("test");


  val doc: Document = Document("_id" -> 0, "name" -> "Ahmed", "type" -> "database",
    "count" -> 1, "info" -> Document("x" -> 203, "y" -> 102))


  val observable: Observable[Completed] = collection.insertOne(doc)
  observable.subscribe(new Observer[Completed] {

    override def onNext(result: Completed): Unit = println("Inserted")

    override def onError(e: Throwable): Unit = println("Failed")

    override def onComplete(): Unit = println("Completed")
  })

  val documents = (1 to 100) map { i: Int => Document("i" -> i) }
  val insertObservable = collection.insertMany(documents)


  val insertAndCount = for {
    insertResult <- insertObservable
    countResult <- collection.countDocuments()
  } yield countResult


  collection.find().first().printHeadResult()

  collection.find().printResults()

  // insertAndCount.head() onComplete {
  //   case Success(value) => println(value)
  //    case Failure(exception) => println(x = "error" + exception.getMessage)
  // }


  //  collection.find().head() onComplete {
  //   case Success(value) => println(value)
  //  case Failure(exception) => println(x = "error" + exception.getMessage)
  //}
  Thread.sleep(5000)


}
