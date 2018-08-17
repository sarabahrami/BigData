import org.apache.spark.sql.SparkSession

val spark= SparkSession.builder().getOrCreate()

val df = spark.read.csv("CitiGroup2006_2008.csv")
val df = spark.read.option("header","true").option("inferSchema","true").csv("CitiGroup2006_2008.csv")
df.head(5)

for (row <- df.head(5)){
  println(row)
}

df.columns
df.describe().show()
df.select("Volume").show()
df.select($"Date",$"Close").show()
val newdf=df.withColumn("HighPlusLow",df("High")+df("low"))
newdf.printSchema()
newdf.select(newdf("HighPlusLow").as("HPL")).show()
