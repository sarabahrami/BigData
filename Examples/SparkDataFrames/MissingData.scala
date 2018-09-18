import org.apache.spark.sql.SparkSession
val spark=SparkSession.builder.getOrCreate()
val df=spark.read.option("header","true").option("inferschema","true").csv("ContainsNull.csv")
df.printSchema()
// for those values that are missing we have three options:
// 1. drop  fill  replace
df.show()
df.na.drop().show
// you can provide an integer which means if a row contains less
//drop rows that have less than a minimum of non null values
// we are droping any row that have less than int non null values
df.na.drop(1).show
df.na.drop(2).show
df.na.drop(3).show
// it only fills the data column that have the same datatype numberical(integer or duoble)
//for example 100 is integer
df.na.fill(100).show()
df.na.fill("Missing Name").show()
// in order to specify the exact column that we want to fill
df.na.fill("NewName",Array("Name")).show()
df.na.fill(200, Array("Sales")).show()
//
df.na.fill(400.5, Array("Sales")).show()
df.na.fill("Missing name", Array("Name")).show()
// how we cam have both at the same time?
val df2=df.na.fill(400.5, Array("Sales"))
df2.na.fill("Missing name", Array("Name")).show()
