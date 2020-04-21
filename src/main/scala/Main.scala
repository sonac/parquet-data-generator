import com.danielasfregola.randomdatagenerator.RandomDataGenerator
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import org.scalacheck._

case class MyCustomObject(id: Long, name: String, age: Int)
case class Foo(
    foint: Int,
    name: String,
    barstring: String,
    boo: Boolean,
    lfoo: List[String]
)

object Main extends RandomDataGenerator {
  def main(args: Array[String]): Unit = {

    implicit val arbitraryFoo: Arbitrary[Foo] = Arbitrary {
      for {
        foint <- Arbitrary.arbInt.arbitrary
        name <- Gen.oneOf("Kevin", "Martin", "John")
        barstring <- Gen.alphaStr
        boo <- Arbitrary.arbBool.arbitrary
        lfoo <- Gen.containerOf[List, String](Gen.alphaStr)
      } yield Foo(foint, name, barstring, boo, lfoo)
    }

    val randomFoo = random[Foo](3)
    println(randomFoo)
    val path =
      "file:///Users/andriisumko/git/parquet-data-generator/data/foo.parquet"
    val spark = SparkSession.builder.appName("Simple Application").getOrCreate()
    import spark.implicits._
    val df = randomFoo.toDF()

    df.show()
    spark.stop()
  }
}
