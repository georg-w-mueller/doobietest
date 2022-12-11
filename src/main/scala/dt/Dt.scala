package doobietest.dt

import doobie._
import doobie.implicits._
import cats.effect.IO
import scala.concurrent.ExecutionContext

import cats.effect.unsafe.implicits.global

case class Country(code: String, name: String, population: Long)

object F:
  val xa =
    Transactor.fromDriverManager[IO](
      "org.postgresql.Driver",
      "jdbc:postgresql:world",
      "postgres",
      ""
    )

  val res = find("France").transact(xa).unsafeRunSync()

  def find(n: String): ConnectionIO[Option[Country]] =
    sql"select code, name, population from country where name = $n"
      .query[Country]
      .option
