package service

import util.LoggingComponent
import org.apache.spark.{SparkConf, SparkContext}
import com.tuplejump.calliope.utils.RichByteBuffer._
import com.tuplejump.calliope.Implicits._
import com.tuplejump.calliope.CasBuilder
import java.util.{UUID => JUUID}
import org.apache.spark.SparkContext._
import com.tuplejump.calliope.Types.{ThriftRowMap, ThriftRowKey}


/**
 * Created by mwielocha on 06.07.2014.
 */
trait AnalyticsServiceComponent {

  val analyticsService: AnalyticsService

  class AnalyticsService extends LoggingComponent {

    implicit def ThriftRowKeyToJUUID(rowKey: ThriftRowKey): JUUID = {
      JUUID.fromString(rowKey.asCharBuffer().toString)
    }

    implicit def ThriftRowMapToJUUID(rowMap: ThriftRowMap): Map[JUUID, String] = {
      rowMap.map {
        case (name, value) => {
          JUUID.fromString(name.asCharBuffer().toString) -> value.asCharBuffer().toString
        }
      }.toMap
    }

    def analyze = {

      val conf = new SparkConf()
        .setMaster("spark://10.53.2.21:7077")
        .setAppName("Crunchero.Analytics.analyze")
        .set("spark.executor.memory", "1g")

      val sc = new SparkContext(conf)

      val rdd = sc.thriftCassandra[JUUID, Map[JUUID, String]]("DefaultOpigramLibrary", "ValuesByMemberAndVariable")

      val result = rdd.filter({
        case (memberRef, values) => values.contains(JUUID.fromString("14f2eac0-f162-11e3-b1af-1ed043699d3c"))
      }).flatMap({
        case (memberRef, values) => values.keys.map(_.toString -> 1)
      }).reduceByKey(_ + _)
        .saveAsTextFile("/tmp/result.spark")
    }

  }
}
