package models

import scala.slick.driver.ExtendedProfile
import scala.slick.integration._
import scala.slick.session.Database

case class Product(name: String, description: String, id: Option[Long] = None) extends Entity[Product] {
  def withId(id: Long): Product = copy(id = Some(id))
}

trait ProductComponent extends _Component { self: Profile =>

  object Products extends Mapper[Product]("product") {

    def name = column[String]("name")
    def description = column[String]("description")

    def * = name ~ description ~ id.? <> (Product, Product.unapply _)

  }

}
