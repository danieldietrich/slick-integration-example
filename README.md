# Slick Integration Example #

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

_see app/models_

## Example Model Usage ##

_see test/DomainModelSpec.scala_
