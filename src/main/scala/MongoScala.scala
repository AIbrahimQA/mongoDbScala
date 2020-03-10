import org.mongodb.scala._


import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global


object MongoScala extends App {

  val mongoClient: MongoClient = MongoClient()

  val database: MongoDatabase = mongoClient.getDatabase("mydb")


  val collection: MongoCollection[Document] = database.getCollection("test");


  val doc: Document = Document("_id" -> 0, "name" -> "MongoDB", "type" -> "database",
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

  insertAndCount.head() onComplete {
    case Success(value) => println(value)
    case Failure(exception) => println(x = "error" + exception.getMessage)
  }


  collection.find().first().head() onComplete {
    case Success(value) => println(value)
    case Failure(exception) => println(x = "error" + exception.getMessage)
  }
  Thread.sleep(5000)


}
