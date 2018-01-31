package com.scala.spark

import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import scala.tools.scalap.Main
/**
 * 不适用目前业务需求
 */
object SparkStreaming {
  def count(){
    val URL_NAME = "192.168.0.120";
    val conf = new SparkConf().setAppName("work count").setMaster("local[1]")
    val ssc = new StreamingContext(conf,Seconds(10))
    val lines = ssc.socketTextStream(URL_NAME, 4040)
    val file = ssc.textFileStream("J:\\testSpark\\testSpark.txt");
    
    val words = lines.flatMap(_.split(" "))
    print("xxxxxxxxxxxx"+words.toString())
    words.print()
    val pairs = words.map(word => (word,1))
    val wordCounts = pairs.reduceByKey(_+_)
    print("xxxxxxxxxxxx"+wordCounts.toString())
    wordCounts.print()
    ssc.start()
    ssc.awaitTermination()
  }
 
  
  def main(args: Array[String]): Unit = {
    count()
  }
}
































