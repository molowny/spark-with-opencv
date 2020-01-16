package io.olownia.sparkWithOpenCV

import org.scalatest.FunSpec

class ImagesTest extends FunSpec with SparkSessionTestWrapper {
  describe("images") {
    it("loads images and performs transform") {
      val images = spark.read
        .format("image")
        .load(getClass.getResource("/images").toString)
      assert(images.transform(Images.dark(83)).count() == 1)
    }
  }
}
