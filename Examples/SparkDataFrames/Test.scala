import org.apache.spark.sql.SparkSession
val Spark=SparkSession.builder.getOrCreate()
val df=Spark.read.option("header","true").option("inferschema","true").csv("Netflix_2011_2016.csv")
//df.orderBy($"High".desc).show(1)

//df.filter($"High">500).count() * 1.0 / df.count() *100
val dfmonth=df.withColumn("Month", month(df("Date")))
dfmonth.select($"Month",$"Close").groupBy("Month").mean().orderBy("Month").show()
