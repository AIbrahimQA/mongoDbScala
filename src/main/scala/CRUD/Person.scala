////import MongoScala.{collection, insertAndCount}
//import org.mongodb.scala.bson.ObjectId
//import org.mongodb.scala._
//import org.mongodb.scala.bson.codecs.Macros._
//import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
//import org.mongodb.scala._
//
//
//import scala.util.{Failure, Success}
//import scala.concurrent.ExecutionContext.Implicits.global
//
//import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
//
//import scala.util.{Failure, Success}
//
//object Person{
//  def apply(firstName: String, lastName: String): Person =
//    Person(new ObjectId(), firstName, lastName)
//}
//case class Person(_id: ObjectId, firstName: String, lastName: String)
//
//val codecRegistry = fromRegistries(fromProviders(classOf[Person]), DEFAULT_CODEC_REGISTRY )
//
//val mongoClient: MongoClient = MongoClient()
//val database: MongoDatabase = mongoClient.getDatabase("mydb").withCodecRegistry(codecRegistry)
//val collection: MongoCollection[Person] = database.getCollection("test")
//
//
//val person: Person = Person("Ahmed", "Ibrahim")
//
//val observable: Observable[Completed] = collection.insertOne(person)
//
//insertAndCount.head() onComplete {
//  case Success(value) => println(value)
//  case Failure(exception) => println(x = "error" + exception.getMessage)
//}
//
//
//collection.find().first().head() onComplete {
//  case Success(value) => println(value)
//  case Failure(exception) => println(x = "error" + exception.getMessage)
//}
//Thread.sleep(5000)