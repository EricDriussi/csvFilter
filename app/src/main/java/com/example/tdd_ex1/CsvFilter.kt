package com.example.tdd_ex1

class CsvFilter {

    fun filter(lines: List<String>): List<String> {

        val result = mutableListOf<String>()
        result.add(lines[0])
        val invoice = lines[1]
        val fields = invoice.split(',')
        val ivaIndex = 4
        val igicIndex = 5
        val ivaField = fields[ivaIndex]
        val igicField = fields[igicIndex]
        val decimalRegex = "\\d+(\\.\\d+)?".toRegex()

        val taxFieldsAreMutuallyExclusive =
                (ivaField.matches(decimalRegex) || igicField.matches(decimalRegex))
                        && (ivaField.isNullOrEmpty() || igicField.isNullOrEmpty())

        if (taxFieldsAreMutuallyExclusive)
            result.add(invoice)

        return result.toList()
    }

}