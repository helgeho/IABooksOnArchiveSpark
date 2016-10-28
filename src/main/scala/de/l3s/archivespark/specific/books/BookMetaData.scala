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

import java.time.LocalDateTime

import de.l3s.archivespark.utils.JsonConvertible

import scala.util.Try
import scala.xml._

object BookMetaData {
  val DateTimeFormatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

  def fromMap(meta: Map[String, Seq[String]]): Option[BookMetaData] = {
      val raw = meta.map{case (key, value) => (key, value.toList)}
      Some(BookMetaData(
        raw.getOrElse("title", Seq("")).head,
        raw.getOrElse("creator", Seq("")).head,
        raw.getOrElse("publisher", Seq("")).head,
        raw.getOrElse("date", Seq("")).head,
        raw.getOrElse("language", Seq("")).head,
        raw.getOrElse("publicdate", Seq("")).head,
        raw.getOrElse("subject", Seq()),
        raw.getOrElse("collection", Seq()),
        raw
      ))
  }

  def fromXml(xml: String): Option[BookMetaData] = fromXml(XML.loadString(xml))

  def fromXml(xml: Elem): Option[BookMetaData] = Try {
    val map = xml.child.flatMap { child =>
      if (child.isAtom || child.child.size != 1) Iterator.empty
      else Iterator(child.label -> child.child.head.mkString)
    }.groupBy{case (key, value) => key}.mapValues(keyValues => keyValues.map{case (key, value) => value})
    fromMap(map)
  }.getOrElse(None)
}

case class BookMetaData(title: String,
                        creator: String,
                        publisher: String,
                        date: String,
                        language: String,
                        publicdateStr: String,
                        subjects: Seq[String],
                        collections: Seq[String],
                        raw: Map[String, Seq[String]]) extends JsonConvertible {
  def publicdate = Try(LocalDateTime.parse(publicdateStr, BookMetaData.DateTimeFormatter)).getOrElse(null)

  def toJson: Map[String, Any] = Map[String, Any](
    "title" -> title,
    "creator" -> creator,
    "publisher" -> publisher,
    "date" -> date,
    "language" -> language,
    "publicdate" -> publicdateStr,
    "subjects" -> subjects.mkString(", "),
    "collections" -> collections.mkString(", ")
  )
}
