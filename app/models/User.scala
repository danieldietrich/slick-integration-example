package models

import scala.slick.session.Session
import scala.slick.integration._

case class User(activated: Boolean = false, email: String, forename: String = "", surname: String = "", password: String = "", imageUrl: Option[String] = None, id: Option[Long] = None) extends Entity[User] {
  def withId(id: Long): User = copy(id = Some(id))
}

trait UserComponent extends _Component { self: Profile =>

  import profile.simple._
  
  object Users extends Mapper[User]("user") {

    def activated = column[Boolean]("activated")
    def email = column[String]("email")
    def forename = column[String]("forename")
    def surname = column[String]("surname")
    def password = column[String]("password")
    def imageUrl = column[Option[String]]("image_url")

    def * = activated ~ email ~ forename ~ surname ~ password ~ imageUrl ~ id.? <> (User, User.unapply _)

    /**
     * Checks if User with a given email exists.
     */
    lazy val existsQuery = for {
      email <- Parameters[String]
      u <- Users if u.email.toLowerCase === email.toLowerCase
    } yield u.id.count > 0
    
    def exists(email: String): Boolean = db.withSession { implicit s: Session =>
      existsQuery(email).first
    }
    
    /**
     * Checks if a specific account is activated.
     */
    lazy val isActivatedQuery = for {
      email <- Parameters[String]
      u <- Users if u.email.toLowerCase === email.toLowerCase && u.activated === true
    } yield u.id.count > 0
    
    def isActivated(email: String): Boolean = db.withSession { implicit s: Session =>
      isActivatedQuery(email).first
    }
    
    /**
     * Retrieve a User by email.
     */
    lazy val findByEmailQuery = for {
      email <- Parameters[String]
      u <- Users if u.email.toLowerCase === email.toLowerCase
    } yield u
    
    def findByEmail(email: String): Option[User] = db.withSession { implicit s: Session =>
      findByEmailQuery(email).firstOption
    }

    /**
     * Authenticate a User.
     */
    lazy val authenticateQuery = for {
      (email, password) <- Parameters[(String, String)]
      u <- Users if u.email.toLowerCase === email.toLowerCase && u.password === password
    } yield u

    def authenticate(email: String, password: String): Option[User] = db.withSession { implicit s: Session =>
      authenticateQuery(email, password).firstOption
    }
    
  }
  
}
