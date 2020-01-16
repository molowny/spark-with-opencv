package io.olownia.sparkWithOpenCV

import java.nio.ByteBuffer

import org.apache.spark.sql.{DataFrame, Row}
import org.apache.spark.sql.functions.{col, udf}

import org.bytedeco.javacpp.opencv_core.{Mat, mean}
import org.bytedeco.javacpp.BytePointer

object Images {

  val brightness = udf((row: Row) => {
    val image = new Mat(
      row.getInt(2),
      row.getInt(1),
      row.getInt(4),
      new BytePointer(ByteBuffer.wrap(row.getAs[Array[Byte]](5)))
    )
    (1 - mean(image).get(2) / 255) * 100
  })

  def dark(boundary: Int)(df: DataFrame): DataFrame =
    df.withColumn("brightness", brightness(col("image")))
      .filter(col("brightness") > boundary)

}
