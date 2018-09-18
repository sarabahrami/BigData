
import org.apache.spark.sql.SparkSession
val spark=SparkSession.builder().getrOrCreate()
val df=spark.read.option("header", "true").option("inferschema","true").csv("Sales.csv")
df.printSchema()
df.show()
//group by specific company
df.groupBy("Company").mean().show()

df.groupBy("Company").count.show()
df.groupBy("Company").max().show()
df.groupBy("Company").min().show()
df.groupBy("Company").sum().show()

// not grouping by company. statistics for all the data
df.select(countDistinct("Sales")).show()
df. select(sumDistinct("Sales")).show()
df.select(stddev("sales")).show()
df.select(variance("sales")).show()
//if you want the result as array
df.select(countDistinct("Sales")).collect()
df.show()
//Ascending orderBy
df.orderBy("Sales").show()
//Descending orderBy
df.orderBy($"Sales".desc).show()
