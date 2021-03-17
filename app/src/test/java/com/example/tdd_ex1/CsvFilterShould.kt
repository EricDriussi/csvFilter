package com.example.tdd_ex1

import org.junit.Test
import org.assertj.core.api.Assertions.assertThat

class CsvFilterShould {


    val headerLine = "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, CIF_cliente, NIF_cliente"

    @Test
    fun allow_for_correct_lines_only() {
        val invoiceLine = "1,02/05/2019,1000,810,19,,ACER Laptop,B76430134,"
        val result = CsvFilter().filter(listOf(headerLine, invoiceLine))
        assertThat(result).isEqualTo(listOf(headerLine, invoiceLine))

    }

    @Test
    fun exclude_lines_with_both_tax_fields_populated() {
        val invoiceLine = "1,02/05/2019,1000,810,19,8,ACER Laptop,B76430134,"
        val result = CsvFilter().filter(listOf(headerLine, invoiceLine))
        assertThat(result).isEqualTo(listOf(headerLine))

    }

    @Test
    fun exclude_lines_with_both_tax_fields_empty() {
        val invoiceLine = "1,02/05/2019,1000,810,,,ACER Laptop,B76430134,"
        val result = CsvFilter().filter(listOf(headerLine, invoiceLine))
        assertThat(result).isEqualTo(listOf(headerLine))

    }

    @Test
    fun exclude_lines_with_non_decimal_tax_fields() {
        val invoiceLine = "1,02/05/2019,1000,810,XYZ,,ACER Laptop,B76430134,"
        val result = CsvFilter().filter(listOf(headerLine, invoiceLine))
        assertThat(result).isEqualTo(listOf(headerLine))

    }

    @Test
    fun exclude_lines_with_both_tax_fields_populated_even_if_non_decimal() {
        val invoiceLine = "1,02/05/2019,1000,810,XYZ,12,ACER Laptop,B76430134,"
        val result = CsvFilter().filter(listOf(headerLine, invoiceLine))
        assertThat(result).isEqualTo(listOf(headerLine))

    }

}