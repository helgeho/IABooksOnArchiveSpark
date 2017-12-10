/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2017 Helge Holzmann (L3S) and Vinay Goel (Internet Archive)
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

package de.l3s.archivespark.specific.books.enrichfuncs

import de.l3s.archivespark.enrich.dataloads.TextLoad
import de.l3s.archivespark.enrich.functions.DataLoad
import de.l3s.archivespark.enrich.{EnrichFunc, _}

/**
  * Enrich function to get the first n line of a text.
  * This example is not only specific to books, it can be applied to any EnrichRoot with TextLoad or any text field.
  * To apply it to an RDD of EnrichRoots with TextLoads (e.g., TxtBookRecords), simply call `val enriched = rdd.enrich(FirstLines(50))`.
  * To apply it to a specific text field (e.g., generated by a enrichfunc called TextField), call `val enriched = rdd.enrich(FirstLines(50).of(TextField))`.
  */
class FirstLines private (n: Int) extends BasicEnrichFunc(TextLoad, s"first-$n-lines", (in: TypedEnrichable[String]) => Some {
  in.get.split("\\n").take(n).mkString("\n")
})

object FirstLines {
  def apply(n: Int) = new FirstLines(n)
}