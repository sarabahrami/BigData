import org.apache.spark.sql.SparkSession
val Spark=SparkSession.builder.getOrCreate()
val df=Spark.read.option("header","true").option("inferschema","true").csv("Netflix_2011_2016.csv")
df.printSchema()
df.head(5)
//df.describe()

// Create a new dataframe with a column called HV Ratio that
// is the ratio of the High Price versus volume of stock traded
// for a day.
//return back the max of high price
//df.select(max("High")).show()
val dfnew=df.withColumn("HV Ratio",df("High")/df("Volume"))
dfnew.printSchema()
dfnew.head(5)

 //What day had the Peak High in Price?
 df.select(max("High")).show()
df.filter($"High"===716.159996).show()   // answer: 2015-07-13 00:00:00
// eaiser way
df.orderBy($"High".desc).show(1)
//What is the mean of the Close column?
df.select(mean("Close")).show()

// What is the max and min of the Volume column?
df.select(max("Volume")).show()
df.select(min("Volume")).show()

// For Scala/Spark $ Syntax

// How many days was the Close lower than $ 600?
df.filter($"Close"<600).count()

// What percentage of the time was the High greater than $500 ?
df.filter($"High">500).count() * 1.0 / df.count() *100
// What is the Pearson correlation between High and Volume?
df.select(corr("High","Volume")).show()
// What is the max High per year?
//  first step creat a year column
val df2=df.withColumn("Year",year(df("Date")))
df2.select($"Year",$"High").groupBy("Year").max().orderBy("Year").show()
// What is the average Close for each Calender Month?
val dfmonth=df.withColumn("Month", month(df("Date")))
dfmonth.select($"Month",$"Close").groupBy("Month").mean().orderBy("Month").show()
