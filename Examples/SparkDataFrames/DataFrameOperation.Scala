//Actions of data frames: Filter, Show,count

import org.apache.spark.sql.SparkSession
val spark=SparkSession.builder().getOrCreate()
val df=spark.read.option("header","true").option("inferschema","true").csv("CitiGroup2006_2008.csv")
df.head(5)
for (row <- df.head(5)) {
  println(row)
}
df.printSchema()
//filter out the data who meets the criteria
// to use $ scala notation needs to import saprk.implicits
import spark.implicits._
filter with scala notation
df.filter($"Close">480).show()
//filter with sql notation
df.filter("Close> 480").show()
//scala
df.filter($"Close" <480 && $"High"< 480).show()
//SQL
df.filter("Close < 480 AND High < 480").show()
// instead of showing the result we can collect them to an object. it returns it
// as array which you can travers and do whatever you wanna do
val result=df.filter($"Close" <480 && $"High"< 480).collect()
// how many rows have this condition?
val result=df.filter($"Close" <480 && $"High"< 480).count()
//in order to filter a row for an specific value == is not working you should use triple = (===)
df.filter($"High" ===484.40).show() // will not work
df.filter($"High" ===484.40).show() // this works
// in sql you won't have that problem
df.filter ("High = 484.40").show ()
// example to get pierson correlation between two columns
df.select(corr("High","Low")).show()
