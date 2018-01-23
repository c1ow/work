package com.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

/**
 * spark 测试
 * @author 11273
 *
 */
public class SparkTest {
	public static void getCount() throws InterruptedException {
		SparkConf sparkConf = new SparkConf();
		sparkConf.setAppName("work count");
		sparkConf.setMaster("spark://192.168.0.120:4040");
		JavaStreamingContext jsc = new JavaStreamingContext(sparkConf,Durations.seconds(1));
		JavaReceiverInputDStream<String> lines = jsc.socketTextStream("192.168.0.120", 4040);
		JavaDStream<String> errorLines = lines.filter(new Function<String, Boolean>() {

			public Boolean call(String arg0) throws Exception {
				
				return arg0.contains("error");
			}
			
		});
		errorLines.print();
		jsc.start();
		jsc.awaitTermination();
	}
	public static void main(String[] args) throws InterruptedException {
		getCount();
	}
}
