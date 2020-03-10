package PersonDB
import org.mongodb.scala.{Completed, Document, Observer}


import java.util.concurrent.TimeUnit

import org.mongodb.scala.{Document, Observable}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Helpers {

  implicit class DocumentObservable[C](val observable: Observable[Document]) extends ImplicitObservable[Document] {
    override val converter: (Document) => String = (doc) => doc.toJson
  }

  implicit class GenericObservable[C](val observable: Observable[C]) extends ImplicitObservable[C] {
    override val converter: (C) => String = (doc) => doc.toString
  }

  trait ImplicitObservable[C] {
    val observable: Observable[C]
    val converter: (C) => String

    def results(): Seq[C] = Await.result(observable.toFuture(), Duration(10, TimeUnit.SECONDS))
    def headResult() = Await.result(observable.head(), Duration(10, TimeUnit.SECONDS))
    def printResults(initial: String = ""): Unit = {
      if (initial.length > 0) print(initial)
      results().foreach(res => println(converter(res)))
    }
    def printHeadResult(initial: String = ""): Unit = println(s"${initial}${converter(headResult())}")
  }


  val observeInsert: Observer[Completed] = new Observer[Completed] {
    override def onNext(result: Completed): Unit = println(s"Inserted")

    override def onError(error: Throwable): Unit = println(s"Failed $error")

    override def onComplete(): Unit = println("Completed")
  }

}
