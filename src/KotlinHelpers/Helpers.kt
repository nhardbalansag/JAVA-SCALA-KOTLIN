package KotlinHelpers

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.lang.Double.parseDouble
import java.lang.Integer.parseInt


class Helpers {

    var dataReturnValue: MutableList<String> = ArrayList()

    fun generateDate(): String {
        val dateFormat = "yyyy/MM/dd HH:mm:ss"
        val dtf = DateTimeFormatter.ofPattern(dateFormat)
        val now = LocalDateTime.now()
        var date = dtf.format(now)
        date = "$date"

        return date
    }

    fun editline(filename: String, data: String, content: String): Boolean{

        var newString = ""
        var dataBool: Boolean = false
        val filecontent = File(filename)
        val br = BufferedReader(FileReader(filecontent))
        var line = br.readLine()
        var dataPositionsArr: MutableList<Int> = ArrayList()
        var dataCotentArr: MutableList<String> = ArrayList()
        var inc_new = 0
        var fileindex = 0

        File(filename).forEachLine {
            if(line.contains(data)){
                dataPositionsArr.add(fileindex)
                if(line.contains("none")){
                    for (item in line.split(',')){
                        if(inc_new != line.split(',').size - 1){
                            newString = newString + "$item,"
                        }
                        inc_new++
                    }
                    newString = it.replace("$line", "$newString$content")
                }else{
                    newString = it.replace("$line", "$line.$content")
                }
                dataCotentArr.add(newString)
            }else{
                dataPositionsArr.add(fileindex)
                dataCotentArr.add(line)
            }
            line = br.readLine()
            fileindex++
        }
        try {
            File(filename).writeText("")
            dataCotentArr.forEachIndexed{index, element ->
                filecontent.appendText(element + System.getProperty("line.separator"))
            }
            dataBool = true
        }catch (e:Exception){
            dataBool = false
        }
        return dataBool
    }

    fun checkFileExist(filename: String, fileData: String): Boolean{
        val myfile = File(filename)
        myfile.appendText(fileData + System.getProperty("line.separator"))
        return true
    }

    fun readFile(filename: String, param1: String): MutableList<String>{

        val filecontent = File(filename)
        val br = BufferedReader(FileReader(filecontent))
        var line = br.readLine()

        File(filename).forEachLine {
            if(line.contains(param1)){
                this.dataReturnValue.add(line)
            }
            line = br.readLine()
        }
        return this.dataReturnValue
    }

    fun readAllFile(filename: String): Array<String>{
        val filecontent = File(filename)
        val br = BufferedReader(FileReader(filecontent))
        var line = br.readLine()
        var inc = 0
        var emptyArray = Array(countRecord(filename)){""}

        File(filename).forEachLine {
            emptyArray[inc] = line
            inc++
            line = br.readLine()
        }

        return emptyArray
    }

    fun countRecord(filename: String): Int{

        var dataCount: Int = 0
        File(filename).forEachLine {
            dataCount++
        }

        return dataCount
    }

    fun checknullInput(inputdata: String): Boolean{
        var dataBool: Boolean = false
        if(inputdata.isEmpty() || inputdata === ""){
            dataBool = true
        }
        return dataBool
    }

    fun readAll(dataColumn: Int, filename: String): Array<String>{

        var emptyArray = Array(readAllFile(filename).size){""}
        var inc = 0
        for (arraydata in readAllFile(filename)){
            arraydata.split(",")
            emptyArray[inc] = arraydata.split(",")[dataColumn]
            inc++
        }
        return emptyArray
    }

    fun readAllInarray(dataColumn: Int, filename: Array<String>) : Array<String>{
        var emptyArray = Array(filename.size){""}
        filename.forEachIndexed{ index, line ->
            line.split(",")
            emptyArray[index] = line.split(",")[dataColumn]
        }
        return emptyArray
    }

    fun countRelatedFile(filename : String, compare : String, column : Int): Int{
        var dataCount: Int = 0
        readAll(column, filename).forEachIndexed{ i, data_i ->
            if (compare == data_i){
                dataCount++
            }
        }
        return dataCount
    }

    fun checkLessThanZeroandIfNumber(data : String): Boolean{
        var result = true
        try {
            val number = parseInt(data)
            if(number <= 0){
                result = false
            }
        } catch (e: NumberFormatException) {
            result = false
        }
        return result
    }


    fun editCol(filename: String, data: String, newData : String, columnNumber: Int): Boolean{
        var newString = ""
        var dataBool: Boolean = false
        val filecontent = File(filename)
        val br = BufferedReader(FileReader(filecontent))
        var line = br.readLine()
        var dataPositionsArr: MutableList<Int> = ArrayList()
        var dataCotentArr: MutableList<String> = ArrayList()
        var fileindex = 0

        File(filename).forEachLine {
            if(line.contains(data)){
                line.split(",").forEachIndexed{ index, data ->
                    if(index < columnNumber){
                        newString = newString + "$data,"
                    }else if(index == columnNumber){
                        newString = newString + "$newData,"
                    }else if(index == line.split(",").lastIndex){
                        newString = newString + "$data"
                    }else{
                        newString = newString + "$data,"
                    }
                }
                newString = it.replace(line, newString)
                dataCotentArr.add(newString)
            }else{
                dataPositionsArr.add(fileindex)
                dataCotentArr.add(line)
            }
            line = br.readLine()
            fileindex++
        }
        try {
            File(filename).writeText("")
            dataCotentArr.forEachIndexed{index, element ->
                filecontent.appendText(element + System.getProperty("line.separator"))
            }
            dataBool = true
        }catch (e:Exception){
            dataBool = false
        }
        return dataBool
    }

}