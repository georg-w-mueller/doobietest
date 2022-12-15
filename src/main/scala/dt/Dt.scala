package doobietest.dt

import doobie._
import doobie.implicits._
import cats.effect.IO
import scala.concurrent.ExecutionContext

import cats.effect.unsafe.implicits.global
import cats.instances.string
// import java.sql.DriverManager

case class Country(code: String, name: String, population: Long)

case class CountryC(id: String, name: String)

object F:
  val xa =
    Transactor.fromDriverManager[IO](
      "org.postgresql.Driver",
      "jdbc:postgresql:world",
      "postgres",
      ""
    )

  // val conn =
  //   DriverManager.getConnection(
  // //
  // "jdbc:crate://192.168.1.101:4201/guestbook?connectTimeout=50&socketTimeout=50",
  //     // // ,192.168.1.101:4202,192.168.1.101:4203
  // "jdbc:postgresql://192.168.1.101:15432/guestbook?user=crate&connectTimeout=50&socketTimeout=50"
  // // ,192.168.1.101:4202,192.168.1.101:4203
  //     // "crate",
  //     // ""
  //   );

  val cserver1 = "192.168.1.101:15432"
  val cserver2 = "192.168.1.101:15433"
  val cserver3 = "192.168.1.101:15434"

  val cluster = Array(cserver1, cserver2, cserver3)
  val clstring =
    cluster.drop(1).foldLeft(cluster(0))((r: String, e: String) => s"$r,$e")

  val schema = "guestbook"
  val user   = "crate"
  val passwd = ""
  val topars = "connectTimeout=50&socketTimeout=50"

  val xacrate =
    Transactor.fromDriverManager[IO](
      "org.postgresql.Driver",
      s"jdbc:postgresql://$clstring/$schema?$topars",
      user,
      passwd
    )

  val res = find("France").transact(xa).unsafeRunSync()

  val resc = findcrate("USA").transact(xacrate).unsafeRunSync()

  def find(n: String): ConnectionIO[Option[Country]] =
    sql"select code, name, population from country where name = $n"
      .query[Country]
      .option

  def findcrate(n: String): ConnectionIO[Option[CountryC]] =
    sql"select id, name from guestbook.countries where id = $n"
      .query[CountryC]
      .option
end F
