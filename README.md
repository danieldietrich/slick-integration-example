# Slick Integration Example #

## Usage ##

Currently there are only tests. Start ```play test```.

Also have a look at the [schema generator](https://github.com/danieldietrich/slick-integration-example/blob/master/test/EvolutionsGenerator.scala). Call it as Scala standalone app.

## Dependencies ##

project/Build.scala

```scala
jdbc,
"com.typesafe" % "slick_2.10.0-RC5" % "0.11.2",
"net.danieldietrich" %% "slick-integration" % "1.0-SNAPSHOT"
```

```scala
resolvers += "Daniel's Repository" at "http://danieldietrich.net/repository/snapshots"
```

## Driver Configuration ##

conf/application.conf

```scala
db {
  default {
    driver=com.mysql.jdbc.Driver
    url="jdbc:mysql://localhost/slicktest?characterEncoding=UTF-8"
    user="root"
    password=""
  }
  test {
    driver=org.h2.Driver
    url="jdbc:h2:mem:slicktest"
    user="sa"
    password=""
  }
}
```

```scala
slick {
  default {
    driver=scala.slick.driver.MySQLDriver
  }
  test {
    driver=scala.slick.driver.H2Driver
  }
}
```

## Model Implementation ##

see [app/models](https://github.com/danieldietrich/slick-integration-example/tree/master/app/models)

## Example Model Usage ##

see [test/DomainModelSpec.scala](https://github.com/danieldietrich/slick-integration-example/blob/master/test/DomainModelSpec.scala)
