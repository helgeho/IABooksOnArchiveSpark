## Internet Archive Books on [ArchiveSpark](https://github.com/helgeho/ArchiveSpark)

This example project enables you to analyze digitized books from the Internet Archive remotely with ArchiveSpark using local XML meta data.

It was created to demonstrate how easily [ArchiveSpark](https://github.com/helgeho/ArchiveSpark) can be extended.
The repo can be used as a template to add support for new data types to [ArchiveSpark](https://github.com/helgeho/ArchiveSpark) as well as to implement new enrich functions separated from the core project, such as [FirstLine](src/main/scala/de/l3s/archivespark/specific/books/enrichfuncs/FirstLine.scala).

The namespaces / package names in this code base are similar to the ones in the core [ArchiveSpark](https://github.com/helgeho/ArchiveSpark) code.
However, this is not a requirement, so please feel free to choose any package names, ideally under the namespace of your organization.

### License

The MIT License (MIT)

Copyright (c) 2015-2016 Helge Holzmann ([L3S](http://www.L3S.de)) and Vinay Goel ([Internet Archive](http://www.archive.org))

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
