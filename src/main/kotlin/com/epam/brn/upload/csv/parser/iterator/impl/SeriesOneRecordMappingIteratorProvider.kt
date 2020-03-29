package com.epam.brn.upload.csv.parser.iterator.impl

import com.epam.brn.upload.csv.parser.iterator.MappingIteratorProvider
import com.epam.brn.upload.csv.record.SeriesOneRecord
import com.fasterxml.jackson.databind.MappingIterator
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import java.io.InputStream
import org.apache.commons.lang3.StringUtils

class SeriesOneRecordMappingIteratorProvider :
    MappingIteratorProvider<SeriesOneRecord> {

    override fun iterator(file: InputStream): MappingIterator<SeriesOneRecord> {
        val csvMapper = CsvMapper()

        val csvSchema = csvMapper
            .schemaFor(SeriesOneRecord::class.java)
            .withColumnSeparator(' ')
            .withLineSeparator(StringUtils.SPACE)
            .withColumnReordering(true)
            .withArrayElementSeparator(",")
            .withHeader()

        return csvMapper
            .readerWithTypedSchemaFor(SeriesOneRecord::class.java)
            .with(csvSchema)
            .readValues(file)
    }
}