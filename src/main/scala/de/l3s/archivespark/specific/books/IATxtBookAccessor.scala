/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2016 Helge Holzmann (L3S) and Vinay Goel (Internet Archive)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package de.l3s.archivespark.specific.books

import java.net.URL

import de.l3s.archivespark.dataspecs.access.DataAccessor
import org.jsoup.Jsoup

import scala.io.Source
import scala.util.Try

import collection.JavaConverters._

class IATxtBookAccessor(detailsUrl: String) extends DataAccessor[String] {
  override def get: Option[String] = {
    val url = new URL(detailsUrl.replace("/details/", "/download/"))
    val txtFile = Jsoup.parse(url, 0).select("a").iterator.asScala.map(_.attr("href")).find(_.endsWith("txt"))
    if (txtFile.isEmpty) return null
    Try{Source.fromURL(new URL(url + "/" + txtFile.get)).mkString}.toOption
  }
}